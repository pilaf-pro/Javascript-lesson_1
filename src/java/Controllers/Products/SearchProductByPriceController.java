/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Products;

import Models.DAO.CategoriesDAO;
import Models.DAO.ProductsDAO;
import Models.DTO.Categories;
import Models.DTO.Products2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SearchProductByPriceController extends HttpServlet {
    private final String homePage = "Home.jsp";
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
        String url = homePage;
        String minValueParam = request.getParameter("txtMinValue");
        String maxValueParam = request.getParameter("txtMaxValue");
        Double minValue, maxValue;
        String message = null;
        ProductsDAO productDAO = new ProductsDAO();
        CategoriesDAO categoryDAO = new CategoriesDAO();
        List<Categories> categoryList = categoryDAO.getAllCategories();
        List<Products2> productList = null;
        if(!minValueParam.isEmpty() && !maxValueParam.isEmpty()){
                minValue = Double.parseDouble(minValueParam);
                maxValue = Double.parseDouble(maxValueParam);
                productList = productDAO.searchProductByProduct2Price(minValue, maxValue);
            }else if(!minValueParam.isEmpty() && maxValueParam.isEmpty()){
                message = "Please enter max price!";
                productList = productDAO.getAllProducts2();
            }else if(minValueParam.isEmpty() && !maxValueParam.isEmpty()){
                message = "Please enter min price!";
                productList = productDAO.getAllProducts2();
            }else if(minValueParam.isEmpty() && maxValueParam.isEmpty()){
                message = "Please enter max and min price for searching!";
                productList = productDAO.getAllProducts2();
            }
        request.setAttribute("message", message);
        request.setAttribute("ProductList", productList);
        request.setAttribute("CategoryList", categoryList);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
