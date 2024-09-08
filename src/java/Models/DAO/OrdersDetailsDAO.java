/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.util.*;
import java.sql.*;
import Models.DTO.OrderDetails;
import Models.DTO.Orders;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class OrdersDetailsDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public List<OrderDetails> getAllOrderDetails() {
        List<OrderDetails> orderdetailsList = new ArrayList<>();
        String sql = "select * from OrderDetails";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderdetailsList.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return orderdetailsList;
    }
    
    public List<OrderDetails> getProductSoldList(Date startDate, Date endDate){
        List<OrderDetails> list = new ArrayList<>();
        String ProductID;
        int Quantity;
        String sql ="Select ProductID,SUM(Quantity)" +
                    " From OrderDetails" +
                    " Where OrderID In (Select OrderID from Orders where OrderDate BETWEEN ? AND ?)" +
                    " GROUP BY ProductID" +
                    " ORDER BY SUM(Quantity) DESC";
        try {
            cnn = new DBContext().getConnection();
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
            preStm = cnn.prepareStatement(sql);
            preStm.setDate(1, sqlStartDate);
            preStm.setDate(2, sqlEndDate);
            rs = preStm.executeQuery();
            while(rs.next()){
                ProductID = rs.getString(1);
                Quantity = rs.getInt(2);
                OrderDetails orderDetail = new OrderDetails("",ProductID,0,Quantity);
                list.add(orderDetail);
            }
            preStm.close();
            cnn.close();
        } catch (Exception ex) {
            
        }
        return list;
    }
    
    public List<OrderDetails> getOrderDetailsByOrderID(String orderID) {
        List<OrderDetails> orderdetailsList = new ArrayList<>();
        String sql = "select * from OrderDetails where OrderID = ?";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, orderID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderdetailsList.add(new OrderDetails(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return orderdetailsList;
    }
    
    public boolean addOrderDetails(List<OrderDetails> orderDetailsList) throws Exception {
        String sql = "Insert OrderDetails values(?,?,?,?)";
            try {   
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                for (OrderDetails orderDetails : orderDetailsList) {
                    preStm.setString(1, orderDetails.getOrderID());
                    preStm.setString(2, orderDetails.getProductID());
                    preStm.setDouble(3, orderDetails.getUnitPrice());
                    preStm.setInt(4, orderDetails.getQuantity());
                    preStm.addBatch();
                }
                int[] result = preStm.executeBatch();
                return result.length == orderDetailsList.size();
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
    public void deleteOrderDetails(String orderID) throws SQLException {
        String query = "delete from OrderDetails\n"
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
