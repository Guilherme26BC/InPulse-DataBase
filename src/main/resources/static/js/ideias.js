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
            
            // Adicione um status padrão 'nova' pois sua API ainda não o retorna.
            allIdeas = allIdeas.map(idea => ({ ...idea, status: 'nova' }));
            
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

            document.getElementById('modal-title').textContent = idea.nome;
            const autor = idea.funcionario_nome ? `${idea.funcionario_nome.primeiro_nome} ${idea.funcionario_nome.ultimo_sobrenome} (ID: ${idea.ideia_id})` : 'Anônimo';
            document.getElementById('modal-author').textContent = autor;
            document.getElementById('modal-description').textContent = idea.descricao;
            document.getElementById('modal-pain').textContent = idea.problema;
            document.getElementById('modal-goal').textContent = 'O objetivo não está disponível na API.'; 

            const statusBadge = document.getElementById('modal-status-badge');
            const status = statusConfig[idea.status] || statusConfig['nova'];
            statusBadge.className = `badge ${status.bg}`;
            statusBadge.textContent = status.text;

            const attachmentsContainer = document.getElementById('modal-attachments');
            attachmentsContainer.innerHTML = '<p class="text-muted">Nenhum anexo disponível.</p>';
            
            updateStatusOptions(idea);

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

    elements.saveChangesBtn.addEventListener('click', async function() {
        const ideaId = parseInt(this.dataset.ideaId);
        const newStatus = document.getElementById('changeStatus').value;
        const feedback = document.getElementById('feedbackComment').value;
        
        try {
            const response = await fetch(`/api/ideias/${ideaId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ status: newStatus })
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
        if (startDateValue) filteredIdeas = filteredIdeas.filter(i => i.submissionDate >= startDateValue);
        if (endDateValue) filteredIdeas = filteredIdeas.filter(i => i.submissionDate <= endDateValue);

        filteredIdeas.sort((a, b) => {
            if (sortValue === 'recentes') return new Date(b.submissionDate) - new Date(a.submissionDate);
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

            document.getElementById('modal-title').textContent = idea.nome;
            document.getElementById('modal-author').textContent = `${idea.funcionario_nome.primeiro_nome} ${idea.funcionario_nome.ultimo_sobrenome} (ID: ${idea.ideia_id})`;
            document.getElementById('modal-description').textContent = idea.descricao;
            document.getElementById('modal-pain').textContent = idea.problema;
            document.getElementById('modal-goal').textContent = idea.goal; // Este campo não existe no seu DTO. Ajuste seu DTO ou o HTML.

            const statusBadge = document.getElementById('modal-status-badge');
            const status = statusConfig[idea.status]; // A sua API não tem o campo 'status'.
            if (status) {
                statusBadge.className = `badge ${status.bg}`;
                statusBadge.textContent = status.text;
            } else {
                statusBadge.textContent = 'Status Indefinido';
            }

            const attachmentsContainer = document.getElementById('modal-attachments');
            // A sua API não tem o campo 'attachments'. Adicione um valor padrão ou ajuste seu DTO.
            attachmentsContainer.innerHTML = '<p class="text-muted">Nenhum anexo.</p>';
            
            updateStatusOptions(idea);

        } catch (error) {
            console.error("Erro ao carregar detalhes da ideia:", error);
            // Mensagem de erro para o usuário
        }
    }

    function updateStatusOptions(idea) {
        // ... (manter esta função inalterada, ela depende do campo 'status' da ideia)
    }
    
    elements.detailModal.addEventListener('show.bs.modal', function (event) {
        const ideaId = parseInt(event.relatedTarget.getAttribute('data-idea-id'));
        if (ideaId) {
            populateModal(ideaId);
            elements.saveChangesBtn.dataset.ideaId = ideaId;
        }
    });

    elements.saveChangesBtn.addEventListener('click', async function() {
        const ideaId = parseInt(this.dataset.ideaId);
        const newStatus = document.getElementById('changeStatus').value;
        const feedback = document.getElementById('feedbackComment').value;
        
        try {
            // A sua API não tem o endpoint de aprovação. O endpoint que você criou foi para atualizar curtidas.
            // Para aprovar a ideia, você precisaria de um endpoint na API que recebesse o novo status.
            // Por exemplo: PUT /api/ideias/aprovar/{id} com o novo status no corpo.
            // Aqui, vamos simular a chamada PUT para o endpoint de curtidas, já que é o único PUT que você tem.
            // No entanto, isso não aprovará a ideia.
            const response = await fetch(`/api/ideias/${ideaId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ curtidas: 1 }) // Exemplo, já que seu PUT é para curtidas
            });

            if (!response.ok) {
                throw new Error(`Erro ao atualizar ideia: ${response.status}`);
            }

            // Recarrega a tabela após a atualização
            await fetchAllIdeas();

            // Fecha o modal
            const modalInstance = bootstrap.Modal.getInstance(elements.detailModal);
            modalInstance.hide();
        } catch (error) {
            console.error("Erro ao salvar alterações:", error);
            alert("Não foi possível salvar as alterações.");
        }
    });

    // Adiciona os listeners para os filtros
    [elements.filterStatus, elements.filterPrograma, elements.startDate, elements.endDate, elements.sortOrder].forEach(el => {
        el.addEventListener('change', applyFiltersAndSort);
    });

    // Inicia o carregamento dos dados da API
    fetchAllIdeas();

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

    function renderTable(ideas) {
        elements.tableBody.innerHTML = '';
        if (ideas.length === 0) {
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">Nenhuma ideia encontrada.</td></tr>`;
            return;
        }
        ideas.forEach(idea => {
            const status = statusConfig[idea.status];
            const formattedDate = new Date(idea.submissionDate + 'T00:00:00').toLocaleDateString('pt-BR');
            elements.tableBody.innerHTML += `
                <tr>
                    <td class="fw-bold">${idea.title}</td>
                    <td>Anônimo</td>
                    <td>${formattedDate}</td>
                    <td class="text-center"><span class="badge ${status.bg}">${status.text}</span></td>
                    <td class="text-center"><i class="fas fa-thumbs-up me-1"></i> ${idea.engagement}</td>
                    <td class="text-center">
                        <button class="btn btn-sm btn-outline-dark" data-bs-toggle="modal"
                                data-bs-target="#detailModal" data-idea-id="${idea.id}">
                            <i class="fas fa-search me-1"></i> Detalhes
                        </button>
                    </td>
                </tr>`;
        });
    }

    function applyFiltersAndSort() {
        let filteredIdeas = [...mockIdeas];
        const statusValue = elements.filterStatus.value;
        const programaValue = elements.filterPrograma.value;
        const startDateValue = elements.startDate.value;
        const endDateValue = elements.endDate.value;
        const sortValue = elements.sortOrder.value;

        if (statusValue !== 'todos') filteredIdeas = filteredIdeas.filter(i => i.status === statusValue);
        if (programaValue !== 'todos') filteredIdeas = filteredIdeas.filter(i => i.program === programaValue);
        if (startDateValue) filteredIdeas = filteredIdeas.filter(i => i.submissionDate >= startDateValue);
        if (endDateValue) filteredIdeas = filteredIdeas.filter(i => i.submissionDate <= endDateValue);

        filteredIdeas.sort((a, b) => {
            if (sortValue === 'recentes') return new Date(b.submissionDate) - new Date(a.submissionDate);
            if (sortValue === 'engajamento') return b.engagement - a.engagement;
        });

        renderTable(filteredIdeas);
    }

    function populateModal(idea) {
        document.getElementById('modal-title').textContent = idea.title;
        document.getElementById('modal-author').textContent = `${idea.author} (ID: ${idea.id})`;
        document.getElementById('modal-description').textContent = idea.description;
        document.getElementById('modal-pain').textContent = idea.pain;
        document.getElementById('modal-goal').textContent = idea.goal;

        const statusBadge = document.getElementById('modal-status-badge');
        const status = statusConfig[idea.status];
        statusBadge.className = `badge ${status.bg}`;
        statusBadge.textContent = status.text;

        const attachmentsContainer = document.getElementById('modal-attachments');
        attachmentsContainer.innerHTML = idea.attachments.length > 0
            ? idea.attachments.map(file => `<a href="#" class="d-block mb-1"><i class="fas fa-file-${file.type} me-1"></i> ${file.name}</a>`).join('')
            : '<p class="text-muted">Nenhum anexo.</p>';

        updateStatusOptions(idea);
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
            // Esconde as ações para ideias Aprovadas ou Rejeitadas
            managerActions.style.display = 'none';
        }
    }
    
    elements.detailModal.addEventListener('show.bs.modal', function (event) {
        const ideaId = parseInt(event.relatedTarget.getAttribute('data-idea-id'));
        const idea = mockIdeas.find(i => i.id === ideaId);
        if (idea) {
            populateModal(idea);
            // Guarda o ID da ideia no botão Salvar para uso posterior
            elements.saveChangesBtn.dataset.ideaId = ideaId;
        }
    });

    elements.saveChangesBtn.addEventListener('click', function() {
        const ideaId = parseInt(this.dataset.ideaId);
        const newStatus = document.getElementById('changeStatus').value;
        const feedback = document.getElementById('feedbackComment').value;

        // Lógica para salvar os dados (aqui você faria uma chamada `fetch` para sua API)
        console.log(`Salvando ideia ID: ${ideaId}`);
        console.log(`Novo Status: ${newStatus}`);
        console.log(`Feedback: ${feedback}`);

        // Atualiza o dado no array de mock (simulação)
        const ideaIndex = mockIdeas.findIndex(i => i.id === ideaId);
        if(ideaIndex !== -1) {
            mockIdeas[ideaIndex].status = newStatus;
        }

        // Fecha o modal e atualiza a tabela
        const modalInstance = bootstrap.Modal.getInstance(elements.detailModal);
        modalInstance.hide();
        applyFiltersAndSort();
    });

    // Adiciona os listeners para os filtros
    [elements.filterStatus, elements.filterPrograma, elements.startDate, elements.endDate, elements.sortOrder].forEach(el => {
        el.addEventListener('change', applyFiltersAndSort);
    });

    // Renderização inicial
    applyFiltersAndSort();
