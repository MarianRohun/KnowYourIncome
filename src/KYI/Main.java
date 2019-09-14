package KYI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Fadeaway.fxml"));
        primaryStage.setTitle("KnowYourIncome");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setFullScreen(true);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
