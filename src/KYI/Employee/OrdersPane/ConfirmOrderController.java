package KYI.Employee.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Product;
import KYI.Owner.OwnerController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.Month.*;
import static java.time.Month.DECEMBER;

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
            System.out.println(product.getId());
            System.out.println(order.getId());
        });
    }


    public void onClickConfirmOrder(ActionEvent actionEvent) throws SQLException {
        Statement statement = connection.createStatement();
        String insert = "INSERT INTO ordersHistory (name,quantity,buyingPrice,warranty,dateInit) VALUES ('"+order.getName()+"',"+
                order.getQuantity()+","+order.getBuyingPrice()+",'"+order.getWarranty()+"','"+order.getDateInit()+"')";
        statement.executeLargeUpdate(insert);
        if (LocalDate.now().getMonth().equals(JANUARY)){
            OwnerController.expenses[0] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(FEBRUARY)){
            OwnerController.expenses[1] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(MARCH)){
            OwnerController.expenses[2] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(APRIL)){
            OwnerController.expenses[3] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(MAY)){
            OwnerController.expenses[4] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(JUNE)){
            OwnerController.expenses[5] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(JULY)){
            OwnerController.expenses[6] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(AUGUST)){
            OwnerController.expenses[7] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(SEPTEMBER)){
            OwnerController.expenses[8] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(OCTOBER)){
            OwnerController.expenses[9] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(NOVEMBER)){
            OwnerController.expenses[10] +=order.getQuantity()*order.getBuyingPrice();
        }
        if (LocalDate.now().getMonth().equals(DECEMBER)){
            OwnerController.expenses[11] +=order.getQuantity()*order.getBuyingPrice();
        }

        System.out.println("Order delivered successfully");
        String delete = "DELETE FROM orders_has_products WHERE orders_o_id = " +order.getId()+ " AND products_p_id = "+order.getProductId();
        statement.executeLargeUpdate(delete);
        String select = "SELECT * FROM products";
        ResultSet resultSet = connection.prepareStatement(select).executeQuery();
        int quantity = 0;
        while (resultSet.next()) {
            Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDate(6));
            if (product.getName().equals(order.getName())) {
                quantity = product.getQuantity();
                break;
            }
        }
        quantity += order.getQuantity();
        String update = "UPDATE products SET quantity = " +quantity+ " WHERE name = '"+order.getName()+"'";
        statement.executeLargeUpdate(update);


        isOrderConfirmed = true;
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }

    public void onClickCancelConfirmation(ActionEvent actionEvent) {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        stage.close();
    }
}
