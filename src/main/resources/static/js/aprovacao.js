document.addEventListener('DOMContentLoaded', function () {

    // =================================================================================
    // PREPARAÇÃO PARA API REST (JAVA)
    // =================================================================================
    // O objeto 'mockIdea' simula os dados de uma ideia que viriam de um GET /api/ideas/{id}
    // A função 'simulateApiCall' simula uma chamada PUT /api/ideas/{id}/status
    // =================================================================================

    // <!-- INICIO USUÁRIO DE TESTE -->
    let mockIdea = {
        id: 1,
        title: 'Otimização do processo de logística reversa',
        author: 'Carlos Andrade',
        description: 'A proposta é criar um fluxo otimizado para a devolução de materiais, reduzindo o tempo de processamento e reaproveitando itens.',
        status: 'nova', // Altere para 'em_analise', 'aprovada', ou 'rejeitada' para testar a UI
    };
    // <!-- FIM USUÁRIO DE TESTE -->

    const elements = {
        ideaTitle: document.getElementById('idea-title'),
        ideaAuthor: document.getElementById('idea-author'),
        ideaDescription: document.getElementById('idea-description'),
        statusBadgeContainer: document.getElementById('idea-status-badge-container'),
        actionButtonsContainer: document.getElementById('action-buttons-container'),
        workflowSteps: document.querySelectorAll('.workflow-step'),
        modal: new bootstrap.Modal(document.getElementById('confirmationModal')),
        modalTitle: document.getElementById('modal-title'),
        modalText: document.getElementById('modal-text'),
        feedbackComment: document.getElementById('feedback-comment'),
        feedbackError: document.getElementById('feedback-error'),
        confirmActionBtn: document.getElementById('confirm-action-btn'),
        toast: new bootstrap.Toast(document.getElementById('notificationToast')),
        toastTitle: document.getElementById('toast-title'),
        toastBody: document.getElementById('toast-body'),
    };

    const statusConfig = {
        nova: { bg: 'bg-primary', text: 'Nova' },
        em_analise: { bg: 'bg-warning text-dark', text: 'Em Análise' },
        aprovada: { bg: 'bg-success', text: 'Aprovada' },
        rejeitada: { bg: 'bg-danger', text: 'Rejeitada' }
    };

    function renderPage(idea) {
        // Preenche detalhes
        elements.ideaTitle.textContent = idea.title;
        elements.ideaAuthor.textContent = idea.author;
        elements.ideaDescription.textContent = idea.description;

        // Atualiza o badge de status
        const status = statusConfig[idea.status];
        elements.statusBadgeContainer.innerHTML = `<span class="badge fs-6 ${status.bg}">${status.text}</span>`;
        
        updateWorkflow(idea.status);
        renderActionButtons(idea.status);
    }

    function updateWorkflow(currentStatus) {
        const statusOrder = ['nova', 'em_analise', 'aprovada'];
        const currentIndex = statusOrder.indexOf(currentStatus);

        elements.workflowSteps.forEach(step => {
            const stepStatus = step.getAttribute('data-step');
            const stepIndex = statusOrder.indexOf(stepStatus);
            
            step.classList.remove('active', 'completed');

            if (stepIndex < currentIndex) {
                step.classList.add('completed');
            } else if (stepIndex === currentIndex) {
                step.classList.add('active');
            }
        });
    }

    function renderActionButtons(status) {
        elements.actionButtonsContainer.innerHTML = '';
        let buttons = [];

        switch (status) {
            case 'nova':
                buttons = [
                    { text: 'Iniciar Análise', action: 'start-analysis', class: 'btn-primary' },
                    { text: 'Rejeitar', action: 'reject', class: 'btn-outline-danger' }
                ];
                break;
            case 'em_analise':
                buttons = [
                    { text: 'Aprovar Ideia', action: 'approve', class: 'btn-success' },
                    { text: 'Rejeitar', action: 'reject', class: 'btn-outline-danger' }
                ];
                break;
        }

        if (buttons.length > 0) {
            buttons.forEach(btnInfo => {
                const button = document.createElement('button');
                button.className = `btn ${btnInfo.class}`;
                button.textContent = btnInfo.text;
                button.dataset.action = btnInfo.action;
                elements.actionButtonsContainer.appendChild(button);
            });
        } else {
            elements.actionButtonsContainer.innerHTML = '<p class="text-muted">Nenhuma ação disponível para este status.</p>';
        }
    }
    
    elements.actionButtonsContainer.addEventListener('click', (e) => {
        if (e.target.tagName === 'BUTTON') {
            const action = e.target.dataset.action;
            prepareConfirmationModal(action);
            elements.modal.show();
        }
    });

    function prepareConfirmationModal(action) {
        const isMandatory = action === 'reject';
        elements.modalTitle.textContent = `Confirmar "${event.target.textContent}"`;
        elements.modalText.textContent = `Você está prestes a alterar o status desta ideia. Por favor, adicione um comentário${isMandatory ? ' (obrigatório)' : ''}.`;
        elements.feedbackComment.value = '';
        elements.feedbackComment.required = isMandatory;
        elements.feedbackComment.classList.remove('is-invalid');
        elements.confirmActionBtn.dataset.action = action;
    }

    elements.confirmActionBtn.addEventListener('click', async () => {
        const action = elements.confirmActionBtn.dataset.action;
        const comment = elements.feedbackComment.value;

        if (elements.feedbackComment.required && !comment.trim()) {
            elements.feedbackComment.classList.add('is-invalid');
            return;
        }
        elements.feedbackComment.classList.remove('is-invalid');

        toggleLoading(true);
        
        try {
            const newStatus = await simulateApiCall(mockIdea.id, action, comment);
            mockIdea.status = newStatus; // Atualiza o estado local
            showToast('Sucesso!', 'Status da ideia atualizado com sucesso.', 'success');
            elements.modal.hide();
            renderPage(mockIdea);
        } catch (error) {
            showToast('Erro!', error.message, 'danger');
        } finally {
            toggleLoading(false);
        }
    });

    function simulateApiCall(ideaId, action, comment) {
        console.log(`API CALL: Enviando para /api/ideas/${ideaId}/status`);
        console.log({ action, comment });

        return new Promise((resolve, reject) => {
            setTimeout(() => {
                // Simula uma falha aleatória
                if (Math.random() < 0.1) { // 10% de chance de erro
                    return reject(new Error('Não foi possível se comunicar com o servidor.'));
                }

                let newStatus = mockIdea.status;
                if (action === 'start-analysis') newStatus = 'em_analise';
                if (action === 'approve') newStatus = 'aprovada';
                if (action === 'reject') newStatus = 'rejeitada';
                
                resolve(newStatus);
            }, 1500); // Simula 1.5s de delay da rede
        });
    }

    function toggleLoading(isLoading) {
        const spinner = elements.confirmActionBtn.querySelector('.spinner-border');
        if (isLoading) {
            elements.confirmActionBtn.disabled = true;
            spinner.classList.remove('d-none');
        } else {
            elements.confirmActionBtn.disabled = false;
            spinner.classList.add('d-none');
        }
    }

    function showToast(title, body, type = 'success') {
        const toastHeader = elements.toast._element.querySelector('.toast-header');
        toastHeader.classList.remove('bg-success', 'bg-danger', 'text-white');
        if (type === 'success') {
            toastHeader.classList.add('bg-success', 'text-white');
        } else if (type === 'danger') {
            toastHeader.classList.add('bg-danger', 'text-white');
        }
        elements.toastTitle.textContent = title;
        elements.toastBody.textContent = body;
        elements.toast.show();
    }

    // Renderização inicial da página
    renderPage(mockIdea);
});
