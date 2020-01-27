package KYI.Owner.EmployeesPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static KYI.Owner.OwnerController.employeesObservableList;


public class AddingEmployeeController extends Controller implements Initializable {

    @FXML
    private AnchorPane addingPane;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label errorLabel;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onClickConfirm(ActionEvent actionEvent) throws SQLException {
        if (nameTextField.getText().isEmpty()){
            errorLabel.setText("Please enter the name");
        }
        if (surnameTextField.getText().isEmpty()){
            errorLabel.setText("Please enter the surname");
        }
        else if (validate(emailTextField.getText()) == false)  {
            errorLabel.setText("Please enter the correct E-MAIL");
        } else {

            Statement statement = connection.createStatement();
            String insert = "INSERT INTO users(name,surname,email,position) VALUES " +
                    "('"+nameTextField.getText()+"','" +surnameTextField.getText()+"','"+emailTextField.getText()+"','"+
                    0+"')";
            statement.executeLargeUpdate(insert);
            System.out.println("Employee added successfully");
            User employee = new User(nameTextField.getText(),surnameTextField.getText(),emailTextField.getText(),0);
            employeesObservableList.add(employee);
            connection.close();
            onClickCancel(actionEvent);

        }
    }
    public void  onClickCancel(javafx.event.ActionEvent ActionEvent){
        Stage stage = (Stage) addingPane.getScene().getWindow();
        stage.close();
    }
}

