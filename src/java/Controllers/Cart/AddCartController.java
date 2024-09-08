/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Models.DAO.ProductsDAO;
import Models.DTO.Account;
import Models.DTO.Cart;
import Models.DTO.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddCartController extends HttpServlet {
    private final String loginPage = "Login.jsp";
    private final String homeController = "HomeController";
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
        String url = null;
        String message, ProductID;
        Products selectedProduct = null;
        Cart item = null;
        HashMap<String, Cart> itemsInCart = null;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("accountLoggedIn");
            if(account == null){
                url = loginPage;
            }else{
                ProductsDAO productDAO = new ProductsDAO();
                HttpSession shoppingCart = request.getSession();
                itemsInCart = (HashMap<String, Cart>)shoppingCart.getAttribute("Cart");
                ProductID = request.getParameter("ProductID");
                selectedProduct = productDAO.getProductByProductID(ProductID);
                if(itemsInCart == null){
                    itemsInCart = new HashMap<String, Cart>();
                    shoppingCart.setAttribute("Cart", itemsInCart);
                }
                item = itemsInCart.get(selectedProduct.getProductID());
                if(item == null){
                    item = new Cart(selectedProduct.getProductID(), selectedProduct.getProductName(), 1, selectedProduct.getUnitPrice());
                    itemsInCart.put(item.getItemId(), item);
                    message = "The pizza " + item.getItemName() + " has been added to cart successfully.";
                }else{
                    item.setQuantity(item.getQuantity() + 1);
                    message = "The cart has been updated successfully";
                }
                url = homeController;
                request.setAttribute("Message", "<h4 style='text-align: center; color: green;'>" + message + "</h4>");
           } 
        }catch(Exception ex){
            log("AddCartController has error:" + ex.getMessage());
        }finally{
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
