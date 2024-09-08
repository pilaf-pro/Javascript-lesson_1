<%-- 
    Document   : RemoveProduct
    Created on : 06-07-2024, 18:49:55
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
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
            .button{
                border-radius: 4px;
                padding: 10px;
                border: none;
                transition-duration: 0.4s;
            }
            .button:hover{
                background-color: red;
                color: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <c:set var="ProductID" value="${param.ProductID}"/>
        <c:set var="product" value="${product}" />
        <c:if test="${product != null}">  
        <form action="ProductController" method="post">
            <div>
                <h1>Are you sure you want to delete this?</h1>
            </div>
            <h3>Product</h3>
            <div class="table-container">
                <table border="1">
                    <tr>
                        <td style="font-weight: bold;">ProductID</td>
                        <td>${product.productID}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">ProductName</td>
                        <td>${product.productName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">SupplierID</td>
                        <td>${product.supplierID}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">CategoryID</td>
                        <td>${product.categoryID}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">QuantityPerUnit</td>
                        <td>${product.quantityPerUnit}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">UnitPrice</td>
                        <td>${product.unitPrice}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold;">ProductImage</td>
                        <td>${product.productImage}</td>
                    </tr>
                </table>
            </div>
            <input type="hidden" name="ProductID" value="${param.ProductID}" /> 
            <input class="button" type="submit" value="Remove" name="action" /> |
            <a class="link-action" href=HomeController>Back to Home Page</a>
        </form>    
        </c:if>
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
