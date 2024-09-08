<%-- 
    Document   : ProductDetails
    Created on : 06-07-2024, 18:49:20
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
            <h1>Product Details</h1>
        </div>
        <c:set var="product" value="${product}" />
        <c:if test="${product != null}">
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
            <a class="link-action" href=ProductController?action=Update&ProductID=${product.productID}>Edit</a> |
            <a class="link-action" href=HomeController>Back to Home Page</a>
        </c:if>
    </body>
</html>
