package KYI.Owner.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ConfirmOrderController extends Controller implements Initializable {
    @FXML
    private Label nameLabel, quantityLabel, dateLabel;
    @FXML
    private Button cancelConfirmationButton, confirmOrderButton;
    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    static boolean isOrderConfirmed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            nameLabel.setText(order.getName());
            quantityLabel.setText(order.getQuantity()+"");
            dateLabel.setText(order.getDateInit().toString());
        });
    }


    public void onClickConfirmOrder(ActionEvent actionEvent) throws SQLException {
        Statement statement = connection.createStatement();
        String update = "UPDATE orders_has_products SET deliverStatus = TRUE WHERE orders_o_id ="+order.getId()+" AND " +
                "products_p_id = "+order.getProductId();
        statement.executeLargeUpdate(update);
        System.out.println("Order delivered successfully");
        isOrderConfirmed = true;
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }

    public void onClickCancelConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }
}
