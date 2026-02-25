import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        Vetor<Pessoa> vetorPessoas = new Vetor<>(50);
        Vetor<Cidade> vetorCidades = new Vetor<>(50);

        // --- ENTRADA DE DADOS: CIDADES ---
        System.out.print("Quantas cidades deseja cadastrar? ");
        int qtdCidades = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < qtdCidades; i++) {
            System.out.println("\n--- Cadastro de Cidade " + (i + 1) + " ---");
            System.out.print("Nome da Cidade: ");
            String nomeC = scanner.nextLine();
            System.out.print("Adjetivo Pátrio (Ex: Uberlandense): ");
            String adjetivoC = scanner.nextLine();
            System.out.print("Estado: ");
            String estadoC = scanner.nextLine();

            vetorCidades.adicionar(new Cidade(nomeC, adjetivoC, estadoC));
        }

        // --- ENTRADA DE DADOS: PESSOAS ---
        System.out.print("\nQuantas pessoas deseja cadastrar? ");
        int qtdPessoas = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < qtdPessoas; i++) {
            System.out.println("\n--- Cadastro de Pessoa " + (i + 1) + " ---");
            System.out.print("Nome: ");
            String nomeP = scanner.nextLine();
            System.out.print("Sexo (M/F): ");
            char sexoP = scanner.nextLine().toUpperCase().charAt(0);
            System.out.print("Naturalidade (Nome da cidade onde nasceu): ");
            String naturalidadeP = scanner.nextLine();

            vetorPessoas.adicionar(new Pessoa(nomeP, sexoP, naturalidadeP));
        }

        // --- BUSCA ---
        System.out.print("\nDigite um nome para realizar a busca: ");
        String nomeBusca = scanner.nextLine();

        // Lógica de busca da Pessoa
        Pessoa pessoaEncontrada = null;
        for (int i = 0; i < vetorPessoas.getTamanho(); i++) {
            if (vetorPessoas.getElemento(i).getNome().equalsIgnoreCase(nomeBusca)) {
                pessoaEncontrada = vetorPessoas.getElemento(i);
                break; // Encerra a busca ao encontrar a pessoa
            }
        }

        // Avaliação dos resultados da busca
        if (pessoaEncontrada != null) {
            // Lógica de busca da Cidade caso a pessoa seja encontrada
            Cidade cidadeEncontrada = null;
            for (int i = 0; i < vetorCidades.getTamanho(); i++) {
                if (vetorCidades.getElemento(i).getNome().equalsIgnoreCase(pessoaEncontrada.getNaturalidade())) {
                    cidadeEncontrada = vetorCidades.getElemento(i);
                    break;
                }
            }

            if (cidadeEncontrada != null) {
                // Caso a pessoa e a cidade sejam encontradas
                String artigo = (pessoaEncontrada.getSexo() == 'F') ? "A" : "O";
                System.out.println(artigo + " " + cidadeEncontrada.getAdjetivo() + " " +
                        pessoaEncontrada.getNome() + " nasceu em " +
                        cidadeEncontrada.getNome() + " - " +
                        cidadeEncontrada.getEstado() + ".");
            } else {
                // Caso a pessoa seja encontrada, mas a cidade NÃO
                System.out.println(pessoaEncontrada.getNome() + " nasceu em " +
                        pessoaEncontrada.getNaturalidade() + ".");
            }

        } else {
            // Caso a pessoa NÃO seja encontrada
            System.out.println(nomeBusca + " nasceu em cidade desconhecida.");
        }

        scanner.close();
    }
}
