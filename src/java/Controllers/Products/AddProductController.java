/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Products;

import Models.DAO.ProductsDAO;
import Models.DTO.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AddProductController extends HttpServlet {
    private final String addProductPage = "AddProduct.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, ProductImage;
        double UnitPrice;
        String message = null;
        String url = addProductPage;
        try {
            ProductID = request.getParameter("txtProductID");
            ProductName = request.getParameter("txtProductName");
            SupplierID = request.getParameter("txtSupplierID");
            CategoryID = request.getParameter("txtCategoryID");
            QuantityPerUnit = request.getParameter("txtQuantityPerUnit");
            UnitPrice = Double.parseDouble(request.getParameter("txtUnitPrice"));
            ProductImage = request.getParameter("txtProductImage");
            if(!ProductID.isEmpty()){
                ProductsDAO productDAO = new ProductsDAO();
                if(productDAO.getProductByProductID(ProductID) != null){
                    message = "This ProductID has already existed. Try again!";
                }
                else{
                    Products product = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage);
                    productDAO.addProduct(product);
                    message = "Product added successfully!";
                }
            }
        }catch(Exception ex){
            log(ex.getMessage());
        }finally{
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
