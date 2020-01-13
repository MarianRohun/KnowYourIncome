package KYI.SystemGuard;

import KYI.Controllers.Controller;
import KYI.Controllers.SendEmail;
import KYI.Entits.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static KYI.Controllers.Controller.user;

public class SystemGuardController extends Controller implements Initializable {
    @FXML
    private Label errorField;
    @FXML
    private TextField insertKeyField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    String key = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            key = generateKey();
        SendEmail.run(user.getEmail(), "KnowYourIncome LOGIN KEY",
                "Your LOGIN KEY: \n\n" +
                        key+
                        "\n-------------------------- \n" +
                        "Your KNOWYOURINCOME");
        System.out.println("Email sent successfully");
        System.out.println(key);
        });
    }


    public void verifyKey(javafx.event.ActionEvent actionEvent)  throws IOException {

                if (key.equals(insertKeyField.getText())){
                    user.setloginStatus(1);
                    close();
                }

                else {errorField.setText("Wrong key");
                    System.out.println("Wrong key");
                }

            }

        public void close (){
            Stage stage = (Stage) insertKeyField.getScene().getWindow();
            stage.close();
        }
    }

