package KYI.Owner.SoldUnit;

import KYI.Entits.SoldUnit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;


public class SoldUnitCardController extends ListCell<SoldUnit> {
    @FXML
    private AnchorPane SoldUnitsAnchorPane;
    @FXML
    private Label soldUnitsNameLabel;
    @FXML
    private Label soldUnitsQuantityLabel;
    @FXML
    private Label soldUnitsSellingPriceLabel;
    @FXML
    private Label SoldUnitsDateLabel;
    @FXML
    private Label SoldUnitsCashierLabel;
    @FXML
    private FXMLLoader loader;

    @Override
    protected void updateItem(SoldUnit soldUnit, boolean empty) {
        super.updateItem(soldUnit, empty);

        if (empty || soldUnit == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("SoldUnitCard.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            soldUnitsNameLabel.setText(soldUnit.getName());
            soldUnitsQuantityLabel.setText(soldUnit.getQuantity()+ "pcs");
            soldUnitsSellingPriceLabel.setText(soldUnit.getSellingPrice()+ "€");
            SoldUnitsDateLabel.setText(String.valueOf(soldUnit.getDate()));
            SoldUnitsCashierLabel.setText(soldUnit.getCashier());



            setText(null);
            setGraphic(SoldUnitsAnchorPane);
          
        }
    }
}
