package KYI.Employee.SellPane;

import KYI.Employee.EmployeeController;
import KYI.Entits.Product;
import KYI.Entits.User;
import KYI.Owner.OwnerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SellCardController extends ListCell<Product> {
    @FXML
    private Button addCardButton,cancelSellButton;
    @FXML
    private ChoiceBox productChoiceBox;
    @FXML
    private TextField quantityTextfield;
    @FXML
    private AnchorPane sellCardAnchorPane;

    private FXMLLoader loader;

    private EmployeeController employeeController;

    public SellCardController(EmployeeController employeeController){this.employeeController = employeeController;}

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
            cancelSellButton.setOnAction(event -> {

            });
            setText(null);
            setGraphic(sellCardAnchorPane);
        }
    }

}