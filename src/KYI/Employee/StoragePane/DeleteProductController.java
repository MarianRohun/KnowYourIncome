package KYI.Employee.StoragePane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Order;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteProductController extends Controller implements Initializable {

    @FXML
    private Label productLabel;
    @FXML
    private Button cancelProductButton,deleteProductButton;
    @FXML
    private AnchorPane confirmDeleteStoragePane;

    static boolean isProductDeleted = true;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            productLabel.setText(product.getName());
        });
    }
    public void onClickDelete(javafx.event.ActionEvent event) throws SQLException {
        Statement statement = connection.createStatement();
        String select = "SELECT orders.o_id,products.name,orders_has_products.orderedQuantity,products.buyingPrice,products.warranty,orders.dateInit, " +
                "products.p_id FROM orders_has_products JOIN products ON (products_p_id = p_id) " +
                "JOIN orders ON (orders_o_id = o_id) ORDER BY dateInit ASC";

            ResultSet result = connection.prepareStatement(select).executeQuery();
            isProductDeleted=true;
            while (result.next()){
                Order order = new Order(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4),
                        result.getDate(5),result.getDate(6),result.getInt(7));
                if (order.getName().equals(product.getName())){
                    System.out.println(product.getName());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("You have upcoming order with this product");
                    alert.setContentText("You have to PROCEED/DELETE order to remove product");
                    alert.showAndWait();
                    isProductDeleted = false;
                    break;
                }
            }
            if (isProductDeleted){
                String delete = "DELETE FROM products WHERE name = '"+product.getName()+"'";
                statement.executeLargeUpdate(delete);
                connection.close();
                System.out.println("Product deleted successfully");
                Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
                stage.close();
            }
            else {
                Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
                stage.close();
            }

    }

    public void onClickCancel(javafx.event.ActionEvent event){
        isProductDeleted = false;
        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();

    }
}
