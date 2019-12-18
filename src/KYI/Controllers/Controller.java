package KYI.Controllers;

import KYI.Entits.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

import java.io.IOException;

public class Controller {

    public static User user = new User();

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static void changeScene(Stage stage, FXMLLoader loader, String title) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static void changeSceneUser(Stage stage, User user, FXMLLoader loader, String title) throws IOException {

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Controller controller = (Controller) loader.getController();
        controller.setUser(user);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void openWindow(String stage) throws Exception {
        Stage newstage = new Stage();
        Parent root = FXMLLoader.load(Controller.class.getResource(stage));
        Scene scene = new Scene(root);
        newstage.setScene(scene);
        newstage.setResizable(false);
        newstage.initModality(Modality.APPLICATION_MODAL);
        newstage.showAndWait();
    }


    public static String generateKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvxyz0123456789";
        String key = "";
        int length = 5;
        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for (int i = 0; i < text.length; i++) {
            key += text[i];
        }

        System.out.println(key);

        return key;
    }

    public static String sendMail(String ToEmail, String Subject, String Text) {

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
            Msg = "true";
            return Msg;

        } catch (Exception e) {
            return e.toString();
        }
    }
}





