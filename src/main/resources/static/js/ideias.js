document.addEventListener('DOMContentLoaded', function () {
    const elements = {
        tableBody: document.getElementById('ideas-table-body'),
        filterStatus: document.getElementById('filterStatus'),
        filterPrograma: document.getElementById('filterPrograma'),
        startDate: document.getElementById('startDate'),
        endDate: document.getElementById('endDate'),
        sortOrder: document.getElementById('sortOrder'),
        detailModal: document.getElementById('detailModal'),
        saveChangesBtn: document.getElementById('save-changes-btn')
    };

    const statusConfig = {
        nova: { bg: 'text-bg-primary', text: 'Nova' },
        em_analise: { bg: 'text-bg-warning', text: 'Em Análise' },
        aprovada: { bg: 'text-bg-success', text: 'Aprovada' },
        rejeitada: { bg: 'text-bg-danger', text: 'Rejeitada' }
    };

    let allIdeas = [];


    async function fetchAllIdeas() {
        try {
            const response = await fetch('/api/ideias');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            allIdeas = await response.json();

            // Adicione o status 'nova' como padrão para todas as ideias, se a API não o retornar
            allIdeas = allIdeas.map(idea => ({ ...idea, status: idea.status || 'nova' }));

            applyFiltersAndSort();
        } catch (error) {
            console.error("Erro ao buscar ideias:", error);
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Não foi possível carregar as ideias.</td></tr>`;
        }
    }



    function renderTable(ideas) {
        elements.tableBody.innerHTML = '';
        if (ideas.length === 0) {
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">Nenhuma ideia encontrada.</td></tr>`;
            return;
        }
        ideas.forEach(idea => {
            const status = statusConfig[idea.status];
            const formattedDate = new Date(idea.data).toLocaleDateString('pt-BR');
            // A linha abaixo faz a verificação para evitar 'undefined undefined'
            const autor = idea.funcionario_nome ? `${idea.funcionario_nome.primeiro_nome} ${idea.funcionario_nome.ultimo_sobrenome}` : 'Anônimo';

            elements.tableBody.innerHTML += `
            <tr>
                <td class="fw-bold">${idea.nome}</td>
                <td>${autor}</td>
                <td>${formattedDate}</td>
                <td class="text-center"><span class="badge ${status.bg}">${status.text}</span></td>
                <td class="text-center"><i class="fas fa-thumbs-up me-1"></i> ${idea.curtidas}</td>
                <td class="text-center">
                    <button class="btn btn-sm btn-outline-dark" data-bs-toggle="modal"
                            data-bs-target="#detailModal" data-idea-id="${idea.ideia_id}">
                        <i class="fas fa-search me-1"></i> Detalhes
                    </button>
                </td>
            </tr>`;
        });
    }


    function applyFiltersAndSort() {
        let filteredIdeas = [...allIdeas];
        const statusValue = elements.filterStatus.value;
        const programaValue = elements.filterPrograma.value;
        const startDateValue = elements.startDate.value;
        const endDateValue = elements.endDate.value;
        const sortValue = elements.sortOrder.value;

        if (statusValue !== 'todos') filteredIdeas = filteredIdeas.filter(i => i.status === statusValue);
        // O seu DTO tem categorias, não programas.
        if (programaValue !== 'todos') filteredIdeas = filteredIdeas.filter(i => i.categoriasIcone.includes(programaValue));
        if (startDateValue) filteredIdeas = filteredIdeas.filter(i => i.data >= startDateValue);
        if (endDateValue) filteredIdeas = filteredIdeas.filter(i => i.data <= endDateValue);

        filteredIdeas.sort((a, b) => {
            if (sortValue === 'recentes') return new Date(b.data) - new Date(a.data);
            if (sortValue === 'engajamento') return b.curtidas - a.curtidas;
        });

        renderTable(filteredIdeas);
    }

    async function populateModal(ideaId) {
        try {
            const response = await fetch(`/api/ideias/${ideaId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const idea = await response.json();

            // Garante que o status tenha um valor padrão se for nulo
            const ideaStatus = idea.status || 'nova';

            // Atualiza os detalhes da ideia no modal
            document.getElementById('modal-title').textContent = idea.nome;

            // Corrige a exibição do nome do autor
            const autor = idea.funcionario_nome
                ? `${idea.funcionario_nome.primeiro_nome} ${idea.funcionario_nome.ultimo_sobrenome}`
                : 'Anônimo';
            document.getElementById('modal-author').textContent = autor;

            document.getElementById('modal-description').textContent = idea.descricao;
            document.getElementById('modal-pain').textContent = idea.problema;

            // Seção de Categorias
            const categoriasContainer = document.getElementById('modal-categorias');
            categoriasContainer.innerHTML = ''; // Limpa a seção
            if (idea.categoriasIcone && idea.categoriasIcone.length > 0) {
                idea.categoriasIcone.forEach(categoria => {
                    categoriasContainer.innerHTML += `<span class="badge bg-secondary me-1">${categoria}</span>`;
                });
            } else {
                categoriasContainer.innerHTML = '<p class="text-muted">Nenhuma categoria.</p>';
            }

            // Atualiza o status
            const statusBadge = document.getElementById('modal-status-badge');
            const status = statusConfig[ideaStatus];
            statusBadge.className = `badge ${status.bg}`;
            statusBadge.textContent = status.text;

            // Seção de anexos
            const attachmentsContainer = document.getElementById('modal-attachments');
            attachmentsContainer.innerHTML = '<p class="text-muted">Nenhum anexo disponível.</p>';

            // Adiciona a lógica da seção de comentários
            const commentsList = document.getElementById('modal-comments-list');
            const commentCountElement = document.getElementById('comment-count');

            commentsList.innerHTML = '';
            if (idea.contribuicoes && idea.contribuicoes.length > 0) {
                idea.contribuicoes.forEach(comment => {
                    const commentHtml = `
                    <div class="list-group-item">
                        <div class="d-flex w-100 justify-content-between">
                            <h6 class="mb-1 fw-bold">${comment.nomeAutor || 'Anônimo'}</h6>
                        </div>
                        <p class="mb-1">${comment.coment}</p>
                    </div>
                `;
                    commentsList.innerHTML += commentHtml;
                });
                commentCountElement.textContent = idea.contribuicoes.length;
            } else {
                commentsList.innerHTML = `<div class="list-group-item text-muted text-center">Nenhum comentário.</div>`;
                commentCountElement.textContent = 0;
            }

            // Atualiza as opções de status e feedback
            updateStatusOptions({ status: ideaStatus });

        } catch (error) {
            console.error("Erro ao carregar detalhes da ideia:", error);
            alert("Não foi possível carregar os detalhes da ideia.");
        }
    }

    function updateStatusOptions(idea) {
        const changeStatusSelect = document.getElementById('changeStatus');
        const managerActions = document.getElementById('manager-actions');
        changeStatusSelect.innerHTML = '';

        const statusFlow = {
            nova: [
                { value: 'em_analise', text: 'Mover para "Em Análise"' },
                { value: 'rejeitada', text: 'Rejeitar Ideia' }
            ],
            em_analise: [
                { value: 'aprovada', text: 'Aprovar Ideia' },
                { value: 'rejeitada', text: 'Rejeitar Ideia' }
            ]
        };

        const availableActions = statusFlow[idea.status];
        if (availableActions) {
            managerActions.style.display = 'block';
            availableActions.forEach(action => {
                changeStatusSelect.innerHTML += `<option value="${action.value}">${action.text}</option>`;
            });
        } else {
            managerActions.style.display = 'none';
        }
    }

    elements.detailModal.addEventListener('show.bs.modal', function (event) {
        const ideaId = parseInt(event.relatedTarget.getAttribute('data-idea-id'));
        if (ideaId) {
            populateModal(ideaId);
            elements.saveChangesBtn.dataset.ideaId = ideaId;
        }
    });


        elements.saveChangesBtn.addEventListener('click', async function () {
            const ideaId = parseInt(this.dataset.ideaId);
            const newStatus = document.getElementById('changeStatus').value;

            try {
                const response = await fetch(`/api/ideias/${ideaId}/status`, { // Endpoint corrigido
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ status: newStatus }) // DTO corrigido
                });

                if (!response.ok) {
                    throw new Error(`Erro ao atualizar ideia: ${response.status}`);
                }

                await fetchAllIdeas();

                const modalInstance = bootstrap.Modal.getInstance(elements.detailModal);
                modalInstance.hide();
            } catch (error) {
                console.error("Erro ao salvar alterações:", error);
                alert("Não foi possível salvar as alterações.");
            }
        });

        [elements.filterStatus, elements.filterPrograma, elements.startDate, elements.endDate, elements.sortOrder].forEach(el => {
            el.addEventListener('change', applyFiltersAndSort);
        });

        fetchAllIdeas();
    });
