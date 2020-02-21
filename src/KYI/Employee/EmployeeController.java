package KYI.Employee;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Employee.SellPane.SellCardController;
import KYI.Entits.Order;
import KYI.Entits.Product;
import KYI.Employee.OrdersPane.OrderCardController;
import KYI.Employee.StoragePane.StorageCardController;
import KYI.Entits.SellCard;
import KYI.Main;
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
import java.net.PortUnreachableException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static KYI.Employee.SellPane.SellCardController.*;

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
    private Button noteButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane homePane,sidebarPane;
    @FXML
    private Pane ordersPane;
    @FXML
    private Pane storagePane;
    @FXML
    private Pane sellPane,ShiftSalesPane;
    @FXML
    private Pane notePane;
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
        noteButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        homeButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        ordersButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        storageButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sellButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sidebarPane.setStyle("-fx-border-color: "+parseColor(pickedTheme));
        orderTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        storageTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        if (user.isentry()==true){
            homePane.toFront();
            changeColor(homeButton);
            homeButton.setDisable(false);
            ordersButton.setDisable(false);
            storageButton.setDisable(false);
            sellButton.setDisable(false);
            noteButton.setDisable(false);
        }
        else {
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
            noteButton.setDisable(true);

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
                            noteButton.setDisable(false);

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            });

        }
        positionLabel.setText("Employee: ");
        nameLabel.setText(user.getSurname());
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            profilePicture.setImage(image);
        }
        else {
            Image avatar = new Image("@../../icons/user.png");
            profilePicture.setImage(avatar);
        }

    }
    public void onClickHome(javafx.event.ActionEvent ActionEvent){
        homePane.toFront();
        changeColor(homeButton);
        sellButton.setText("Sell");
    }
    public void onClickOrders(javafx.event.ActionEvent ActionEvent){
        ordersPane.toFront();
        changeColor(ordersButton);
        sellButton.setText("Sell");
        ArrayList<Order> orders = new ArrayList<>();

        String select = "SELECT orders.o_id,products.name,orders_has_products.orderedQuantity,products.buyingPrice,products.warranty,orders.dateInit, " +
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
        ordersObservableList.removeIf(order -> order.getId() == orderID && order.getProductId() == productID);;
        ordersPane.toFront();
    }
    public void onClickStorage(javafx.event.ActionEvent ActionEvent){
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
        storagePane.toFront();
    }
    public void refreshStorageListView(int productId){
        productsObservableList.removeIf(product -> product.getId() == productId);
        storagePane.toFront();
    }

    public void onClickSell(javafx.event.ActionEvent ActionEvent) throws SQLException{
        ArrayList<Product> sellCards = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();

        String select = "SELECT * FROM products GROUP BY name";
        ResultSet resultSet = connection.prepareStatement(select).executeQuery();

        while (resultSet.next()){
            Product product = new Product(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),
                    resultSet.getDouble(5),resultSet.getDate(6));
            products.add(product);

        }

        sellPane.toFront();
        sellButton.setText("Sell");
        changeColor(sellButton);
        switchToShiftSalesButton.setOnAction(event -> {
            ShiftSalesPane.toFront();
            sellButton.setText("Shift Sales");
        });
        switchToSellButton.setOnAction(event -> {
            sellPane.toFront();
            sellButton.setText("Sell");
        });

            Product product = new Product();

            sellCards.add(product);

            sellObservableList = FXCollections.observableArrayList();
            sellObservableList.setAll(products);

            sellListView.setItems(sellObservableList);
            sellListView.setCellFactory(sellListView -> new SellCardController(this, products));
            addsellButton.setDisable(true);

        confirmSellButton.setOnAction(e -> {
            ArrayList<Product> soldProducts = new ArrayList<>();

            Iterator<ChoiceBox> itChoice = choiceBoxes.iterator();
            Iterator<TextField> itText = textFields.iterator();

            while (itChoice.hasNext() && itText.hasNext()){
                if (itChoice.next().getValue() != null && !itText.next().getText().isEmpty()) {
                    Product soldProduct = new Product((String) itChoice.next().getValue(), Integer.parseInt(itText.next().getText()));
                    soldProducts.add(soldProduct);
                }
            }

            for (Product soldProduct : soldProducts){
                System.out.println(soldProduct.getName() + " " + soldProduct.getQuantity());
            }
        });

    }
    public void onClickNote(javafx.event.ActionEvent ActionEvent){
        notePane.toFront();
        changeColor(noteButton);
        sellButton.setText("Sell");
    }
    public void onClickSettings(javafx.event.ActionEvent ActionEvent) {
        changePasswordButton.setText("Change password");
        settingsPane.toFront();
        changeColor(settingsButton);
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
                    if (user.getProfilePicture() != null) {
                        Image image = new Image(user.getProfilePicture());
                        sampleImage.setImage(image);
                        imagePath.setText(user.getProfilePicture());
                    }
                    else {
                        Image questionmark = new Image("@../../icons/question.png");
                        sampleImage.setImage(questionmark);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        saveColorButton.setOnAction(event -> {
            pickedTheme = themePicker.getValue();
            String update = "UPDATE users SET theme = '"+parseColor(pickedTheme)+"' WHERE u_id ="+user.getId()+";";
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
        noteButton.setStyle("-fx-background-color:white;-fx-text-fill:"+parseColor(pickedTheme));
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
            reader = new BufferedReader(new FileReader("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css\\main.css"));
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

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css\\main.css", false));
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
