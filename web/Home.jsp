<%-- 
    Document   : Home
    Created on : 21-06-2024, 14:04:22
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DTO.Categories"%>
<%@page import="Models.DTO.Products2"%>
<%@page import="Models.DTO.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-color: #f2f2f2;
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
                margin-top: 20px;
                margin-left: 20px;
                display: flex; /* Sử dụng flexbox để các form nằm ngang */
                justify-content: flex-start; /* Các form sẽ được căn giữa và cách nhau một khoảng */
                margin-bottom: 20px; /* Khoảng cách dưới cùng để tránh chồng lên nhau */
            }
            .search-container form {
                margin-right: 30px;
            }
            input{
                padding: 6px 10px;
                margin: 8px 0;
                box-sizing: border-box;
            }
            input[type=text] {
                border: 0.5px solid gray;
                border-radius: 4px;
            }
            input[type=password] {
                border: 0.5px solid gray;
                border-radius: 4px;
            }
            label{
                color: black;
                margin-right: 20px;
                font-family: sans-serif;
            }
            .search-container-item{
                margin: 0 auto;
            }
            .line-container{
                content: '';
                display: block;
                height: 1px; /* Độ dày của đường thẳng */
                background-color: #EEEFF0; /* Màu sắc của đường thẳng */
                width: 100%; /* Độ rộng của đường thẳng */
                margin: 20px 0; /* Khoảng cách trên và dưới */
            }
            body{
                margin: 0;
            }
            .gallery {
                display: flex;
                flex-wrap: wrap;
                width: 100%;
                justify-content: center;
                align-items: center;
                margin: 50px 0;
            }
            .content {
                width: 25%;
                margin: 15px;
                box-sizing: border-box;
                float: left;
                text-align: center;
                border-radius: 20px;
                border: 0.5px solid #E8E8E8;
                cursor: pointer;
                padding-top: 10px;
                box-shadow: 0 14px 28px 0 rgba(0,0,0,0.25),0 10px 10px rgba(0,0,0,0,22);
                background-color: #FFFFFF;
                transition: .4s;
            }
            
            .content:hover{
                box-shadow: 0 3px 6px rgba(0,0,0,0.16),0 3px 6px rgba(0,0,0,0.23);
                transform: translate(0px, -8px);
            }
            .content img {
                width: 200px;
                height: 200px;
                text-align: center;
                margin: 0 auto;
                display: block;
            }
            .content h4 {
                font-weight: normal;
                font-size: 25px;
                font-family: sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                text-align: center;
                color: #4078AF;
                padding-bottom: 10px;
                padding-top: 10px;
            }
            .content p {
                text-align: center;
                color: #b2bec3;
                padding-top: 0 8px;
            }
            
            .content h6{
                font-size: 20px;
                text-align: center;
                color: #222f3e;
                margin: 0;
            }

            .content button {
                text-align: center;
                font-size: 24px;
                color: white;
                width: 100%;
                padding: 15px;
                border: 0;
                outline: none;
                cursor: pointer;
                margin-top: 5px;
                border-bottom-left-radius: 20px;
                border-bottom-right-radius: 20px;
            }
            .buy-1{
               background-color: #58C1DD;
            }

            .content button:hover {
               background-color: #3A8093;
            }
            .button {
                background-color: #5DB85B; /* Green */
                width: 60px;
                padding: 10px;
                border: none;
                border-radius: 4px;
                transition-duration: 0.4s;
                color: white;
                text-decoration: none;
                margin-left: 0;
                margin-bottom: 20px;
                margin-top: 10px;
            }
            .button:hover {
                background-color: #3E7A3C;
                color: white;
            }
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <c:set var="searchNameValue" value="${param.txtSearchNameValue}" />
        <c:set var="searchMinValue" value="${param.txtMinValue}" />
        <c:set var="searchMaxValue" value="${param.txtMaxValue}" />
        
        <c:set var="searchIdValue" value="${param.txtSearchIdValue}" />
        <c:set var="accountLoggedIn" value="${accountLoggedIn}"/>
        <c:set var="accountType" value="${accountType}"/>
        
        <c:if test="${accountLoggedIn!=null}">
            <c:set var="loggedIn" value="${true}"/>
        </c:if>
        
        <c:if test="${accountLoggedIn==null}">
            <c:set var="loggedIn" value="${false}"/>
        </c:if>
        
        
        <c:if test="${(accountLoggedIn==null && loggedIn == false) || (accountLoggedIn!=null && loggedIn && accountType == 2)}">
            <div class="text-container">
                <p style="font-size: 30px; font-family: sans-serif; margin-bottom: 0;">Pizzas Menu</p>
            </div>
            <div class="search-container">
                
                <form action="SearchProductController" method="post">
                    <div class="search-container-item">
                        <h3>Search By Name</h3>
                        <label for="searchNameValue">Enter Pizza's Name</label><br>
                        <input id="searchNameValue" type="text" name="txtSearchNameValue" value="${searchNameValue!=null?searchNameValue:""}"/><br/>
                    </div>
                    <button type="submit" value="Search" name="action">Search</button>
                </form>
                    
                <form action="SearchProductByPriceController" method="post">
                    <div class="search-container-item">
                        <h3>Search By Price</h3>
                        <label for="minValue">Enter Min Price </label>
                        <input id="minValue" type="text" name="txtMinValue" value="${searchMinValue!=null?searchMinValue:""}"><br>
                        <label for="maxValue">Enter Max Price </label>
                        <input id="maxValue" type="text" name="txtMaxValue" value="${searchMaxValue!=null?searchMaxValue:""}"><br>
                    </div>
                    <button type="submit" value="Search" name="action">Search</button>
                </form>    
                        
            </div>
            <c:set var="message" value="${message}" />
            <c:if test="${message!=null}">
                <h3 style="color:red;"><c:out value="${message}"/></h3>
            </c:if>
            <p style="font-size: 15px; font-family: sans-serif; margin-bottom: 0;">All Pizzas</p>
            <div class="line-container"></div>
            
                <%
                    if(request.getAttribute("Message")!=null){
                %>
                        <%=request.getAttribute("Message")%>
                <%
                    }
                %>
            
            <div class="gallery">
                <c:if test="${(accountLoggedIn==null && loggedIn == false) || (accountLoggedIn!=null && loggedIn && accountType == 2)}">
                    <c:forEach items="${ProductList}" var="o">
                        <div class="content">
                            <img src="${o.productImage}" alt="${o.productName}">
                            <h4>${o.productName}</h4>
                            <h6>$${o.unitPrice}</h6>
                            <p><b>Category: ${o.categoryName}</b></p>
                            <p>${o.description}</p>
                            <button class="buy-1"><a class="custom-link" href=CartController?action=Add&ProductID=${o.productID}>Add To Cart</a></button>
                        </div>
                    </c:forEach> 
                </c:if>
            </div>
            <jsp:include page="Footer.jsp"/>   
        </c:if>
            
        
        <c:if test="${accountLoggedIn!=null && loggedIn && accountType == 1}">
            <div class="text-container">
                <p style="font-size: 30px; font-family: sans-serif; margin-bottom: 20px; margin-top: 20px;">Pizzas<p>
            </div>
            <a class="button" href="AddProduct.jsp">Create New</a>
            <div class="search-container">
                <form action="SearchProductByIdController" method="post">
                    <div class="search-container-item">
                        <label for="searchNameValue"><b>Search</b></label>
                        <i class="glyphicon glyphicon-search"></i>
                        <input id="searchNameValue" type="text" name="txtSearchIdValue" value="${searchIdValue!=null?searchIdValue:""}"/><br/>
                    </div>
                    <button type="submit" value="Search" name="action">Search</button>
                </form>
            </div>
            <c:set var="message" value="${message}" />
            <c:if test="${message!=null}">
                <h3 style="color:red;"><c:out value="${message}"/></h3>
            </c:if>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>ImageUrl</th>
                            <th>IsPizzaOfTheWeek</th>
                            <th>Category</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach items="${ProductList}" var="o">
                        <tbody>
                            <tr>
                                <td style="width: 200px;">${o.productName}</td>
                                <td style="width: 150px;">$${o.unitPrice}</td>
                                <td style="width: 500px;">${o.description}</td>
                                <td style="width: 700px;">${o.productImage}</td>
                                <td style="width: 150px;"><input type="checkbox" <c:if test="${o.unitPrice<=14.99}" >checked="true"</c:if> /></td>
                                <td style="width: 150px;">${o.categoryName}</td>
                                <td style="width: 150px;">
                                    <a class="link-action" href="ProductController?action=Update&ProductID=${o.productID}">Edit</a> |
                                    <a class="link-action" href="ProductController?action=Details&ProductID=${o.productID}">Details</a> |
                                    <a class="link-action" href="ProductController?action=Remove&ProductID=${o.productID}">Delete</a>
                                </td>
                            </tr>
                        </tbody>
                    </c:forEach> 
                </table>
            </div>
            <jsp:include page="Footer.jsp"/>    
        </c:if>
    </body>
</html>
