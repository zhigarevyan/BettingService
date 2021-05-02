<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userInfo</title>
</head>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="login" var="login"/>
<fmt:message key="changePassword" var="changePassword"/>
<fmt:message key="enterNewPassword" var="writeNewPassword"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="email" var="email"/>
<fmt:message key="header.profile" var="profile"/>
<fmt:message key="birthday" var="birthday"/>
<fmt:message key="edit" var="edit"/>
<fmt:message key="regex.password" var="regex_password"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="symbols" var="symbols"/>
<fmt:message key="header.rub" var="rub"/>
<fmt:message key="topUpBalance" var="topUpBalance"/>
<fmt:message key="sum" var="sum"/>
<fmt:setBundle basename="regex"/>
<fmt:message key="regex.password" var="regex_password"/>
<fmt:message key="regex.balance" var="regex_balance"/>


<c:set var="user" value="${sessionScope.user}"/>
<body>
<form id="changePasswordForm" action="Controller" method="post">
    <input type="hidden" name="command" value="change_password">
</form>
<form id="editProfileForm" action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_edit_profile_page">
</form>
<form id="topUpBalanceForm" action="Controller" method="post">
    <input type="hidden" name="command" value="top_up_balance">
</form>
<jsp:include page="header.jsp"/>
<div class="row justify-content-center">
    <div class="container col-6 mt-2 border rounded bg-light">
        <div class="row justify-content-center">
            <b>${profile}</b>
        </div>
        <div class="row">
            <div class="col-6">
                <div class="row justify-content-between">
                    <div class="col">
                        <label for="loginText">${login} : </label>
                        <b id="loginText">${user.login}</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="nameText">${name} : </label>
                        <b id="nameText">${user.name}</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="surNameText">${surname} : </label>
                        <b id="surNameText">${user.surName}</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="emailText">${email} : </label>
                        <b id="emailText">${user.email}</b>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label for="birthdayText">${birthday} : </label>
                        <b id="birthdayText">${user.birthdayDate}</b>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col mr-3">
                    <button class="btn btn-success" form="editProfileForm">${edit}</button>
                </div>
                <div class="col">
                    <button class="btn btn-primary" type="button" data-toggle="collapse"
                            data-target="#collapseChangePassword" aria-expanded="false">
                        ${changePassword}
                    </button>
                    <div class="collapse" id="collapseChangePassword">
                        <div class="card card-body">
                            <div class="row">
                                <label for="changePasswordInput">${writeNewPassword}</label>
                                <input type="password" form="changePasswordForm" class="form-control"
                                       name="password" id="changePasswordInput" required
                                       pattern="${regex_password}" placeholder="${from} 6 ${to} 18 ${symbols}">
                                <button type="submit" form="changePasswordForm" class="btn btn-success">${edit}</button>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${user.role == 1}">
                    <div class="col">
                        <button class="btn btn-primary" type="button" data-toggle="collapse"
                                data-target="#collapseBalance" aria-expanded="false">
                                ${topUpBalance}
                        </button>
                        <div class="collapse" id="collapseBalance">
                            <div class="card card-body">
                                <div class="row">
                                    <label for="topUpBalanceInput">${sum}</label>
                                    <input type="number" form="topUpBalanceForm" class="form-control"
                                           name="balance" id="topUpBalanceInput" min="1" required
                                           pattern="${regex_balance}" placeholder="${from} 1 ${rub}">
                                    <button type="submit" form="topUpBalanceForm"
                                            class="btn btn-success">${edit}</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>


        </div>

    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
