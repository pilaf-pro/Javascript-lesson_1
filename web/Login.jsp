<%-- 
    Document   : Login
    Created on : 21-06-2024, 14:03:49
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .line-container{
                content: '';
                display: block;
                height: 1px; /* Độ dày của đường thẳng */
                background-color: #EEEFF0; /* Màu sắc của đường thẳng */
                width: 100%; /* Độ rộng của đường thẳng */
                margin: 20px 0; /* Khoảng cách trên và dưới */
            }
            .login-container{
                margin-left: 80px;
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
            input[type=password] {
                border: 1px solid gray;
                border-radius: 4px;
            }
            label{
                color: black;
                margin-right: 20px;
                font-family: sans-serif;
            }
            .password-container{
                margin-left: 9px;
            }
            button{
                width: 60px;
                margin-left: 110px;
                padding: 10px;
                border: none;
                border-radius: 4px;
                transition-duration: 0.4s;
                background-color: #337AB7;
                color: white;
            }
            button:hover{
                background-color: #22517A;
                color: white;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div class="text-container">
            <p style="font-size: 30px; font-family: sans-serif; margin-bottom: 0;">Please log in here.<p>
            <p style="font-family: sans-serif; margin-top: 0;">Enter your details below</p>
        </div>
        <div class="line-container"></div>
        <div class="login-container">
            <form action="LoginController" method="post">
                <div class="username-container">
                    <label for="username"><b>User Name</b></label>
                    <input type="text" id="username" name="txtUserName" required/><br/>
                </div>
                <div class="password-container">
                    <label for="password"><b>Password</b></label>
                    <input type="password" id="password" name="txtPassword" required/><br/>
                </div>
                <button type="submit" value="Login" name="action">Log in</button>
            </form>
        </div>
    </body>
    <c:set var="message" value="${message}" />
        <c:if test="${message!=null}">
            <h3 style="color:red;"><c:out value="${message}"/></h3>
        </c:if>
        <%
            String message = (String) request.getAttribute("message");
            if(message != null){
        %>
        <div>
            <h3 style="color:red;"><%=message%></h3>
        </div>
        <%
            }
        %>
</html>
