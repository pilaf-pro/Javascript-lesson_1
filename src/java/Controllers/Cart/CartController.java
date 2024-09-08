/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import Models.DTO.Account;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CartController extends HttpServlet {
    private final String loginPage = "Login.jsp";
    private final String addCartController = "AddCartController";
    private final String viewCartController = "ViewCartController";
    private final String removeCartController = "RemoveCartController";
    private final String updateCartController = "UpdateCartController";
    private final String saveCartController = "SaveCartController";
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
        String url = viewCartController, action;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("accountLoggedIn");
        try {
            if (account == null) {
               url = loginPage;
            }else if(account != null){
                action = request.getParameter("action");
                if(action.equalsIgnoreCase("Add")){
                    url = addCartController;
                }else if(action.equalsIgnoreCase("ViewCart")){
                    url = viewCartController;
                }else if(action.equalsIgnoreCase("Remove")){
                    url = removeCartController;
                }else if(action.equalsIgnoreCase("Update")){
                    url = updateCartController;
                }else if(action.equalsIgnoreCase("Save")){
                    url = saveCartController;
                }
            }
        }catch(Exception ex){
            log("CartController has error:" + ex.getMessage());
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
