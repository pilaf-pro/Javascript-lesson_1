/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import Models.DTO.Customers;
/**
 *
 * @author Admin
 */
/*
Profile cua khach hang
*/
public class CustomersDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public Customers getCustomersByCustomerID(String CustomerID) {
        String sql = "select * from Customers\n"
                + "where CustomerID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CustomerID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Customers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean addCustomer(Customers customer) throws Exception {
        String sql = "Insert Customers values(?,?,?,?,?)";
            try {
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, customer.getCustomerID());
                preStm.setString(2, customer.getPassword());
                preStm.setString(3, customer.getContactName());
                preStm.setString(4, customer.getAddress());
                preStm.setString(5, customer.getPhone());
                return preStm.executeUpdate() > 0;
            } catch (Exception ex) {
                throw ex;
            } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    
    public boolean updateCustomer(Customers customer) throws Exception {
            String sql = "Update Customers Set Password=?,ContactName=?,Address=?,Phone=? Where CustomerID=?";
            try {
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, customer.getPassword());
                preStm.setString(2, customer.getContactName());
                preStm.setString(3, customer.getAddress());
                preStm.setString(4, customer.getPhone());
                preStm.setString(5, customer.getCustomerID());
                return preStm.executeUpdate() > 0;
            } catch (Exception ex) {
                throw ex;
            } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    
     public Customers getLastCustomer(){
        String sql = "select top 1 * from Customers\n"
                + "ORDER BY CustomerID DESC";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Customers(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
}
