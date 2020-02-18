package KYI.Owner.StoragePane;

import KYI.Controllers.Connectivity;
import KYI.Entits.Product;
import KYI.Owner.OwnerController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class AddProductController extends OwnerController implements Initializable {
    @FXML
    private AnchorPane addProductPane;
    @FXML
    private TextField quantityTextfield,sellingPriceTextfield,buyingPriceTextfield;
    @FXML
    private ChoiceBox nameChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Button cancelButton,confirmButton;

    ArrayList<Product> products = new ArrayList<>();


    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()-> {
            String select = "SELECT p_id, name FROM products GROUP BY name";
            try {
                ResultSet result = connection.prepareStatement(select).executeQuery();
                while (result.next()) {
                    Product product = new Product(result.getInt(1), result.getString(2));
                    products.add(product);
                    nameChoiceBox.getItems().add(product.getName());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }
    public void onClickConfirm(javafx.event.ActionEvent actionEvent) throws SQLException {
        Double pricePerUnit = null;

        if (nameChoiceBox.getSelectionModel().isEmpty()){
            errorLabel.setText("Select product");
        }
        else if (quantityTextfield.getText().isEmpty()){
            errorLabel.setText("Enter quantity");
        }
        else if (!isNumber(quantityTextfield.getText())){
            errorLabel.setText("Incorrect quantity format");
        }
        else if (buyingPriceTextfield.getText().isEmpty()){
            errorLabel.setText("Enter price per unit");
        }
        else if (!isNumber(buyingPriceTextfield.getText().replace(",", "."))){
            errorLabel.setText("Incorrect price per unit format");
        } else {

            pricePerUnit = Double.parseDouble(buyingPriceTextfield.getText().replace(",", "."));
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.DOWN);

            Statement statement = connection.createStatement();

            String insert = "INSERT INTO products (name,quantity,buyingPrice) VALUES ('" + nameChoiceBox.getValue() + "'," + quantityTextfield.getText() + "," + pricePerUnit + ")";
            statement.executeLargeUpdate(insert);
            System.out.println("Product added successfully");

            Product addedProduct = new Product((String) nameChoiceBox.getValue(),Integer.parseInt(quantityTextfield.getText()),pricePerUnit);

            for (Product product : productsObservableList){
                int quantity = product.getQuantity();
                if (product.getName().equals(addedProduct.getName())){
                    quantity += addedProduct.getQuantity();
                    product.setQuantity(quantity);
                    break;
                }
            }


            Stage stage = (Stage) addProductPane.getScene().getWindow();
            stage.close();
        }
    }
    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) addProductPane.getScene().getWindow();
        stage.close();
    }
}
