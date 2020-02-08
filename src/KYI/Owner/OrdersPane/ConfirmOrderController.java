package KYI.Owner.OrdersPane;

import KYI.Controllers.Connectivity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ConfirmOrderController implements Initializable {
    @FXML
    private Label nameLabel, quantityLabel, dateLabel;
    @FXML
    private Button cancelOrderButton, confirmOrderButton;
    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
