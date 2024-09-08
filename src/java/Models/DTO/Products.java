/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DTO;

/**
 *
 * @author Admin
 */
public class Products {
    private String ProductID;
    private String ProductName;
    private String SupplierID;
    private String CategoryID;
    private String QuantityPerUnit;
    private double UnitPrice;
    private String ProductImage;

    public Products() {
    }

    public Products(String ProductID, String ProductName, String SupplierID, String CategoryID, String QuantityPerUnit, double UnitPrice, String ProductImage) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.SupplierID = SupplierID;
        this.CategoryID = CategoryID;
        this.QuantityPerUnit = QuantityPerUnit;
        this.UnitPrice = UnitPrice;
        this.ProductImage = ProductImage;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %f, %s", ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage);
    }
    
    
}
