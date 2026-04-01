package prodPlan;

public class Motor extends Parte {
    float potencia;
    float corrente;
    int rpm;

    public Motor(int cod, String nome, String descricao, float valor, float potencia, float corrente, int rpm) {
        super(cod, nome, descricao, valor);
        this.potencia = potencia;
        this.corrente = corrente;
        this.rpm = rpm;
    }

    @Override
    public float calculaValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return "codigo:" + cod + " nome:" + nome + " descricao:" + descricao + 
               " valor:" + valor + " potencia:" + potencia + " corrente:" + 
               corrente + " rpm:" + rpm;
    }
}