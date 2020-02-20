package KYI.Employee.SellPane;

import KYI.Employee.EmployeeController;
import KYI.Entits.Product;
import KYI.Entits.User;
import KYI.Owner.OwnerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class SellCardController extends ListCell<Product> {
    @FXML
    private Button cancelSellButton;
    @FXML
    private ChoiceBox productChoiceBox;
    @FXML
    private TextField quantityTextfield;
    @FXML
    private AnchorPane sellCardAnchorPane;

    private ArrayList<Product> products;
    private FXMLLoader loader;

    private EmployeeController employeeController;

    public SellCardController(EmployeeController employeeController, ArrayList<Product> products){this.employeeController = employeeController;
    this.products = products;}

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if (empty || product == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("SellCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            for (Product prod : products){
                productChoiceBox.getItems().add(prod.getName());
            }

            setText(null);
            setGraphic(sellCardAnchorPane);
        }
    }

}