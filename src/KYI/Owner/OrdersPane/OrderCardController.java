package KYI.Owner.OrdersPane;

import KYI.Entits.Order;
import KYI.Owner.OwnerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;


import java.sql.Date;
import java.time.LocalDate;

import static KYI.Owner.OrdersPane.CancelOrderController.isOrderDeleted;

public class OrderCardController extends ListCell<Order> {
    @FXML
    private AnchorPane orderCardAnchorPane;
    @FXML
    private Label orderNameLabel;
    @FXML
    private Label orderQuantityLabel;
    @FXML
    private Label orderBuyingPriceLabel;
    @FXML
    private Label orderSumPriceLabel;
    @FXML
    private Label orderWarrantyLabel;
    @FXML
    private Label dateInitLabel;
    @FXML
    private Button confirmOrderButton;
    @FXML
    private Button cancelOrderButton;
    @FXML
    private FXMLLoader loader;

    private OwnerController ownerController;

    @Override
    protected void updateItem(Order order, boolean empty) {
        super.updateItem(order, empty);

        if (empty || order == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("OrderCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            confirmOrderButton.setOnAction(event -> {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            cancelOrderButton.setOnAction(event -> {
                try {
                    ownerController.openWindowOrder("../Owner/OrdersPane/CancelOrder.fxml",order);
                    if (isOrderDeleted == true) {
                        ownerController.refreshOrdersListView(order.getId());
                    }
                    isOrderDeleted = false;
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            });



            orderNameLabel.setText(order.getName());
            orderQuantityLabel.setText(Integer.toString(order.getQuantity()));
            orderBuyingPriceLabel.setText(Double.toString(order.getBuyingPrice()));

            double sumPrice = order.getQuantity() * order.getBuyingPrice();

            orderSumPriceLabel.setText(Double.toString(sumPrice));
            orderWarrantyLabel.setText(order.getWarranty().toString());
            dateInitLabel.setText(order.getDateInit().toString());


           if (Date.valueOf(LocalDate.now().plusDays(1)).equals(order.getDateInit())){
               orderCardAnchorPane.setStyle("-fx-background-color:#9fc5e8");
               confirmOrderButton.setDisable(true);
            }
           else if (Date.valueOf(LocalDate.now()).equals(order.getDateInit())){
               orderCardAnchorPane.setStyle("-fx-background-color:#b8d7a8");
           }
           else if (Date.valueOf(LocalDate.now()).after(order.getDateInit())){
               orderCardAnchorPane.setStyle("-fx-background-color:#e06666");
           }
           else {
               orderCardAnchorPane.setStyle("-fx-border-color: white;");
               confirmOrderButton.setDisable(true);
           }
            setText(null);
            setGraphic(orderCardAnchorPane);


        }
    }
}
