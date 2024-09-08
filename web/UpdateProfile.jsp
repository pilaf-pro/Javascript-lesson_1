<%-- 
    Document   : UpdateProfile
    Created on : 07-07-2024, 15:15:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <h1>Update Profile</h1>
        </div>
        <div class="form-container">
            <form action="ProfileController" method="post" class="form">
                <label for="oldPassword">Old Password</label><br>
                <input type="password" id="oldPassword" name="txtOldPassword" required><br>
                <label for="password">New Password</label><br>
                <input type="password" id="password" name="txtPassword" required><br>
                <label for="repeat-password">Repeat Password</label><br>
                <input type="password" id="repeat-password" name="txtRepeatPassword" required><br>
                <label for="contactName">New ContactName</label><br>
                <input type="text" id="contactName" name="txtContactName" required><br>
                <label for="address">New Address</label><br>
                <input type="text" id="address" name="txtAddress" required><br>
                <label for="phone">New Phone</label><br>
                <input type="text" id="phone" name="txtPhone" required><br>
                <input class="button" type="submit" value="Update" name="action" /><br>
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
            <a href="Login.jsp">Back To Login Page</a>
        </div>
        <%
            }
        %>
    </body>
</html>
