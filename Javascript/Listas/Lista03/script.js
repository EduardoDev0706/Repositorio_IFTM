document.addEventListener('DOMContentLoaded', () => {
    const loginInput = document.getElementById('login');
    const senhaInput = document.getElementById('senha');
    const confirmarSenhaInput = document.getElementById('confirmarSenha');
    const btnEntrar = document.getElementById('btnEntrar');
    const btnLimpar = document.getElementById('btnLimpar');
    const mensagemDiv = document.getElementById('mensagem');

    btnEntrar.addEventListener('click', () => {
        mensagemDiv.textContent = '';
        mensagemDiv.style.color = 'black';

        // Login não pode ser vazio
        if (loginInput.value.trim() === '') {
            mensagemDiv.textContent = 'O campo Login deve ser preenchido.';
            mensagemDiv.style.color = 'red';
            return;
        }

        // Valida a senha
        if (senhaInput.value !== confirmarSenhaInput.value) {
            mensagemDiv.textContent = 'As senhas digitadas não são iguais.';
            mensagemDiv.style.color = 'red';
            senhaInput.value = '';
            confirmarSenhaInput.value = '';
            return;
        }

        mensagemDiv.textContent = 'Todos os campos foram preenchidos corretamente.';
        mensagemDiv.style.color = 'green';
    });

    btnLimpar.addEventListener('click', () => {
        loginInput.value = '';
        senhaInput.value = '';
        confirmarSenhaInput.value = '';
        mensagemDiv.textContent = '';
    });
});