import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Bilheteria
 */
public class Bilheteria {

    private static int ingressosInseguros = 10000;

    private static AtomicInteger ingressosSeguros = new AtomicInteger(10000);

    public static void main(String[] args) {
        
        System.out.println("Iniciando a venda simultânea de 10.000 ingressos...\n");

        // Cria o pool de 100 threads
        ExecutorService executor = Executors.newFixedThreadPool(100);

        // Disparando 10.000 tarefas concorrentes
        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                // Tentativa insegura (Race Condition)
                ingressosInseguros--;

                // Tentativa segura (Thread-safe)
                ingressosSeguros.decrementAndGet();
            });
        }

        executor.shutdown();

       try {
            // Seguramos o programa principal até que todas as threads terminem o trabalho, com limite de 1 minuto.
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("Aviso: O tempo limite esgotou antes de todas as tarefas terminarem.");
            }
        } catch (InterruptedException e) {
            System.err.println("Erro: A thread principal foi interrompida enquanto aguardava.");
            // Boa prática fundamental: restaurar o status de interrupção para que outras partes do sistema saibam o que houve
            Thread.currentThread().interrupt();
        }

        System.out.println("--- RESULTADOS FINAIS ---");
        System.out.println("Esperado: 0 ingressos restantes em ambos.\n");
        System.out.println("Ingressos restantes (Variável Comum)   : " + ingressosInseguros);
        System.out.println("Ingressos restantes (Variável Atômica) : " + ingressosSeguros.get());
        
        if (ingressosInseguros != 0) {
            System.out.println("\nFALHA DETECTADA: Você teve " + ingressosInseguros + " ingressos 'fantasmas' no método comum.");
        }
    }
}