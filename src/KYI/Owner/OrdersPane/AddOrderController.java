package KYI.Owner.OrdersPane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Order;
import KYI.Entits.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import static KYI.Owner.OwnerController.ordersObservableList;


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
            dateInitDatePicker.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();

                    setDisable(empty || date.compareTo(today) < 0 );
                }

            });
            warrantyDatePicker.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();

                    setDisable(empty || date.compareTo(today) < 0);
                }
            });
        });
    }

    public void onClickConfirmOrder (javafx.event.ActionEvent ActionEvent) throws SQLException {
             Double pricePerUnit = null;

             if (orderNameChoiceBox.getSelectionModel().isEmpty()){
                 errorOrderLabel.setText("Select product");
             }
             else if (orderQuantityTextField.getText().isEmpty()){
                 errorOrderLabel.setText("Enter quantity");
             }
             else if (isNumber(orderQuantityTextField.getText()) == false){
                    errorOrderLabel.setText("Incorrect quantity format");
             }
             else if (orderPerUnitTextField.getText().isEmpty()){
                 errorOrderLabel.setText("Enter price per unit");
             }
             else if (!isNumber(orderPerUnitTextField.getText().replace(",", "."))){
                 errorOrderLabel.setText("Incorrect price per unit format");
             }
             else if (warrantyDatePicker.getValue() == null){
                 errorOrderLabel.setText("Select warranty date");
             }
             else if (dateInitDatePicker.getValue() == null){
                 errorOrderLabel.setText("Select date init");
             }
             else if (orderQuantityTextField.getText().equals("0")){
                 errorOrderLabel.setText("Quantity cannot be 0");
             }
             else if(orderPerUnitTextField.getText().equals("0")){
                 errorOrderLabel.setText("Price per unit cannot be 0");
             }
             else {

                 pricePerUnit = Double.parseDouble(orderPerUnitTextField.getText().replace(",","."));
                 DecimalFormat df = new DecimalFormat("#.##");
                 df.setRoundingMode(RoundingMode.DOWN);

                 Statement statement = connection.createStatement();

                 String insert = "INSERT INTO orders (dateInit) VALUES ('" + dateInitDatePicker.getValue() + "')";
                 String insertProduct = "INSERT INTO products (name,buyingPrice,warranty) VALUES ('" + orderNameChoiceBox.getValue() + "',"
                         + df.format(pricePerUnit) + ",'" + warrantyDatePicker.getValue() + "')";

                 statement.executeLargeUpdate(insert);
                 statement.executeLargeUpdate(insertProduct);

                 String select = "SELECT o_id FROM orders WHERE dateInit = '" + dateInitDatePicker.getValue() + "'";
                 ResultSet result = connection.prepareStatement(select).executeQuery();
                 int orderId = 0;
                 while (result.next()) {
                     orderId = result.getInt(1);
                 }
                 String selectproduct = "SELECT p_id FROM products WHERE name = '" + orderNameChoiceBox.getValue() + "'";
                 result = connection.prepareStatement(selectproduct).executeQuery();
                 int productId = 0;
                 while (result.next()) {
                     productId = result.getInt(1);
                 }

                 String insertMN = "INSERT INTO orders_has_products (orders_o_id, products_p_id, orderedQuantity) VALUES " +
                         "(" + orderId + "," + productId + "," + Integer.parseInt(orderQuantityTextField.getText()) + ")";
                 statement.executeLargeUpdate(insertMN);

                 System.out.println("Order added");
                 Order order = new Order(orderId,orderNameChoiceBox.getValue().toString(),Integer.parseInt(orderQuantityTextField.getText()),Double.parseDouble(df.format(pricePerUnit)),
                         Date.valueOf(warrantyDatePicker.getValue()),Date.valueOf(dateInitDatePicker.getValue()),productId);
                 ordersObservableList.add(order);



                 Comparator<Order> orderComparator = Comparator.comparing(Order::getDateInit);
                 Collections.sort(ordersObservableList,orderComparator);


                 Stage stage = (Stage) addingOrderPane.getScene().getWindow();
                 stage.close();
             }
           }


        public void onClickCancelOrder (javafx.event.ActionEvent ActionEvent){
            Stage stage = (Stage) addingOrderPane.getScene().getWindow();
            stage.close();
        }
}

