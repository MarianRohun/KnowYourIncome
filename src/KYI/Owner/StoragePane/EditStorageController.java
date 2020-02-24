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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ResourceBundle;

import static KYI.Owner.OwnerController.productsObservableList;

public class EditStorageController extends Controller implements Initializable {
    @FXML
    private TextField nameTextfield, sellingPriceTextfield,quantityTextfield;
    @FXML
    private Button confirmEditButton,cancelEditButton;
    @FXML
    private AnchorPane confirmEditStoragePane;
    @FXML
    private Label errorLabel;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();
    public int quantityTemp;
    static boolean isProductChanged = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            nameTextfield.setText(product.getName());
            quantityTextfield.setText(String.valueOf(product.getQuantity()));
            sellingPriceTextfield.setText(String.valueOf(product.getSellingPrice()));
            quantityTemp=Integer.parseInt(quantityTextfield.getText());
        });

    }
    public void onClickEdit(javafx.event.ActionEvent actionEvent) throws SQLException {
        Double sellingPrice = null;

        if (nameTextfield.getText().isEmpty()){
            errorLabel.setText("Enter product name");
        }
        else if (quantityTextfield.getText().isEmpty()){
            errorLabel.setText("Enter quantity");
        }
        else if (isNumber(quantityTextfield.getText()) == false){
            errorLabel.setText("Incorrect quantity format");
        }
        else if (sellingPriceTextfield.getText().isEmpty()){
            errorLabel.setText("Enter selling price");
        }  else if (!isNumber(sellingPriceTextfield.getText().replace(",", "."))){
            errorLabel.setText("Incorrect selling price format");
        } else {
            sellingPrice = Double.parseDouble(sellingPriceTextfield.getText().replace(",","."));
            String update = "UPDATE products SET name = '" +nameTextfield.getText()+"',quantity ="+Integer.parseInt(quantityTextfield.getText())+",sellingPrice ="+sellingPrice
                    +" WHERE name = '"+product.getName()+"'";

            Statement statement = connection.createStatement();
            statement.executeLargeUpdate(update);
            System.out.println("Product updated successfully");
            isProductChanged = true;



            Stage stage = (Stage) confirmEditStoragePane.getScene().getWindow();
            stage.close();

        }
    }
    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) confirmEditStoragePane.getScene().getWindow();
        stage.close();
    }
}
