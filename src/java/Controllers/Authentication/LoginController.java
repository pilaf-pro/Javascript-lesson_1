/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Authentication;

import Controllers.Cart.CartUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.DAO.AccountDAO;
import Models.DAO.CustomersDAO;
import Models.DTO.Account;
import Models.DTO.Cart;
import Models.DTO.Customers;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {
    private final String homePage = "HomeController";
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
        String userName = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        Cookie cookieCart = null;
        HashMap<String, Cart> cart = null;
        try{
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.login(userName, password);
            if(account!=null){
                HttpSession session = request.getSession(true);
                session.setAttribute("AccountId", account.getAccountID());
                session.setAttribute("password", account.getPassword());
                session.setAttribute("accountLoggedIn", account);
                session.setAttribute("accountType", account.getType());
                
                CustomersDAO customerDAO = new CustomersDAO();
                Customers customer = customerDAO.getCustomersByCustomerID("CUST" + account.getAccountID());
                session.setAttribute("customer", customer);
                session.setAttribute("customerID", customer.getCustomerID());
                session.setAttribute("address", customer.getAddress());
                
                CartUtil cartUtils = new CartUtil();
                cookieCart = cartUtils.getCookieByName(request, account.getAccountID());
                if (cookieCart != null) {
                    cart = cartUtils.getCartFromCookie(cookieCart, account.getAccountID());
                    session.setAttribute("Cart", cart);
                }
            }
            else if(account == null){
                request.setAttribute("message","The userId or password is invalid");
            }
        }catch(Exception ex){
            log(ex.getMessage());
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
