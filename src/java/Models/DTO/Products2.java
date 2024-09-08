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
public class Products2 {
    private String ProductID;
    private String ProductName;
    private double UnitPrice;
    private String ProductImage;
    private String CategoryName;
    private String Description;

    public Products2() {
    }

    public Products2(String ProductID ,String ProductName, double UnitPrice, String ProductImage, String CategoryName, String Description) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.ProductImage = ProductImage;
        this.CategoryName = CategoryName;
        this.Description = Description;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%f,%s,%s,%s", ProductID, ProductName,UnitPrice,ProductImage,CategoryName,Description);
    }
}
