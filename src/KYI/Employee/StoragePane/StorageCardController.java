package KYI.Employee.StoragePane;

import KYI.Employee.EmployeeController;
import KYI.Entits.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import static KYI.Controllers.Controller.openWindowProduct;
import static KYI.Employee.StoragePane.DeleteProductController.isProductDeleted;
import static KYI.Employee.StoragePane.EditStorageController.isProductChanged;


public class StorageCardController extends ListCell<Product> {

    @FXML
    private AnchorPane storageCardAnchorPane;
    @FXML
    private Label storageNameLabel,storageQuantityLabel,storageBuyingPriceLabel,sellingPriceLabel;
    @FXML
    private Button cancelProductButton,editProductButton;
    @FXML
    private FXMLLoader loader;

    private EmployeeController employeeController;
    public StorageCardController(EmployeeController employeeController){this.employeeController = employeeController;}


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

            if (product.getQuantity() >= 20){
                storageCardAnchorPane.setStyle("-fx-border-style: dotted;");
            }
            if (product.getQuantity() < 20){
                storageCardAnchorPane.setStyle("-fx-background-color: #ffae19;"+"-fx-border-style: none;"+"-fx-border-color: none;");
            }
            if (product.getQuantity() < 5) {
                storageCardAnchorPane.setStyle("-fx-background-color: #e06666;"+"-fx-border-style: none;"+"-fx-border-color: none;");
            }

            setText(null);
            setGraphic(storageCardAnchorPane);
            cancelProductButton.setOnAction(event -> {
                try {
                    openWindowProduct("../Employee/StoragePane/DeleteProduct.fxml",product);
                   if (isProductDeleted) {
                       employeeController.refreshStorageListView(product.getId());
                   }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            editProductButton.setOnAction(event -> {
                try {
                    openWindowProduct("../Employee/StoragePane/EditStorage.fxml",product);
                    if (isProductChanged) {
                        ActionEvent ActionEvent = null;
                        employeeController.onClickStorage(ActionEvent);
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }
}
