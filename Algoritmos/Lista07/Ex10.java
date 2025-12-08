import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex10 {

    public static class Aluno {
        private String nome;
        private String matricula;
        private String cpf;

        public Aluno(String nome, String matricula, String cpf) {
            this.nome = nome;
            this.matricula = matricula;
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "Nome: " + nome + " | CPF: " + cpf + " | Matrícula: " + matricula;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mapa 1: Dados Cadastrais (Chave: Matrícula -> Valor: Objeto Aluno)
        Map<String, Aluno> mapaAlunos = new HashMap<>();

        // Mapa 2: Notas (Chave: Matrícula -> Valor: Nota Double)
        Map<String, Double> mapaNotas = new HashMap<>();

        int opcao = -1;

        System.out.println("=== SISTEMA DE GESTÃO ESCOLAR ===");

        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Adicionar Aluno");
            System.out.println("2 - Lançar Nota para Aluno");
            System.out.println("3 - Calcular Média da Turma");
            System.out.println("4 - Imprimir Relatório de Alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: // Adicionar Aluno
                    System.out.print("Digite a Matrícula: ");
                    String mat = scanner.nextLine();

                    if (mapaAlunos.containsKey(mat)) {
                        System.out.println(">> Erro: Matrícula já existente!");
                    } else {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        Aluno novoAluno = new Aluno(mat, nome, cpf);
                        mapaAlunos.put(mat, novoAluno);
                        System.out.println(">> Aluno cadastrado com sucesso.");
                    }
                    break;

                case 2: // Adicionar Nota
                    System.out.print("Digite a Matrícula do aluno para dar nota: ");
                    String matNota = scanner.nextLine();

                    // Só podemos dar nota se o aluno existir no cadastro
                    if (mapaAlunos.containsKey(matNota)) {
                        System.out.print("Digite a nota (use ponto para decimais): ");
                        try {
                            double nota = Double.parseDouble(scanner.nextLine());

                            // Adiciona ou atualiza a nota no segundo mapa
                            mapaNotas.put(matNota, nota);
                            
                            // Busca o nome para dar um Feedback
                            String nomeAluno = mapaAlunos.get(matNota).getNome();
                            System.out.println(">> Nota " + nota + " atribuída a " + nomeAluno + ".");

                        } catch (NumberFormatException e) {
                            System.out.println(">> Erro: Valor da nota inválido.");
                        }
                    } else {
                        System.out.println(">> Erro: Aluno não encontrado.");
                    }
                    break;

                case 3: // Média da Turma
                    if (mapaNotas.isEmpty()) {
                        System.out.println(">> Não há notas lançadas para calcular a média.");
                    } else {
                        double soma = 0;
                        for (Double n : mapaNotas.values()) {
                            soma += n;
                        }
                        double media = soma / mapaNotas.size();
                        System.out.printf(">> Média geral da turma (baseada em %d notas): %.2f%n",
                                mapaNotas.size(), media);
                    }
                    break;

                case 4: // Imprimir Dados e Notas
                    System.out.println("\n--- Relatório Final ---");
                    if (mapaAlunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        for (String matricula : mapaAlunos.keySet()) {
                            Aluno a = mapaAlunos.get(matricula);

                            // Verifica se existe nota para essa matrícula
                            Double nota = mapaNotas.get(matricula);
                            String textoNota = (nota == null) ? "Sem nota" : String.valueOf(nota);

                            System.out.println(a + " | Nota: " + textoNota);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();

    }
}