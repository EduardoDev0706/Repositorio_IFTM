import { usersTable } from "./table.js";

// 1. Exibir o nome e idade de pessoas maiores de idade
const maioresIdade = usersTable.filter(user => user.idade >= 18);
maioresIdade.forEach(user => {
   console.log(`O usuário ${user.nome} é maior de idade (${user.idade} anos).`);
});

// 2. Exibir o nome de todas as pessoas do sexo masculino
const maioresIdadeHomens = usersTable.filter(user => user.sexo === "M" && user.idade >= 18);
maioresIdadeHomens.forEach(user => {
    console.log(`O seguinte usuário é do sexo masculino e maior de idade: ${user.nome}`)
});

// 3. Exibir os dados da pessoa com o maior salario
const maiorSalario = usersTable.reduce((anterior, atual) => {
    if (atual.salario > anterior.salario) {
        return atual;
    } else {
        return anterior;
    }
});
console.log(`O usuário com o maior salário é: ${maiorSalario.nome}`);

// 4. Há alguma mulher que ganha acima de R$5000,00
const mulherCincoMil = usersTable.filter(user => user.sexo === "F" && user.salario >= 5000);
mulherCincoMil.forEach(user => {
    console.log(`Usuário encontrado: ${user.nome} | Salário: R$ ${user.salario}`);
});

// 5. Media dos salarios dos homens e das mulheres
const calcularMedia = (sexoAlvo) => {
    const grupo = usersTable.filter(u => u.sexo === sexoAlvo);
    // Soma tudo e divide pelo total, ou retorna 0 se a lista for vazia
    return grupo.length 
        ? (grupo.reduce((acc, u) => acc + u.salario, 0) / grupo.length) 
        : 0;
};

console.log(`Média Homens: R$ ${calcularMedia('M').toFixed(2)}`);
console.log(`Média Mulheres: R$ ${calcularMedia('F').toFixed(2)}`);