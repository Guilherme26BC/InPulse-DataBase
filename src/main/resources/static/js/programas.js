document.addEventListener('DOMContentLoaded', function () {
    const elements = {
        tableBody: document.getElementById('programas-table-body'),
        detailsModal: document.getElementById('detailsModal'),
        detailsModalBody: document.getElementById('details-modal-body')
    };

    async function fetchAllProgramas() {
        try {
            const response = await fetch('/api/programas');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const allProgramas = await response.json();
            renderTable(allProgramas);
        } catch (error) {
            console.error("Erro ao buscar programas:", error);
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-danger">Não foi possível carregar os programas.</td></tr>`;
        }
    }

    function renderTable(programas) {
        elements.tableBody.innerHTML = '';
        if (!programas || programas.length === 0) {
            elements.tableBody.innerHTML = `<tr><td colspan="6" class="text-center text-muted">Nenhum programa encontrado.</td></tr>`;
            return;
        }

        programas.forEach(p => {
            const dataInicio = p.dataInicio ? p.dataInicio : 'N/A';
            const dataFim = p.dataFim ? p.dataFim : 'N/A';
            const participantes = p.funcionarios_nome ? p.funcionarios_nome.length : 0;
            const ideias = p.ideias_nome ? p.ideias_nome.length : 0;

            elements.tableBody.innerHTML += `
                <tr>
                    <td class="fw-bold">${p.programa_id}</td>
                    <td class="fw-bold">${p.nome_programa}</td>
                    <td>${dataInicio} até ${dataFim}</td>
                    <td>${participantes}</td>
                    <td>${ideias}</td>
                    <td class="text-center">
                        <button class="btn btn-sm btn-outline-dark" data-bs-toggle="modal"
                                data-bs-target="#detailsModal" data-programa-id="${p.programa_id}">
                            <i class="fas fa-search me-1"></i> Detalhes
                        </button>
                    </td>
                </tr>`;
        });
    }
    
    elements.detailsModal.addEventListener('show.bs.modal', async function (event) {
        const programaId = event.relatedTarget.getAttribute('data-programa-id');
        elements.detailsModalBody.innerHTML = '<div class="text-center p-5"><div class="spinner-border text-primary" role="status"></div></div>';

        try {
            const response = await fetch(`/api/programas/${programaId}`);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const p = await response.json();

            const dataInicio = p.dataInicio ? p.dataInicio : 'N/A';
            const dataFim = p.dataFim ? p.dataFim : 'N/A';

            const participantesHtml = p.funcionarios_nome && p.funcionarios_nome.length > 0
                ? `<ul class="list-unstyled">` + p.funcionarios_nome.map(nome => `<li><i class="fas fa-user-check me-2 text-success"></i>${nome}</li>`).join('') + `</ul>`
                : `<p class="text-muted">Nenhum funcionário inscrito.</p>`;
            
            const ideiasHtml = p.ideias_nome && p.ideias_nome.length > 0
                ? `<ul class="list-unstyled">` + p.ideias_nome.map(nome => `<li><i class="fas fa-lightbulb me-2 text-warning"></i>${nome}</li>`).join('') + `</ul>`
                : `<p class="text-muted">Nenhuma ideia associada.</p>`;

            elements.detailsModalBody.innerHTML = `
                <div class="mb-4">
                    <h6><i class="fas fa-bullseye me-2 text-primary"></i>Nome do Programa</h6>
                    <p class="fw-bold">${p.nome_programa} (ID: ${p.programa_id})</p>
                    <h6><i class="fas fa-file-alt me-2 text-secondary"></i>Descrição</h6>
                    <p>${p.descricao_programa}</p>
                    <h6><i class="fas fa-calendar-alt me-2 text-info"></i>Período</h6>
                    <p>${dataInicio} até ${dataFim}</p>
                </div>
                <hr>
                <h6><i class="fas fa-users me-2 text-info"></i>Participantes</h6>
                ${participantesHtml}
                <hr>
                <h6><i class="fas fa-lightbulb me-2 text-warning"></i>Ideias Associadas</h6>
                ${ideiasHtml}
            `;

        } catch (error) {
            console.error("Erro ao carregar detalhes do programa:", error);
            elements.detailsModalBody.innerHTML = `<div class="alert alert-danger">Não foi possível carregar os detalhes do programa.</div>`;
        }
    });

    fetchAllProgramas();
});