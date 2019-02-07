<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <!DOCTYPE html>
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
</html> --%>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newsfeed/styles.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8"/>
</head>

<body>
<div class="navbar-bg">
    <div class="navbar">
        <a href="#">Discover</a>
        <a href="#">Me</a>
        <a href="#">News</a>
        <div class="src-container">
            <form action="">
                <input type="text" placeholder="Search..." name="search"/>
                <button type="submit"><i class="fa fa-search"></i></button>
            </form>
        </div>
    </div>
</div>
<div class="post-container">
    <div class="author">
        <img class="avatar" src="${pageContext.request.contextPath}/resources/newsfeed/img/avatar.jpg"
             alt="Profile Picture">
        <div class="author-name">Kiss Dávid</div>
        <br>
        <p class="author-date">2019. 01. 23.</p>
    </div>
    <div class="content">
        <img class="content" src="${pageContext.request.contextPath}/resources/newsfeed/img/content.jpg"
             alt="Post Content">
        <p class="content-text">If you say Jesus backwards, it sounds like sausage. Sausage is innuendo for "dick," and
            a sausage party is a party full of guys. Two men putting their sausages together is gay. Jesus is trying to
            say that gay sex is backwards and not in alignment with his word. Therefore, being gay is a sin. lmao</p>
    </div>
    <div class="like-section">
        <button class="like"></button>
        <p class="like-text">256 likes</p>
    </div>
</div>
</body>

</html>