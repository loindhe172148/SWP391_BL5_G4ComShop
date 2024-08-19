
package entity;

public class CartDetail {
     private int productDetailId; // key  not null
     private int quantity; //  not null
     private float price; // not null
     private float totalPrice; // not null
     private int customerId; // key not null

    public CartDetail() {
    }

    public CartDetail(int productDetailId, int quantity, float price, float totalPrice, int customerId) {
        this.productDetailId = productDetailId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }


    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
     
     

}
