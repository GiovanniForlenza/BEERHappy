/*
package control;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public SendEmail(){
    }
    public void SendingEmail(String email, String messaggio, String oggetto) throws MessagingException {
        String mittente="beerhappy@gmail.com";
        String host ="smtp.ngi.it";
        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);
        props.put("mail.smtp.host", "smtp.tre.it");
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(mittente));
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject(oggetto);
        message.setText(messaggio);
        Transport.send(message);
    }
}
*/
