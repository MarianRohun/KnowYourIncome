package KYI.Owner;

import KYI.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
<<<<<<< Updated upstream
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
=======
import javafx.scene.layout.Pane;

import javax.swing.text.Style;
import java.awt.*;
>>>>>>> Stashed changes
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
    private Pane settingsPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
<<<<<<< Updated upstream

        storageButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
=======
        storagePane.toFront();
    }
    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersPane.toFront();
>>>>>>> Stashed changes
    }

    public void onClickEmployees(javafx.event.ActionEvent ActionEvent){
<<<<<<< Updated upstream

        employeesButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
=======
        employeesPane.toFront();
>>>>>>> Stashed changes
    }
    public void onClickHome(javafx.event.ActionEvent ActionEvent){
<<<<<<< Updated upstream
        homeButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickSoldunits(javafx.event.ActionEvent ActionEvent){
        soldunitsButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
=======
        homePane.toFront();
    }
    public void onClickSoldunits(javafx.event.ActionEvent ActionEvent){
        soldunitsPane.toFront();
>>>>>>> Stashed changes
    }

    public void onClickIncome(javafx.event.ActionEvent ActionEvent){
<<<<<<< Updated upstream

        incomeButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
=======
        incomePane.toFront();
>>>>>>> Stashed changes
    }

    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
<<<<<<< Updated upstream
        settingsButton.setStyle("-fx-background-color: #b38b4d; -fx-text-fill: white;");
    }

    public void onClickLogout(javafx.event.ActionEvent ActionEvent) throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
=======
        settingsPane.toFront();
    }public void onClickLogout(javafx.event.ActionEvent ActionEvent){

>>>>>>> Stashed changes
    }

}
