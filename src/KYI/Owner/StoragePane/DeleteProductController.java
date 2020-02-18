package KYI.Owner.StoragePane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteProductController extends Controller implements Initializable {

    @FXML
    private Label productLabel;
    @FXML
    private Button cancelProductButton,deleteProductButton;
    @FXML
    private AnchorPane confirmDeleteStoragePane;

    static boolean isProductDeleted = true;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            productLabel.setText(product.getName());
        });
    }
    public void onClickDelete(javafx.event.ActionEvent event) throws SQLException {
        Statement statement = connection.createStatement();
        String delete = "DELETE FROM products WHERE name = '"+product.getName()+"'";

        try {
            statement.executeLargeUpdate(delete);
            connection.close();
        } catch (SQLIntegrityConstraintViolationException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING");
            alert.setHeaderText("You have upcoming order with this product");
            alert.setContentText("You have to PROCEED/DELETE order to remove product");
            alert.showAndWait();
            isProductDeleted = false;
            Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
            stage.close();
        }

        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();
        System.out.println("Product "+product.getName()+" deleted successfully");
    }

    public void onClickCancel(javafx.event.ActionEvent event){
        isProductDeleted = false;
        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();

    }
}
