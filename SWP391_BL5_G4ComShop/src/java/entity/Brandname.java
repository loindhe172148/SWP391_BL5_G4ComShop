/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author LENOVO
 */
public class Brandname {
    private  int idBrandName; 
    private  String brandName ;

    public Brandname() {
    }

    public Brandname(int idBrandName, String brandName) {
        this.idBrandName = idBrandName;
        this.brandName = brandName;
    }

    public int getIdBrandName() {
        return idBrandName;
    }

    public void setIdBrandName(int idBrandName) {
        this.idBrandName = idBrandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    

    
    
}
