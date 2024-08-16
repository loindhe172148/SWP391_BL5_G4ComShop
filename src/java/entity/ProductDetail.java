/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author xuant
 */
public class ProductDetail {
    private int id;
    private int productId;
    private int ramId;
    private int cpuId;
    private int cardId;
    private double variantPrice;

    public ProductDetail() {
    }

    public ProductDetail(int id, int productId, int ramId, int cpuId, int cardId, double variantPrice) {
        this.id = id;
        this.productId = productId;
        this.ramId = ramId;
        this.cpuId = cpuId;
        this.cardId = cardId;
        this.variantPrice = variantPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public double getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(double variantPrice) {
        this.variantPrice = variantPrice;
    }
    
    
}
