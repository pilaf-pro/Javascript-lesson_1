<%-- 
    Document   : Header
    Created on : 23-06-2024, 15:25:06
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.DTO.Account"%>
<%@page import="Models.DTO.Categories"%>
<%@page import="Models.DTO.Products"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            header {
                background-color: #58C1DD;
            }

            nav ul {
                list-style: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
            }

            nav ul li {
                float: left;
            }

            nav ul li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            nav ul li a:hover {
                background-color: #3A8093;
            }

            nav ul li ul {
                display: none;
                position: absolute;
                min-width: 160px;
            }

            nav ul li:hover ul {
                display: block;
            }

            nav ul li ul li {
                float: none;
            }

            nav ul li ul li a {
                padding: 12px 16px;
            }

            nav ul li.right {
                float: right;
            }
            .dropdown-content {
              display: none;
              position: absolute;
              background-color: #f9f9f9;
              min-width: 160px;
              box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
              z-index: 1;
            }
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
            }
            .dropdown-content a:hover {
                background-color: #ddd;
            }
            .dropdown.show .dropdown-content {
                display: block;
            }
            a{
                font-family: sans-serif;
            }
        </style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="accountLoggedIn" value="${accountLoggedIn}"/>
        <c:set var="accountType" value="${accountType}"/>
        <c:set var="customer" value="${customer}" />
        <c:if test="${accountLoggedIn!=null}">
            <c:set var="loggedIn" value="${true}"/>
        </c:if>
        <c:if test="${accountLoggedIn==null}">
            <c:set var="loggedIn" value="${false}"/>
        </c:if>
        <header>
            <nav>
                <ul>
                    <li><a href="HomeController">PizzaStore</a></li>
                    <li><a href="#">Pizzas</a></li>
                    <li class="dropdown"><a onclick="toggleDropdown()" class="dropbtn">Categories <i class="fa fa-caret-down"></i></a>
                        <ul class="dropdown-content" id="myDropdown">
                            <c:forEach items="${CategoryList}" var="x">
                                <li><a href="CategoryListController?categoryID=${x.categoryID}">${x.categoryName}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a href="#">Reviews</a></li>
                    <c:if test="${accountLoggedIn!=null && loggedIn && accountType == 1}">
                        <li><a href="ViewOrdersController">Orders</a></li>
                        <li class="right"><a href="LogoutController">Log out</a></li>
                        <li class="right"><a href="#">Welcome,admin</a></li>
                        <li class="right"><a href="#">Admin Page</a></li>
                    </c:if>
                    <c:if test="${accountLoggedIn!=null && loggedIn && accountType == 2}">
                        <li><a href="ViewOrdersController">Order History</a></li>
                        <li class="right"><a href="CartController?action=ViewCart"><i class="fas fa-shopping-cart"></i></a></li>
                        <li class="right"><a href="LogoutController">Log out</a></li>
                        <li class="right"><a href="#">Welcome, <c:out value="${sessionScope.accountLoggedIn.fullName}"/></a></li>
                        <li class="right" class="dropdown"><a onclick="toggleDropdown()" class="dropbtn">Profile <i class="fa fa-caret-down"></i></a>
                            <ul class="dropdown-content" id="myDropdown">
                                <c:if test="${customer != null}"> 
                                    <li><a href="ProfileController?action=View">View Profile</a></li>
                                </c:if>
                                <c:if test="${customer == null}"> 
                                    <li><a href="AddProfile.jsp">Add Profile</a></li>
                                </c:if>
                                <c:if test="${customer != null}"> 
                                    <li><a href="UpdateProfile.jsp">Update Profile</a></li>
                                </c:if>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${accountLoggedIn==null && loggedIn == false}">
                        <li class="right"><a href="Login.jsp">Log in</a></li>
                        <li class="right"><a href="Register.jsp">Register</a></li>
                    </c:if>
                </ul>
            </nav>
        </header>
        <script>
        function toggleDropdown() {
            var dropdown = document.getElementById("myDropdown");
            dropdown.parentElement.classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown");
                for (var i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
        </script>
    </body>
</html>
