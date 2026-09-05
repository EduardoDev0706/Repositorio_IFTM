public class App {
    public static void main(String[] args) {
        // 1. Validação do CPF
        String cpfTeste = "12345678901";
        if (!Validacoes.validarCpf(cpfTeste)) {
            System.out.println("CPF Inválido. Encerrando aplicação.");
            return;
        }
        // 2. Instanciação
        Gerente gerente = new Gerente("Alice", 8000.0, new Departamento("TI"), "Rua Principal, 100");
        Desenvolvedor desenvolvedor = new Desenvolvedor("Bob", 5000.0);

        // 3. Registrar KPI (Bonus de 20%)
        gerente.registrarKpi(20.0);

        // 4. Salva as classes em um array polimórfico
        Colaborador[] folhaPagamento = { gerente, desenvolvedor };

        // 5. Processamento e Saída
        System.out.println("PROCESSAMENTO DA FOLHA");

        for (Colaborador colab : folhaPagamento) {
            try {
                double liquido = colab.calcularSalarioLiquido();
                // Formatando para 2 casas decimais, substituindo ponto por vírgula como no PDF
                // [cite: 81, 82]
                System.out.printf("Salário Líquido Processado: R$ %.2f\n", liquido);
            } catch (CalculoInvalidoException e) {
                System.out.println("Erro ao processar salário de " + colab.getNome() + ": " + e.getMessage());
            }
        }

        // 6. Total de colaboradores [cite: 83]
        System.out.println("Total de colaboradores: " + Colaborador.totalColaboradores);
    }

}
