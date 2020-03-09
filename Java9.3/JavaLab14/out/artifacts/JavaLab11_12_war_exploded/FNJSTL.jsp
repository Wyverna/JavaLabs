<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2019
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <title>FNJSTL</title>
</head>
<body>
<c:set var = "string1" value = "This is first String."/>
<c:set var = "string2" value = "${fn:split(string1, ' ')}" />
<c:set var = "string3" value = "${fn:join(string2, '-')}" />
<p>String (3) : ${string3}</p>
<c:set var = "string4" value = "${fn:split(string3, '-')}" />
<c:set var = "string5" value = "${fn:join(string4, ' ')}" />
<p>String (5) : ${string5}</p>
</body>
</html>
