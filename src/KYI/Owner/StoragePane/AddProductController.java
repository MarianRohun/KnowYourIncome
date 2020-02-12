package KYI.Owner.StoragePane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    @FXML
    private AnchorPane addProductPane;
    @FXML
    private Label quantityLabel,sellingPriceLabel;
    @FXML
    private ChoiceBox nameChoiceBox;
    @FXML
    private Button cancelButton,confirmButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onClickConfirm(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) addProductPane.getScene().getWindow();
        stage.close();
    }
    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) addProductPane.getScene().getWindow();
        stage.close();
    }
}
