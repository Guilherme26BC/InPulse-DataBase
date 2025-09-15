document.addEventListener('DOMContentLoaded', function () {

    // =================================================================================
    // PREPARAÇÃO PARA API REST (JAVA)
    // =================================================================================
    // Substitua o array 'mockAuditLogs' por uma chamada fetch para sua API de logs.
    // Ex: const allLogs = await fetch('/api/v1/audit-logs').then(res => res.json());
    // =================================================================================

    // <!-- INICIO LOGS DE EXEMPLO -->
    const mockAuditLogs = [
        {
            timestamp: "2025-08-28T14:30:00.000Z",
            user: { name: "Lucas Bezerra", email: "lucas.bezerra@eurofarma.com.br" },
            logType: "aprovado",
            details: "Aprovou a ideia 'Otimização do processo de logística' (ID: 1)."
        },
        {
            timestamp: "2025-08-28T11:05:10.000Z",
            user: { name: "Mariana Costa", email: "mariana.costa@eurofarma.com.br" },
            logType: "aguardando_aprovacao",
            details: "Editou a ideia 'Plataforma de mentoria interna' (ID: 2), aguardando aprovação."
        },
        {
            timestamp: "2025-08-27T18:00:00.000Z",
            user: { name: "Sistema", email: "system@internal" },
            logType: "erro",
            details: "Falha ao processar anexo para a ideia 'Novo layout para o refeitório' (ID: 4)."
        },
        {
            timestamp: "2025-08-27T15:22:10.000Z",
            user: { name: "Carlos Andrade", email: "carlos.andrade@eurofarma.com.br" },
            logType: "enviada_com_sucesso",
            details: "Submeteu a nova ideia 'Implementação de painéis solares na matriz' (ID: 3)."
        },
        {
            timestamp: "2025-08-26T09:00:00.000Z",
            user: { name: "João Pereira", email: "joao.pereira@eurofarma.com.br" },
            logType: "cancelado",
            details: "Cancelou a submissão da ideia 'Gamificação no treinamento de segurança'."
        }
    ];
    // <!-- FIM LOGS DE EXEMPLO -->

    const elements = {
        tableBody: document.getElementById('logs-table-body'),
        filterLogType: document.getElementById('filterLogType'),
        startDate: document.getElementById('startDate'),
        endDate: document.getElementById('endDate'),
    };

    const logTypeConfig = {
        aprovado: { bg: 'bg-success', text: 'Aprovado' },
        aguardando_aprovacao: { bg: 'bg-warning text-dark', text: 'Aguardando Aprovação' },
        enviada_com_sucesso: { bg: 'bg-primary', text: 'Enviada com Sucesso' },
        cancelado: { bg: 'bg-secondary', text: 'Cancelado' },
        erro: { bg: 'bg-danger', text: 'Erro' },
        enviada: { bg: 'bg-info text-dark', text: 'Enviada' },
        auditado: { bg: 'bg-dark', text: 'Auditado' }
    };

    function renderTable(logs) {
        elements.tableBody.innerHTML = '';
        if (logs.length === 0) {
            elements.tableBody.innerHTML = `<tr><td colspan="4" class="text-center text-muted">Nenhum log encontrado com os filtros aplicados.</td></tr>`;
            return;
        }

        logs.forEach(log => {
            const config = logTypeConfig[log.logType] || { bg: 'bg-light text-dark', text: log.logType };
            const formattedDate = new Date(log.timestamp).toLocaleString('pt-BR');

            elements.tableBody.innerHTML += `
                <tr>
                    <td>${formattedDate}</td>
                    <td><span class="badge ${config.bg}">${config.text}</span></td>
                    <td>
                        <div>${log.user.name}</div>
                        <div class="small text-muted">${log.user.email}</div>
                    </td>
                    <td>${log.details}</td>
                </tr>`;
        });
    }

    function applyFilters() {
        let filteredLogs = [...mockAuditLogs];

        const logType = elements.filterLogType.value;
        if (logType !== 'todos') {
            filteredLogs = filteredLogs.filter(log => log.logType === logType);
        }

        const startDate = elements.startDate.value;
        if (startDate) {
            filteredLogs = filteredLogs.filter(log => new Date(log.timestamp) >= new Date(startDate));
        }

        const endDate = elements.endDate.value;
        if (endDate) {
            const endOfDay = new Date(endDate);
            endOfDay.setDate(endOfDay.getDate() + 1);
            filteredLogs = filteredLogs.filter(log => new Date(log.timestamp) < endOfDay);
        }

        // Ordena por data mais recente
        filteredLogs.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));

        renderTable(filteredLogs);
    }

    // Adiciona os listeners para os filtros
    [elements.filterLogType, elements.startDate, elements.endDate].forEach(el => {
        el.addEventListener('change', applyFilters);
    });

    // Renderização inicial
    applyFilters();
});
