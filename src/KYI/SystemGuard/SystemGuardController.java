package KYI.SystemGuard;

import KYI.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import static KYI.Login.LoginController.key;

public class SystemGuardController extends Controller implements Initializable {
    @FXML
    private Label errorField;
    @FXML
    private TextField insertKeyField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private int counter =0;
    public void verifyKey(javafx.event.ActionEvent actionEvent)  throws IOException {

                if (key.equals(insertKeyField.getText())){
                    user.setloginStatus(1);
                    close();
                }
                 if(counter>=4){
                    close();
                }
                else {
                    counter++;
                    errorField.setText("Wrong key");
                }
            }
        public void close (){
            Stage stage = (Stage) insertKeyField.getScene().getWindow();
            stage.close();
        }
    }

