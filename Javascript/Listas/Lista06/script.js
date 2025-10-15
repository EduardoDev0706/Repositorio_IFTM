/* 
DISCLAIMER !!!
Código comentado será utilizado como uma prova dos testes
de cada etapa realizada pelo autor: Eduardo do Carmo Pereira
*/

// ETAPA 1: Criar o vetor base de cartas (1 a 27)
// Criando o vetor base
const vetorCartas = Array.from({ length: 27 }, (_, i) => i + 1);
// console.log('ETAPA 1 - vetorCartas:', vetorCartas);

let cartasDoJogo = [];
let numTentativas = 0;
let numAcertos = 0;
let numMaxAcertos = 0;
let cartasViradas = [];
let bloqueioClique = false;

// Funções Auxiliares
function pegarCartasAleatorias(vetorBase, numPares) {
    // 1. Clonar o vetor para não alterar o original
    let cartasDisponiveis = [...vetorBase];
    let paresSelecionados = [];

    // 2. Selecionar cartas aleatoriamente
    for (let i = 0; i < numPares; i++) {
        // Gera um índice aleatório
        const indiceAleatorio = Math.floor(Math.random() * cartasDisponiveis.length);
        // Pega a carta
        const cartaSelecionada = cartasDisponiveis[indiceAleatorio];
        // Adiciona a carta ao array de pares (duas vezes para formar o par)
        paresSelecionados.push(cartaSelecionada);
        paresSelecionados.push(cartaSelecionada);
        // Remove a carta selecionada do vetor para evitar repetição excessiva

        if (cartasDisponiveis.length > 0) {
            cartasDisponiveis.splice(indiceAleatorio, 1);
        } else {
            console.error("Não há cartas suficientes.");
            break;
        }
    }
    return paresSelecionados;
}

function embaralharVetor(vetor) {
    // Implementação do algoritmo Fisher-Yates (Já utilizado anteriormente)
    for (let i = vetor.length - 1; i > 0; i--) {
        // Escolhe um elemento aleatório
        const j = Math.floor(Math.random() * (i + 1));
        // Troca com o elemnento atual
        [vetor[i], vetor[j]] = [vetor[j], vetor[i]];
    }
    return vetor;
}

// Função para atualizar o placar
function atualizarPlacar() {
    document.getElementById('numTentativas').textContent = numTentativas;
    document.getElementById('numAcertos').textContent = numAcertos;

    // ETAPA 11: Cálculo do Aproveitamento
    let aproveitamento = 0.00;
    if (numTentativas > 0) {
        aproveitamento = (numAcertos / numTentativas) * 100;
    }
    document.getElementById('aproveitamento').textContent = `${aproveitamento.toFixed(2)}%`;
}

// ETAPA 10: Lógica de verificação e desvirar
function verificarPar() {
    bloqueioClique = true; // Bloqueia novos cliques
    numTentativas++;
    atualizarPlacar();

    const [carta1, carta2] = cartasViradas;

    if (carta1.dataset.valor === carta2.dataset.valor) {
        // Acertou: Cartas permanecem viradas
        numAcertos++;
        alert("Parabéns! Você acertou!"); // ETAPA 8: Mensagem de acerto

        // Remove os listeners de clique para que não possam ser viradas novamente
        carta1.removeEventListener('click', clicarCarta);
        carta2.removeEventListener('click', clicarCarta);

        cartasViradas = [] // Limpa para a próxima jogada
        bloqueioClique = false; // Desbloqueia

        if (numAcertos === numMaxAcertos) {
            // Lógica de fim de jogo
            alert(`FIM DE JOGO! Você acertou todas as duplas de cartas! \nSeu aproveitamento foi de ${document.getElementById('aproveitamento').textContent}.`);
        }
    } else {
        // Errou: Desvirar após 1 segundo
        alert("Errou!"); // ETAPA 8: Mensagem de erro

        setTimeout(() => {
            carta1.classList.remove('revelada');
            carta2.classList.remove('revelada');
            cartasViradas = [] // Limpa para a próxima jogada
            bloqueioClique = false; // Desbloqueia após virar
        }, 1000); // 1 segundo
    }
}

// ETAPA 7/9/10 (Atualizada)
function clicarCarta(evento) {
    const cartaClicada = evento.currentTarget;

    // Bloqueio e limite de cliques
    if (bloqueioClique) {
        return; // Ignora o clique se o tabuleiro estiver bloqueado
    }

    if (cartaClicada.classList.contains('revelada')) {
        return;
    }

    if (cartasViradas.length === 1 && cartasViradas[0] === cartaClicada) {
        alert("ATENÇÃO: A segunda carta a ser clicada deverá ser, obrigatoriamente, diferente da primeira.");
        return;
    }

    // ETAPA 11: Apenas duas cartas podem ser clicadas por jogador
    if (cartasViradas.length >= 2) {
        alert("Não é possível clicar em mais de duas cartas na sua vez.");
        return;
    }

    cartaClicada.classList.add('revelada');
    cartasViradas.push(cartaClicada);

    // ETAPA 7: Exibir a descrição no clique
    // alert(`Você clicou na carta de número: ${valorCarta}. \nDescrição: Esta é a carta ${valorCarta} do baralho.`);

    if (cartasViradas.length === 2) {
        verificarPar();
    }

}

// ETAPA 4: Exibir as cartas na interface
function criarTabuleiro(cartasEmbaralhadas) {
    const tabuleiro = document.getElementById('tabuleiro');
    // ETAPA 6: Limpar o tabuleiro antes de adicionar as novas cartas
    tabuleiro.innerHTML = '';
    cartasDoJogo = [];

    const numCartas = cartasEmbaralhadas.length;
    const colunas = numCartas <= 16 ? 4 : 6;
    tabuleiro.style.gridTemplateColumns = `repeat(${colunas}, 1fr)`
    tabuleiro.style.display = 'grid';

    cartasEmbaralhadas.forEach(numeroCarta => {
        // Cria um elemento div para a carta
        const divCarta = document.createElement('div');
        divCarta.classList.add('carta');
        // Classe 'verso' para mostrar a imagem de fundo
        divCarta.classList.add('verso');
        // Adiciona o atributo de dados para saber qual é o par
        divCarta.dataset.valor = numeroCarta;

        // Cria a imagem
        const img = document.createElement('img');
        img.src = `img/carta${numeroCarta}.png`;
        img.alt = `Carta ${numeroCarta}`;

        divCarta.appendChild(img);
        tabuleiro.appendChild(divCarta);

        // ETAPA 7: Adicionar o listener de clique
        divCarta.addEventListener('click', clicarCarta);

        cartasDoJogo.push(divCarta); // Armazena a referência
    });

    // console.log('ETAPA 4 - Cartas exibidas no HTML');
}

// Resetar o estado do jogo
function resetarJogo() {
    numTentativas = 0;
    numAcertos = 0;
    numMaxAcertos = 0;
    cartasViradas = [];
    bloqueioClique = false;
    document.getElementById('tabuleiro').innerHTML = '';
    // ETAPA 8: Zera o placar no início
    atualizarPlacar();
}

function iniciarJogo() {
    // 1. Resetar o estado anterior
    resetarJogo();

    const nivelSelecionado = document.getElementById('nivelDificuldade').value;
    const numPares = parseInt(nivelSelecionado, 10);

    // ETAPA 8: Definir o número máximo de acertos
    numMaxAcertos = numPares;

    // 2. Gerar e embaralhar
    let vetorPares = pegarCartasAleatorias(vetorCartas, numPares);
    let vetorParesEmbaralhados = embaralharVetor(vetorPares);

    // 3. Exibir o tabuleiro
    criarTabuleiro(vetorParesEmbaralhados);

    // console.log(`ETAPA 5 - Jogo iniciado com ${numPares} pares.`);
    // console.log('ETAPA 6 - Cartas anteriores removidas. Novo tabuleiro criado.')
    console.log(`ETAPA 8 - Placar inicializado. Máximo de acertos: ${numMaxAcertos}`)
}

// Inicialização
document.addEventListener('DOMContentLoaded', () => {
    // Inicializa o placar no carregamento
    atualizarPlacar();
    // ETAPA 5/6: Lógica de Início
    document.getElementById('btnIniciar').addEventListener('click', iniciarJogo);
});

// // ETAPA 2: Gerar 4 pares aleatórios
// let vetorPares = pegarCartasAleatorias(vetorCartas, 4);
// // console.log('Etapa 2 - vetorPares (4 pares):', vetorPares);

// // ETAPA 3: Embaralhar o vetor de pares
// let vetorParesEmbaralhados = embaralharVetor(vetorPares);
// // console.log('ETAPA 3 - vetorParesEmbaralhados:', vetorParesEmbaralhados);

// Funções comentadas por não terem mais necessidade de estarem no código