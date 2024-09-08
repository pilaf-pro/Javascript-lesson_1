/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;
import java.sql.*;
import Models.DTO.Products;
import Models.DTO.Products2;
import java.util.*;
/**
 *
 * @author Admin
 */
public class ProductsDAO {
    Connection cnn = null;
    PreparedStatement preStm = null;
    ResultSet rs = null;
    
    public List<Products> getAllProducts() {
        List<Products> productsList = new ArrayList<>();
        String sql = "select * from Products";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productsList.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return productsList;
    }
    
    public List<Products> getTop3() {
        List<Products> top3List = new ArrayList<>();
        String sql = "select top 3 * from Products";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                top3List.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return top3List;
    }
    
    public List<Products> getNext3Product(int amount) {
        List<Products> next3List = new ArrayList<>();
        String sql = "SELECT *\n"
                + "  FROM Products\n"
                + " ORDER BY ProductID\n"
                + "OFFSET ? ROWS\n"
                + " FETCH NEXT 3 ROWS ONLY";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, amount);
            rs = preStm.executeQuery();
            while (rs.next()) {
                next3List.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return next3List;
    }
    
    public List<Products> getProductByCategoryID(String CategoryID) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where CategoryID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CategoryID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products> getProductByCategoryName(String CategoryName) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products, Categories\n"
                + "where Products.CategoryID = Categories.CategoryID and Categories.CategoryName = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CategoryName);
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products> searchProductByProductID(String searchIdValue) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where [ProductID] like ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchIdValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products> searchProductByProductName(String searchNameValue) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where [ProductName] like ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchNameValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products> searchProductByProductIDAndProductName(String searchIdValue, String searchNameValue) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where [ProductID] like ? and [ProductName] like ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchIdValue + "%");
            preStm.setString(2, "%" + searchNameValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products> searchProductByUnitPrice(double minValue, double maxValue) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where UnitPrice between ? and ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setDouble(1, minValue);
            preStm.setDouble(2, maxValue);
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public Products getProductByProductID(String ProductID) {
        String sql = "select * from Products\n"
                + "where ProductID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, ProductID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Products getLastProduct() {
        String query = "select top 1 * from Products\n"
                + "order by ProductID desc";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(query);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return new Products(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteProduct(String ProductID) {
        String query = "delete from Products\n"
                + "where ProductID = ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(query);
            preStm.setString(1, ProductID);
            preStm.executeUpdate();
        } catch (Exception e) {
        }
    }
     
    public boolean addProduct(Products product) throws Exception {
        String sql = "Insert Products values(?,?,?,?,?,?,?)";
            try {
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, product.getProductID());
                preStm.setString(2, product.getProductName());
                preStm.setString(3, product.getSupplierID());
                preStm.setString(4, product.getCategoryID());
                preStm.setString(5, product.getQuantityPerUnit());
                preStm.setDouble(6, product.getUnitPrice());
                preStm.setString(7, product.getProductImage());
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
   
    public boolean updateProduct(Products product) throws Exception {
            String sql = "Update Products Set ProductName=?,SupplierID=?,CategoryID=?,QuantityPerUnit=?,UnitPrice=?,ProductImage=? Where  ProductID=?";
            try {
                cnn = new DBContext().getConnection();
                preStm = cnn.prepareStatement (sql);
                preStm.setString(1, product.getProductName());
                preStm.setString(2, product.getSupplierID());
                preStm.setString(3, product.getCategoryID());
                preStm.setString(4, product.getQuantityPerUnit());
                preStm.setDouble(5, product.getUnitPrice());
                preStm.setString(6, product.getProductImage());
                preStm.setString(7, product.getProductID());
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
    
    public List<Products2> getAllProducts2() {
        List<Products2> productsList = new ArrayList<>();
        String sql = "select p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productsList.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return productsList;
    }
    
    public List<Products2> getTop3Products2() {
        List<Products2> productsList = new ArrayList<>();
        String sql = "select top 3 p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productsList.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return productsList;
    }
    
    public List<Products2> searchProductByProduct2Name(String searchNameValue) {
        List<Products2> list = new ArrayList<>();
        String sql = "select p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID and [ProductName] like ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchNameValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products2> searchProductByProduct2Id(String searchIdValue) {
        List<Products2> list = new ArrayList<>();
        String sql = "select p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID and [ProductID] like ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + searchIdValue + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products2> searchProductByProduct2Price(Double minPrice, Double maxPrice) {
        List<Products2> list = new ArrayList<>();
        String sql = "select p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID and [UnitPrice] between ? and ?";
        try {
            cnn = new DBContext().getConnection();//mo ket noi voi sql
            preStm = cnn.prepareStatement(sql);
            preStm.setDouble(1, minPrice);
            preStm.setDouble(2, maxPrice);
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Products2> getProduct2ByCategoryID(String CategoryID) {
        List<Products2> list = new ArrayList<>();
        String sql = "select p.ProductID, p.ProductName, p.UnitPrice, p.ProductImage, c.CategoryName, c.Description from Products p, Categories c where p.CategoryID = c.CategoryID and p.CategoryID = ?";
        try {
            cnn = new DBContext().getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CategoryID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                list.add(new Products2(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public boolean reduceStock(String productID, int quantity) throws Exception {
        String sql = "UPDATE Products SET Quantity = Quantity - ? WHERE ProductID = ?";
        try {
            cnn = new DBContext().getConnection();
            preStm.setInt(1, quantity);
            preStm.setString(2, productID);
            return preStm.executeUpdate() > 0;
        }catch (Exception ex) {
            throw ex;
        }finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
                
            }
        }    
    }
}
