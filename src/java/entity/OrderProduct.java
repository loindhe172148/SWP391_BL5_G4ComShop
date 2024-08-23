/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hbtth
 */
public class OrderProduct {
    private int orderid;
    private int productid;
    private float price;
    private int quantity;
    private Product product;
    private ProductWithDetails productdetails;
    public OrderProduct() {
    }

    public OrderProduct(int orderid, int productid, float price, int quantity) {
        this.orderid = orderid;
        this.productid = productid;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductWithDetails getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(ProductWithDetails productdetails) {
        this.productdetails = productdetails;
    }
    
    
}
