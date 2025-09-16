document.addEventListener('DOMContentLoaded', function() {
    const userNameMobileElement = document.getElementById('user-name-mobile');
    const userNameDesktopElement = document.getElementById('user-name-desktop');
    const loggedInUser = sessionStorage.getItem('loggedInUser');

    if (loggedInUser) {
        if (userNameMobileElement) {
            userNameMobileElement.textContent = loggedInUser;
        }
        
        if (userNameDesktopElement) {
            userNameDesktopElement.textContent = loggedInUser;
        }

    } else {
        // Se não houver usuário logado, redireciona para a página de login
        window.location.href = '/'; 
    }
});