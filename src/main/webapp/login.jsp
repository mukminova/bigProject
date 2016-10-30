<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">На главную</a>
<h1>Аутентификация</h1>
<form action="authentication" method="POST">
    <table>
        <tr>
            <td>User email:</td>
            <td><input type="email" name="userEmail"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="userPass"></td>
        </tr>
    </table>
    <input type="submit" value="authentication">
</form>
</body>
</html>