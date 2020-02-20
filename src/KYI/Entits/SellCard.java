package KYI.Entits;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Date;


public class SellCard extends Product {
    private ChoiceBox productName;
    private TextField quantity;

    public SellCard(int id, String name, double buyingPrice, double sellingPrice, int quantity, Date warranty, ChoiceBox productName, TextField quantity1) {
        super(id, name, buyingPrice, sellingPrice, quantity, warranty);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(int id, String name, double buyingPrice, int quantity, Date warranty, ChoiceBox productName, TextField quantity1) {
        super(id, name, buyingPrice, quantity, warranty);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(String name, ChoiceBox productName, TextField quantity) {
        super(name);
        this.productName = productName;
        this.quantity = quantity;
    }

    public SellCard(String name, int quantity, double buyingPrice, double sellingPrice, ChoiceBox productName, TextField quantity1) {
        super(name, quantity, buyingPrice, sellingPrice);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(ChoiceBox productName, TextField quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public SellCard(int id, String name, ChoiceBox productName, TextField quantity) {
        super(id, name);
        this.productName = productName;
        this.quantity = quantity;
    }

    public SellCard(int id, String name, int quantity, double sellingPrice, ChoiceBox productName, TextField quantity1) {
        super(id, name, quantity, sellingPrice);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(String name, int quantity, double buyingPrice, ChoiceBox productName, TextField quantity1) {
        super(name, quantity, buyingPrice);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(String name, double buyingPrice, int quantity, Date warranty, ChoiceBox productName, TextField quantity1) {
        super(name, buyingPrice, quantity, warranty);
        this.productName = productName;
        this.quantity = quantity1;
    }

    public SellCard(int id, String name, int quantity, double buyingPrice, double sellingPrice, Date warranty, ChoiceBox productName, TextField quantity1) {
        super(id, name, quantity, buyingPrice, sellingPrice, warranty);
        this.productName = productName;
        this.quantity = quantity1;
    }
}
