<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="WEB-INF/jsp/header.jsp"/>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="enterAccount" var="enterAccount"/>
<fmt:message key="login" var="login"/>
<fmt:message key="enter" var="enter"/>
<fmt:message key="password" var="password"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="letters" var="letters"/>
<fmt:message key="symbols" var="symbols"/>
<fmt:message key="signUp" var="signUp"/>
<fmt:message key="and" var="and_locale"/>
<%--TODO неверный логин пароль--%>
<fmt:setBundle basename="regex"/>
<fmt:message key="regex.login" var="regex_login"/>
<fmt:message key="regex.password" var="regex_password"/>


<form id="signUpForm" action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_sign_up_page"/>
</form>
<div class="container" id="body">
    <div class="row justify-content-center">
        <div class="col-3">
            <c:if test="${message_error != null}">
                <div class="col bg-danger rounded">
                    <b class="message_label">${message_error}</b>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-4  border rounded bg-light">
            <div class="row justify-content-center ">
                <b class="login-label">${enterAccount}</b>
            </div>
            <div class="row justify-content-md-center">
                <div class="col ">
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="sign_in"/>
                        <div class="form-group">
                            <label for="loginInput">${login}:</label>
                            <input type="text" class="form-control" name="login" id="loginInput"
                                   required pattern="${regex_login}"
                                   placeholder="${from} 6 ${to} 16 ${letters} ${and_locale} '_'">
                        </div>
                        <div class="form-group">
                            <label for="passwordInput">${password}:</label>
                            <input type="password" class="form-control" name="password" id="passwordInput" required
                                   pattern="${regex_password}" placeholder="${from} 6 ${to} 18 ${symbols}">
                        </div>

                        <div class="form-group row">
                            <div class="col justify-content-center d-flex">
                                <button type="submit" class="btn btn-success">${enter.toUpperCase()}</button>
                            </div>
                        </div>

                        <div class="form-group row ">
                            <div class="col justify-content-center d-flex">
                                <button form="signUpForm" type="submit"
                                        class="btn btn-warning">${signUp.toUpperCase()}</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


</div>


<jsp:include page="WEB-INF/jsp/footer.jsp"/>
</body>
</html>
