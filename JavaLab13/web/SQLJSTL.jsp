<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2019
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<head>
    <title>SQLJSTL</title>
</head>
<body>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                   url = "jdbc:mysql://localhost:3306/Java8?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC"
                   user = "root"  password = "1111" />
<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * from Users;
</sql:query>
<table border = "1" width = "100%">
<tr>
    <th>Name</th>
    <th>Password</th>
    <th>Type</th>
</tr>
<c:forEach var = "row" items = "${result.rows}">
    <tr>
        <td> <c:out value = "${row.Name}"/></td>
        <td> <c:out value = "${row.Password}"/></td>
        <td> <c:out value = "${row.Type}"/></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
