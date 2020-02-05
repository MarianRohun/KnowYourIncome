package KYI.Entits;

import java.sql.Date;

public class Order extends Product {
    private Date dateInit;

    public Order(int id, String name, int quantity,double buyingPrice, java.util.Date warranty, Date dateInit) {
        super(id, name, buyingPrice, quantity, warranty);
        this.dateInit = dateInit;
    }


    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    @Override
    public String toString() {
        return "Order{" +
                super.toString()+
                "dateInit=" + dateInit +
                '}';
    }
}
