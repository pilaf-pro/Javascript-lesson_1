<%-- 
    Document   : ViewOrderDetails
    Created on : 09-07-2024, 21:32:51
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .table-container{
                margin-top: 20px;
            }
            table{
                border-top: none;
                border-right: none;
                border-left: none;
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            td, th{
                text-align: left;
                border-right: none;
                border-left: none;
                border-bottom: 1px solid #ddd;
                padding-top: 20px;
                padding-bottom: 20px;
            }
            th{
                font-weight: bold;
            }
            .custom-link {
                color: white;          
                text-decoration: none; 
            }
            .custom-link:hover {
                color: white;          
                text-decoration: none; 
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="Header.jsp"/>
        <h2>Order Details</h2>
        <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>OrderID</th>
                            <th>ProductID</th>
                            <th>UnitPrice</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <c:forEach items="${orderDetailList}" var="o">
                        <tbody>
                            <tr>
                                <td>${o.orderID}</td>
                                <td>${o.productID}</td>
                                <td>${o.unitPrice}</td>
                                <td>${o.quantity}</td>
                            </tr>
                        </tbody>
                    </c:forEach> 
                </table>
            </div>
            <div>
                <a href=HomeController>Back To Home Page</a>
            </div>
    </body>
</html>
