package KYI.Controllers;

import KYI.Entits.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.Random;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Controller controller = loader.getController();
        controller.setUser(user);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add("D:\\KnowYourIncome\\src\\KYI\\Css\\Main.css");
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
    public static void openWindowUser(String stage,User user) throws Exception {
        Stage newstage = new Stage();
        Parent root = FXMLLoader.load(Controller.class.getResource(stage));
        Scene scene = new Scene(root);
        newstage.setScene(scene);
        Controller controller = new Controller();
        controller.setUser(user);
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

    public static final Pattern VALIDEMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        if (emailStr.isEmpty()) return false;
        Matcher matcher = VALIDEMAIL.matcher(emailStr);
        return matcher.find();
    }
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}





