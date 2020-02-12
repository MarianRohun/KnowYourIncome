package KYI.Owner.StoragePane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditStorageController implements Initializable {
    @FXML
    private Label nameLabel,quantityLabel,sellingPriceLabel;
    @FXML
    private Button confirmEditButton,cancelEditButton;
    @FXML
    private AnchorPane confirmEditStoragePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onClickEdit(javafx.event.ActionEvent actionEvent){

        Stage stage = (Stage) confirmEditStoragePane.getScene().getWindow();
        stage.close();
    }
    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) confirmEditStoragePane.getScene().getWindow();
        stage.close();
    }
}
