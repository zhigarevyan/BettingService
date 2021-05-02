<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LUCKYBET</title>
</head>
<jsp:include page="WEB-INF/jsp/header.jsp"/>


<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <c:set var="locale" value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="welcome" var="welcome"/>
<fmt:message key="welcomeText" var="welcomeText"/>
<fmt:message key="enter" var="enter"/>
<fmt:message key="signUp" var="signUp"/>


<div class="row justify-content-center">
    <div class="container col-6 mt-2 border rounded bg-light">
        <div class="row justify-content-center m-2">
            <b>
                ${welcome}
            </b>
        </div>
        <div class="row m-2">
            ${welcomeText}
        </div>
        <div class="row justify-content-center m-2">
            <a class="btn btn-success" href="Controller?command=go_to_sign_in_page">${enter}</a>
        </div>
        <div class="row justify-content-center m-2">
            <a class="btn btn-warning" href="Controller?command=go_to_sign_up_page">${signUp}</a>
        </div>
    </div>
</div>

<jsp:include page="WEB-INF/jsp/footer.jsp"/>
</body>
</html>
