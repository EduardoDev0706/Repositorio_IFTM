public class Vetor<T> {
    private T[] vet;
    private int tamanho;

    // Construtor que inicializa o vetor genérico
    @SuppressWarnings("unchecked")
    public Vetor(int capacidade) {
        // Em Java, não é possível instanciar um array genérico diretamente (new T[]).
        // A solução padrão é criar um array de Object e fazer o cast para T[].
        this.vet = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    // Método auxiliar para facilitar a inserção no App
    public void adicionar(T elemento) {
        if (tamanho < vet.length) {
            vet[tamanho] = elemento;
            tamanho++;
        } else {
            System.out.println("Vetor cheio!");
        }
    }

    public T getElemento(int index) {
        return vet[index];
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}