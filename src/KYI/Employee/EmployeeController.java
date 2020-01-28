package KYI.Employee;

import KYI.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePane.toFront();
        positionLabel.setText("Employee: ");
        nameLabel.setText(user.getSurname());
        if (user.getPassword()==null){
            settingsPane.toFront();
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
        settingsPane.toFront();
        changeColor(settingsButton);
    }
    public void onClickLogout(javafx.event.ActionEvent ActionEvent)throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }
    public void changeColor(Button t){
        homeButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        sellButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        ordersButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        storageButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        noteButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        settingsButton.setStyle("-fx-background-color:white;-fx-text-fill: #b38b4d;");
        t.setStyle("-fx-background-color:#b38b4d; -fx-text-fill: white;");
    }

}
