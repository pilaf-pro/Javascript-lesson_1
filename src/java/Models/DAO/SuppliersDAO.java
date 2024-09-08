/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import Models.DTO.Suppliers;
import java.util.*;
/**
 *
 * @author Admin
 */
public class SuppliersDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public List<Suppliers> getAllSuppliers() {
        List<Suppliers> suppliersList = new ArrayList<>();
        String sql = "select * from Suppliers";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                suppliersList.add(new Suppliers(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
        }
        return suppliersList;
    }
}
