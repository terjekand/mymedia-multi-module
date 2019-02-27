<%@ page import="org.mymedia.database.dao.UserDataBase" %>
<%@ page import="org.mymedia.database.entities.User" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Object username = request.getAttribute("USERNAME");
%>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="newsfeed/styles.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8" />
</head>

<body>
<div class="navbar">
    <a href="#"><img class="icon" src="newsfeed/img/discovery.png"></a>
    <a href="profile.html"><img class="icon" src="newsfeed/img/me.png"></a>
    <a href="news.html"><img class="icon" src="newsfeed/img/feed.png"></a>
    <div class="src-container">
        <form action="#">
            <input type="text" placeholder="Search..." onfocus="this.placeholder=''" onblur="this.placeholder = 'Search...'" name="search" />
        </form>
    </div>
</div>
<div class="post-container">
    <div class="author">
        <img class="avatar" src="newsfeed/img/avatar.jpg" alt="Profile Picture">
        <div class="author-name">kissd97</div>
    </div>
    <p class="author-date">2019. 01. 23.</p>
    <div class="content">
        <img class="content" src="newsfeed/img/content.jpg" alt="Post Content">
        <p class="content-text">If you say Jesus backwards, it sounds like sausage. Sausage is innuendo for "dick," and a sausage party is a party full of guys. Two men putting their sausages together is gay. Jesus is trying to say that gay sex is backwards and not in alignment with his word. Therefore, being gay is a sin. lmao</p>
    </div>
    <div class="like-section">
        <button class="like"></button>
        <p class="like-text">256 likes</p>
    </div>
</div>
</body>

</html>