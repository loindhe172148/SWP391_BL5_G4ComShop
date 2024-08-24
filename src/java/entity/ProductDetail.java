package entity;

public class ProductDetail {

    private int id; // key not null
    private int productId; // not null
    private int ramId; // not null
    private int cpuId; // not null
    private int cardId; // not null
    private String color; // not null
    private double originPrice; // not null
    private double salePrice; // not null
    private int quantity; // not null
    public ProductDetail() {
    }

    public ProductDetail(int id, int productId, int ramId, int cpuId, int cardId, String color, double originPrice, double salePrice, int quantity) {
        this.id = id;
        this.productId = productId;
        this.ramId = ramId;
        this.cpuId = cpuId;
        this.cardId = cardId;
        this.color = color;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "id=" + id + ", productId=" + productId + ", ramId=" + ramId + ", cpuId=" + cpuId + ", cardId=" + cardId + ", color=" + color + ", originPrice=" + originPrice + ", salePrice=" + salePrice + ", quantity=" + quantity + '}';
    }
    

}

