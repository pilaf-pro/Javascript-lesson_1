/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import Models.DTO.Categories;
import java.util.*;
/**
 *
 * @author Admin
 */
public class CategoriesDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public List<Categories> getAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                categoriesList.add(new Categories(rs.getString(1),
                        rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return categoriesList;
    }
    
    public static void main(String[] args) {
        
       CategoriesDAO categoryDAO = new CategoriesDAO();
        
        
        
        
            
            
            // Call the method with the date range
            List<Categories> categoryList = categoryDAO.getAllCategories();
            
            // Print the results
            for (Categories category : categoryList) {
                System.out.println(category);
            }
        
    }
}
