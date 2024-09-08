/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Models.DTO.Account;
import Models.DTO.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class SaveCartController extends HttpServlet {
    private final String loginPage = "Login.jsp";
    private final String cartController = "CartController";
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
        String message = null;
        HashMap<String, Cart> itemsInCart = null;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("accountLoggedIn");
            if(account == null){
                url = loginPage;
            }
            HttpSession shoppingCart = request.getSession();
            itemsInCart = (HashMap<String, Cart>)shoppingCart.getAttribute("Cart");
            CartUtil cart = new CartUtil();
            if(itemsInCart != null){
                String strItemsInCart = cart.convertCartToString(new ArrayList<Cart>(itemsInCart.values()));
                cart.saveCartToCookie(request, response, strItemsInCart, account.getAccountID());
                message = "Your cart has been saved successfully.";
            }else{
                message = "Your cart is empty.";
            }
            url = cartController + "?action=View Cart";
        }catch(Exception ex){
            log("SaveCartController has error:" + ex.getMessage());
        }finally{
            request.setAttribute("Message","<h4 style='text-align: center; color: green;'>" + message + "</h4>");
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
