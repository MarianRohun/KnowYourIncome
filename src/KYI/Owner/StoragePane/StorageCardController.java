package KYI.Owner.StoragePane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class StorageCardController implements Initializable {
    @FXML
    private Label storageNameLabel,storageQuantityLabel,storageBuyingPriceLabel,storageSumPriceLabel,storageWarrantyLabel,storageDateInitLabel,sellingPriceLabel;
    @FXML
    private Button deleteProductButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
