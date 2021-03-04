<%--
  Created by IntelliJ IDEA.
  User: kiki
  Date: 2/28/21
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_sign_up"/>
</form>
<div class="container">

</div>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="sign_in" /> Enter
    login:<br /> <input type="text" name="login" value="" /><br />
    Enter password:<br /> <input type="password" name="password" value="" /><br />

    <input type="submit" value="Отправить" /><br />
</form>

<br />

<a href="Controller?command=go_to_sign_up_page">Registration</a>
<jsp:include page="footer.jsp"/>
</body>
</html>
