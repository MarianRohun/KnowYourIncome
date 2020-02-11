package KYI.Owner.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Product;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class AddOrderController extends Controller implements Initializable {
    @FXML
    private DatePicker warrantyDatePicker,dateInitDatePicker;
    @FXML
    private TextField orderQuantityTextField, orderPerUnitTextField;
    @FXML
    private ChoiceBox orderNameChoiceBox;
    @FXML
    private Label errorOrderLabel;
    @FXML
    private Button cancelOrderButton, confirmOrderButton;
    @FXML
    private Pane addingOrderPane;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    ArrayList<Product> products = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            String select = "SELECT p_id, name FROM products GROUP BY name";
            try {
                ResultSet result = connection.prepareStatement(select).executeQuery();
                while (result.next()){
                    Product product = new Product(result.getInt(1),result.getString(2));
                    products.add(product);
                    orderNameChoiceBox.getItems().add(product.getName());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
        public void onClickConfirmOrder (javafx.event.ActionEvent ActionEvent) throws SQLException {

            if (orderNameChoiceBox.getValue() == null) {
                errorOrderLabel.setText("Please enter the name");
            }
            if (orderQuantityTextField.getText().isEmpty()) {
                errorOrderLabel.setText("Please enter quantity");
            }
            if (orderPerUnitTextField.getText().isEmpty()) {
                errorOrderLabel.setText("Please enter Per unit");
            }
            Double pricePerUnit = null;
            if (orderPerUnitTextField.getText().contains(",")){
                pricePerUnit = Double.parseDouble(orderPerUnitTextField.getText().replaceAll(",","."));
            }
            if (warrantyDatePicker.getValue() == null){
                errorOrderLabel.setText("Select date for warranty");
            }
            if (dateInitDatePicker.getValue() ==null){
                errorOrderLabel.setText("Select date for date init");
            }
            else {

                Statement statement = connection.createStatement();
                String insert = "INSERT INTO orders (dateInit) VALUES ('" + dateInitDatePicker.getValue() +"')";
                String insertProduct = "INSERT INTO products (name,quantity,buyingPrice,warranty) VALUES ('"+orderNameChoiceBox.getValue()+"',"
                        +Integer.parseInt(orderQuantityTextField.getText())+ ","+pricePerUnit+",'"+warrantyDatePicker.getValue()+"')";

                statement.executeLargeUpdate(insert);
                statement.executeLargeUpdate(insertProduct);

                String select = "SELECT o_id FROM orders WHERE dateInit = '"+dateInitDatePicker.getValue()+"'";
                ResultSet result = connection.prepareStatement(select).executeQuery();
                int orderId = 0;
                while (result.next()){
                    orderId = result.getInt(1);
                }
                String selectproduct = "SELECT p_id FROM products WHERE name = '"+orderNameChoiceBox.getValue()+"'";
                result = connection.prepareStatement(selectproduct).executeQuery();
                int productId = 0;
                while (result.next()){
                    productId = result.getInt(1);
                }

                String insertMN = "INSERT INTO orders_has_products (orders_o_id, products_p_id, orderedQuantity) VALUES " +
                        "("+orderId+","+productId+","+Double.parseDouble(orderQuantityTextField.getText())+")";
                statement.executeLargeUpdate(insertMN);

                System.out.println("Order added");

                Stage stage = (Stage) addingOrderPane.getScene().getWindow();
                stage.close();

            }
        }

        public void onClickCancelOrder (javafx.event.ActionEvent ActionEvent){
            Stage stage = (Stage) addingOrderPane.getScene().getWindow();
            stage.close();
        }

    }

