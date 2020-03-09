<%@ page import="models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.05.2019
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom.tld"%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<ex:KVOtable rows="1">${Login},${Password}</ex:KVOtable>
<fmt:requestEncoding value = "UTF-8" />
<fmt:setLocale value = "en"/>
<c:set var = "now" value = "<%=new java.util.Date()%>" />
<p>Date in Current Zone: <fmt:formatDate value = "${now}" type = "both" timeStyle = "long" dateStyle = "long" /></p>
<p>Change Time Zone to GMT-8</p>
<fmt:setTimeZone value = "GMT-8" />
<p>Date in Changed Zone: <fmt:formatDate value = "${now}" type = "both" timeStyle = "long" dateStyle = "long" /></p>
<c:import url="Header.jsp"/>
<c:set var="name" value="${Login}"/>
<p>Добрый день,<c:out value="${name}"/></p>
<c:set var="users" value="${items}"/>
<c:catch>
<TABLE border='1'>
<TR><TD>LOGIN</TD><TD>PASSWORD</TD><TD>Type</TD></TR>
<c:forEach var="user" items="${users}">
        <TR>
        <TD>${user.getName()}</TD>
        <TD>${user.getPassword()}</TD>
        <TD> ${user.getType()}</TD>
        </TR>
</c:forEach>
</TABLE>
</c:catch>
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
<c:import url="footer.jsp"/>
<button onclick='location.href="controller?command=loginpage"'>Вход</button>
<button onclick='location.href="controller?command=SignOut"'>Выход</button>
</body>
</html>
