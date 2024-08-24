/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author LENOVO
 */
public class Accessory {

    private  int id ; 
    private  String name ;
    private  float  capacity ;
    private  String color ; 
    private  String connect; 
    private  String brandname ; 
    private  String DPI ;
    private  String compatible ; 
    private  String layout; 
    private  String swich ; 
    private  String  feature ;
    private  float  originprice; 
    private  float  saleprice; 
        private  int quantity ; 
        private  String  description ;
        private  String status ; 
        private String img;

    public Accessory(int id, String name, float capacity, String color, String connect, String brandname, String DPI, String compatible, String layout, String swich, String feature, float originprice, float saleprice, int quantity, String description, String status, String img) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.color = color;
        this.connect = connect;
        this.brandname = brandname;
        this.DPI = DPI;
        this.compatible = compatible;
        this.layout = layout;
        this.swich = swich;
        this.feature = feature;
        this.originprice = originprice;
        this.saleprice = saleprice;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
        this.img = img;
    }

    public Accessory() {
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

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getCompatible() {
        return compatible;
    }

    public void setCompatible(String compatible) {
        this.compatible = compatible;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getSwich() {
        return swich;
    }

    public void setSwich(String swich) {
        this.swich = swich;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public float getOriginprice() {
        return originprice;
    }

    public void setOriginprice(float originprice) {
        this.originprice = originprice;
    }

    public float getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(float saleprice) {
        this.saleprice = saleprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
         
}

