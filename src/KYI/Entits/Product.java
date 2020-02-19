package KYI.Entits;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double buyingPrice;
    private double sellingPrice;
    private Date warranty;

    public Product(int id, String name, double buyingPrice, double sellingPrice,int quantity, Date warranty){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;

        this.warranty = warranty;
    }

    public Product(int id, String name, double buyingPrice, int quantity, Date warranty) {
        this.id = id;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
        this. warranty = warranty;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, int quantity, double buyingPrice, double sellingPrice) {
        this.name = name;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public Product() {

    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(int id, String name, int quantity, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
    }
    public Product(String name, int quantity, double buyingPrice){
        this.name = name;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
    }

    public Product(String name, double buyingPrice, int quantity, Date warranty) {
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
        this.warranty = warranty;
    }
    public Product(int id,String name,int quantity,double buyingPrice,double sellingPrice, Date warranty){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.warranty = warranty;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getWarranty() {
        return warranty;
    }

    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                ", warranty=" + warranty +
                '}';
    }
}