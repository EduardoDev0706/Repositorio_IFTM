package prodPlan;

public class Teste {

    public static void listaPartes(String titulo, Parte[] partes) {
        System.out.println(titulo);
        for (Parte p : partes) {
            if (p != null) System.out.println(p.toString());
        }
    }

    public static Parte[] criaPartes() {
        Parte[] p = new Parte[8];
        p[0] = new Motor(112, "motor m112", "motor de avanco do cabecote", 100.0f, 1.2f, 1.1f, 1250);
        p[1] = new Motor(114, "motor m114", "motor auxiliar", 60.0f, 0.6f, 0.8f, 1250);
        p[2] = new Motor(111, "motor m111", "motor de ventilador", 70.0f, 1.0f, 1.0f, 3000);
        p[3] = new Motor(110, "motor m110", "motor principal", 120.0f, 1.8f, 1.5f, 1250);
        p[4] = new Parafuso(231, "parafuso p1", "parafuso de fixacao do cabecote", 2.5f, 100.0f, 8.0f);
        p[5] = new Parafuso(232, "parafuso p2", "parafuso de fixacao do motor", 2.5f, 80.0f, 6.0f);
        p[6] = new Parafuso(233, "parafuso p3", "parafuso de fixacao do ventilador", 2.0f, 60.0f, 6.0f);
        p[7] = new Parafuso(234, "parafuso p4", "parafuso de uso geral", 3.0f, 120.0f, 12.0f);
        return p;
    }

    public static Item[] criaItens(Parte[] partes) {
        Item[] itens = new Item[4];
        // Mapeia as partes específicas conforme a saída solicitada
        itens[0] = new Item(partes[0], 10); // motor m112
        itens[1] = new Item(partes[5], 50); // parafuso p2
        itens[2] = new Item(partes[7], 30); // parafuso p4
        itens[3] = new Item(partes[2], 5);  // motor m111
        return itens;
    }

    public static void listaItens(String titulo, Item[] itens) {
        System.out.println("\n" + titulo);
        float totalGeral = 0;
        for (Item it : itens) {
            if (it != null) {
                System.out.println(it.toString());
                totalGeral += it.calculaValor();
            }
        }
        System.out.println("Valor total:" + totalGeral);
    }

    public static void main(String[] args) {
        Parte[] estoque = criaPartes();
        listaPartes("*** Partes utilizadas na producao ****", estoque);
        
        Item[] pedido = criaItens(estoque);
        listaItens("*** Itens solicitados ***", pedido);
    }
}