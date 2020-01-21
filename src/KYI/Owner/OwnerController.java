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
import javafx.scene.layout.Pane;

import javax.swing.text.Style;
import java.awt.*;
import java.net.URL;
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
    private Pane settingsPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user.getposition()==1){
            positionLabel.setText("Owner: ");
        }
        else {
            positionLabel.setText("Employee: ");
        }
        nameLabel.setText(user.getSurname());
    }
    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
        storagePane.toFront();
        changeColor(storageButton);
    }

    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersPane.toFront();
        changeColor(ordersButton);

    }
    public void onClickEmployees(javafx.event.ActionEvent ActionEvent){
        employeesPane.toFront();
        changeColor(employeesButton);
    }
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

    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
        settingsPane.toFront();
        changeColor(settingsButton);
    }

    public void onClickLogout(javafx.event.ActionEvent ActionEvent) throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
        settingsPane.toFront();
    }
    public void changeColor(Button t){
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
