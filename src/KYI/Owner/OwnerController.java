package KYI.Owner;

import KYI.Controllers.Connectivity;
import KYI.Controllers.Controller;
import KYI.Entits.Order;
import KYI.Entits.Product;
import KYI.Entits.User;
import KYI.Owner.EmployeesPane.UserCardController;
import KYI.Owner.OrdersPane.HistoryOrderCardController;
import KYI.Owner.OrdersPane.OrderCardController;

import KYI.Owner.StoragePane.AddProductController;
import KYI.Owner.StoragePane.StorageCardController;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;


public class OwnerController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label nameLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Button storageButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Button employeesButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button soldunitsButton;
    @FXML
    private Button incomeButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane homePane,sidebarPane;
    @FXML
    private Pane employeesPane, ordersHistoryPane;
    @FXML
    private Pane ordersPane;
    @FXML
    private Pane storagePane;
    @FXML
    private Pane soldunitsPane;
    @FXML
    private Pane incomePane;
    @FXML
    private Pane notePane;
    @FXML
    private Pane settingsPane;
    @FXML
    private ListView employeeListView, ordersListView, ordersHistoryListView,storageListView;
    @FXML
    private Button addUserButton,addOrdersButton,addProductToStorage,createProductButton;
    @FXML
    private TextField searchEmployeeTextfield,searchOrderTextfield,searchOrderHistoryTextfield,searchStorageTextfield;
    @FXML
    private Button noteButton;
    @FXML
    private Button switchToHistoryButton,switchToOrdersButton;
    @FXML
    private Button chooseImageButton, saveButton, changePasswordButton, confirmPasswordButton;
    @FXML
    private ImageView profilePicture, sampleImage;
    @FXML
    private Label imagePath, errorLabel;
    @FXML
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;
    @FXML
    private ColorPicker themePicker;
    @FXML
    private Button saveColorButton;
    @FXML
    private HBox orderTableHeader,storageTableHeader,orderHistoryTableHeader,employeeTableHeader;


    public static Color pickedTheme ;
    public static ObservableList<Order> ordersObservableList;
    public static ObservableList<User> employeesObservableList;
    public static ObservableList<Product> productsObservableList;


    Connectivity connectivity = new Connectivity();
    Connection connection = connectivity.getConnection();

    User user = getUser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pickedTheme = Color.valueOf(parseColor(Color.valueOf(user.getTheme())));
        logoutButton.setStyle("-fx-border-color:"+parseColor(pickedTheme)+";-fx-text-fill:"+parseColor(pickedTheme));
        noteButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        homeButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        employeesButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        ordersButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        storageButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        soldunitsButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        incomeButton.setStyle("-fx-text-fill:"+parseColor(pickedTheme));
        settingsButton.setStyle("-fx-text-fill:" +parseColor(pickedTheme));
        sidebarPane.setStyle("-fx-border-color: "+parseColor(pickedTheme));
        homeButton.setStyle("-fx-background-color:"+parseColor(pickedTheme)+";");
        homePane.toFront();
        orderTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        storageTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        orderHistoryTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";");
        employeeTableHeader.setStyle("-fx-background-color: "+parseColor(pickedTheme)+";"+"-fx-border-color:"+parseColor(pickedTheme)+";");
        positionLabel.setText("Owner: ");
        nameLabel.setText(user.getName()+" "+user.getSurname());
        if (user.getProfilePicture() != null) {
            Image image = new Image(user.getProfilePicture());
            profilePicture.setImage(image);
        }
        else {
            Image avatar = new Image("@../../icons/user.png");
            profilePicture.setImage(avatar);
        }
    }

    public void onClickStorage(javafx.event.ActionEvent ActionEvent) throws SQLException {
        storagePane.toFront();
        changeColor(storageButton);
        ordersButton.setText("Orders");

        ArrayList<Product> products = new ArrayList<>();

        new Thread(() ->{
        String select = "SELECT p_id,name,SUM(quantity),sellingPrice FROM products GROUP BY name ORDER BY SUM(quantity)";
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
        }).start();

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
        openWindow("../Owner/StoragePane/CreateProduct.fxml");
    }
    public void onClickAddProductToStorage(javafx.event.ActionEvent actionEvent)throws Exception{
        openWindow("../Owner/StoragePane/AddProduct.fxml");
        productsObservableList.sort(Comparator.comparing(Product::getQuantity));
    }
    public void refreshStorageListView(int productId){
        productsObservableList.removeIf(product -> product.getId() == productId);
    }
    public void refresh() {

    }




    public void onClickOrders(javafx.event.ActionEvent ActionEvent)throws SQLException{
        ordersPane.toFront();
        changeColor(ordersButton);

        ArrayList<Order> orders = new ArrayList<>();

        new Thread(() -> {
        String select = "SELECT orders.o_id,products.name,orders_has_products.orderedQuantity,products.buyingPrice,products.warranty,orders.dateInit, " +
                "products.p_id, deliverStatus FROM orders_has_products JOIN products ON (products_p_id = p_id) " +
                "JOIN orders ON (orders_o_id = o_id) WHERE deliverStatus = FALSE ORDER BY dateInit ASC";

            ResultSet result = null;
            try {
                result = connection.prepareStatement(select).executeQuery();


            while (result.next()){
            Order order = new Order(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4),
                    result.getDate(5),result.getDate(6),result.getInt(7),result.getBoolean(8));
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
        }).start();
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
        switchToHistoryButton.setOnAction(e -> {
            ordersHistoryPane.toFront();
            ordersButton.setText("Orders History");

            ArrayList<Order> ordersHistory = new ArrayList<>();
            ObservableList<Order> ordersHistoryObservableList;

            String selectForHistory = "SELECT orders.o_id,products.name,orderedQuantity,products.buyingPrice,products.warranty,orders.dateInit, " +
                    "products.p_id,deliverStatus FROM orders_has_products JOIN products ON (products_p_id = p_id) " +
                    "JOIN orders ON (orders_o_id = o_id) WHERE deliverStatus = TRUE ORDER BY dateInit ASC";
            try {
                ResultSet resultSet = connection.prepareStatement(selectForHistory).executeQuery();
                while (resultSet.next()){
                    Order order = new Order(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDouble(4),
                            resultSet.getDate(5),resultSet.getDate(6),resultSet.getInt(7),resultSet.getBoolean(8));

                    if (order.getDateInit().before(Date.valueOf(LocalDate.ofYearDay(LocalDate.now().getYear(), 1)))) {
                        Statement statement = connection.createStatement();
                        String deleteFromorder = "DELETE FROM orders_has_products WHERE orders_o_id = "+order.getId();
                        statement.executeLargeUpdate(deleteFromorder);
                        String deleteOrder = "DELETE FROM orders WHERE dateInit = '"+order.getDateInit()+"'";
                        statement.executeLargeUpdate(deleteOrder);
                        statement.close();
                        System.out.println("Order from last year successfully deleted");
                    }
                    else ordersHistory.add(order);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ordersHistoryObservableList= FXCollections.observableArrayList();
            ordersHistoryObservableList.addAll(ordersHistory);

            ordersHistoryListView.setItems(ordersHistoryObservableList);
            ordersHistoryListView.setCellFactory(ordersHistoryListView -> new HistoryOrderCardController());

            searchOrderHistoryTextfield.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    ordersHistoryObservableList.clear();
                    if (searchValidate(searchOrderHistoryTextfield.getText().toLowerCase()) == null) {
                        for (Order order : ordersHistory) {
                            if (order.getName().toLowerCase().contains(searchOrderHistoryTextfield.getText().toLowerCase())) {
                                ordersHistoryObservableList.add(order);
                            }
                            else if (order.getWarranty().toString().contains(searchOrderHistoryTextfield.getText().toLowerCase())) {
                                ordersHistoryObservableList.add(order);
                            }
                            else if (order.getDateInit().toString().contains(searchOrderHistoryTextfield.getText().toLowerCase())) {
                                ordersHistoryObservableList.add(order);
                            }
                        }
                        if (ordersHistoryObservableList.isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("VAROVANIE");
                            alert.setHeaderText("There is no such an Order");
                            alert.showAndWait();
                            ordersHistoryObservableList.addAll(ordersHistory);
                        }
                    } else {
                        ordersHistoryObservableList.addAll(ordersHistory);
                    }
                }
            });

            switchToOrdersButton.setOnAction(event -> {
                ordersPane.toFront();
                ordersButton.setText("Orders");
            });
        });
    }
    public void onClickAddOrder(ActionEvent event) throws Exception{
        openWindow("../Owner/OrdersPane/AddOrder.fxml");
    }

    public void refreshOrdersListView(int orderID, int productID){
        ordersObservableList.removeIf(order -> order.getId() == orderID && order.getProductId() == productID);;
    }
    //===============================================================
    //EMPLOYEE LIST PANE
    //================================================================
    public void onClickEmployees(javafx.event.ActionEvent ActionEvent) throws IOException, SQLException {
        employeesPane.toFront();
        changeColor(employeesButton);
        ordersButton.setText("Orders");

        ArrayList<User> employees = new ArrayList<>();


        String select = "SELECT * FROM users";
        ResultSet result = connection.prepareStatement(select).executeQuery();

        while (result.next()){
            User user = new User(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5),
                    result.getInt(7));
            if (user.getposition() == 0) {
                employees.add(user);
            }
        }
        employeesObservableList = FXCollections.observableArrayList();
        employeesObservableList.addAll(employees);

        employeeListView.setItems(employeesObservableList);
        employeeListView.setCellFactory(employeeListView -> new UserCardController(this));

        searchEmployeeTextfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                employeesObservableList.clear();
                if (searchValidate(searchEmployeeTextfield.getText().toLowerCase()) == null) {
                    for (User user : employees) {
                        if (user.getName().toLowerCase().contains(searchEmployeeTextfield.getText().toLowerCase())) {
                            employeesObservableList.add(user);
                        }
                        else if (user.getSurname().toLowerCase().contains(searchEmployeeTextfield.getText().toLowerCase())) {
                            employeesObservableList.add(user);
                        }
                        else if (user.getEmail().toLowerCase().contains(searchEmployeeTextfield.getText().toLowerCase())) {
                            employeesObservableList.add(user);
                        }

                    }
                    if (employeesObservableList.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("VAROVANIE");
                        alert.setHeaderText("There is no such an employee");
                        alert.showAndWait();
                        employeesObservableList.addAll(employees);
                    }
                } else {
                    employeesObservableList.addAll(employees);
                }
            }
        });
        }
    public void refreshEmployeesListView(int userID){
        employeesObservableList.removeIf(employee -> employee.getId() == userID);
        employeesPane.toFront();
    }
    public void onClickAddUser(ActionEvent actionEvent) throws Exception {
        openWindow("../Owner/EmployeesPane/AddingEmployee.fxml");
    }
    //============================================================
    //HOMESCREEN PANE
    //============================================================
    public void onClickHome(javafx.event.ActionEvent ActionEvent){
        homePane.toFront();
        changeColor(homeButton);
        ordersButton.setText("Orders");
    }

    public void onClickSoldunits(javafx.event.ActionEvent ActionEvent){
        soldunitsPane.toFront();
        changeColor(soldunitsButton);
        ordersButton.setText("Orders");
    }
    public void onClickIncome(javafx.event.ActionEvent ActionEvent){
        incomePane.toFront();
        changeColor(incomeButton);
        ordersButton.setText("Orders");
    }
    public void onClickNote(javafx.event.ActionEvent ActionEvent){
        notePane.toFront();
        changeColor(noteButton);
        ordersButton.setText("Orders");

    }
    //================================================================
    //SETTINGS PANE
    //===============================================================
    public void onClickSettings(javafx.event.ActionEvent ActionEvent){
        settingsPane.toFront();
        changeColor(settingsButton);
        ordersButton.setText("Orders");

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

        else {
            Image questionmark = new Image("@../../icons/question.png");
            sampleImage.setImage(questionmark);
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

            System.exit(1);
        });

    }
    //==================================================================================
    //END OF PANE CONTROLLERS
    //==================================================================================
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

    public void onClickLogout(javafx.event.ActionEvent ActionEvent) throws IOException {
        Stage stage = (Stage) nameLabel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
        Controller.changeScene(stage, loader, "KnowYourIncome");
    }
    public void changeColor(Button t){
        noteButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        homeButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        employeesButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        ordersButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        storageButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        soldunitsButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        incomeButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
        settingsButton.setStyle("-fx-background-color:white;-fx-text-fill:" +parseColor(pickedTheme));
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
            File file = new File("C:\\Users\\Marian\\Desktop\\KnowYourIncome\\src\\KYI\\Css");
            reader = new BufferedReader(new FileReader(file));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
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
