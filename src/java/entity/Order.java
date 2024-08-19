
package entity;
import java.sql.Date;

public class Order {
    private int id;
    private int customerid;
    private Date orderdate;
    private float totalamount;
    private String statusid;
    private String shippingaddress;

    public Order() {
    }

    public Order(int id, int customerid, Date orderdate, float totalamount, String statusid, String shippingaddress) {
        this.id = id;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.totalamount = totalamount;
        this.statusid = statusid;
        this.shippingaddress = shippingaddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public float getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(float totalamount) {
        this.totalamount = totalamount;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }
    
    
}
