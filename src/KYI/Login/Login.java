package KYI.Login;

import KYI.Connectivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class Login implements Initializable { //pridame alert ked niekto neuhadne heslo tak caka 30sec
    @FXML
    public TextField loginEmailField;
    @FXML
    public PasswordField loginPasswordField;
    @FXML
    public Button loginButton;
    @FXML
    public Label error;


    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();
    PreparedStatement PS = null;

    public void Login(ActionEvent event) throws Exception{

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
