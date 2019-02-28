<%@ page import="org.mymedia.database.dao.UserDataBase" %>
<%@ page import="org.mymedia.database.entities.User" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object username = request.getAttribute("USERNAME");
    Cookie[] cookies = request.getCookies();
    Cookie token = null;
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("token")){
            token = cookie;
            break;
        }
    }
    String tokenValue = token.getValue();
%>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="newsfeed/styles.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8" />
</head>

<body>
<div>
    <form action="logout" method="get">
        <button type = 'submit' value = <%! tokenValue  %> name = "logout">LOGOUT</button>
    </form>
</div>



</body>

</html>