public class Fatura implements Pagavel {
    private final String numeroPeca;
    private final String descricaoPeca;
    private int quantidade;
    private double precoItem;

    public Fatura(String numeroPeca, String descricaoPeca, int quantidade, double precoItem) {
        this.numeroPeca = numeroPeca;
        this.descricaoPeca = descricaoPeca;
        setQuantidade(quantidade);
        setPrecoItem(precoItem);
    }

    public String getNumeroPeca() {
        return numeroPeca;
    }

    public String getDescricaoPeca() {
        return descricaoPeca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a zero.");
        }
        this.quantidade = quantidade;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(double precoItem) {
        if (precoItem < 0.0) {
            throw new IllegalArgumentException("O preço do item deve ser maior ou igual a zero.");
        }
        this.precoItem = precoItem;
    }

    @Override
    public double calculaPagamento() {
        return getQuantidade() * getPrecoItem();
    }

    @Override
    public String toString() {
        return String.format("Fatura:\nNúmero da peça: %s (%s)\nQuantidade: %d\nPreço por item: $%.2f",
                getNumeroPeca(), getDescricaoPeca(), getQuantidade(), getPrecoItem());
    }
}