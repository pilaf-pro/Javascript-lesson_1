/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import Models.DTO.Account;
import Models.DTO.Products;
/**
 *
 * @author Admin
 */
/*

*/
public class AccountDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public Account login(String UserName, String Password) throws Exception{
        Account account = null;
        String AccountID;
        String FullName;
        int Type;
        try{
            cnn = new DBContext().getConnection();
            String sql =
            "select AccountID,FullName,Type from Account where [UserName]=? and [Password]=?";
            preStm = cnn.prepareStatement (sql);
            preStm.setString (1, UserName);
            preStm.setString(2, Password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                AccountID = rs.getString(1);
                FullName = rs.getString (2);
                Type = rs.getInt(3);
                account = new Account(AccountID, UserName, Password, FullName, Type);
            }//end while
            } catch (Exception ex) {
              throw ex;
            } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
            return account;
        }
    }
    
    public boolean signup(Account account) throws Exception {
        String sql = "Insert Account values(?,?,?,?,?)";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, account.getAccountID());
            preStm.setString(2, account.getUserName());
            preStm.setString(3, account.getPassword());
            preStm.setString(4, account.getFullName());
            preStm.setInt(5, account.getType());
            return  preStm.executeUpdate() > 0 ;
        } catch (Exception e) {
            throw e;
        }finally{
             if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    
    public Account getAccountByAccountID(String AccountID) {
        String sql = "select * from Account\n"
                + "where AccountID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, AccountID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Account getLastAccount(){
        String sql = "select top 1 * from Account\n"
                + "ORDER BY accountID DESC";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1),
                        rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean updateAccount(Account account) throws Exception {
            String sql = "update Account set Password = ? where AccountID = ?";
            try {
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, account.getPassword());
                preStm.setString(2, account.getAccountID());
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
}
