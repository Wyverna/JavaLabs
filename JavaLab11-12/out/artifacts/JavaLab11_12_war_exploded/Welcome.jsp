<%@ page import="models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.05.2019
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<% String name =request.getParameter("Login");%>
<p>Добрый день,<%out.print(name);%></p>
<% List<User> users=(List<User>) request.getAttribute("items");
out.print("<TABLE border='1'>");
out.print("<TR><TD>LOGIN</TD><TD>PASSWORD</TD><TD>Type</TD>" +
    "</TR>");
    for (User user:users)
    {
        out.print("<TR>");
        // Получение очередного сотрудника из коллекции
        // Заполнение строки таблицы свойствами сотрудника
        out.print("<TD>" + user.getName()+ "</TD>");
        out.print("<TD>" + user.getPassword()+ "</TD>");
        out.print("<TD>" + user.getType()+ "</TD>");
        out.print("</TR>");
    }
out.print("</TABLE>");
%>
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
<button onclick='location.href="controller?command=loginpage"'>Вход</button>
<button onclick='location.href="controller?command=SignOut"'>Выход</button>
</body>
</html>
