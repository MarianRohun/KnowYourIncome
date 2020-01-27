package KYI.Owner.EmployeesPane;

import KYI.Controllers.Connectivity;
import KYI.Entits.User;
import KYI.Owner.OwnerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class UserCardController extends ListCell<User> {


    public static User user = new User();

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private AnchorPane usercardAnchorPane;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label workedHoursLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private FXMLLoader loader;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (empty || user == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("UserCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            usernameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
            emailLabel.setText(user.getEmail());
            workedHoursLabel.setText(Integer.toString(user.getWorkedHours()));
            String userEmail = user.getEmail();

            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    try {
                        Statement statement = connection.createStatement();
                        String delete = "DELETE FROM users WHERE u_id = '" + userEmail+ "'";
                        statement.executeLargeUpdate(delete);
                        System.out.println("Employee deleted successfully");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });


            setText(null);
            setGraphic(usercardAnchorPane);
        }
    }
}
