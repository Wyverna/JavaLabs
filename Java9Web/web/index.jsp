<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Java9Web</title>
  </head>
  <body>
  <button onclick='location.href="http://localhost:8080/web_war_exploded/hello"'>Время</button>
  <button onclick='location.href="http://localhost:8080/web_war_exploded/authorize.jsp"'>Authorize/registrate</button>
  <button onclick='location.href="http://localhost:8080/web_war_exploded/Servlet4?id=1"'>Redirect</button>
  <button method="GET" onclick='location.href="http://localhost:8080/web_war_exploded/Servlet6?id=1"'>Forward1</button><br><br>
  <form action="Servlet6" method="POST">
    ID: <input name="ID" />
    <br><br>
    <input type="submit" value="Forward2" />
  </form>
  <button method="GET" onclick='location.href="http://localhost:8080/web_war_exploded/Servlet6"'>Forward3</button>
  </body>
</html>