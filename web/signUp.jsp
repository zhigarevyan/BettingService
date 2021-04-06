<%@ page import="java.util.Date" %>
<%@ page import="by.zhigarev.util.DateFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<jsp:include page="WEB-INF/jsp/header.jsp"/>

<fmt:setBundle basename="locale"/>
<fmt:message key="createAccount" var="createAccount"/>
<fmt:message key="login" var="login"/>
<fmt:message key="signUp" var="signUp"/>
<fmt:message key="name" var="name"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="and" var="and_locale"/>
<fmt:message key="letters" var="letters"/>
<fmt:message key="symbols" var="symbols"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="password" var="password"/>
<fmt:message key="email" var="email"/>
<fmt:message key="birthday" var="birthday"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.fio" var="regex_user_fio"/>
<fmt:message key="regex.login" var="regex_login"/>
<fmt:message key="regex.password" var="regex_password"/>
<fmt:message key="regex.email" var="regex_email"/>

<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate var="currentDate" value="${date}" pattern="yyyy-MM-dd"/>

<div class="row justify-content-center " id="body">
    <c:if test="${message != null}">
        <p class="message_label">${message}</p>
    </c:if>
    <div class="col-5 justify-content-center border rounded bg-light mt-2">
        <div class="row justify-content-md-center">
            <b class="registration-label">${createAccount}</b>
        </div>
        <div class="col">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="sign_up">
                <div class="form-group">
                    <label for="nameInput">${name}:</label>
                    <input type="text" class="form-control" id="nameInput" name="name" required
                           pattern="${regex_user_fio}"
                           placeholder="${from} 2 ${to} 20 ${letters}"/>
                </div>
                <div class="form-group">
                    <label for="surNameInput">${surname}:</label>
                    <input type="text" class="form-control" id="surNameInput" name="surname" required
                           pattern="${regex_user_fio}"
                           placeholder="${from} 2 ${to} 20 ${letters}"/>
                </div>
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
                <div class="form-group">
                    <label for="emailInput">${email}:</label>
                    <input type="email" class="form-control" name="email" id="emailInput" required
                           pattern="${regex_email}">
                </div>
                <div class="form-group">
                    <label for="birthdayInput">${birthday}:</label>
                    <input type="date" class="form-control" value="${currentDate}" max="${currentDate}" name="birthday"
                           id="birthdayInput">
                </div>
                <div class="form-group row">
                    <div class="col justify-content-center d-flex">
                        <button type="submit" class="btn btn-success">${signUp.toUpperCase()}</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<jsp:include page="WEB-INF/jsp/footer.jsp"/>
</body>
</html>
