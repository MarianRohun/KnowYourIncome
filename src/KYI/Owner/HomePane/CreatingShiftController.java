package KYI.Owner.HomePane;


import KYI.Controllers.Connectivity;
import KYI.Entits.Shift;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public String startShift,endShift;
    public void onClickConfirmShift(ActionEvent actionEvent) throws SQLException {
        Connectivity connectivity = new Connectivity();
        Connection connection = connectivity.getConnection();
        Statement statement = connection.createStatement();

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
            if (button.getText().isEmpty()) {
                button.setText(sinceTextField.getText() + "-" + toTextField.getText());
            button.setStyle("-fx-background-color: "+parseColor(colorPicker.getValue())+";\n"+"-fx-border-color: black;"+"-fx-font-size:13;"+"-fx-border-width: 1 1 1 1;"+"-fx-cursor: hand;");

                ArrayList<Shift> shifts = new ArrayList<>();
                Shift shift = new Shift(surname.getText(),button.getLayoutX(), button.getLayoutY(), colorPicker.getValue().toString(), button.getText());
                shifts.add(shift);
                String insert = "INSERT INTO shifts (worker,layoutX,layoutY,shiftColor,shiftTime) VALUES (?,?,?,?,?)";
                try {
                    PreparedStatement ps = connection.prepareStatement(insert);
                    ps.setString(1,shift.getWorker());
                    ps.setDouble(2, shift.getLayoutX());
                    ps.setDouble(3, shift.getLayoutY());
                    ps.setString(4, shift.getShiftColor());
                    ps.setString(5, shift.getShiftTime());
                    ps.executeLargeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else {
                String update = "UPDATE shifts SET shiftTime = '" +sinceTextField.getText() + "-" +toTextField.getText() +"',shiftColor = '"+colorPicker.getValue()+
                        "' WHERE layoutY = '" +button.getLayoutY() +"' AND layoutX = '"+button.getLayoutX() +"'";
                statement.executeLargeUpdate(update);
                button.setText(sinceTextField.getText() + "-" + toTextField.getText());
                button.setStyle("-fx-background-color: "+parseColor(colorPicker.getValue())+";\n"+"-fx-border-color: black;"+"-fx-font-size:13;"+"-fx-border-width: 1 1 1 1;"+"-fx-cursor: hand;");
            }
            Stage stage = (Stage) createShiftPane.getScene().getWindow();
            stage.close();
        }
    }

    public void onClickCancelShift(ActionEvent actionEvent) {
        Stage stage = (Stage) createShiftPane.getScene().getWindow();
        stage.close();
    }


}
