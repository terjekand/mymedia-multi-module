<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../styles/bscstyle.css">
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h2>SuperTopSecretPage</h2>
<h3>Number of logged in users: ${numberOfUsers}</h3>


<p>
    <form action="status" method="logoutAllUser">
        <button type="submit">Logout all user</button>
    </form>
</p>

<p>
    <form action="status" method="registrateTemplate1">
        <button type="submit" id = "registrateTemplate1">Registrate template user 1</button>
    </form>
    <form action="status" method="registrateTemplate2">
        <button type="submit" id = "registrateTemplate2">Registrate template user 2</button>
    </form>
<form action="status" method="registrateTemplate3">
    <button type="submit" id = "registrateTemplate3">Registrate template user 3</button>
</form>
</p>
<p>
    <form action="status" method="deleteTemplate1">
        <button type="submit" id = "deleteTemplate1">Delete template user 1</button>
    </form>
    <form action="status" method="deleteTemplate2">
        <button type="submit" id = "deleteTemplate2">Delete template user 2</button>
    </form>
<form action="status" method="deleteTemplate3">
    <button type="submit" id = "deleteTemplate3">Delete template user 3</button>
</form>
</p>
</body>
</html>