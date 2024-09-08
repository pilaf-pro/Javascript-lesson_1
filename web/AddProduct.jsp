<%-- 
    Document   : AddProduct
    Created on : 02-07-2024, 18:20:12
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.DTO.Categories"%>
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
        <div class="header">
            <h1>Insert New Pizza</h1>
        </div>
        <div class="form-container">
            <form action="ProductController" method="post" class="form">
                <label for="productId">ProductID</label><br>
                <input type="text" id="productId" name="txtProductID" required><br>
                <label for="productName">ProductName</label><br>
                <input type="text" id="productName" name="txtProductName" required><br>
                <label for="supplierID">SupplierID</label><br>
                <input type="text" id="supplierID" name="txtSupplierID" required><br>
                <label for="categoryID">CategoryID</label><br>
                <input type="text" id="categoryID" name="txtCategoryID"/><br>
                <label for="quantityPerUnit">QuantityPerUnit</label><br>
                <input type="text" id="quantityPerUnit" name="txtQuantityPerUnit" required><br>
                <label for="unitPrice">UnitPrice</label><br>
                <input type="text" id="unitPrice" name="txtUnitPrice" required><br>
                <label for="productImage">ProductImage</label><br>
                <input type="text" id="productImage" name="txtProductImage" required><br>
                <input class="button" type="submit" value="Add" name="action" /><br>
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
