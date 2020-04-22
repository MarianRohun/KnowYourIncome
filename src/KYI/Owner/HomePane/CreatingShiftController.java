package KYI.Owner.HomePane;


import KYI.Owner.OwnerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatingShiftController extends OwnerController implements Initializable {
    @FXML
    private AnchorPane createShiftPane;
    @FXML
    private Button cancelShiftButton;
    @FXML
    private Button confirmShiftButton;
    @FXML
    private TextField sinceTextField,toTextField;
    @FXML
    private Label errorShiftLabel;
    @FXML
    private ColorPicker colorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onClickConfirmShift(ActionEvent actionEvent) {
        if (sinceTextField.getText().isEmpty()){
            errorShiftLabel.setText("Insert SINCE time");
        }
        if (toTextField.getText().isEmpty()){
            errorShiftLabel.setText("Insert TO time");
        }
        if (!validateTime(sinceTextField.getText())){
            errorShiftLabel.setText("Wrong SINCE time format");
        }
        if (!validateTime(toTextField.getText())){
            errorShiftLabel.setText("Wrong TO time format");
        }
        else {
            button.setText(sinceTextField.getText() + "-" + toTextField.getText());
            button.setStyle("-fx-background-color: "+parseColor(colorPicker.getValue())+";\n"+"-fx-border-color: black;"+"-fx-font-size:13;"+"-fx-border-width: 1 1 1 1;"+"-fx-cursor: hand;");
            Stage stage = (Stage) createShiftPane.getScene().getWindow();
            stage.close();
        }
    }

    public void onClickCancelShift(ActionEvent actionEvent) {
        Stage stage = (Stage) createShiftPane.getScene().getWindow();
        stage.close();
    }


}
