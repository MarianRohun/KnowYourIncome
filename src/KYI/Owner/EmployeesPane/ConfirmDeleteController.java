package KYI.Owner.EmployeesPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class ConfirmDeleteController extends Controller implements Initializable {

    @FXML
    private AnchorPane confirmDeletePane;
    @FXML
    private Button deleteButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();


    static boolean isDeleted = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            usernameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
            emailLabel.setText(user.getEmail());
        });
    }

    public void onClickDelete(ActionEvent actionEvent) throws SQLException {
        Statement statement = connection.createStatement();
        String delete = "DELETE FROM users WHERE u_id = "+user.getId();
        statement.executeLargeUpdate(delete);
        connection.close();
        System.out.println("Employee "+user.getId()+"deleted successfully");
        isDeleted = true;
        Stage stage = (Stage) confirmDeletePane.getScene().getWindow();
        stage.close();

    }

    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmDeletePane.getScene().getWindow();
        stage.close();
    }
}
