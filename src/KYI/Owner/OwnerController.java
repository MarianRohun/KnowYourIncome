package KYI.Owner;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.User;
import KYI.Owner.EmployeesPane.UserCardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Pane settingsPane;
    @FXML
    private ListView employeeListView;
    @FXML
    private Button addUserButton;

    public static ObservableList<User> employeesObservableList;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePane.toFront();
        positionLabel.setText("Owner: ");
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

    //EMPLOYEE LIST PANE
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
        employeeListView.setCellFactory(employeeListView -> new UserCardController());

    }
    public void onClickAddUser(ActionEvent actionEvent) throws Exception {
        openWindow("../Owner/EmployeesPane/AddingEmployee.fxml");
    }


    //HOMESCREEN PANE
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
