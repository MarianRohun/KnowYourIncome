package KYI.Employee.SellPane;

import KYI.Employee.EmployeeController;
import KYI.Entits.Product;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;


public class SellCardController extends ListCell<Product> {

    @FXML
    private Button cancelSellButton;
    @FXML
    private ChoiceBox<String> productChoiceBox;
    @FXML
    private TextField quantityTextfield;
    @FXML
    private AnchorPane sellCardAnchorPane;
    @FXML
    private FXMLLoader loader;
    private ArrayList<Product> products;

    public static ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();
    public static ArrayList<TextField> textFields = new ArrayList<>();


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

            productChoiceBox.getSelectionModel().clearSelection();
            productChoiceBox.getItems().clear();

            choiceBoxes.add(productChoiceBox);
            textFields.add(quantityTextfield);

            for (Product prod : products){
                productChoiceBox.getItems().add(prod.getName());
            }




            setText(null);
            setGraphic(sellCardAnchorPane);
        }
    }

}