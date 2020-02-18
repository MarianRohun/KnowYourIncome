package KYI.Employee;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EmployeeController extends Controller implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Button ordersButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button storageButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button noteButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane homePane;
    @FXML
    private Pane ordersPane;
    @FXML
    private Pane storagePane;
    @FXML
    private Pane sellPane;
    @FXML
    private Pane notePane;
    @FXML
    private Pane settingsPane;
    @FXML
    private Button chooseImageButton, saveButton, changePasswordButton,confirmPasswordButton,logoutButton;
    @FXML
    private ImageView profilePicture, sampleImage;
    @FXML
    private Label imagePath, errorLabel,changePassLabel;
    @FXML
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;
    @FXML
    private ColorPicker themePicker;
    @FXML
    private Button saveColorButton;

    public static Color pickedTheme ;
    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pickedTheme = Color.valueOf(parseColor(Color.valueOf(user.getTheme())));
        logoutButton.setStyle("-fx-border-color:"+parseColor(pickedTheme)+";-fx-text-fill:"+parseColor(pickedTheme));
        noteButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        homeButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        ordersButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        storageButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sellButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        if (user.isentry()==true){
            homePane.toFront();
            changeColor(homeButton);
            homeButton.setDisable(false);
            ordersButton.setDisable(false);
            storageButton.setDisable(false);
            sellButton.setDisable(false);
            noteButton.setDisable(false);
        }
        else {
            settingsPane.toFront();
            changeColor(settingsButton);
            changePasswordButton.setVisible(true);
            oldPasswordField.setVisible(false);
            newPasswordField.setVisible(false);
            confirmPasswordField.setVisible(false);
            confirmPasswordButton.setVisible(false);
            errorLabel.setVisible(false);

            changePassLabel.setText("Set your password");
            changePasswordButton.setText("Set password");

            homeButton.setDisable(true);
            ordersButton.setDisable(true);
            storageButton.setDisable(true);
            sellButton.setDisable(true);
            noteButton.setDisable(true);

            changePasswordButton.setOnAction(actionEvent -> {
                oldPasswordField.setVisible(true);
                newPasswordField.setVisible(true);
                confirmPasswordField.setVisible(true);
                confirmPasswordButton.setVisible(true);
                errorLabel.setVisible(true);
                changePasswordButton.setDisable(true);
                changePasswordButton.setStyle("-fx-border-color: black;");

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

                            user.setentry(true);
                            System.out.println("pass updated");
                            changePassLabel.setText("Password successfully updated");
                            changePasswordButton.setText("Change password");
                            homeButton.setDisable(false);
                            ordersButton.setDisable(false);
                            storageButton.setDisable(false);
                            sellButton.setDisable(false);
                            noteButton.setDisable(false);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
        positionLabel.setText("Employee: ");
        nameLabel.setText(user.getSurname());
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            profilePicture.setImage(image);
        }

    }
    public void onClickHome(javafx.event.ActionEvent ActionEvent){
        homePane.toFront();
        changeColor(homeButton);
    }
    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersPane.toFront();
        changeColor(ordersButton);
    }
    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
        storagePane.toFront();
        changeColor(storageButton);
    }
    public void onClickSell(javafx.event.ActionEvent ActionEvent){
        sellPane.toFront();
        changeColor(sellButton);
    }
    public void onClickNote(javafx.event.ActionEvent ActionEvent){
        notePane.toFront();
        changeColor(noteButton);
    }
    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
        changePasswordButton.setText("Change password");
        settingsPane.toFront();
        changeColor(settingsButton);
        settingsPane.toFront();
        changeColor(settingsButton);

        changePasswordButton.setDisable(false);
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
            changePasswordButton.setStyle("-fx-border-color: black;");

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
                        user.setentry(true);
                        try {
                            String updateEntry = "UPDATE users SET isEntry ='true' WHERE u_id = "+user.getId();
                            statement.executeLargeUpdate(update);
                            System.out.println("user entered KYI");
                            statement.executeLargeUpdate(updateEntry);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        System.out.println("pass updated");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        saveColorButton.setOnAction(event -> {
            pickedTheme = themePicker.getValue();
            String update = "UPDATE users SET theme = '"+parseColor(pickedTheme)+"' WHERE u_id ="+user.getId()+";";
            changeHoverColor(pickedTheme);
            try {
                Statement statement = connection.createStatement();
                statement.executeLargeUpdate(update);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.exit(1);
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
    public void onClickLogout(javafx.event.ActionEvent ActionEvent)throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }
    public void changeColor(Button t){
        homeButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        sellButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        ordersButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        storageButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        noteButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        t.setStyle("-fx-background-color:"+parseColor(pickedTheme)+"; -fx-text-fill: white;");
    }
    public String parseColor(Color _c){
        String c=_c.toString().replace("0x","#");
        char[] a = c.toCharArray();
        c =""+a[0]+a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+"";
        return c;
    }

    public void changeHoverColor(Color color){
        BufferedReader reader = null;
        StringBuffer inputBuffer = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css"));
            inputBuffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            reader.close();
            String[] inputArray = inputBuffer.toString().split("\n");
            for (int i = 0; i < inputArray.length; i++){
                if (inputArray[i].contains("/*buttons hover*/")){
                    inputArray[i+1] = "-fx-border-color: " +parseColor(color) +";";
                }
                if (inputArray[i].contains("/*card dots*/")) {
                    inputArray[i + 1] = "-fx-border-color: " + parseColor(color) + ";";
                }
                if (inputArray[i].contains("/*window border*/")){
                    inputArray[i + 1] = "-fx-border-color: " + parseColor(color) + ";";
                }
                if (inputArray[i].contains("/*label text*/")){
                    inputArray[i + 1] = " -fx-text-fill: " + parseColor(color) + ";";
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css", false));
            for (String item : inputArray) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
