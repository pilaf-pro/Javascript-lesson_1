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
public class Suppliers {
    private String SupplierID;
    private String CompanyName;
    private String Address;
    private String Phone;

    public Suppliers() {
    }

    public Suppliers(String SupplierID, String CompanyName, String Address, String Phone) {
        this.SupplierID = SupplierID;
        this.CompanyName = CompanyName;
        this.Address = Address;
        this.Phone = Phone;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", SupplierID, CompanyName, Address, Phone);
    }
    
    
}
