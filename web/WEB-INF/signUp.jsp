<%--
  Created by IntelliJ IDEA.
  User: kiki
  Date: 2/28/21
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="sign_up">
        Enter name:<br/>
        <input type="text" name="name" value=""/><br/>
        Enter surname:<br/>
        <input type="text" name="surname" value=""/><br/>
        Enter login:<br/>
        <input type="text" name="login" value=""/><br/>
        Enter password:<br/>
        <input type="password" name="password" value=""/><br/>
        Enter email:<br/>
        <input type="text" name="email" value=""/><br/>
        Enter date:<br/>
        <input type="date" name="date" value=""/><br/>
        <input type="submit" value="Зарегистрироваться">

    </form>
<jsp:include page="footer.jsp"/>
</body>
</html>
