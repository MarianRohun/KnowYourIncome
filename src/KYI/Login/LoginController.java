package KYI.Login;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Controllers.SendEmail;
import KYI.Entits.User;
;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;




public class LoginController extends Controller implements Initializable { //pridame alert ked niekto neuhadne heslo tak caka 30sec

    @FXML
    private AnchorPane loginPane;
    @FXML
    private TextField loginEmailField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label error;


    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();
    public static String key;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickLogin(javafx.event.ActionEvent actionEvent) throws Exception {
        if (validate(loginEmailField.getText()) == false)  {
            error.setText("Please enter the correct E-MAIL");
        } else if (loginPasswordField.getText().isEmpty()) {
            error.setText("Please enter your Password");
        } else {

            String select = "SELECT * FROM users WHERE email = '" + loginEmailField.getText() + "' AND password = '"
                    + loginPasswordField.getText() + "'";
            ResultSet result = connection.prepareStatement(select).executeQuery();

            if (result.next()) {
                User user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getInt(5), result.getString(6), result.getInt(7));
                System.out.println(user.getEmail());

                new Thread(() -> {
            key = generateKey();
            SendEmail.send(user.getEmail(), "KnowYourIncome LOGIN KEY",
                    "Your LOGIN KEY: \n\n" +
                            key+
                            "\n-------------------------- \n" +
                            "Your KNOWYOURINCOME");
            System.out.println("Email sent successfully");
            System.out.println(key);
            }).start();

                Controller.openWindowUser("../SystemGuard/SystemGuard.fxml", user);

                if (user.getloginStatus() == 1) {
                    if (user.getposition() == 1) {
                        System.out.println("Owner logged");

                        Stage stage = (Stage) loginEmailField.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Owner/Owner.fxml"));
                        Controller.changeSceneUser(stage, user, loader, "LOGGED OWNER");
                    } else {
                        System.out.println("Employee logged");

                        Stage stage = (Stage) loginEmailField.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Employee/Employee.fxml"));
                        Controller.changeSceneUser(stage, user, loader, "LOGGED EMPLOYEE");
                    }
                }

                }
            else {
                loginPasswordField.clear();
                loginEmailField.clear();
                error.setText("Wrong username or password");
            }
                }

            }
    }


