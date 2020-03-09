<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.04.2019
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form registrate/authorize</title>
</head>
<body>
<form action="database" method="POST">
    Login: <input name="Login" />
    <br><br>
    Password: <input name="Password" />
    <br><br>
    <input type="submit" value="Authorize" />
</form>
<form action="databaseregistrate" method="POST">
    Login: <input name="Login" />
    <br><br>
    Password: <input name="Password" />
    <br><br>
    Type: <select name="Type">
    <option>admin</option>
    <option>user</option>
</select>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
