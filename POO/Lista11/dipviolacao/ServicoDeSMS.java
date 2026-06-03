package dipviolacao;

public class ServicoDeSMS implements Notificador {
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Enviando SMS para " + destinatario + " : " + mensagem);
    }
}
