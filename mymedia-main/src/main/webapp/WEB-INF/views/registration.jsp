<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../styles/bscstyle.css">
    <title>Registration</title>
</head>
<body>
<h1>Register</h1>
<form action="registration" method="post">
    <table>

        <tr>
            <td>Full Name</td>
        </tr>

        <td>
            <center><input type="text" name=fullname></center>
        </td>
        </tr>

        <tr>
            <td>Email</td>
        </tr>

        <td><input type="text" name=email></td>
        </tr>

        <tr>
            <td>Username</td>
        </tr>

        <td><input type="text" name=username></td>
        </tr>

        <tr>
            <td>Password</td>
        </tr>

        <tr>
            <td><input type="password" name=password></td>
        </tr>

        <tr>
            <td><input type="submit" value="Registration"></td>
        </tr>

    </table>
</form>
</body>
</html>