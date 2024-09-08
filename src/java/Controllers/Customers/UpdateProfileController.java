/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Customers;

import Models.DAO.AccountDAO;
import Models.DAO.CustomersDAO;
import Models.DTO.Account;
import Models.DTO.Customers;
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
public class UpdateProfileController extends HttpServlet {
    private final String updateProfilePage = "UpdateProfile.jsp";
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
        String CustomerID, Password, ContactName, Address, Phone;
        String oldPassword, repeatPassword;
        String message = null;
        String url = updateProfilePage;
        try {
             HttpSession session = request.getSession();
             Account account = (Account) session.getAttribute("accountLoggedIn");
             AccountDAO accountDAO = new AccountDAO();
             if(account != null){
                CustomersDAO customerDAO = new CustomersDAO();
                CustomerID = "CUST" + account.getAccountID();
                oldPassword = request.getParameter("txtOldPassword");
                Password = request.getParameter("txtPassword");
                repeatPassword = request.getParameter("txtRepeatPassword");
                if(oldPassword.equals(account.getPassword()) && Password.equals(repeatPassword)){
                    account = new Account(account.getAccountID(), account.getUserName(), Password, account.getFullName(), account.getType());
                    accountDAO.updateAccount(account);
                }else if(!oldPassword.equals(account.getPassword())){
                    message = "Old password does not match account's password. Please try again!";
                }else if(!Password.equals(repeatPassword)){
                    message = "Repeat password does not match new password.Please try again!";
                }
                ContactName = request.getParameter("txtContactName");
                Address = request.getParameter("txtAddress");
                Phone = request.getParameter("txtPhone");
                Customers customer = new Customers(CustomerID, Password, ContactName, Address, Phone);
                customerDAO.updateCustomer(customer);
                session.setAttribute("customer", customer);
                session.setAttribute("customerID", CustomerID);
                message = "Profile updated successfully";
                request.setAttribute("message", message);
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
