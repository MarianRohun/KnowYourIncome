package KYI.Owner.OrdersPane;

import KYI.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class CancelOrderController extends Controller implements Initializable {
    @FXML
    private Label namesLabel,quantitysLabel,datesLabel;
    @FXML
    private Button cancelOrdersButton,deleteOrdersButton;

    static boolean isOrderDeleted = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
