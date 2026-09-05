window.addEventListener("DOMContentLoaded", function () {
    let btnCadastrar = document.getElementById("btnCadastrar");

    btnCadastrar.addEventListener("click", cadastrarUser);

    function cadastrarUser() {
        let user = document.getElementById("usr").value;
        let pwd = document.getElementById("pwd").value;

        let novoUsuario = {usuario: user, senha: pwd};

        localStorage.setItem("usuarios", JSON.stringify(novoUsuario));
    }
})