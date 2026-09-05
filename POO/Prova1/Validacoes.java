public class Validacoes {
    public static boolean validarCpf(String cpf) {
        // Remove caracteres não numéricos, caso venham formatados
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11; 
    }

    public static void validarValorPositivo(double valor) throws CalculoInvalidoException {
        if (valor < 0) {
            throw new CalculoInvalidoException("Valor negativo inválido: " + valor);
        }
    }
}
