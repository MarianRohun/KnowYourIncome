package KYI.Employee;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Order;
import KYI.Entits.Product;
import KYI.Employee.OrdersPane.OrderCardController;
import KYI.Employee.StoragePane.StorageCardController;
import KYI.Entits.Shift;
import KYI.Owner.OwnerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.Month.*;
import static java.time.Month.DECEMBER;

public class EmployeeController extends Controller implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Button ordersButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button storageButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane homePane,sidebarPane;
    @FXML
    private Pane ordersPane;
    @FXML
    private Pane storagePane;
    @FXML
    private Pane sellPane, sellFooterPane, sellHeaderPane;
    @FXML
    private Pane settingsPane;
    @FXML
    private Button chooseImageButton, saveButton, changePasswordButton,confirmPasswordButton,logoutButton;
    @FXML
    private ImageView profilePicture, sampleImage;
    @FXML
    private Label imagePath, errorLabel,changePassLabel;
    @FXML
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;
    @FXML
    private ColorPicker themePicker;
    @FXML
    private Button saveColorButton;
    @FXML
    private ListView sellListView,shiftSaleListView,ordersListView,storageListView;
    @FXML
    private Button stornoButton,confirmSellButton,confirmShiftButton,switchToShiftSalesButton,switchToSellButton,addsellButton,addProductToStorage,createProductButton;
    @FXML
    private TextField searchOrderTextfield,searchStorageTextfield;
    @FXML
    private HBox orderTableHeader,storageTableHeader;
    @FXML
    private Label monDate, tueDate, wedDate, thuDate, friDate, satDate, sunDate;


    public static Color pickedTheme ;

    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    public static ObservableList<Product> sellObservableList;
    public static ObservableList<Order> ordersObservableList;
    public static ObservableList<Product> productsObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pickedTheme = Color.valueOf(parseColor(Color.valueOf(user.getTheme())));
        logoutButton.setStyle("-fx-border-color:"+parseColor(pickedTheme)+";-fx-text-fill:"+parseColor(pickedTheme));
        homeButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        ordersButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        storageButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sellButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sidebarPane.setStyle("-fx-border-color: "+parseColor(pickedTheme));
        orderTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        storageTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        System.out.println(user.isentry());
        if (user.isentry()==true){
            sellFooterPane.toBack();
            sellHeaderPane.toBack();
            ActionEvent actionEvent = null;
            try {
                onClickHome(actionEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            changeColor(homeButton);
            homeButton.setDisable(false);
            ordersButton.setDisable(false);
            storageButton.setDisable(false);
            sellButton.setDisable(false);
        }
        else {
            sellFooterPane.toBack();
            sellHeaderPane.toBack();
            settingsPane.toFront();
            changeColor(settingsButton);
            changePasswordButton.setVisible(true);
            oldPasswordField.setVisible(false);
            newPasswordField.setVisible(false);
            confirmPasswordField.setVisible(false);
            confirmPasswordButton.setVisible(false);
            errorLabel.setVisible(false);

            changePassLabel.setText("Set your password");
            changePasswordButton.setText("Set password");

            homeButton.setDisable(true);
            ordersButton.setDisable(true);
            storageButton.setDisable(true);
            sellButton.setDisable(true);

            changePasswordButton.setOnAction(actionEvent -> {
                oldPasswordField.setVisible(true);
                newPasswordField.setVisible(true);
                confirmPasswordField.setVisible(true);
                confirmPasswordButton.setVisible(true);
                errorLabel.setVisible(true);
                changePasswordButton.setDisable(true);
                changePasswordButton.setStyle("-fx-border-color: black;");

                oldPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
                errorLabel.setText("");

            });

            confirmPasswordButton.setOnAction(actionEvent -> {

                if (oldPasswordField.getText().isEmpty()){
                    errorLabel.setText("Please enter the old password");
                }

                else if (newPasswordField.getText().isEmpty()){
                    errorLabel.setText("Please enter the new password");
                }

                else if (confirmPasswordField.getText().isEmpty()){
                    errorLabel.setText("Please confirm your new password");
                }

                else if (!confirmPasswordField.getText().equals(newPasswordField.getText())){
                    errorLabel.setText("New Passwords dont match");
                }
                else {
                    try {
                        Statement statement = connection.createStatement();
                        String select = "SELECT * FROM users WHERE password = '" + oldPasswordField.getText() + "'";
                        ResultSet result = connection.prepareStatement(select).executeQuery(select);

                        String oldPassword = "";

                        if (result.next()){
                            oldPassword = result.getString(6);
                        }

                        if (!oldPasswordField.getText().equals(oldPassword)){
                            errorLabel.setText("Wrong old password");
                        }
                        else if (oldPassword.equals(newPasswordField.getText())) {
                            errorLabel.setText("Old and New password cannot be same");
                        }
                        else {
                            String update = "UPDATE users SET password = '" + newPasswordField.getText() +
                                    "' WHERE u_id =" + user.getId();
                            statement.executeLargeUpdate(update);

                            String updateUser = "UPDATE users SET isEntry = 1 WHERE u_id = "+user.getId();
                                Statement statement2 = connection.createStatement();
                                statement2.executeLargeUpdate(updateUser);

                            errorLabel.setText("");
                            oldPasswordField.clear();
                            newPasswordField.clear();
                            confirmPasswordField.clear();

                            System.out.println("Password successfully changed");

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("INFORMATION");
                            alert.setHeaderText("PASSWORD SUCCESSFULLY CHANGED");
                            alert.showAndWait();

                            oldPasswordField.clear();
                            newPasswordField.clear();
                            confirmPasswordField.clear();

                            oldPasswordField.setVisible(false);
                            newPasswordField.setVisible(false);
                            confirmPasswordField.setVisible(false);
                            confirmPasswordButton.setVisible(false);
                            changePasswordButton.setDisable(false);
                            errorLabel.setVisible(false);
                            user.setentry(true);



                            System.out.println("pass updated");
                            changePassLabel.setText("Password successfully updated");
                            changePasswordButton.setText("Change password");
                            homeButton.setDisable(false);
                            ordersButton.setDisable(false);
                            storageButton.setDisable(false);
                            sellButton.setDisable(false);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
        nameLabel.setText(user.getName() + " " + user.getSurname());
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            profilePicture.setImage(image);
        }
        else {
            Image avatar = new Image("@../../icons/user.png");
            profilePicture.setImage(avatar);
        }

    }
    public void onClickHome(javafx.event.ActionEvent ActionEvent) throws SQLException {
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        homePane.toFront();
        changeColor(homeButton);
        sellButton.setText("Sell");

        Calendar cal = Calendar.getInstance();
        ArrayList<Calendar> calendars = new ArrayList<>();

        ArrayList<String> output = new ArrayList<>();

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                cal.add(Calendar.DATE, -6);
                break;

            case Calendar.TUESDAY:
                cal.add(Calendar.DATE, -1);
                break;

            case Calendar.WEDNESDAY:
                cal.add(Calendar.DATE, -2);
                break;

            case Calendar.THURSDAY:
                cal.add(Calendar.DATE, -3);
                break;

            case Calendar.FRIDAY:
                cal.add(Calendar.DATE, -4);
                break;

            case Calendar.SATURDAY:
                cal.add(Calendar.DATE, -5);
                break;
        }
        output.add(cal.getTime().toString());
        for (int x = 1; x < 7; x++) {
            calendars.add(cal);
            cal.add(Calendar.DATE, 1);
            output.add(cal.getTime().toString());
        }

        monDate.setText("      " + output.get(0).substring(4, 10));
        tueDate.setText("      " + output.get(1).substring(4, 10));
        wedDate.setText("      " + output.get(2).substring(4, 10));
        thuDate.setText("      " + output.get(3).substring(4, 10));
        friDate.setText("      " + output.get(4).substring(4, 10));
        satDate.setText("      " + output.get(5).substring(4, 10));
        sunDate.setText("      " + output.get(6).substring(4, 10));

        for (Calendar c : calendars) {
            LocalDate localDate = LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate();
        }

        ArrayList<Shift> shifts = new ArrayList<>();

        String select = "SELECT * FROM shifts WHERE worker = '" + user.getSurname() + "'";

        ResultSet result = connection.prepareStatement(select).executeQuery();

        while (result.next()) {
            Shift shift = new Shift(result.getString(2), result.getDouble(3), result.getDouble(4), result.getString(5), result.getString(6));
            shifts.add(shift);
        }
        int layoutX = 87;
        for (int i = 0; i < 7; i++) {
            for (Shift shift : shifts) {
                Button button = new Button();
                button.setStyle("-fx-background-color: transparent;" + "-fx-border-color: black;" + "-fx-border-width: 1 1 1 1;" + "-fx-cursor: hand;");
                button.setPrefWidth(93);
                button.setPrefHeight(60);
                button.setLayoutX(layoutX);
                button.setLayoutY(84);
                if (layoutX == shift.getLayoutX()) {
                    button.setText(shift.getShiftTime());
                    button.setStyle("-fx-background-color: " + shift.getShiftColor() + ";" + "-fx-border-color: black;" + "-fx-border-width: 1 1 1 1;" + "-fx-cursor: hand;");
                }
                homePane.getChildren().add(button);
            }

            layoutX += 92;
        }
    }


    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        ordersPane.toFront();
        changeColor(ordersButton);
        ArrayList<Order> orders = new ArrayList<>();

        String select = "SELECT orders.o_id,products.name,orders_has_products.orderedQuantity,products.buyingPrice," +
                "products.warranty,orders.dateInit, " +
                "products.p_id FROM orders_has_products JOIN products ON (products_p_id = p_id) " +
                "JOIN orders ON (orders_o_id = o_id) ORDER BY dateInit ASC";

        ResultSet result = null;
        try {
            result = connection.prepareStatement(select).executeQuery();


            while (result.next()){
                Order order = new Order(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4),
                        result.getDate(5),result.getDate(6),result.getInt(7));
                orders.add(order);
            }

            orders.removeIf(order -> order.getDateInit().before(Date.valueOf(LocalDate.now().minusDays(2))));

            ordersObservableList = FXCollections.observableArrayList();
            ordersObservableList.addAll(orders);

            ordersListView.setItems(ordersObservableList);
            ordersListView.setCellFactory(ordersListView -> new OrderCardController(this));}
        catch (SQLException e) {
            e.printStackTrace();
        }
        searchOrderTextfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                ordersObservableList.clear();
                if (searchValidate(searchOrderTextfield.getText().toLowerCase()) == null) {
                    for (Order order : orders) {
                        if (order.getName().toLowerCase().contains(searchOrderTextfield.getText().toLowerCase())) {
                            ordersObservableList.add(order);
                        }
                        else if (order.getWarranty().toString().contains(searchOrderTextfield.getText().toLowerCase())) {
                            ordersObservableList.add(order);
                        }
                        else if (order.getDateInit().toString().contains(searchOrderTextfield.getText().toLowerCase())) {
                            ordersObservableList.add(order);
                        }

                    }
                    if (ordersObservableList.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("VAROVANIE");
                        alert.setHeaderText("There is no such an Order");
                        alert.showAndWait();
                        ordersObservableList.addAll(orders);
                    }
                } else {
                    ordersObservableList.addAll(orders);
                }
            }
        });
    }
    public void onClickAddOrder(ActionEvent event) throws Exception{
        openWindow("../Employee/OrdersPane/AddOrder.fxml");
    }
    public void refreshOrdersListView(int orderID, int productID){
        ordersObservableList.removeIf(order -> order.getId() == orderID && order.getProductId() == productID);
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        ordersPane.toFront();
    }
    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        storagePane.toFront();
        changeColor(storageButton);
        sellButton.setText("Sell");
        ArrayList<Product> products = new ArrayList<>();

        String select = "SELECT p_id,name,quantity,sellingPrice FROM products GROUP BY name ORDER BY quantity";
        ResultSet result = null;
        try {
            result = connection.prepareStatement(select).executeQuery();

            while (result.next()) {
                Product product = new Product(result.getInt(1),result.getString(2), result.getInt(3), result.getDouble(4));
                products.add(product);
            }
            productsObservableList = FXCollections.observableArrayList();
            productsObservableList.addAll(products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        storageListView.setItems(productsObservableList);
        storageListView.setCellFactory(storageListView -> new StorageCardController(this));

        searchStorageTextfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                productsObservableList.clear();
                if (searchValidate(searchStorageTextfield.getText().toLowerCase()) == null) {
                    for (Product product : products) {
                        if (product.getName().toLowerCase().contains(searchStorageTextfield.getText().toLowerCase())) {
                            productsObservableList.add(product);
                        }
                    }
                    if (productsObservableList.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("VAROVANIE");
                        alert.setHeaderText("There is no such an Product");
                        alert.showAndWait();
                        productsObservableList.addAll(products);
                    }
                } else {
                    productsObservableList.addAll(products);
                }
            }
        });
    }
    public void onClickCreateProduct(ActionEvent event)throws Exception{
        openWindow("../Employee/StoragePane/CreateProduct.fxml");
    }
    public void onClickAddProductToStorage(javafx.event.ActionEvent actionEvent)throws Exception{
        openWindow("../Employee/StoragePane/AddProduct.fxml");
        productsObservableList.sort(Comparator.comparing(Product::getQuantity));
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        storagePane.toFront();
    }
    public void refreshStorageListView(int productId){
        productsObservableList.removeIf(product -> product.getId() == productId);
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        storagePane.toFront();
    }

    public void onClickSell(javafx.event.ActionEvent ActionEvent) throws SQLException{

        sellPane.getChildren().clear();
        sellPane.toFront();

        sellHeaderPane.toFront();
        sellFooterPane.toFront();
        changeColor(sellButton);

        ArrayList<Product> products = new ArrayList<>();



        String select = "SELECT * FROM products GROUP BY name ORDER BY quantity";
        ResultSet resultSet = connection.prepareStatement(select).executeQuery();
        while (resultSet.next()){
            Product product = new Product(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),
                    resultSet.getDouble(5),resultSet.getDate(6));
            if (product.getQuantity() != 0) {
                products.add(product);
            }
        }


        ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>(products.size());
        ArrayList<TextField> textFields = new ArrayList<>(products.size());

        sellPane.getChildren().clear();

        ObservableList<Product> sellObservableList = FXCollections.observableArrayList();
        sellObservableList.setAll(products);

        int layoutY = 71;


        choiceBoxes.clear();
        for (int i = 0; i < products.size(); i++){
            ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(sellObservableList.get(i).getName()));
            choiceBox.getSelectionModel().selectFirst();
            choiceBox.setPrefWidth(185);
            choiceBox.setLayoutX(150);
            choiceBox.setLayoutY(layoutY);
            choiceBox.setStyle("-fx-background-color: transparent;"+"-fx-border-width: 1 1 1 1;"+"-fx-border-color: black");
            choiceBoxes.add(choiceBox);
            sellPane.getChildren().add(choiceBox);
            layoutY += 42;
        }
        layoutY = 71;

        for (int i = 0; i < products.size(); i++){
            TextField textField = new TextField();
            textField.setLayoutX(400);
            textField.setLayoutY(layoutY);
            textFields.add(textField);
            sellPane.getChildren().add(textField);
            layoutY += 42;
        }

        confirmSellButton.setOnAction(e -> {
            ArrayList<Product> soldProducts = new ArrayList<>();

            Iterator<ChoiceBox> itChoice = choiceBoxes.iterator();
            Iterator<TextField> itText = textFields.iterator();

            while (itChoice.hasNext() && itText.hasNext()){
                String productName = String.valueOf(itChoice.next().getValue());
                String tmpquantity = itText.next().getText();
                System.out.println(productName + " " + tmpquantity);
                if (productName != null && !tmpquantity.equals("")) {
                    if (!isNumber(tmpquantity)){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("WARNING");
                        alert.setHeaderText("INCORRECT QUANTITY FORMAT");
                        alert.showAndWait();
                        break;
                    }
                    int quantity = Integer.parseInt(tmpquantity);
                    Product soldProduct = new Product(productName, quantity);
                    soldProducts.add(soldProduct);
                }
            }
            for (TextField textField : textFields){
                textField.clear();
            }

            boolean areProductSold = true;
           for (Product soldProduct : soldProducts) {
               for (Product product : products) {
                   if (product.getName().equals(soldProduct.getName())) {
                       if (product.getQuantity() > soldProduct.getQuantity()) {
                           try {
                               Statement statement = connection.createStatement();
                               String insert = "INSERT INTO soldunits (name,quantity,sellingPrice,date,cashier) VALUES ('" +
                                       soldProduct.getName() + "'," + soldProduct.getQuantity() + "," + product.getSellingPrice() + ",'" +
                                       Date.valueOf(LocalDate.now()) + "','" + user.getName() + "')";
                               statement.executeLargeUpdate(insert);
                               product.setQuantity(product.getQuantity() - soldProduct.getQuantity());
                               String update = "UPDATE products SET quantity = " + product.getQuantity() + " WHERE name = '" + product.getName() + "'";
                               statement.executeLargeUpdate(update);



                               if (LocalDate.now().getMonth().equals(JANUARY)) {
                                   OwnerController.income[0] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(FEBRUARY)) {
                                   OwnerController.income[1] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(MARCH)) {
                                   OwnerController.income[2] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(APRIL)) {
                                   OwnerController.income[3] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(MAY)) {
                                   OwnerController.income[4] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(JUNE)) {
                                   OwnerController.income[5] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(JULY)) {
                                   OwnerController.income[6] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(AUGUST)) {
                                   OwnerController.income[7] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(SEPTEMBER)) {
                                   OwnerController.income[8] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(OCTOBER)) {
                                   OwnerController.income[9] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(NOVEMBER)) {
                                   OwnerController.income[10] += soldProduct.getQuantity() * product.getSellingPrice();
                               }
                               if (LocalDate.now().getMonth().equals(DECEMBER)) {
                                   OwnerController.income[11] += soldProduct.getQuantity() * product.getSellingPrice();
                               }

                               System.out.println("Product sold successfully");
                           } catch (SQLException ex) {
                               ex.printStackTrace();
                           }
                       }

                       else {
                           areProductSold = false;
                           Alert alert = new Alert(Alert.AlertType.ERROR);
                           alert.setTitle("WARNING");
                           alert.setHeaderText("YOU DON'T HAVE ENOUGH " + product.getName().toUpperCase() + " IN STOCK");
                           alert.showAndWait();
                           break;
                       }
                   }

               }
           }

           if (areProductSold) {
               String alertContext = "";
               for (Product sold : soldProducts) {
                   alertContext += " " + sold.getName() + " " + sold.getQuantity() + "\n";
               }
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("SUCCESSFUL");
               alert.setHeaderText("You have successfully sold products:");
               alert.setContentText(alertContext);
               alert.showAndWait();
           }
        });

        stornoButton.setOnAction(e -> {
            for (TextField textField : textFields){
                textField.clear();
            }
        });

    }

    public void onClickSettings(javafx.event.ActionEvent ActionEvent) {
        changePasswordButton.setText("Change password");
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        settingsPane.toFront();
        changeColor(settingsButton);
        sellFooterPane.toBack();
        sellHeaderPane.toBack();
        settingsPane.toFront();
        changeColor(settingsButton);

        changePasswordButton.setDisable(false);
        changePasswordButton.setVisible(true);
        oldPasswordField.setVisible(false);
        newPasswordField.setVisible(false);
        confirmPasswordField.setVisible(false);
        confirmPasswordButton.setVisible(false);
        errorLabel.setVisible(false);

        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            sampleImage.setImage(image);
            imagePath.setText(user.getProfilePicture());
        }
        changePasswordButton.setOnAction(actionEvent -> {
            oldPasswordField.setVisible(true);
            newPasswordField.setVisible(true);
            confirmPasswordField.setVisible(true);
            confirmPasswordButton.setVisible(true);
            errorLabel.setVisible(true);
            changePasswordButton.setDisable(true);

            oldPasswordField.clear();
            newPasswordField.clear();
            confirmPasswordField.clear();
            errorLabel.setText("");

        });

        confirmPasswordButton.setOnAction(actionEvent -> {

            if (oldPasswordField.getText().isEmpty()){
                errorLabel.setText("Please enter the old password");
            }

            else if (newPasswordField.getText().isEmpty()){
                errorLabel.setText("Please enter the new password");
            }

            else if (confirmPasswordField.getText().isEmpty()){
                errorLabel.setText("Please confirm your new password");
            }

            else if (!confirmPasswordField.getText().equals(newPasswordField.getText())){
                errorLabel.setText("New Passwords dont match");
            }
            else {
                try {
                    Statement statement = connection.createStatement();
                    String select = "SELECT * FROM users WHERE password = '" + oldPasswordField.getText() + "'";
                    ResultSet result = connection.prepareStatement(select).executeQuery(select);

                    String oldPassword = "";

                    if (result.next()){
                        oldPassword = result.getString(6);
                    }

                    if (!oldPasswordField.getText().equals(oldPassword)){
                        errorLabel.setText("Wrong old password");
                    }
                    else if (oldPassword.equals(newPasswordField.getText())) {
                        errorLabel.setText("Old and New password cannot be same");
                    }
                    else {
                        String update = "UPDATE users SET password = '" + newPasswordField.getText() +
                                "' WHERE u_id =" + user.getId();
                        statement.executeLargeUpdate(update);

                        errorLabel.setText("");
                        oldPasswordField.clear();
                        newPasswordField.clear();
                        confirmPasswordField.clear();

                        System.out.println("Password successfully changed");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION");
                        alert.setHeaderText("PASSWORD SUCCESSFULLY CHANGED");
                        alert.showAndWait();

                        oldPasswordField.clear();
                        newPasswordField.clear();
                        confirmPasswordField.clear();

                        oldPasswordField.setVisible(false);
                        newPasswordField.setVisible(false);
                        confirmPasswordField.setVisible(false);
                        confirmPasswordButton.setVisible(false);
                        changePasswordButton.setDisable(false);
                        errorLabel.setVisible(false);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            sampleImage.setImage(image);
            imagePath.setText(user.getProfilePicture());
        } else {
            Image questionmark = new Image("@../../icons/question.png");
            sampleImage.setImage(questionmark);
        }

        saveColorButton.setOnAction(event -> {
            pickedTheme = themePicker.getValue();
            String update = "UPDATE users SET theme = '"+parseColor(pickedTheme)+"';";
            changeHoverColor(pickedTheme);
            try {
                Statement statement = connection.createStatement();
                statement.executeLargeUpdate(update);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("KYI. will reset\n to set your theme");
            alert.showAndWait();
            System.exit(1);
        });
    }
    public void onChooseImageClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null){
            String path = selectedFile.toURI().toString();
            Image imge = new Image(path);
            sampleImage.setImage(imge);
            imagePath.setText(selectedFile.getAbsolutePath());
            saveButton.setOnAction(actionEvent1 -> {
                profilePicture.setImage(imge);
                try {
                    Statement statement = connection.createStatement();
                    String update = "UPDATE users SET profilePicture ='" +path+ "' WHERE u_id = "+user.getId();
                    statement.executeLargeUpdate(update);
                    user.setProfilePicture(path);
                    System.out.println("Profile picture saved successfully");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    public void onClickLogout(javafx.event.ActionEvent ActionEvent)throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }
    public void changeColor(Button t){
        homeButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        sellButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        ordersButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        storageButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
        t.setStyle("-fx-background-color:"+parseColor(pickedTheme)+"; -fx-text-fill: white;");
    }
    public String parseColor(Color _c){
        String c=_c.toString().replace("0x","#");
        char[] a = c.toCharArray();
        c =""+a[0]+a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+"";
        return c;
    }

    public void changeHoverColor(Color color){
        BufferedReader reader = null;
        StringBuffer inputBuffer = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css"));
            inputBuffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            reader.close();
            String[] inputArray = inputBuffer.toString().split("\n");
            for (int i = 0; i < inputArray.length; i++){
                if (inputArray[i].contains("/*buttons hover*/")){
                    inputArray[i+1] = "-fx-border-color: " +parseColor(color) +";";
                }
                if (inputArray[i].contains("/*card dots*/")) {
                    inputArray[i + 1] = "-fx-border-color: " + parseColor(color) + ";";
                }
                if (inputArray[i].contains("/*window border*/")){
                    inputArray[i + 1] = "-fx-border-color: " + parseColor(color) + ";";
                }
                if (inputArray[i].contains("/*label text*/")){
                    inputArray[i + 1] = " -fx-text-fill: " + parseColor(color) + ";";
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css\\Main.css",false));
            for (String item : inputArray) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
