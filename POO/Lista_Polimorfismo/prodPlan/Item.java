package prodPlan;

public class Item {
    Parte parte;
    int quantidade;

    public Item(Parte parte, int quantidade) {
        this.parte = parte;
        this.quantidade = quantidade;
    }

    public float calculaValor() {
        return parte.calculaValor() * quantidade;
    }

    @Override
    public String toString() {
        return "cod:" + parte.cod + " nome:" + parte.nome + " quantidade:" + quantidade + 
               " valor unitario:" + parte.valor + " valor:" + calculaValor();
    }
}