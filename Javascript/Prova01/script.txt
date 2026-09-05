// Variáveis Globais do Programa
// Base para o funcionamento das funções

let textoOriginal = "";
const caixaSeletora = document.getElementById('caixaSeletora');
const imagem = document.getElementById('personagem-img');
const textoAbaixo = document.getElementById('texto-imagem');
const botao06 = document.getElementById('botao06');

// Responsável pela checagem do campo de input
function checarCampo() {
    if (caixaSeletora.value.trim() === "") {
        alert("O campo de texto está vazio. Informe o input esperado.");
        return false;
    }
    return true;
}

// Garante que existam apenas seis ações
// Caso ambos os requisitos não sejam verdadeiros, o programa não funcionará
function acaoPersonagem(numero) {
    if (!checarCampo() && numero !== 6) {
        return;
    }

    const textoInput = caixaSeletora.value.trim();

    switch (numero) {
        // Personagem 01
        case 1:
            imagem.src = 'img/imagem01.png';
            textoAbaixo.textContent = textoInput;
            break;
        // Personagem 02
        case 2:
            imagem.src = 'img/imagem02.png';
            textoAbaixo.textContent = textoInput.toUpperCase();
            break;
        // Personagem 03
        case 3:
            imagem.src = 'img/imagem03.png';
            const primeiraPalavra = textoInput.split(' ')[0];
            textoAbaixo.textContent = primeiraPalavra;
            break;
        // Personagem 04
        case 4:
            imagem.src = 'img/imagem04.png';
            textoAbaixo.textContent = textoInput.toLowerCase();
            break;
        // Personagem 05
        case 5:
            imagem.src = 'img/imagem05.png';

            // Inicia em 0
            const valorAtual = parseInt(textoInput, 0);

            // Verifica se o valor inserido é um número inteiro
            // Caso seja, soma o valor inserido + 1
            if (!isNaN(valorAtual)) {
                caixaSeletora.value = valorAtual + 1;
                textoAbaixo.textContent = `Valor incrementado: ${caixaSeletora.value}`;
            } else {
                alert("Para o personagem 05, o valor inserido na caixa deve ser numérico.");
                textoAbaixo.textContent = "Valor inválido para incremento";
            }
            break;
        // Personagem 06
        case 6:
            if (checarCampo()) {
                imagem.src = 'img/imagem06.png';

                // Pede o nome ao usuário
                const nomeUsuario = prompt("Por favor, digite seu nome para o personagem 06: ");

                // Concatena o nome inserido com o texto do input
                const textoFinal = nomeUsuario + ", " + textoInput;

                textoAbaixo.textContent = textoFinal;
                alert("O texto concatenado para o Personagem 06 é: " + textoFinal);
            }
            break;

        default:
            console.log("Ação desconhecida.");
    }
}