<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editUserInfo</title>
</head>
<body>
<fmt:setBundle basename="locale"/>
<fmt:message key="login" var="login"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="email" var="email"/>
<fmt:message key="save" var="save"/>
<fmt:message key="header.profile" var="profile"/>
<fmt:message key="birthday" var="birthday"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="and" var="and_locale"/>
<fmt:message key="letters" var="letters"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.fio" var="regex_user_fio"/>
<fmt:message key="regex.login" var="regex_login"/>
<fmt:message key="regex.email" var="regex_email"/>

<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate var="currentDate" value="${date}" pattern="yyyy-MM-dd"/>

<form id="saveProfileChangesForm" action="Controller" method="post">
    <input type="hidden" name="command" value="save_profile_changes">
</form>
<jsp:include page="header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<div class="container col-6 border rounded bg-light">
    <div class="row justify-content-center m-2">
        <div class="col-4 text-center">${profile}</div>
    </div>
    <div class="row m-2">
        <div class="col-3">
            <label for="loginInput">${login} : </label>
        </div>
        <div class="col">
            <input type="text" value="${user.login}" form="saveProfileChangesForm" class="form-control" name="login"
                   id="loginInput"
                   required pattern="${regex_login}" placeholder="${from} 6 ${to} 16 ${letters} ${and_locale} '_'"/>
        </div>
    </div>
    <div class="row m-2">
        <div class="col-3">
            <label for="nameInput">${name} : </label>
        </div>
        <div class="col">
            <input type="text" value="${user.name}" form="saveProfileChangesForm" class="form-control" id="nameInput"
                   name="name" required pattern="${regex_user_fio}"
                   placeholder="${from} 2 ${to} 20 ${letters}"/>

        </div>
    </div>
    <div class="row m-2">
        <div class="col-3">
            <label for="surNameInput">${surname} : </label>
        </div>
        <div class="col">
            <input type="text" value="${user.surName}" form="saveProfileChangesForm" class="form-control"
                   id="surNameInput" name="surname" required
                   pattern="${regex_user_fio}"
                   placeholder="${from} 2 ${to} 20 ${letters}"/>
        </div>
    </div>
    <div class="row m-2">
        <div class="col-3">
            <label for="emailInput">${email} : </label>
        </div>
        <div class="col">
            <input type="email" value="${user.email}" form="saveProfileChangesForm" class="form-control" name="email"
                   id="emailInput" required
                   pattern="${regex_email}">
        </div>
    </div>
    <div class="row m-2">
        <div class="col-3">
            <label for="birthdayInput">${birthday} : </label>
        </div>
        <div class="col">
            <input type="date" max="${currentDate}" value="${user.birthdayDate}" form="saveProfileChangesForm"
                   class="form-control" name="birthday" id="birthdayInput">
        </div>
    </div>
    <button type="submit" form="saveProfileChangesForm" class="btn btn-success">${save}</button>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
