<%-- 
    Document   : ViewOrder
    Created on : 08-07-2024, 20:19:58
    Author     : Admin
--%>

<%@page import="java.time.LocalDate"%>
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
             .search-container button{
                width: 80px;
                margin: 0 auto;
                padding: 10px;
                border: none;
                border-radius: 4px;
                transition-duration: 0.4s;
                background-color: #58C1DD;
                color: white;
            }
            button:hover{
                background-color: #3A8093;
                color: white;
            }
            .search-container{
                margin: 0 auto;
            }
            .search-container form {
                margin-right: 30px;
            }
            input{
                padding: 6px 10px;
                margin: 8px 0;
                box-sizing: border-box;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <c:set var="accountLoggedIn" value="${accountLoggedIn}"/>
        <c:set var="accountType" value="${accountType}"/>
        
        <c:set var="startDate" value="${param.startDate}"/>
        <c:set var="endDate" value="${endDate}"/>
        
        <c:if test="${accountLoggedIn!=null && accountType == 1}">
            <div class="search-container">
                <h2>Orders</h2>
                <h3>Search Orders by Date Range</h3>
                <form action="SearchOrderByDateRange" method="post">
                    <div class="search-container-item">
                        <label for="startdate">Start Date:</label>
                        <input type="date" id="startdate" name="startDate" value="${startDate!=null?startDate:""}" required><br><br>

                        <label for="enddate">End Date:</label>
                        <input type="date" id="enddate" name="endDate" value="${endDate!=null?endDate:""}" required><br><br>
                    </div>
                    <button type="submit" value="Search" name="action">Search</button>
                </form>
            </div>
            <c:set var="message" value="${message}" />
            <c:if test="${message!=null}">
                <h3 style="color:red;"><c:out value="${message}"/></h3>
            </c:if>
        </c:if>
                
        <%
            LocalDate today = LocalDate.now();
            request.setAttribute("today", today);
        %>
        <c:set var="today" value="${today}"/>        
        
        <c:if test="${accountLoggedIn!=null && accountType == 2}">
            <h2>Order History</h2>
        </c:if>
        <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>OrderID</th>
                            <th>CustomerID</th>
                            <th>OrderDate</th>
                            <th>RequiredDate</th>
                            <th>ShippedDate</th>
                            <th>Freight</th>
                            <th>ShipAddress</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${orderList}" var="o">
                        <tbody>
                            <tr>
                                <td>${o.orderID}</td>
                                <td>${o.customerID}</td>
                                <td>${o.orderDate}</td>
                                <td>${o.requiredDate}</td>
                                <td>${o.shippedDate}</td>
                                <td>$${o.freight}</td>
                                <td>${o.shipAddress}</td>
                                <td>
                                    <a class="link-action" href="ViewOrdersDetailsController?OrderID=${o.orderID}">Details</a>
                                    <c:if test="${today.isBefore(o.shippedDate)}">
                                       |  <a class="link-action" href="DeleteOrderController?OrderID=${o.orderID}">Delete</a>
                                    </c:if>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach> 
                </table>
            </div>
            <c:set var="message" value="${message}" />
            <c:if test="${message!=null}">
                <h3 style="color:red;"><c:out value="${message}"/></h3>
            </c:if>
            <div>
                <a href=HomeController>Back To Home Page</a>
            </div>
    </body>
</html>
