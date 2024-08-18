
package entity;

public class ProductDetail {
    private int id; // key not null
    private int productId; // not null
    private int ramId; // not null
    private int romId; // not null
    private int cpuId; // not null
    private int cardId; // not null
    private String color; // not null
    private int typeId; // not null
    private double originPrice; // not null
    private double salePrice; // not null

    public ProductDetail() {
    }

    public ProductDetail(int id, int productId, int ramId, int romId, int cpuId, int cardId, String color, int typeId, double originPrice, double salePrice) {
        this.id = id;
        this.productId = productId;
        this.ramId = ramId;
        this.romId = romId;
        this.cpuId = cpuId;
        this.cardId = cardId;
        this.color = color;
        this.typeId = typeId;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
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

    public int getRomId() {
        return romId;
    }

    public void setRomId(int romId) {
        this.romId = romId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    

}
