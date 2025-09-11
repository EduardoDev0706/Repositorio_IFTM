// Numero base
const nmrSorteado = 58;

function megaSena() {

  var numbers = prompt("Adicione os 6 números da mega-sena")

  if (numbers <= 0 || numbers > 60 )
  {
    return alert("Isso não consta como número válido")
  }

  var errou = false;

  nmrSorteado == 58 ? every("acertou!") : (errou = true);
}

megaSena();












































// function calculadora(operacao, num1, num2) {
//   let resultado;

//   if (isNaN(num1) || isNaN(num2)) {
//     return "Erro: Entrada inválida. Por favor, insira apenas números.";
//   }

//   switch (operacao) {
//     case 'soma':
//       resultado = num1 + num2;
//       break;
//     case 'subtracao':
//       resultado = num1 - num2;
//       break;
//     case 'multiplicacao':
//       resultado = num1 * num2;
//       break;
//     case 'divisao':
//       if (num2 === 0) {
//         return "Erro: Não é possível dividir por zero.";
//       }
//       resultado = num1 / num2;
//       break;
//     default:
//       return "Erro: Operação inválida.";
//   }

//   return resultado;
// }

// function main() {
//   const operacaoEscolhida = prompt("Escolha a operação: 'soma', 'subtracao', 'multiplicacao' ou 'divisao'");

//   const valor1 = parseFloat(prompt("Insira o primeiro número:"));
//   const valor2 = parseFloat(prompt("Insira o segundo número:"));

//   const resultado = calculadora(operacaoEscolhida, valor1, valor2);

//   alert(`O resultado é: ${resultado}`);
// }

// main();