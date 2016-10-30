<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">На главную</a>
<h1>Регистрация</h1>
<form action="registration" method="post">
    <table>
        <tr>
            <td>
                <label>User email*:</label>
            </td>
            <td><input type="email" name="userEmail"/></td>
        </tr>
        <tr>
            <td><label>Password*:</label></td>
            <td><input type="password" name="userPass"/></td>
        </tr>
        <tr>
            <td><label>Repeat password*:</label></td>
            <td><input type="password" name="userRepeatPass"/></td>
        </tr>
        <tr>
            <td><label>Name*:</label></td>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <td><label>Last name:</label></td>
            <td><input type="text" name="userLastName"/></td>
        </tr>
    </table>

    <input type="submit" value="registration"/>

</form>
</body>
</html>
