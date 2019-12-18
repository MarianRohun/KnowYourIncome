package KYI.Login;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.User;
;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable { //pridame alert ked niekto neuhadne heslo tak caka 30sec

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickLogin(javafx.event.ActionEvent actionEvent) throws Exception {
        if (loginEmailField.getText().isEmpty()) {
            error.setText("Please enter your E-MAIL");
        } else if (loginPasswordField.getText().isEmpty()) {
            error.setText("Please enter your Password");
        } else {
            Statement statement = connection.createStatement();
            String select = "SELECT * FROM users WHERE email = '" + loginEmailField.getText() + "' AND password = '"
                    + loginPasswordField.getText() + "'";
            ResultSet result = connection.prepareStatement(select).executeQuery();

            if (result.next()) {
                User user = new User(result.getInt(1), result.getString(2),
                        result.getString(3), result.getString(4),
                        result.getInt(5));
                System.out.println(user.getEmail());

                Controller.sendMail(user.getEmail(), "KnowYourIncome LOGIN KEY",
                        "Your LOGIN KEY: \n\n" +
                                Controller.generateKey() +
                                "\n-------------------------- \n" +
                                "Your KNOWYOURINCOME"
                );


                Controller.openWindow("../SystemGuard/SystemGuard.fxml");

            }



                }

                System.out.println("Email sent successfully");



               /* if (user.getposition() == 1) {
                    System.out.println("Owner logged");

                    Stage stage = (Stage) loginEmailField.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Owner/Owner.fxml"));
                    Controller.changeSceneUser(stage, user, loader, "LOGGED OWNER");

                } else {
                    System.out.println("Employee logged");

                    Stage stage = (Stage) loginEmailField.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Employee/Employee.fxml"));
                    Controller.changeSceneUser(stage, user, loader, "LOGGED EMPLOYEE");
                } */

            }
    }


