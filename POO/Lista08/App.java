import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        try {
            verificarSenha(senha);
            System.out.println("Bem Vindo ao Sistema Financeiro");
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        scanner.close();
    }

    public static void verificarSenha(String senha) {
        if (!senha.equals("123456")) {
            throw new IllegalArgumentException("Senha incorreta!");
        }
    }
}