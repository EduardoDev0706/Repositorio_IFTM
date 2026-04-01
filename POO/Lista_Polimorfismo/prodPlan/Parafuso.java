package prodPlan;

public class Parafuso extends Parte {
    float comprimento;
    float diametro;

    public Parafuso(int cod, String nome, String descricao, float valor, float comprimento, float diametro) {
        super(cod, nome, descricao, valor);
        this.comprimento = comprimento;
        this.diametro = diametro;
    }

    @Override
    public float calculaValor() {
        return this.valor;
    }

    @Override
    public String toString() {
        return "codigo:" + cod + " nome:" + nome + " descricao:" + descricao + 
               " valor:" + valor + " comprimento:" + comprimento + " diametro:" + diametro;
    }
}