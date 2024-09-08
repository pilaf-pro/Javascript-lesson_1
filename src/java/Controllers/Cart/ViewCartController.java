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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ViewCartController extends HttpServlet {
    private final String viewCartPage = "ViewCart.jsp";
    private final String loginPage = "Login.jsp";
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
        String url = viewCartPage;
        List<Cart> itemsInCart = null;
        HashMap<String, Cart> cart = null;
        Cookie cookieCart = null;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("accountLoggedIn");
            if(account == null){
                url = loginPage;
            }
            else if(account!=null){
                CartUtil cartUtils = new CartUtil();
                cart = (HashMap<String, Cart>)session.getAttribute("Cart");
                if(cart == null){
                    cookieCart = cartUtils.getCookieByName(request, account.getAccountID());
                    if(cookieCart != null){
                        cart = cartUtils.getCartFromCookie(cookieCart, account.getAccountID());
                        if(cart != null){
                            itemsInCart = new ArrayList<Cart>(cart.values());
                            session.setAttribute("Cart", cart);
                        }
                    }
                }else{
                    itemsInCart = new ArrayList<Cart>(cart.values());
                }
            }
            request.setAttribute("Cart", itemsInCart);
        }catch(Exception ex){
            log("ViewCartServlet has error:" + ex.getMessage());
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
