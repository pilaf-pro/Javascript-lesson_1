<%-- 
    Document   : Profile
    Created on : 07-07-2024, 14:42:21
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .link-action:link, .link-action:visited {
                background-color: white;
                color: black;
                border: 2px solid #f2f2f2;
                padding: 2.5px 5px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
              }
            .link-action:hover, .link-action:active {
              background-color: #f2f2f2;
              color: black;
            }
            table{
                border-top: none;
                border-bottom: none;
                border-right: none;
                border-left: none;
                border-collapse: collapse;
                width: 25%;
                margin-top: 20px;
            }
            th{
                padding-bottom: 10px;
                padding-right: 10px;
                padding-top: 10px;
                border-top: none;
                text-align: left;
                border-right: none;
                border-left: none;
                border-bottom: none;
            }
            td{
                padding-bottom: 10px;
                padding-right: 10px;
                padding-top: 10px;
                border-top: none;
                text-align: left;
                border-right: none;
                border-left: none;
                border-bottom: none;
               
            }
            .table-container{
                margin-bottom: 20px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div>
            <h1>Profile</h1>
        </div>    
        <c:set var="customer" value="${customer}" />
        <c:if test="${customer != null}">
            <div class="table-container">
                <table border="1">
                    <tr>
                        <td style="font-weight: bold;">CustomerID</td>
                        <td>${customer.customerID}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Password</td>
                        <td>${customer.password}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">ContactName</td>
                        <td>${customer.contactName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Address</td>
                        <td>${customer.address}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">Phone</td>
                        <td>${customer.phone}</td>
                    </tr>
                </table>
            </div>
            <a class="link-action" href="UpdateProfile.jsp">Update Profile</a> |
            <a class="link-action" href=HomeController>Back to Home Page</a>
        </c:if>
    </body>
</html>
