<%-- 
    Document   : Register
    Created on : 06-07-2024, 11:39:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Sign Up New Account</h1>
        </div>
        <div class="form-container">
            <form action="AddUserController" method="post" class="form">
                <label for="userName">UserName</label><br>
                <input type="text" id="username" name="txtUserName" required><br>
                <label for="password">Password</label><br>
                <input type="password" id="password" name="txtPassword" required><br>
                <label for="repeat-password">Repeat Password</label><br>
                <input type="password" id="repeat-password" name="txtRepeatPassword" required><br>
                <label for="fullName">FullName</label><br>
                <input type="text" id="fullName" name="txtFullName" required><br>
                <input class="button" type="submit" value="Sign Up" name="action" /><br>
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
        <div>
            <a href="Login.jsp">Go to Log In Page</a>
        </div>
        <%
            }
        %>
    </body>
</html>
