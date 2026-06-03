package dipviolacao;

import java.util.List;

public class ServicoDeNotificacao {
    private final List<Notificador> notificadores;

    // Injeção de Dependência pelo construtor
    public ServicoDeNotificacao(List<Notificador> notificadores) {
        this.notificadores = notificadores;
    }

    // Notifica todos os canais injetados
    public void notificarTodos(String mensagem, String destinatario) {
        for (Notificador notificador : notificadores) {
            notificador.enviar(mensagem, destinatario);
        }
    }
}
