package KYI.Owner.StoragePane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Product;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.*;
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
    public void onClickDelete(javafx.event.ActionEvent event) throws SQLException, SQLIntegrityConstraintViolationException {
        Statement statement = connection.createStatement();
        String select = "SELECT products.name FROM orders JOIN products WHERE deliverStatus = 0";
        ResultSet resultSet = connection.prepareStatement(select).executeQuery();
        while (resultSet.next()){
            Product product = new Product (resultSet.getString(1));
            if (product.getName().equals(product.getName())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("WARNING");
                alert.setHeaderText("You have upcoming order with this product");
                alert.setContentText("You have to PROCEED/DELETE order to remove product");
                alert.showAndWait();
                isProductDeleted = false;
                break;
            }
        }

        if (isProductDeleted) {
            String delete = "DELETE FROM products WHERE name = '" + product.getName() + "'";
            statement.executeLargeUpdate(delete);
            connection.close();
            System.out.println("Product "+product.getName()+" deleted successfully");
            Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
            stage.close();
        }


    }

    public void onClickCancel(javafx.event.ActionEvent event){
        isProductDeleted = false;
        Stage stage = (Stage) confirmDeleteStoragePane.getScene().getWindow();
        stage.close();

    }
}
