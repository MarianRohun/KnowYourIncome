package KYI.Owner.StoragePane;

import KYI.Entits.Order;
import KYI.Entits.Product;
import KYI.Owner.OwnerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;


import static KYI.Controllers.Controller.openWindowProduct;
import static KYI.Owner.StoragePane.DeleteProductController.isProductDeleted;
import static KYI.Owner.StoragePane.EditStorageController.isProductChanged;


public class StorageCardController extends ListCell<Product> {

    @FXML
    private AnchorPane storageCardAnchorPane;
    @FXML
    private Label storageNameLabel,storageQuantityLabel,storageBuyingPriceLabel,sellingPriceLabel;
    @FXML
    private Button cancelProductButton,editProductButton;
    @FXML
    private FXMLLoader loader;

    private OwnerController ownerController;
    public StorageCardController(OwnerController ownerController){this.ownerController = ownerController;}


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
            sellingPriceLabel.setText(product.getSellingPrice() + "€");

            if (product.getQuantity() < 20){
                storageCardAnchorPane.setStyle("-fx-background-color: #ffae19");
            }
            if (product.getQuantity() < 5){
                storageCardAnchorPane.setStyle("-fx-background-color: #e06666");
            }



            setText(null);
            setGraphic(storageCardAnchorPane);
            cancelProductButton.setOnAction(event -> {
                try {
                    openWindowProduct("../Owner/StoragePane/DeleteProduct.fxml",product);
                   if (isProductDeleted){
                       ownerController.refreshStorageListView(product.getId());
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            editProductButton.setOnAction(event -> {
                try {
                    openWindowProduct("../owner/StoragePane/EditStorage.fxml",product);
                    if (isProductChanged) {
                        ActionEvent ActionEvent = null;
                        ownerController.onClickStorage(ActionEvent);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }
}
