package KYI.Owner.StoragePane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class DeleteProductController implements Initializable {
    @FXML
    private Label productLabel;
    @FXML
    private Button cancelProductButton,deleteProductButton;
    @FXML
    private AnchorPane confirmDeleteStoragePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onClickDelete(javafx.event.ActionEvent event){
        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();
    }
    public void onClickCancel(javafx.event.ActionEvent event){
        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();

    }
}
