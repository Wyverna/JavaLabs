<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<html>
  <head>
    <title>Java11-12Web</title>
  </head>
  <body>
  <c:import url="Header.jsp"/>
  <c:set var="name" value="${Login}"></c:set>
  <h1 style="color:Red">
  <c:if test="${not empty name}" var="result">
  Неверный логин или пароль, введите все поля
  </c:if>
  </h1>
  <form action="controller?command=login" method="Post">
    Login: <input name="Login" />
    <br><br>
    Password: <input name="Password" />
    <br><br>
    <ex:KVOsubmit></ex:KVOsubmit>
  </form>
 <c:import url="footer.jsp"></c:import>
  </body>
</html>