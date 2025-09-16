document.addEventListener('DOMContentLoaded', function () {
    const elements = {
        tableBody: document.getElementById('funcionarios-table-body'),
        detailsModal: document.getElementById('detailsModal'),
        detailsModalBody: document.getElementById('details-modal-body'),
        searchId: document.getElementById('searchId'),
        searchButton: document.getElementById('searchButton'),
        filterTier: document.getElementById('filterTier'),
        sortOrder: document.getElementById('sortOrder'),
        clearFiltersButton: document.getElementById('clearFiltersButton')
    };

    let allFuncionarios = [];

    async function fetchAllFuncionarios() {
        try {
            const response = await fetch('/api/funcionarios');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            allFuncionarios = await response.json();
            applyFiltersAndSort();
        } catch (error) {
            console.error("Erro ao buscar funcionários:", error);
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Não foi possível carregar os funcionários.</td></tr>`;
        }
    }

    function renderTable(funcionarios) {
        elements.tableBody.innerHTML = '';
        if (!funcionarios || funcionarios.length === 0) {
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">Nenhum funcionário encontrado.</td></tr>`;
            return;
        }

        funcionarios.forEach(f => {
            const nomeCompleto = `${f.primeiro_nome} ${f.ultimo_sobrenome}`;
            elements.tableBody.innerHTML += `
                <tr>
                    <td class="fw-bold">${f.funcionario_id}</td>
                    <td class="fw-bold">${nomeCompleto}</td>
                    <td>${f.email}</td>
                    <td>${f.pontos}</td>
                    <td>${f.tier}</td>
                    <td class="text-center">
                        <button class="btn btn-sm btn-outline-dark" data-bs-toggle="modal"
                                data-bs-target="#detailsModal" data-funcionario-id="${f.funcionario_id}">
                            <i class="fas fa-search me-1"></i> Detalhes
                        </button>
                    </td>
                </tr>`;
        });
    }

    function applyFiltersAndSort() {
        let filteredFuncionarios = [...allFuncionarios];
        const filterTierValue = elements.filterTier.value;
        const sortOrderValue = elements.sortOrder.value;

        // Filtra por nível
        if (filterTierValue !== 'todos') {
            filteredFuncionarios = filteredFuncionarios.filter(f => f.tier === filterTierValue);
        }

        // Ordena
        filteredFuncionarios.sort((a, b) => {
            if (sortOrderValue === 'id_asc') { // Ordena por ID do menor para o maior
                return a.funcionario_id - b.funcionario_id;
            }
            if (sortOrderValue === 'id_desc') { // Ordena por ID do maior para o menor
                return b.funcionario_id - a.funcionario_id;
            }
            if (sortOrderValue === 'nome_asc') {
                return `${a.primeiro_nome} ${a.ultimo_sobrenome}`.localeCompare(`${b.primeiro_nome} ${b.ultimo_sobrenome}`);
            }
            if (sortOrderValue === 'nome_desc') {
                return `${b.primeiro_nome} ${b.ultimo_sobrenome}`.localeCompare(`${a.primeiro_nome} ${a.ultimo_sobrenome}`);
            }
            if (sortOrderValue === 'pontos_desc') {
                return b.pontos - a.pontos;
            }
            if (sortOrderValue === 'pontos_asc') {
                return a.pontos - b.pontos;
            }
            return 0;
        });

        renderTable(filteredFuncionarios);
    }

    elements.searchButton.addEventListener('click', async function() {
        const id = elements.searchId.value.trim();
        if (id) {
            try {
                const response = await fetch(`/api/funcionarios/${id}`);
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const funcionario = await response.json();
                renderTable([funcionario]);
            } catch (error) {
                console.error("Erro ao buscar funcionário por ID:", error);
                elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">Nenhum funcionário encontrado com o ID ${id}.</td></tr>`;
            }
        } else {
            fetchAllFuncionarios();
        }
    });

    elements.clearFiltersButton.addEventListener('click', function() {
        elements.searchId.value = '';
        elements.filterTier.value = 'todos';
        elements.sortOrder.value = 'id_asc'; // Valor padrão agora é por ID
        fetchAllFuncionarios();
    });
    
    [elements.filterTier, elements.sortOrder].forEach(el => {
        el.addEventListener('change', applyFiltersAndSort);
    });

    elements.detailsModal.addEventListener('show.bs.modal', async function (event) {
        const funcionarioId = event.relatedTarget.getAttribute('data-funcionario-id');
        elements.detailsModalBody.innerHTML = '<div class="text-center p-5"><div class="spinner-border text-primary" role="status"></div></div>';

        try {
            const response = await fetch(`/api/funcionarios/${funcionarioId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const f = await response.json();

            let ideiasHtml = '';
            if (f.ideias && f.ideias.length > 0) {
                f.ideias.forEach(idea => {
                    const status = idea.status || 'nova';
                    ideiasHtml += `
                        <div class="card card-body mb-2">
                            <h6><i class="fas fa-lightbulb me-2 text-warning"></i>${idea.nome}</h6>
                            <p class="small text-muted mb-1">${idea.descricao}</p>
                            <span class="badge bg-primary me-1">${status}</span>
                            <span class="badge bg-secondary me-1"><i class="fas fa-thumbs-up me-1"></i>${idea.curtidas}</span>
                        </div>
                    `;
                });
            } else {
                ideiasHtml = `<p class="text-muted">Nenhuma ideia submetida por este funcionário.</p>`;
            }

            elements.detailsModalBody.innerHTML = `
                <div class="mb-4">
                    <h6><i class="fas fa-user me-2 text-primary"></i>Nome</h6>
                    <p class="fw-bold">${f.primeiro_nome} ${f.ultimo_sobrenome} (ID: ${f.funcionario_id})</p>
                    <h6><i class="fas fa-envelope me-2 text-secondary"></i>E-mail</h6>
                    <p>${f.email}</p>
                    <h6><i class="fas fa-star me-2 text-warning"></i>Pontuação</h6>
                    <p>${f.pontos} pontos</p>
                </div>
                <hr>
                <h6><i class="fas fa-lightbulb me-2 text-info"></i>Ideias Submetidas</h6>
                ${ideiasHtml}
            `;
        } catch (error) {
            console.error("Erro ao carregar detalhes do funcionário:", error);
            elements.detailsModalBody.innerHTML = `<div class="alert alert-danger">Não foi possível carregar os detalhes do funcionário.</div>`;
        }
    });

    fetchAllFuncionarios();
});