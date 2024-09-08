<%-- 
    Document   : ViewCart
    Created on : 08-07-2024, 16:29:05
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="Models.DTO.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
                color: #333;
            }

            div {
                width: 80%;
                margin: 20px auto;
                padding: 20px;
                background-color: #f4f4f4;
            }

            h1 {
                text-align: center;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 0 auto;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #f2f2f2;
            }

            input[type="number"] {
                width: 60px;
                padding: 5px;
                margin: 0;
            }

            input[type="submit"] {
                padding: 8px 12px;
                background-color: #5D5C61;
                color: #fff;
                border: none;
                cursor: pointer;
                margin: 5px;
            }

            input[type="submit"]:hover {
                background-color: #4A4A4D;
            }

            form {
                display: inline;
            }

            .message {
                text-align: center;
                color: #ff0000;
                font-weight: bold;
            }

            .total-row {
                font-weight: bold;
                text-align: right;
                background-color: #f2f2f2;
            }

            .continue-save-buttons {
                text-align: center;
                margin-top: 20px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div>
            <h1>Your Cart</h1>
        </div>
        <% 
            double totalAmount = 0;
            int numberOfPizzas = 0;
            List<Cart> itemsInCart = (List<Cart>)request.getAttribute("Cart");
        %>
        <%
            if(itemsInCart == null || itemsInCart.size() == 0){
        %>
        <div>
            <h3 style="color: red; text-align: center;">Cart is empty!!!</h3>
        </div>
        <%
            }
            else if(itemsInCart != null){
        %>
        <div>
            <table border = '1' style="width: 300px;">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>SubTotal</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for(Cart item : itemsInCart){
                            totalAmount+=item.getSubTotal();
                            numberOfPizzas+=item.getQuantity();
                    %>
                <form action="CartController" method="post">
                    <tr>
                        <td><%=(++count)%></td>
                        <td><%=item.getItemId()%><input type="hidden" value='<%=item.getItemId()%>' name="ItemId" /></td>
                        <td><%=item.getItemName()%></td>
                        <td><%=item.getUnitPrice()%></td>
                        <td>
                            <input type="number" min="1" name="quantity" value="<%=item.getQuantity()%>"/>
                        </td>
                        <td><%=String.format("%.2f",item.getSubTotal())%></td>
                        <td style="text-align: center">
                            <input type="submit" value="Remove" name="action" />
                        </td>
                        <td style="text-align: center;">
                            <input type="submit" value="Update" name="action" />
                        </td>
                    </tr>
                </form>
                        <%
                            }
                        %>
                        <tr class="total-row">
                            <td colspan="5" style="text-align: right"><b>Total Amount</b></td>
                            <td><%=String.format("%.2f",totalAmount)%></td>
                        </tr>
                </tbody>
            </table>
        </div>                
                        <h3 style="text-align: center;">Number of pizzas in cart: <%=numberOfPizzas%></h3>
                        <%
                            }
                        %>
                        <div class="continue-save-buttons">
                            <form method="post">
                                <input type="submit" formaction="HomeController" value="Continue"/>
                                <input type="submit" formaction="CartController?action=Save" value="Save Cart" />
                                <%
                                    if(request.getAttribute("Message")!=null){
                                %>
                                <input type="submit" formaction="Order.jsp" value="Order">
                                <%
                                    }
                                %>
                            </form>
                        </div>
                        <%
                            if(request.getAttribute("Message")!=null){
                        %>
                        <%=request.getAttribute("Message")%>
                        <%
                            }
                        %>
    </body>
</html>
