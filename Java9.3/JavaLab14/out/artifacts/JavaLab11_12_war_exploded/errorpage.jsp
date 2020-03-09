<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.05.2019
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ErrorPage</title>
</head>
<body>
<table>
    <tr>
        <td>Status Code:</td>
        <td><%out.print(request.getAttribute("errorMessage"));%></td>
    </tr>
</table>
</body>
</html>
