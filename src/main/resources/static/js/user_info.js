document.addEventListener('DOMContentLoaded', function() {
    const userNameElement = document.querySelector('.fw-bold');
    const welcomeUserElements = document.querySelectorAll('.d-flex.flex-column.lh-1 span.fw-bold');
    const loggedInUser = sessionStorage.getItem('loggedInUser');

    if (loggedInUser) {
        // Atualiza o nome de usuário na versão mobile
        if (userNameElement) {
            userNameElement.textContent = loggedInUser;
        }
        
        // Atualiza o nome de usuário na versão desktop
        welcomeUserElements.forEach(element => {
            element.textContent = loggedInUser;
        });

    } else {
        // Se não houver usuário logado, redireciona para a página de login
        window.location.href = '/'; 
    }
});