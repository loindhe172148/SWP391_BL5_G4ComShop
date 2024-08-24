
package entity;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private String title;
    private String description;
    private String image;
    private int quantity;
    private int categoryId;
    private int brandId;
    private float screenSize;
    private Date createDate;
    private Date updateDate;
    private String status;
    private float originPrice;
   private float salePrice;
   private int productdetailID ; 
   private String color ; 
   private int cpuid ; 
   private int cardid;
   private int  ramid ;

    public int getProductdetailID() {
        return productdetailID;
    }

    public void setProductdetailID(int productdetailID) {
        this.productdetailID = productdetailID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCpuid() {
        return cpuid;
    }

    public void setCpuid(int cpuid) {
        this.cpuid = cpuid;
    }

    public int getCardid() {
        return cardid;
    }

    public void setCardid(int cardid) {
        this.cardid = cardid;
    }

    public int getRamid() {
        return ramid;
    }

    public void setRamid(int ramid) {
        this.ramid = ramid;
    }

    
 
   
// Getters and setters for these fields

    public float getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(float originPrice) {
        this.originPrice = originPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getproductdetailID() {
        return productdetailID;
    }

    public void setproductdetailID(int productdetailID) {
        this.productdetailID = productdetailID;
    }
    

    public Product() {
    }

    public Product(int id, String name, String title, String description, String image, int quantity, int categoryId, int brandId, float screenSize, Date createDate, Date updateDate, String status) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.screenSize = screenSize;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", title=" + title + ", description=" + description + ", image=" + image + ", quantity=" + quantity + ", categoryId=" + categoryId + ", brandId=" + brandId + ", screenSize=" + screenSize + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status + ", originPrice=" + originPrice + ", salePrice=" + salePrice + ", productdetailID=" + productdetailID + '}';
    }


    

    
}
