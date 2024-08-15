/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Date;

/**
 *
 * @author xuant
 */
public class ProductJoinDetail {
    private int id;
    private String name;
    private String title;
    private String description;
    private String image;
    private int quantity;
    private float originPrice;
    private float salePrice;
    private int categoryId;
    private int brandId;
    private float screenSize;
    private Date createDate;
    private Date updateDate;
    private String status;

    private int ramId;
    private int cpuId;
    private int cardId;
    private double variantPrice;

    // Getters and Setters
    // Product fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getOriginPrice() { return originPrice; }
    public void setOriginPrice(float originPrice) { this.originPrice = originPrice; }
    public float getSalePrice() { return salePrice; }
    public void setSalePrice(float salePrice) { this.salePrice = salePrice; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }
    public float getScreenSize() { return screenSize; }
    public void setScreenSize(float screenSize) { this.screenSize = screenSize; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // ProductDetail fields
    public int getRamId() { return ramId; }
    public void setRamId(int ramId) { this.ramId = ramId; }
    public int getCpuId() { return cpuId; }
    public void setCpuId(int cpuId) { this.cpuId = cpuId; }
    public int getCardId() { return cardId; }
    public void setCardId(int cardId) { this.cardId = cardId; }
    public double getVariantPrice() { return variantPrice; }
    public void setVariantPrice(double variantPrice) { this.variantPrice = variantPrice; }
}
