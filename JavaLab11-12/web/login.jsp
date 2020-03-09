<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Java11-12Web</title>
  </head>
  <body>
  <%@ include file="Header.jsp" %>
  <%
  String name=request.getParameter("Login");
  if(name!=null)
  {%>
  <h1 style="color:Red">Неверный логин или пароль, введите все поля</h1>
  <%}%>
  <form action="controller?command=login" method="Post">
    Login: <input name="Login" />
    <br><br>
    Password: <input name="Password" />
    <br><br>
    <input type="submit" value="Authorize" />
  </form>
  <button onclick='location.href="controller?command=RegisterPage"'>Регистрация</button>
  <%@ include file="footer.jsp" %>
  </body>
</html>