package KYI.Owner;

import KYI.Entits.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class UserCardController extends ListCell<User> {

    @FXML
    private AnchorPane main;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label workedHoursLabel;
    @FXML
    private Button deleteUser;
    @FXML
    private FXMLLoader loader;
    @FXML
    private Label fihatralala;

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

            /*usernameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
            emailLabel.setText(user.getEmail());
            workedHoursLabel.setText(Integer.toString(user.getWorkedHours()));
            final int userId = user.getId();*/

            setText(null);
            setGraphic(main);
        }
    }
}
