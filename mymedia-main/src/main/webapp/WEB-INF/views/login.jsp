<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="../styles/bscstyle.css">
        <title>Login</title>
    </head>
    <body vlink = white>
        <h1>Sing in</h1>
        <form action="login" method="post">
            <table>

                <tr>
                    <td>Username</td>
                </tr>

                <tr>
                    <td><input type="text" name = username></td>
                </tr>

                <tr>
                    <td>Password</td>
                </tr>

                <tr>
                    <td><input type="password" name = password></td>
                </tr>

                <tr>
                    <td><input type="submit" value = "login"></td>
                </tr>

                <tr>
                    <td><a href ="registration" >Registration</td>
                </tr>

            </table>
        </form>
    </body>