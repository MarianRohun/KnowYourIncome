package KYI.Entits;

import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double buyingPrice;
    private Double sellingPrice;
    private Date dateOfDelivery;
    private Date warranty;

    public Product(Integer id, String name, Double buyingPrice, Double sellingPrice,Integer quantity, Date dateOfDelivery, Date warranty){
        this.quantity = quantity;
        this.id = id;
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.dateOfDelivery = dateOfDelivery;
        this.warranty = warranty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }
    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }
    public Date getWarranty() {
        return warranty;
    }
    public void setWarranty(Date warranty) {
        this.warranty = warranty;
    }
}

