# Estudo de Princípios SOLID

OCP - Open-Closed Principle

Não é preciso alterar o código fonte existente, que já foi testado e aprovado, para que novas funcionalidades sejam implementadas.

Sintomas de violação: 
- Cascata de if/else ou switch. Toda vez que um novo requisito de negócio exigir que você altere uma classe central para alterar o código.
- Falta de Abstração: Se a adição de uma nova entidade ou regra force a alteração de várias classes espalhadas pelo código.

Exemplo de Código: 

// VIOLAÇÃO DO OCP: Se surgir a assinatura "ENTERPRISE", será preciso alterar essa classe.
public double calcularMensalidade(Assinatura assinatura) {
    if (assinatura.getTipo() == Tipo.BASICO) return 10.0;
    if (assinatura.getTipo() == Tipo.PRO) return 20.0;
    return 0.0; 
}

Como resolver:
Utilize polimorfismo, injeção de dependência e padrões como o Strategy. Em vez de concentrar a lógica de todos os tipos num lugar só, você cria uma interface. Novos comportamentos são adicionados criando novas classes que implementam essa interface, deixando o código original intacto.

LSP - Liskov Substitution Principle

O ponto central é o *contrato*. Uma classe filha não pode distorcer as regras, expectativas ou comportamentos esperados pela classe pai ou interface.

Sintomas de violação:

- Exceções não esperadas (O Sintoma do Pinguim): A classe filha implementa uma interface ou herda de uma classe pai, mas sobrescreve um método apenas para lançar um UnsupportedOperationException. Se Pinguim herda de Ave e o método voar() lança um erro, a substituição falhou.

- Checagem de tipo em tempo de execução: O código que consome as classes precisa usar instanceof (ou typeof) antes de chamar um método para evitar erros com implementações específicas.

- Mudança silenciosa do contrato: A classe filha exige pré-condições mais rígidas para executar um método (ex: a classe pai aceitava null, a filha lança erro) ou retorna um estado não previsto pela abstração original.

- Problema do Quadrado/Retângulo: Matematicamente, um quadrado é um retângulo. No código, se Quadrado herda de Retangulo, os métodos setLargura e setAltura do quadrado precisarão alterar a outra dimensão simultaneamente para manter os lados iguais. Um cliente que consome a abstração Retangulo esperando que alterar a largura não afete a altura terá seu código quebrado.

Como resolver:
Se o subtipo não consegue cumprir o contrato original de forma honesta, a modelagem de herança está errada. Extraia o comportamento comum para uma interface menor e mais específica (conectando-se ao Interface Segregation Principle), ou favoreça a composição no lugar da herança.
