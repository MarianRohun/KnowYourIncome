package KYI.Employee.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CancelOrderController extends Controller implements Initializable {
    @FXML
    private Label nameLabel,quantityLabel,dateLabel;
    @FXML
    private Button cancelOrderButton,deleteOrderButton;
    @FXML
    private Pane confirmDeleteOrderPane;

    static boolean isOrderDeleted = false;
    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            nameLabel.setText(order.getName());
            quantityLabel.setText(order.getQuantity()+"");
            dateLabel.setText(order.getDateInit().toString());
        });
    }
    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) confirmDeleteOrderPane.getScene().getWindow();
        stage.close();
    }

    public void onClickDelete(ActionEvent actionEvent) throws SQLException {
        Statement statement = connection.createStatement();
        String delete = "DELETE FROM orders_has_products WHERE orders_o_id = "+order.getId()+ " AND products_p_id = "+order.getProductId();
        statement.executeLargeUpdate(delete);
        connection.close();
        System.out.println("Order "+order.getName()+" deleted successfully");
        isOrderDeleted = true;
        Stage stage = (Stage) confirmDeleteOrderPane.getScene().getWindow();
        stage.close();
    }
}
