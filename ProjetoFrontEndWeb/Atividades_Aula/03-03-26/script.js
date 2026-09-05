// Sistema de Cadastro de Heróis

const habilidadesPadrao = { forca: 50, agilidade: 50 };
const novoHeroi = { nome: 'Paulin', poderPrincipal: 'Teleporte', forca: 65};

const heroiCompleto = {...habilidadesPadrao, ...novoHeroi};

const { nome, poderPrincipal, agilidade} = heroiCompleto;
console.log(`O herói se chama "${nome}", tem como poder principal a habilidade de ${poderPrincipal} e possui o valor de ${agilidade} em agilidade`);

const user = { nome: 'Iris', idade: 44};
const newUser = {...user, idade: 33};
console.log(newUser);

const spx = () => {
    const numeros = [10, 20, 30];
    function soma(a, b, c) {
        return a + b + c;
    }
    console.log(soma(...numeros));
}

spx();

const numeros = [10, 20, 30];
const [,, final] = numeros; // A primeira vírgula pula o '10'

console.log(final);


// // Operador Spread

// // Quando Utilizar: Você deve utilizar o Spread sempre que precisar manipular dados de forma imutável (ou seja, criando novos conjuntos de dados com base nos antigos, em vez de alterar os originais). Ele é extremamente comum no desenvolvimento frontend moderno (como no React) para atualizar estados, mesclar configurações ou clonar estruturas de dados sem afetar a referência original.

// // Copiando e combinando Arrays
// const frutas = ['Maçã', 'Banana'];
// const maisFrutas = ['Laranja', 'Uva'];

// // Combinando e adicionando novos itens
// const todasAsFrutas = [...frutas, 'Morango', ...maisFrutas];
// console.log(todasAsFrutas);

// // Mesclando Objetos
// const usuario = {nome: 'Carlos', idade: 25};
// const configuraçoes = {tema: 'escuro', notificacoes: true};

// const perfilCompleto = {...usuario, ...configuraçoes};
// console.log(perfilCompleto);

// // Passando argumentos para funções
// const numeros = [10, 5, 20, 8];
// // Math.max espera números separados por vírgula, não um array. O spread resolve isso!
// const maiorNumero = Math.max(...numeros);
// console.log(maiorNumero);

// // Destructuring

// // Quando Utilizar: A desestruturação é ideal quando você trabalha com objetos grandes, como respostas de APIs (JSON), configuração de parâmetros em funções ou ao importar módulos específicos de bibliotecas. Torna o código muito mais limpo e legível.

// // Desestruturação de Objetos
// const desenvolvedor = {
//     nome: 'Ana', 
//     linguagem: 'JavaScript',
//     experiencia: 'Pleno'
// };

// // Extraindo as propriedades diretamente para variáveis
// const {nome, linguagem} = desenvolvedor;

// console.log(nome);
// console.log(linguagem);

// // Desestruturação de Arrays
// // Nota: Ocorre com base na posição (índice)
// const cores = ['Vermelho', 'Verde', 'Azul'];

// const [primeiraCor, segundaCor, terceiraCor] = cores;

// console.log(primeiraCor);
// console.log(segundaCor);
// console.log(terceiraCor);

// // Desestruturação em Parâmetros de Função
// function exibirResumo({ titulo, autor}) {
//     console.log(`O livro "${titulo}" foi escrito por ${autor}.`);
// }

// const livro = {titulo: 'Clean Code', autor: 'Robert C. Martin', ano: 2008};
// exibirResumo(livro);
