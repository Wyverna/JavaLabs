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
    <title>Form registrate</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<%
    String name=request.getParameter("Login");
    String password=request.getParameter("Password");
    if (name!=null & password!=null)
    {
    if(name.length()==0 || password.length()==0)
    {%>
<h1 style="color:Red">Введите все поля</h1>
<%}}%>
<form action="controller?command=register" method="POST">
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
<%@ include file="footer.jsp" %>
</body>
</html>
