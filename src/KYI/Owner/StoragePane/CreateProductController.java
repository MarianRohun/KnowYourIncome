package KYI.Owner.StoragePane;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Order;
import KYI.Entits.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import static KYI.Owner.OwnerController.productsObservableList;




public class CreateProductController extends Controller implements Initializable {
    @FXML
    private Button cancelProductButton,confirmEditButton;
    @FXML
    private AnchorPane CreateProductPane;
    @FXML
    private TextField nameTextfield;
    @FXML
    private Label errorLabel;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void onClickConfirm(javafx.event.ActionEvent actionEvent) throws SQLException {
        if (nameTextfield.getText().isEmpty()){
            errorLabel.setText("Enter product name");
        }
        else if (productsObservableList.stream().anyMatch(product -> product.getName().equals(nameTextfield.getText()))) {
            errorLabel.setText("Product already exists");
        }
        else {
            Statement statement = connection.createStatement();
            String insert = "INSERT INTO products (name) VALUES ('" + nameTextfield.getText() + "')";
            statement.executeLargeUpdate(insert);

            System.out.println("Product created successfully");
            Product product = new Product(nameTextfield.getText(), 0, 0, 0);
            productsObservableList.add(product);

            Comparator<Product> productComparator = Comparator.comparing(Product::getQuantity);
            Collections.sort(productsObservableList, productComparator);

            connection.close();


            Stage stage = (Stage) CreateProductPane.getScene().getWindow();
            stage.close();
        }
    }

    public void onClickCancel(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) CreateProductPane.getScene().getWindow();
        stage.close();
    }
}
