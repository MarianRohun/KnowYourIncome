package KYI.Admin;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends Controller implements Initializable {

    @FXML
    private Label test;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            test.setText("ADMIN");
        });
    }
}
