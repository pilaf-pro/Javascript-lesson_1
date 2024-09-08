<%-- 
    Document   : Order
    Created on : 08-07-2024, 20:19:09
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="Models.DTO.Cart"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .header{
                border-bottom: 1px solid #ddd;
            }
            input{
                padding: 6px 10px;
                margin: 8px 0;
                box-sizing: border-box;
            }
            input[type=text] {
                border: 1px solid gray;
                border-radius: 4px;
            }
            label{
                color: gray;
            }
            .form-container{
                margin-top: 10px;
            }
            .button{
                padding: 10px;
                border: none;
                border-radius: 4px;
                transition-duration: 0.4s;
            }
            .button:hover{
                background-color: blue;
                color: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div>
        <%
            LocalDate Today = LocalDate.now();
            String address = (String) session.getAttribute("address");
            
            HashMap<String, Cart> cart = (HashMap<String, Cart>) session.getAttribute("Cart");
            List<Cart> itemsInCart = new ArrayList<>(cart.values());
      
            double totalAmount = 0;
                    for (Cart cartItem : itemsInCart) {
                        totalAmount += cartItem.getSubTotal();
                    }
                   
            double Freight = totalAmount*0.1;
            
            request.setAttribute("freight", String.format("%.2f", Freight));
            request.setAttribute("totalAmount", String.format("%.2f", totalAmount));
            request.setAttribute("totalOrder", String.format("%.2f", totalAmount + Freight));
            
            String txtFreight = (String)request.getAttribute("freight");
            String txtTotalAmount = (String)request.getAttribute("totalAmount");
            String txtTotalOrder = (String)request.getAttribute("totalOrder");
            
        %>
            <h2>Order Information</h2>
            <form action="AddOrderController" method="post">
                <label for="orderDate">OrderDate</label><br>
                <input type="date" id="orderDate" name="txtOrderDate" value="<%=Today%>" readonly="true"><br>
                <label for="requiredDate">RequiredDate</label><br>
                <input type="date" id="requiredDate" name="txtRequiredDate" value="<%=Today.plusDays(7)%>" readonly="true"><br>
                <label for="shippedDate">ShippedDate</label><br>
                <input type="date" id="shippedDate" name="txtShippedDate" value="<%=Today.plusDays(3)%>" readonly="true"><br>
                <label for="freight">Freight</label><br>
                <input type="text" id="freight" name="txtFreight" value="<%=txtFreight%>" readonly><br>
                <label for="shippedAddress">ShippedAddress</label><br>
                <input type="text" id="shippedAddress" name="txtShipAddress" value="<%=address%>"required><br>
                <label for="totalAmount">Total Amount</label><br>
                <input type="text" id="totalAmount" name="totalAmount" value="<%=txtTotalAmount%>" readonly><br>
                <label for="totalOrder">Total Order</label><br>
                <input type="text" id="totalOrder" name="totalOrder" value="<%=txtTotalOrder%>" readonly><br>
                <input class="button" type="submit" value="Order" name="action" />
                <input class="button" type="submit" formaction="ViewCartController" value="Back To View Cart"/><br>
            </form>
        </div>
          
                
        <%
            String message = (String) request.getAttribute("message");
            if(message != null){
        %>
        <div>
            <h3 style="color:red;"><%=message%></h3>
        </div>
        <div>
            <a href=HomeController>Back To Home Page</a>
        </div>
        <%
            }
        %>
             
    </body>
</html>
