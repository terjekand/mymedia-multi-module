<%@ page import="org.mymedia.database.dao.UserDataBase" %>
<%@ page import="org.mymedia.database.entities.User" %>
<%@ page import="java.io.Writer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object username = request.getAttribute("USERNAME");
    UserDataBase USER_DATA_BASE;
    USER_DATA_BASE = UserDataBase.getDataBase();
    User user = USER_DATA_BASE.getUserByUsername(username.toString());
%>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../styles/newsfeed/styles.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8" />
</head>

<body>
</body>

</html>