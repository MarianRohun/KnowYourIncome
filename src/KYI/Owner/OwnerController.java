package KYI.Owner;

import KYI.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OwnerController extends Controller implements Initializable {

    @FXML
    private Label nameLabel;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onClickStorage(javafx.event.ActionEvent ActionEvent){

        storageButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickEmployees(javafx.event.ActionEvent ActionEvent){

        employeesButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickHome(javafx.event.ActionEvent ActionEvent){
        homeButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickSoldunits(javafx.event.ActionEvent ActionEvent){
        soldunitsButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickIncome(javafx.event.ActionEvent ActionEvent){

        incomeButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
        settingsButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickLogout(javafx.event.ActionEvent ActionEvent) throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }

}
