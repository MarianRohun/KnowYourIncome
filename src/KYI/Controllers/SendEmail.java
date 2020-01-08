package KYI.Controllers;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public static void run(String ToEmail, String Subject, String Text) {
            String Msg;
            final String username = "ownerkyi@gmail.com";
            final String password = "maturita2020";
            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("ownerkyi@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(ToEmail));
                message.setSubject(Subject);
                message.setText(Text);
                Transport.send(message);
            } catch (Exception e) {
            }
    }
}


