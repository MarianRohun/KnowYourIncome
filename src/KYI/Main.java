package KYI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/Fadeaway.fxml"));
        primaryStage.setTitle("KnowYourIncome");
        primaryStage.getIcons().add(new Image("Logo/Icon.png"));
        primaryStage.setMinWidth(668);
        primaryStage.setMinHeight(428);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
