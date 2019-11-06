package KYI.Fxmls;

import KYI.Controllers.Controller;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FadeawayController implements Initializable {

    @FXML
    private AnchorPane welcomePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            fadeOut();
        });
    }

    private void fadeOut(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(2000));
        fadeTransition.setNode(welcomePane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);


        fadeTransition.setOnFinished(actionEvent -> {
            loadNextScene();
        });


        fadeTransition.play();
    }

    private void loadNextScene()  {
        try {
            Stage stage = (Stage) welcomePane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
            Controller.changeScene(stage, loader, "Know Your Income");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
