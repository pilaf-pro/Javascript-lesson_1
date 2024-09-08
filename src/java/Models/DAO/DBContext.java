/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class DBContext {
    public Connection getConnection () throws Exception {
        try {
            Class.forName
            ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString =
            "jdbc:sqlserver://localhost:1433;database=PizzaStore;instanceName=KTEAM";
            //SQL Server Authentication
            Connection cnn = DriverManager.getConnection (connectionString, "sa","12345");
            return cnn;
        }catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
}
