package dipviolacao;

import java.util.Arrays;
import java.util.List;

public class DipViolacao {
    
    public static void main(String[] args) {
        // 1. Criamos as implementações concretas
        Notificador email = new ServicoDeEmail();
        Notificador sms = new ServicoDeSMS();
        
        // 2. Montamos a lista de canais desejados
        List<Notificador> canais = Arrays.asList(email, sms);
        
        // 3. Injetamos as dependências na classe de alto nível
        ServicoDeNotificacao central = new ServicoDeNotificacao(canais);

        // 4. Executamos a ação
        central.notificarTodos("Sua fatura chegou!", "cliente@email.com / 11999999999");
    }
}