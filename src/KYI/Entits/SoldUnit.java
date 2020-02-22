package KYI.Entits;

import java.sql.Date;

public class SoldUnit {
    private int id;
    private String name;
    private int quantity;
    private double sellingPrice;
    private Date date;
    private String cashier;

    public SoldUnit(int id,String name, int quantity, double sellingPrice, Date date, String cashier) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.date = date;
        this.cashier = cashier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
