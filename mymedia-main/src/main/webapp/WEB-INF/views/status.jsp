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

<form action="status" method="post">
    <p>
        <button type="submit" name="operator" value="logoutAllUser">Logout all user</button>
    </p>

    <p>
        <button type="submit" id = "registrateTemplate1" name="operator" value="registrateTemplate1">Registrate template user 1</button>
        <button type="submit" id = "registrateTemplate2" name="operator" value="registrateTemplate2">Registrate template user 2</button>
        <button type="submit" id = "registrateTemplate3" name="operator" value="registrateTemplate3">Registrate template user 3</button>
    </p>

    <p>
        <button type="submit" id = "deleteTemplate1" name="operator" value="deleteTemplate1">Delete template user 1</button>
        <button type="submit" id = "deleteTemplate2" name="operator" value="deleteTemplate2">Delete template user 2</button>
        <button type="submit" id = "deleteTemplate3" name="operator" value="deleteTemplate3">Delete template user 3</button>
    </p>

</form>
</body>
</html>