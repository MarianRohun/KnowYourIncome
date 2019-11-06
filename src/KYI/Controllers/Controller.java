package KYI.Controllers;

import KYI.Entits.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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



    public static String generateKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvxyz0123456789";
        String key = "";
        int length = 5;
        Random rand = new Random();

        char[] text = new char[length];

        for (int i = 0; i < length; i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }

        for(int i = 0; i < text.length; i++){
            key += text[i];
        }

        System.out.println(key);

        return key;
    }

    public static void sendEmail(String reciepient) {
        Properties properties = new Properties();

    }
}
