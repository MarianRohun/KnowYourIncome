package KYI.Owner.OrdersPane;

import KYI.Entits.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.time.LocalDate;

public class HistoryOrderCardController extends ListCell<Order> {

    @FXML
    private AnchorPane orderHistoryCardAnchorPane;
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
    private FXMLLoader loader;

    @Override
    protected void updateItem(Order order, boolean empty) {
        super.updateItem(order, empty);

        if (empty || order == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("HistoryOrderCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            orderNameLabel.setText(order.getName());
            orderQuantityLabel.setText(order.getQuantity() + "pcs");
            orderBuyingPriceLabel.setText(order.getBuyingPrice() + "€");

            double sumPrice = order.getQuantity() * order.getBuyingPrice();

            orderSumPriceLabel.setText(sumPrice + "€");
            orderWarrantyLabel.setText(order.getWarranty().toString());
            dateInitLabel.setText(order.getDateInit().toString());


            setText(null);
            setGraphic(orderHistoryCardAnchorPane);
        }
    }
}

