<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">На главную</a>
<h1>Редактирование профиля</h1>
<form action="profile" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="userName" value="${userName}"></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="userLastName" value="${userLastName}"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="userPass" value="${userPass}"></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="password" name="userRepeatPass" value="${userPass}"></td>
        </tr>
    </table>

    <input type="submit" value="update"/>

</form>
</body>
</html>
