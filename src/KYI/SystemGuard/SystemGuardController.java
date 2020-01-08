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

public class SystemGuardController implements Initializable {
    @FXML
    private TextField insertKeyField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SendEmail.run(user.getEmail(), "KnowYourIncome LOGIN KEY",
                "Your LOGIN KEY: \n\n" +
                        key+
                        "\n-------------------------- \n" +
                        "Your KNOWYOURINCOME");
        System.out.println("Email sent successfully");
        System.out.println(key);

    }
    public static String key=Controller.generateKey();

    public void verifyKey(javafx.event.ActionEvent actionEvent)  throws IOException {

                if (key.equals(insertKeyField.getText())&&user.getposition()==1){
                    System.out.println("Owner logged");
                    Stage stage = (Stage) insertKeyField.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Owner/Owner.fxml"));
                    Controller.changeSceneUser(stage, user, loader, "LOGGED OWNER");
                }
                else if (key.equals(insertKeyField.getText())&&user.getposition()==2){
                         System.out.println("Employee logged");
                         Stage stage = (Stage) insertKeyField.getScene().getWindow();
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Employee/Employee.fxml"));
                         Controller.changeSceneUser(stage, user, loader, "LOGGED EMPLOYEE");
                }
                else {
                    insertKeyField.setText("wrong key");
            }
        }
        public void Close (){
            Stage stage = (Stage) insertKeyField.getScene().getWindow();
            stage.close();
        }
    }

