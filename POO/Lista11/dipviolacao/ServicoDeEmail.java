package dipviolacao;

public class ServicoDeEmail implements Notificador {
    public void enviar(String mensagem, String destinatario) {
        System.out.println("Enviando e-mail para " + destinatario + " : " + mensagem);
    }
}
