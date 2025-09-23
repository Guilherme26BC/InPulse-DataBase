document.addEventListener('DOMContentLoaded', function () {

    const elements = {
        loginForm: document.getElementById('login-form'),
        usernameInput: document.getElementById('username'),
        passwordInput: document.getElementById('password'),
        loginButton: document.getElementById('login-button'),
        buttonSpinner: document.getElementById('button-spinner'),
        buttonText: document.getElementById('button-text'),
        errorMessage: document.getElementById('error-message'),
    };

    function validateForm() {
        const isFormValid = elements.usernameInput.value.trim() !== '' && elements.passwordInput.value.trim() !== '';
        elements.loginButton.disabled = !isFormValid;
    }

    elements.usernameInput.addEventListener('input', validateForm);
    elements.passwordInput.addEventListener('input', validateForm);

    elements.loginForm.addEventListener('submit', async function (event) {
        event.preventDefault();

        toggleLoading(true);
        hideError();

        try {
            const response = await fetch('/api/funcionarios/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    email: elements.usernameInput.value,
                    senha: elements.passwordInput.value
                })
            });

            if (!response.ok) {
                if (response.status === 401) {
                    throw new Error('Usuário ou senha inválidos.');
                }
                throw new Error('Erro ao se conectar com o servidor.');
            }

            const data = await response.json(); 
            // Salva o nome do usuário no sessionStorage
            const fullName = `${data.primeiro_nome} ${data.ultimo_sobrenome}`;
            sessionStorage.setItem('loggedInUser', fullName);
            
            window.location.href = '/ideias';

        } catch (error) {
            showError(error.message);
        } finally {
            toggleLoading(false);
        }
    });

    function toggleLoading(isLoading) {
        elements.loginButton.disabled = isLoading;
        if (isLoading) {
            elements.buttonSpinner.classList.remove('d-none');
            elements.buttonText.textContent = 'Entrando...';
        } else {
            elements.buttonSpinner.classList.add('d-none');
            elements.buttonText.textContent = 'Entrar';
        }
    }

    function showError(message) {
        elements.errorMessage.textContent = message;
        elements.errorMessage.classList.remove('d-none');
    }

    function hideError() {
        elements.errorMessage.classList.add('d-none');
    }
});