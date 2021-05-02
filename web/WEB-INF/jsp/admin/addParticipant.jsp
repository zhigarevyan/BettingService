<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addParticipant</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.addParticipant" var="addParticipant"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="info" var="info"/>
<fmt:message key="add" var="add"/>
<fmt:message key="image" var="image"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="letters" var="letters"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.fio" var="regex_fio"/>
<fmt:message key="regex.requiredField" var="regex_requiredField"/>


<div class="container">
    <div class="row justify-content-md-center mt-2">
        <div class="col-md-8 border rounded bg-light">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="add_Participant"/>
                <div class="form-group mt-2">
                    <label for="participantNameInput">${name} : </label>
                    <input type="text" id="participantNameInput" class="form-control" name="name"
                           required pattern="${regex_fio}" placeholder="${from} 2 ${to} 20 ${letters}">
                </div>

                <div class="form-group">
                    <label for="participantSurNameInput">${surname} : </label>
                    <input type="text" id="participantSurNameInput" class="form-control" name="surname"
                           required pattern="${regex_fio}" placeholder="${from} 2 ${to} 20 ${letters}">
                </div>

                <div class="form-group">
                    <label for="participantInfoInput">${info} : </label>
                    <input type="text" id="participantInfoInput" required pattern="${regex_requiredField}"
                           class="form-control input-lg" name="info"/>
                </div>
                <div class="form-group pt-3">
                    <button type="submit" class="btn btn-success">${add.toUpperCase()}</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
