<%@ page import="org.mymedia.database.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.mymedia.database.dao.UserDataBase" %>
<%@ page import="mymediaMain.services.AuthService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../styles/bscstyle.css">
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
    <h1>You are authorized ${FULLNAME}</h1>
    <h3>Your username is: ${USERNAME}</h3>
    <h3>Your email is: ${EMAIL}</h3>
    <h3>your userid is: ${userid} (it comes from the login)</h3>
    </body>
</html>