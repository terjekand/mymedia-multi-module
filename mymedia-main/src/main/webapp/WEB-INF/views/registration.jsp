<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles/bscstyle.css" />
        <title>Registration</title>
    </head>
    <body>
        <h1>Sing in</h1>
        <form action="registration" method="post">
            <table
                cellpadding = "8"
                align = center>

                <tr>
                    <td><center>Full Name</center></td>
                </tr>

                <td><center><input type="text" name = fullname></center></td>
                </tr>

                <tr>
                    <td><center>Email</center></td>
                </tr>

                <td><center><input type="text" name = email></center></td>
                </tr>

                <tr>
                    <td><center>Username</center></td>
                </tr>

                <td><center><input type="text" name = username></center></td>
                </tr>

                <tr>
                    <td><center>Password</center></td>
                </tr>

                <tr>
                    <td><center><input type="password" name = password></center></td>
                </tr>

                <tr>
                    <td><center><input type="submit" value = "Registration"></center></td>
                </tr>

            </table>
        </form>
    </body>
</html>