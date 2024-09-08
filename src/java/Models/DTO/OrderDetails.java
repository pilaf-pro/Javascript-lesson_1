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
public class OrderDetails {
    private String OrderID;
    private String ProductID;
    private double UnitPrice;
    private int Quantity;

    public OrderDetails() {
    }

    public OrderDetails(String OrderID, String ProductID, double UnitPrice, int Quantity) {
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %f, %d", OrderID, ProductID, UnitPrice, Quantity);
    }
    
    
}
