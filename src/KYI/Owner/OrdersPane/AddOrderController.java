package KYI.Owner.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Entits.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static KYI.Controllers.Controller.validate;
import static KYI.Owner.OwnerController.employeesObservableList;

public class AddOrderController implements Initializable {
    @FXML
    private DatePicker warrantyDatePicker,dateInitDatePicker;
    @FXML
    public TextField orderNameTextField, orderQuantityTextField, orderPerUnitTextField;
    @FXML
    private Label errorOrderLabel;
    @FXML
    private Button cancelOrderButton, confirmOrderButton;
    @FXML
    private Pane addingOrderPane;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
        public void onClickConfirmOrder (javafx.event.ActionEvent ActionEvent)throws SQLException {

            if (orderNameTextField.getText().isEmpty()) {
                errorOrderLabel.setText("Please enter the name");
            }
            if (orderQuantityTextField.getText().isEmpty()) {
                errorOrderLabel.setText("Please enter quantity");
            }
            if (orderPerUnitTextField.getText().isEmpty()) {
                errorOrderLabel.setText("Please enter Per unit");
            }
            if (warrantyDatePicker ==null){
                errorOrderLabel.setText("Select date for warranty");
            }
            if (dateInitDatePicker ==null){
                errorOrderLabel.setText("Select date for date init");
            }
            else {
                Statement statement = connection.createStatement();
                String insert = "";
                statement.executeLargeUpdate(insert);
                System.out.println("Order added");
            }
        }

        public void onClickCancelOrder (javafx.event.ActionEvent ActionEvent){
            Stage stage = (Stage) addingOrderPane.getScene().getWindow();
            stage.close();
        }

    }

