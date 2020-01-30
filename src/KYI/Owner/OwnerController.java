package KYI.Owner;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.User;
import KYI.Owner.EmployeesPane.UserCardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class OwnerController extends Controller implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Button storageButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button soldunitsButton;
    @FXML
    private Button incomeButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane homePane;
    @FXML
    private Pane employeesPane;
    @FXML
    private Pane ordersPane;
    @FXML
    private Pane storagePane;
    @FXML
    private Pane soldunitsPane;
    @FXML
    private Pane incomePane;
    @FXML
    private Pane notePane;
    @FXML
    private Pane settingsPane;
    @FXML
    private ListView employeeListView;
    @FXML
    private Button addUserButton;
    @FXML
    private TextField filterField;
    @FXML
    private Button noteButton;
    @FXML
    private Button chooseImageButton, saveButton, changePasswordButton, confirmPasswordButton;
    @FXML
    private ImageView profilePicture, sampleImage;
    @FXML
    private Label imagePath, errorLabel;
    @FXML
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;



    public static ObservableList<User> employeesObservableList;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    User user = getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePane.toFront();
        positionLabel.setText("Owner: ");
        nameLabel.setText(user.getSurname());
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            profilePicture.setImage(image);
        }
        else {
            Image avatar = new Image("@../../icons/user.png");
            profilePicture.setImage(avatar);
        }
    }

    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
        storagePane.toFront();
        changeColor(storageButton);
    }

    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersPane.toFront();
        changeColor(ordersButton);
    }
    //===============================================================
    //EMPLOYEE LIST PANE
    //================================================================
    public void onClickEmployees(javafx.event.ActionEvent ActionEvent) throws IOException, SQLException {
        employeesPane.toFront();
        changeColor(employeesButton);

        ArrayList<User> employees = new ArrayList<>();


        String select = "SELECT * FROM users";
        ResultSet result = connection.prepareStatement(select).executeQuery();

        while (result.next()){
            User user = new User(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5),
                    result.getInt(7));
            if (user.getposition() == 0) {
                employees.add(user);
            }
        }
        employeesObservableList = FXCollections.observableArrayList();
        employeesObservableList.addAll(employees);

        employeeListView.setItems(employeesObservableList);
        employeeListView.setCellFactory(employeeListView -> new UserCardController(this));

        FilteredList<User>filteredData = new FilteredList<>(employeesObservableList, b -> true);
        
        }

    public void refreshListView(int userID){
        employeesObservableList.removeIf(employee -> employee.getId() == userID);
        employeesPane.toFront();
    }
    public void onClickAddUser(ActionEvent actionEvent) throws Exception {
        openWindow("../Owner/EmployeesPane/AddingEmployee.fxml");
    }
    //============================================================
    //HOMESCREEN PANE
    //============================================================
    public void onClickHome(javafx.event.ActionEvent ActionEvent){
        homePane.toFront();
        changeColor(homeButton);
    }

    public void onClickSoldunits(javafx.event.ActionEvent ActionEvent){
        soldunitsPane.toFront();
        changeColor(soldunitsButton);
    }
    public void onClickIncome(javafx.event.ActionEvent ActionEvent){
        incomePane.toFront();
        changeColor(incomeButton);
    }
    public void onClickNote(javafx.event.ActionEvent ActionEvent){
        notePane.toFront();
        changeColor(noteButton);
    }
    //================================================================
    //SETTINGS PANE
    //===============================================================
    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
        settingsPane.toFront();
        changeColor(settingsButton);

        changePasswordButton.setVisible(true);
        oldPasswordField.setVisible(false);
        newPasswordField.setVisible(false);
        confirmPasswordField.setVisible(false);
        confirmPasswordButton.setVisible(false);
        errorLabel.setVisible(false);

        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            sampleImage.setImage(image);
            imagePath.setText(user.getProfilePicture());
        }

        else {
            Image questionmark = new Image("@../../icons/question.png");
            sampleImage.setImage(questionmark);
        }

        changePasswordButton.setOnAction(actionEvent -> {
            oldPasswordField.setVisible(true);
            newPasswordField.setVisible(true);
            confirmPasswordField.setVisible(true);
            confirmPasswordButton.setVisible(true);
            errorLabel.setVisible(true);
            changePasswordButton.setDisable(true);

            oldPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
            errorLabel.setText("");

        });

        confirmPasswordButton.setOnAction(actionEvent -> {

            if (oldPasswordField.getText().isEmpty()){
                errorLabel.setText("Please enter the old password");
            }

            else if (newPasswordField.getText().isEmpty()){
                errorLabel.setText("Please enter the new password");
            }

            else if (confirmPasswordField.getText().isEmpty()){
                errorLabel.setText("Please confirm your new password");
            }

            else if (!confirmPasswordField.getText().equals(newPasswordField.getText())){
                errorLabel.setText("New Passwords dont match");
            }
            else {
                try {
                    Statement statement = connection.createStatement();
                    String select = "SELECT * FROM users WHERE password = '" + oldPasswordField.getText() + "'";
                    ResultSet result = connection.prepareStatement(select).executeQuery(select);

                    String oldPassword = "";

                    if (result.next()){
                        oldPassword = result.getString(6);
                    }

                    if (!oldPasswordField.getText().equals(oldPassword)){
                        errorLabel.setText("Wrong old password");
                    }
                    else if (oldPassword.equals(newPasswordField.getText())) {
                            errorLabel.setText("Old and New password cannot be same");
                        }
                        else {
                            String update = "UPDATE users SET password = '" + newPasswordField.getText() +
                                    "' WHERE u_id =" + user.getId();
                            statement.executeLargeUpdate(update);

                            errorLabel.setText("");
                            oldPasswordField.clear();
                            newPasswordField.clear();
                            confirmPasswordField.clear();

                            System.out.println("Password successfully changed");

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("INFORMATION");
                            alert.setHeaderText("PASSWORD SUCCESSFULLY CHANGED");
                            alert.showAndWait();

                            oldPasswordField.clear();
                            newPasswordField.clear();
                            confirmPasswordField.clear();

                            oldPasswordField.setVisible(false);
                            newPasswordField.setVisible(false);
                            confirmPasswordField.setVisible(false);
                            confirmPasswordButton.setVisible(false);
                            changePasswordButton.setDisable(false);
                            errorLabel.setVisible(false);
                        }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
    }
    public void onChooseImageClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null){
            String path = selectedFile.toURI().toString();
            Image imge = new Image(path);
            sampleImage.setImage(imge);
            imagePath.setText(selectedFile.getAbsolutePath());
            saveButton.setOnAction(actionEvent1 -> {
                profilePicture.setImage(imge);
                try {
                    Statement statement = connection.createStatement();
                    String update = "UPDATE users SET profilePicture ='" +path+ "' WHERE u_id = "+user.getId();
                    statement.executeLargeUpdate(update);
                    user.setProfilePicture(path);
                    System.out.println("Profile picture saved successfully");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    //==================================================================================
    //END OF PANE CONTROLLERS
    //==================================================================================
    public void onClickLogout(javafx.event.ActionEvent ActionEvent) throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }

    public void changeColor(Button t){
        noteButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        homeButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        employeesButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        ordersButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        storageButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        soldunitsButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        incomeButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        settingsButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        t.setStyle("-fx-background-color:#b38b4d; -fx-text-fill: white;");
    }

}
