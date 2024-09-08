/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DTO;
import java.time.LocalDate;
/**
 *
 * @author Admin
 */
public class Orders {
    private String OrderID;
    private String CustomerID;
    private LocalDate OrderDate;
    private LocalDate RequiredDate;
    private LocalDate ShippedDate;
    private double Freight;
    private String ShipAddress;

    public Orders() {
    }

    public Orders(String OrderID, String CustomerID, LocalDate OrderDate, LocalDate RequiredDate, LocalDate ShippedDate, double Freight, String ShipAddress) {
        this.OrderID = OrderID;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ShippedDate = ShippedDate;
        this.Freight = Freight;
        this.ShipAddress = ShipAddress;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate OrderDate) {
        this.OrderDate = OrderDate;
    }

    public LocalDate getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(LocalDate RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    public LocalDate getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(LocalDate ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public double getFreight() {
        return Math.round(Freight * 100.0) / 100.0;
    }

    public void setFreight(double Freight) {
        this.Freight = Freight;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", CustomerID=" + CustomerID + ", OrderDate=" + OrderDate + ", RequiredDate=" + RequiredDate + ", ShippedDate=" + ShippedDate + ", Freight=" + Freight + ", ShipAddress=" + ShipAddress + '}';
    }
}
