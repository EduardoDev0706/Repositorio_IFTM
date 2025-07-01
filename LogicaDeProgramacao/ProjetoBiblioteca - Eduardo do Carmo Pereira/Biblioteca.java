import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Biblioteca {
    public static void main(String[] args) {
        
        int opcao;
        Scanner myObj = new Scanner(System.in);

        do {
            limparConsole();

        System.out.println("====================================");
        System.out.println("Bem-vindo a Biblioteca de Alexandria");
        System.out.println("===================================="); 
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Listar livros");
        System.out.println("3 - Buscar livros ");
        System.out.println("4 - Remover livros");
        System.out.println("5 - Sair");
        System.out.println();
        System.out.println("Escolha uma opção");
        
        opcao = myObj.nextInt();
        myObj.nextLine();

        switch (opcao) {
        
            case 1:
                cadastrarLivro(myObj);
                break;
            case 2:
                listarLivros();
                break;
            case 3:
                buscarLivro(myObj); 
                break;
            case 4:
                removerLivro(myObj);
                break;
            case 5:
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Opção inválida.");
        }

        if (opcao != 5) {
            System.out.print("\nPressione ENTER para continuar...");
            myObj.nextLine();
        }   

    } while (opcao != 5);
            
            myObj.close();
    }

    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Não foi possível limpar o console.");
        }
    }

    public static void cadastrarLivro(Scanner myObj) {
        try {
            System.out.print("Título: ");
            String titulo = myObj.nextLine();
    
            System.out.print("Autor: ");
            String autor = myObj.nextLine();
    
            System.out.print("Ano: ");
            String ano = myObj.nextLine();
    
            System.out.print("Nota(0 - 5): ");
            String nota = myObj.nextLine();
    
            String linha = titulo + ";" + autor + ";" + ano + ";" + nota + "\n";
    
            FileWriter fw = new FileWriter("livros.txt", true);
            fw.write(linha);
            fw.close();
    
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar o livro.");
        }
    }

    public static void listarLivros() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("livros.txt"));
            String linha;
            int contador = 1;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length == 4) {
                    System.out.println(contador++ + ". " + "Título: " + partes[0] + " | Autor: " + partes[1] + " | Ano: " + partes[2] + " | Nota: " + partes[3]);
                }
                else {
                    System.out.println("Linha mal formatada." + linha);
                }
            }

            br.close();
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo.");
        }

    }

    public static void buscarLivro(Scanner myObj) {
        try {
            System.out.println("Digite o título ou o nome do autor");
            String busca = myObj.nextLine().toLowerCase();

            BufferedReader br = new BufferedReader(new FileReader("livros.txt"));
            String linha;
            boolean encontrado = false;

            while ((linha = br.readLine()) != null) {
                if (linha.toLowerCase().contains(busca)) {
                    String[] partes = linha.split(";");
                    System.out.println("Encontrado : " + "Título: " + partes[0] + " | Autor: " + partes[1] + " | Ano: " + partes[2] + " | Nota: " + partes[3]);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("Livro não encontrado.");
            }
            
            br.close();
        }catch (IOException e) {
            System.out.println("Erro na busca.");
        }
    }

    public static void removerLivro(Scanner myObj) {
        try {
            System.out.println("Digite o nome do livro a remover: ");
            String nomeRemover = myObj.nextLine();

            File inputFile = new File("livros.txt");
            File tempFile = new File("livros_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean removido = false;

            while ((linha = reader.readLine()) != null) {
                if (!linha.contains(nomeRemover)) {
                    writer.write(linha + "\n");
                } else {
                    removido = true;
                }
            }

            reader.close();
            writer.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

            if (removido) {
                System.out.println("Livro removido com sucesso!");
            } else {
                System.out.println("Um livro com este nome não foi encontrado.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao remover o livro.");
        }
    }
}
