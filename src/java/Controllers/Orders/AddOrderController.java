/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Orders;

import Models.DAO.OrdersDAO;
import Models.DAO.OrdersDetailsDAO;
import Models.DTO.Account;
import Models.DTO.Cart;
import Models.DTO.OrderDetails;
import Models.DTO.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
public class AddOrderController extends HttpServlet {
    private final String orderPage = "Order.jsp";
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
        String url = null;
        String message = null;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("accountLoggedIn");    
            if(account == null){
                    url = loginPage;
            }else{
                //Lấy giỏ hàng từ session
                HashMap<String, Cart> cart = (HashMap<String, Cart>) session.getAttribute("Cart");
                if (cart != null) {
                    List<Cart> itemsInCart = new ArrayList<>(cart.values());
                    double totalAmount = 0;
                    for (Cart cartItem : itemsInCart) {
                        totalAmount += cartItem.getSubTotal();
                    }
                    OrdersDAO orderDAO = new OrdersDAO();
                    OrdersDetailsDAO orderDetailsDAO = new OrdersDetailsDAO();
                    
                    String orderID = "ORD" + session.getId();
                    if(orderDAO.getOrderByOrderID(orderID) != null){
                        orderID = "ORD" + session.getId() + "a"; 
                    }
                    
                    String customerID = (String) session.getAttribute("customerID");

                    LocalDate orderDate = LocalDate.parse(request.getParameter("txtOrderDate"));
                    LocalDate requiredDate = LocalDate.parse(request.getParameter("txtRequiredDate"));
                    LocalDate shippedDate = LocalDate.parse(request.getParameter("txtShippedDate"));

                    double Freight = totalAmount*0.1;
                    request.setAttribute("freight", String.format("%.2f", Freight));
                    String shipAddress = request.getParameter("txtShipAddress");
                    
                    request.setAttribute("totalAmount", String.format("%.2f", totalAmount));
                    request.setAttribute("totalOrder", String.format("%.2f", totalAmount + Freight));
                    url = orderPage;
                    
                    Orders order = new Orders(orderID, customerID, orderDate, requiredDate, shippedDate, Freight, shipAddress);
                    boolean orderAdded = orderDAO.addOrder(order);

                    if(orderAdded){
                        //Lưu chi tiết đơn hàng
                        List<OrderDetails> orderDetailsList = new ArrayList<>();

                        for(Cart cartItem : itemsInCart){
                            OrderDetails orderDetails = new OrderDetails();
                            orderDetails.setOrderID(orderID);
                            orderDetails.setProductID(cartItem.getItemId());
                            orderDetails.setUnitPrice(cartItem.getUnitPrice());
                            orderDetails.setQuantity(cartItem.getQuantity());
                            orderDetailsList.add(orderDetails);
                        }

                        boolean orderDetailsAdded = orderDetailsDAO.addOrderDetails(orderDetailsList);
                        if(orderDetailsAdded){
                            session.setAttribute("order", order);
                            session.setAttribute("orderID", order.getOrderID());
                            message = "Ordered successfully";
                        }else {
                            message = "Failed to save order details.";
                        }
                    }else {
                        message = "Failed to place order.";
                    }
                } else if(cart == null) {
                    message = "Your cart is empty.";
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
