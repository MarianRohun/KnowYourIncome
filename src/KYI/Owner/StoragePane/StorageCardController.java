package KYI.Owner.StoragePane;

import KYI.Entits.Order;
import KYI.Entits.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;

import static KYI.Controllers.Controller.openWindow;


public class StorageCardController extends ListCell<Product> {

    @FXML
    private AnchorPane storageCardAnchorPane;
    @FXML
    private Label storageNameLabel,storageQuantityLabel,storageBuyingPriceLabel,sellingPriceLabel;
    @FXML
    private Button cancelProductButton,editProductButton;
    @FXML
    private FXMLLoader loader;

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if (empty || product == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("StorageCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            storageNameLabel.setText(product.getName());
            storageQuantityLabel.setText(product.getQuantity() + "pcs");
            storageBuyingPriceLabel.setText(product.getBuyingPrice() + "€");
            sellingPriceLabel.setText(product.getSellingPrice() + "€");


            setText(null);
            setGraphic(storageCardAnchorPane);
            cancelProductButton.setOnAction(event -> {
                try {
                    openWindow("../Owner/StoragePane/DeleteProduct.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            editProductButton.setOnAction(event -> {
                try {
                    openWindow("../owner/StoragePane/EditStorage.fxml");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }
}
