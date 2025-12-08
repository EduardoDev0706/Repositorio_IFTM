// ETAPA: 01 - Script para capturar o nome do usuário e exibi-lo na página
// Insere o nome informado pelo usuário na página
const nomeUsuario = document.getElementById("nomeDoUsuario");

const botaoExibir = document.querySelector("button");
const paragrafo = document.createElement("p");

botaoExibir.addEventListener("click", function () {
    const nomeInformado = nomeUsuario.value;
    paragrafo.textContent = "Nome informado: " + nomeInformado + ". Clique no botão abaixo para abrir a próxima página após 3 segundos.";

    // Verifica se o parágrafo já foi adicionado para evitar duplicatas
    if (!document.body.contains(paragrafo)) {
        document.body.appendChild(paragrafo);
    }

    const botaoAbrirPagina = document.createElement("button");
    botaoAbrirPagina.textContent = "Abrir página";

    botaoAbrirPagina.addEventListener("click", function () {
        setTimeout(function () {
            window.location.href = "escolherPerfil.html";
        }, 3000);
    });

    // Adiciona o botão à página
    document.body.appendChild(botaoAbrirPagina);
});









