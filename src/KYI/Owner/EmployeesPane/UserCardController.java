package KYI.Owner.EmployeesPane;

import KYI.Entits.User;
import KYI.Owner.OwnerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import static KYI.Owner.EmployeesPane.ConfirmDeleteController.isDeleted;


public class UserCardController extends ListCell<User> {

    private OwnerController ownerController;
    public static User user = new User();

    public UserCardController(OwnerController ownerController) {
        this.ownerController = ownerController;
    }

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
            int userID = user.getId();
            deleteButton.setOnAction(actionEvent -> {

                try {
                    ownerController.openWindowUser("../Owner/EmployeesPane/ConfirmDelete.fxml",user);
                    if (isDeleted == true) {
                        ownerController.refreshListView(user.getId());
                    }
                    isDeleted = false;
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            });


            setText(null);
            setGraphic(usercardAnchorPane);
        }
    }
}
