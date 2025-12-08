import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mapa: Caractere -> Quantidade
        Map<Character, Integer> frequencia = new HashMap<>();

        System.out.println("Contador de Frequência de Letras");
        System.out.println("Digite uma frase ou texto: ");
        String texto = scanner.nextLine();

        for (char letra : texto.toCharArray()) {
            
            // Requisito: Ignorar espaços
            if (letra != ' ') {
                frequencia.merge(letra, 1, Integer::sum);
            }
        }

        System.out.println("Resultado");
        // Exibindo o resultado
        for (Character chave : frequencia.keySet()) {
            System.out.println("Letra '" + chave + "': " + frequencia.get(chave) + " vezes.");
        }

        scanner.close();
    }
}