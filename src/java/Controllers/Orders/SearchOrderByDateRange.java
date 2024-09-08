/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Orders;

import Models.DAO.OrdersDAO;
import Models.DTO.Account;
import Models.DTO.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
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
public class SearchOrderByDateRange extends HttpServlet {
    private final String orderPage = "ViewOrder.jsp";
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
        String url = orderPage;
        String message = null;
        List<Orders> orderList = null;
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("accountLoggedIn");
        String paramStartDate = request.getParameter("startDate");
        String paramEndDate = request.getParameter("endDate");
        if(account == null){
            url = loginPage;
        }else{
            OrdersDAO orderDAO = new OrdersDAO();
            if(!paramStartDate.isEmpty() && !paramEndDate.isEmpty()){
                LocalDate startDate = LocalDate.parse(paramStartDate);
                LocalDate endDate = LocalDate.parse(paramEndDate);
                orderList = orderDAO.getOrdersByDateRange(startDate, endDate);
            }else if(!paramStartDate.isEmpty() && paramEndDate.isEmpty()){
                message = "Please fill in endDate!";
                orderList = orderDAO.getOrderList();
            }else if(paramStartDate.isEmpty() && !paramEndDate.isEmpty()){
                message = "Please fill in startDate!";
                orderList = orderDAO.getOrderList();
            }else if(paramStartDate.isEmpty() && paramEndDate.isEmpty()){
                message = "Please fill in startDate and endDate for searching!";
                orderList = orderDAO.getOrderList();
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("orderList", orderList);
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
