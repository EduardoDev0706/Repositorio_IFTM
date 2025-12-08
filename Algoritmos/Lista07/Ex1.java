import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Ex1 {

    public static class Aluno {
        private String nome;
        private String matricula;

        public Aluno(String nome, String matricula) {
            this.nome = nome;
            this.matricula = matricula;
        }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getMatricula() { return matricula; }
        public void setMatricula(String matricula) { this.matricula = matricula; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Aluno aluno = (Aluno) o;
            // Igualdade baseada na matrícula
            return Objects.equals(matricula, aluno.matricula);
        }

        @Override
        public int hashCode() {
            // Obrigatório ser consistente com o equals
            return Objects.hash(matricula);
        }

        @Override
        public String toString() {
            return "Matrícula: " + matricula + " | Nome: " + nome;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Uso do HashSet para garantir unicidade
        Set<Aluno> conjuntoAlunos = new HashSet<>();

        System.out.println("--- Cadastro de Alunos (HashSet) ---");
        System.out.println("Digite a matrícula '0' para encerrar e listar.\n");

        while (true) {
            System.out.print("Digite a matrícula do aluno: ");
            String matricula = scanner.nextLine();

            // Condição de parada
            if (matricula.equals("0")) {
                break;
            }

            System.out.print("Digite o nome do aluno: ");
            String nome = scanner.nextLine();

            Aluno novoAluno = new Aluno(nome, matricula);

            // Tenta adicionar ao conjunto
            boolean foiAdicionado = conjuntoAlunos.add(novoAluno);

            if (foiAdicionado) {
                System.out.println(">> Sucesso: Aluno cadastrado.");
            } else {
                System.out.println(">> Erro: Matrícula duplicada! Aluno ignorado.");
            }
            System.out.println("------------------------------");
        }

        scanner.close();

        System.out.println("Lista Final de Alunos");
        // Verifica se a lista está vazia antes de imprimir
        if (conjuntoAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : conjuntoAlunos) {
                System.out.println(a);
            }
        }
    }
}