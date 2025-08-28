nome = prompt("Digite seu nome completo: ", "Eduardo do Carmo Pereira");
opcao = prompt(
    "[MENU]" +
    "\n[1] LETRAS MAÍUSCULAS" +
    "\n[2] LETRAS MINÚSCULAS" +
    "\n[3] PRIMEIRA LETRA MAÍSCULA" +
    "\n[4] PRIMEIRO NOME" +
    "\n[5] PRIMEIRO NOME MAÍUSCULO E ÚLTIMO NOME MINÚSCULO" +
    "\n[6] SUBSTITUA O PRIMEIRO NOME POR UM NOVO NOME" +
    "\n[7] PRIMEIRA LETRA DA PRIMEIRA E ÚLTIMA PALAVRA" +
    "\n[8] EXIBA AS 3 PRIMEIRAS LETRAS DO NOME"
);

switch (opcao) {
    case "1":
        document.write(`<p>${nome.toUpperCase()}</p>`);
        break;
    case "2":
        document.write(`<p>${nome.toLowerCase()}</p>`);
        break;

    case "3":
        document.write(`<p>${nome.charAt(0).toUpperCase() + nome.slice(1)}</p>`);
        break;
    case "4":
        document.write(`<p>${nome.split(' ')[0]}</p>`);
        break;
    case "5":
        document.write(`<p>${nome.split(' ')[0].toUpperCase()} ${nome.split(' ')[nome.split(' ').length - 1].toLowerCase()}</p>`);
        break;
    case "6":
        let novoNome = prompt("Digite o novo primeiro nome:");
        document.write(`<p>${nome.replace(nome.split(' ')[0], novoNome)}</p>`);
        break;
    case "7":
        const nomeMinusculo = nome.toLowerCase();
        const partes = nomeMinusculo.split(' ');
        document.write(`<p>${partes[0].charAt(0).toUpperCase() + partes[0].slice(1)
            }${partes.length > 2
                ? ' ' + partes.slice(1, -1).join(' ') + ' '
                : partes.length > 1 ? ' ' : ''
            }${partes.length > 1
                ? partes[partes.length - 1].charAt(0).toUpperCase() + partes[partes.length - 1].slice(1)
                : ''
            }</p>`);
        break;
    case "8":
        document.write(`<p>${nome.slice(0, 3)}</p>`);
        break;
    default:
        document.write("<p>Opção inválida. Por favor, recarregue a página e tente novamente.</p>");
}