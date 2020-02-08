package KYI.Entits;

import java.sql.Date;

public class Order extends Product {
    private Date dateInit;
    private int productId;
    private boolean deliverStatus;

    public Order(int id, String name, int quantity,double buyingPrice, java.util.Date warranty, Date dateInit,int productId,boolean deliverStatus) {
        super(id, name, buyingPrice, quantity, warranty);
        this.dateInit = dateInit;
        this.productId = productId;
        this.deliverStatus = deliverStatus;
    }

    public Order() {
        super();
    }


    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(boolean deliverStatus) {
        this.deliverStatus = deliverStatus;
    }
}