package KYI.Owner.StoragePane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CreateProductController implements Initializable {
    @FXML
    private Button cancelProductButton,confirmEditButton;
    @FXML
    private AnchorPane CreateProductPane;
    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onClickConfirm(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) CreateProductPane.getScene().getWindow();
        stage.close();
    }
    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) CreateProductPane.getScene().getWindow();
        stage.close();
    }
}
