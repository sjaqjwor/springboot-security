<%--
  Created by IntelliJ IDEA.
  User: seungki
  Date: 18. 4. 11
  Time: 오후 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form method="post" action="/login-process">
    <label>
        <input type="text"  placeholder="id" name="id" >
    </label>
    <label>
        <input type="password"  placeholder="password" name="pass">
    </label>
    <button type="submit">Login</button>
</form>

</body>
</html>
