/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import java.sql.Date;
import Models.DTO.Orders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class OrdersDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    CallableStatement cs = null;
    
    public List<Orders> getOrdersByDateRange (LocalDate startDate, LocalDate endDate) {
        List<Orders> list = new ArrayList<>();
        String procedureCall = "{CALL GetOrdersByDateRange(?, ?)}";
        try {
            cnn = new DBContext().getConnection();
            cs = cnn.prepareCall(procedureCall);
            cs.setDate(1, Date.valueOf(startDate));
            cs.setDate(2, Date.valueOf(endDate));
            rs =cs.executeQuery();
                while (rs.next()) {
                    Orders order = new Orders();
                    order.setOrderID(rs.getString("OrderID"));
                    order.setCustomerID(rs.getString("CustomerID"));
                    order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
                    order.setRequiredDate(rs.getDate("RequiredDate").toLocalDate());
                    order.setShippedDate(rs.getDate("ShippedDate").toLocalDate());
                    order.setFreight(rs.getDouble("Freight"));
                    order.setShipAddress(rs.getString("ShipAddress"));
                    list.add(order);
                }
              
            
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Orders> getOrderList(){
        List<Orders> list = new ArrayList<>();
         cnn = null;
         preStm = null;
         rs = null;
        String OrderID;
        String CustomerID;
        LocalDate OrderDate;
        LocalDate RequiredDate;
        LocalDate ShippedDate;
        float Freight;
        String ShipAddress;
        String sql ="Select * from Orders";
        try {
            cnn = new DBContext().getConnection();
            preStm= cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                OrderID = rs.getString(1);
                CustomerID = rs.getString(2);
                OrderDate = rs.getDate(3).toLocalDate();
                RequiredDate = rs.getDate(4).toLocalDate();
                ShippedDate = rs.getDate(5).toLocalDate();
                Freight = rs.getFloat(6);
                ShipAddress = rs.getString(7);
                Orders Object = new Orders(OrderID,CustomerID,OrderDate,RequiredDate,ShippedDate,Freight,ShipAddress);
                list.add(Object);
            }
            preStm.close();
            cnn.close();
        } catch (Exception ex) {
            
        }
        return list;
    }
    
    public List<Orders> getOrderListByCustomerID(String customerID){
        List<Orders> list = new ArrayList<>();
        String OrderID;
        String CustomerID;
        LocalDate OrderDate;
        LocalDate RequiredDate;
        LocalDate ShippedDate;
        float Freight;
        String ShipAddress;
        String sql ="Select * from Orders where CustomerID = ?";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, customerID);
            rs = preStm.executeQuery();
            while(rs.next()){
                OrderID = rs.getString(1);
                CustomerID = rs.getString(2);
                OrderDate = rs.getDate(3).toLocalDate();
                RequiredDate = rs.getDate(4).toLocalDate();
                ShippedDate = rs.getDate(5).toLocalDate();
                Freight = rs.getFloat(6);
                ShipAddress = rs.getString(7);
                Orders Order = new Orders(OrderID,CustomerID,OrderDate,RequiredDate,ShippedDate,Freight,ShipAddress);
                list.add(Order);
            }
            preStm.close();
            cnn.close();
        } catch (Exception ex) {
            
        }
        return list;
    }
     
    public boolean addOrder(Orders order) throws Exception {
        String sql = "Insert Orders values(?,?,?,?,?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {   
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, order.getOrderID());
                preStm.setString(2, order.getCustomerID());
                preStm.setDate(3, Date.valueOf(order.getOrderDate()));
                preStm.setDate(4, Date.valueOf(order.getRequiredDate()));
                preStm.setDate(5, Date.valueOf(order.getShippedDate()));
                preStm.setDouble(6, order.getFreight());
                preStm.setString(7, order.getShipAddress());
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
    
    public Orders getOrderByOrderID(String OrderID) {
        String CustomerID;
        LocalDate OrderDate;
        LocalDate RequiredDate;
        LocalDate ShippedDate;
        float Freight;
        String ShipAddress;
        String sql = "select * from Orders\n"
                + "where ProductID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, OrderID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                OrderID = rs.getString(1);
                CustomerID = rs.getString(2);
                OrderDate = rs.getDate(3).toLocalDate();
                RequiredDate = rs.getDate(4).toLocalDate();
                ShippedDate = rs.getDate(5).toLocalDate();
                Freight = rs.getFloat(6);
                ShipAddress = rs.getString(7);
                Orders order = new Orders(OrderID,CustomerID,OrderDate,RequiredDate,ShippedDate,Freight,ShipAddress);
                return order;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteOrder(String orderID) throws SQLException {
        String query = "delete from Orders\n"
                + "where OrderID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(query);
            preStm.setString(1, orderID);
            preStm.executeUpdate();
        } catch (Exception e) {
        }
    }
}



