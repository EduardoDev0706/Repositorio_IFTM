// Array com os dados dos candidatos
const candidatos = [
    { nome: "Jair Messias Bolsonaro", partido: "PL", imagem: "Jb(PL).png" },
    { nome: "Luis Inácio Lula da Silva", partido: "PT", imagem: "Lula(PT).png" },
    { nome: "Ciro Gomes", partido: "PDT", imagem: "Ciro(PDT).png" },
    { nome: "Simone Tebet", partido: "MDB", imagem: "Simone(MDB).png" }
];

// Armazena os votos de cada candidato na disputa atual
let votosDisputa = {};
// Responsável para contar o número total de sorteios realizados na disputa atual
let totalSorteiosDisputa = 0;

// Responsável por sortear os candidatos e o vencedor
function sortearCandidatos() {
    // Cria uma cópia de arrays dos candidatos para remover os itens
    let candidatosDisponiveis = [...candidatos];

    // Sorteia o primeiro candidato
    let indCand1 = Math.floor(Math.random() * candidatosDisponiveis.length);
    let candidato1 = candidatosDisponiveis[indCand1];
    // Remove o primeiro candidato da array temporária
    candidatosDisponiveis.splice(indCand1, 1);

    // Sorteia o segundo candidato da lista restante
    let indCand2 = Math.floor(Math.random() * candidatosDisponiveis.length);
    let candidato2 = candidatosDisponiveis[indCand2];

    // Cria uma array com apenas os dois candidatos que estão disputando
    let candidatosDisputa = [candidato1, candidato2];
    let indVencedor = Math.floor(Math.random() * 2);
    let candidatoVencedor = candidatosDisputa[indVencedor];

    // Inicializa a contagem de votos para os candidatos da disputa
    if (!votosDisputa[candidato1.nome]) votosDisputa[candidato1.nome] = 0;
    if (!votosDisputa[candidato2.nome]) votosDisputa[candidato2.nome] = 0;

    // Incrementa o voto do candidato que foi vencedor
    votosDisputa[candidatoVencedor.nome]++;
    // Incrementa o contador total de sorteios da disputa
    totalSorteiosDisputa++;

    // Atualiza o HTML com as informações do primeiro candidato
    document.getElementById('candidatoSorteado').innerHTML = candidato1.nome;
    document.getElementById('partidoCandidato').innerHTML = candidato1.partido;
    document.getElementById('imgCandidato').src = `img/${candidato1.imagem}`;

    // Atualiza o HTML com as informações do segundo candidato
    document.getElementById('candidatoSorteado2').innerHTML = candidato2.nome;
    document.getElementById('partidoCandidato2').innerHTML = candidato2.partido;
    document.getElementById('imgCandidato2').src = `img/${candidato2.imagem}`;

    // Exibe o nome do vencedor
    document.getElementById('vencedor-nome').innerHTML = `<span class="vencedor-nome">${candidatoVencedor.nome}</span>`;

    // Chama a função para calcular e exibir as porcentagens
    atualizarPorcentagens(candidato1, candidato2);
}

// Função para calcular e exibir as porcentagens de votos
function atualizarPorcentagens(candidato1, candidato2) {
    const lista = document.getElementById('lista-porcentagens');
    lista.innerHTML = '';

    let totalVotosDisputa = votosDisputa[candidato1.nome] + votosDisputa[candidato2.nome];

    if (totalVotosDisputa === 0) return;

    let porcentagem1 = ((votosDisputa[candidato1.nome] / totalVotosDisputa) * 100).toFixed(2);
    let porcentagem2 = ((votosDisputa[candidato2.nome] / totalVotosDisputa) * 100).toFixed(2);

    // Cria e adiciona o item da lista para o primeiro candidato
    const item1 = document.createElement('li');
    item1.innerHTML = `<strong>${candidato1.nome}</strong>: ${porcentagem1}%`;
    lista.appendChild(item1);

    // Cria e adiciona o item da lista para o segundo candidato
    const item2 = document.createElement('li');
    item2.innerHTML = `<strong>${candidato2.nome}</strong>: ${porcentagem2}%`;
    lista.appendChild(item2);
}