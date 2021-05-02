<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>editPatrticipant</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="admin.participant" var="participant_text"/>
<fmt:message key="name" var="name"/>
<fmt:message key="surname" var="surname"/>
<fmt:message key="info" var="info"/>
<fmt:message key="save" var="save"/>
<fmt:message key="from" var="from"/>
<fmt:message key="to" var="to"/>
<fmt:message key="letters" var="letters"/>
<fmt:message key="image" var="image"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.fio" var="regex_user_fio"/>
<fmt:message key="regex.login" var="regex_login"/>
<fmt:message key="regex.requiredField" var="requiredField"/>

<form id="saveParticipantChangesForm" action="Controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="save_participant_changes">
</form>


<c:set var="participant" value="${requestScope.participant}"/>
<div class="container col-6 border rounded bg-light">
    <div class="row justify-content-center m-2">
        <div class="col-4 text-center">${participant_text}</div>
    </div>
    <div class="row m-1">
        <div class="col-4">
            <label for="nameInput">${name} : </label>
        </div>
        <div class="col">
            <input type="text" value="${participant.name}" form="saveParticipantChangesForm" class="form-control"
                   id="nameInput"
                   name="name" required pattern="${regex_user_fio}"
                   placeholder="${from} 2 ${to} 20 ${letters}"/>
        </div>
    </div>
    <div class="row m-1">
        <div class="col-4">
            <label for="surNameInput">${surname} : </label>
        </div>
        <div class="col">
            <input type="text" value="${participant.surName}" form="saveParticipantChangesForm" class="form-control"
                   id="surNameInput" name="surname" required
                   pattern="${regex_user_fio}"
                   placeholder="${from} 2 ${to} 20 ${letters}"/>
        </div>
    </div>
    <div class="row m-1">
        <div class="col-4">
            <label for="emailInput">${info} : </label>
        </div>
        <div class="col">
            <input type="text" value="${participant.info}" form="saveParticipantChangesForm" class="form-control"
                   name="info"
                   id="emailInput" required
                   pattern="${requiredField}">
        </div>
    </div>
    <div class="row m-1">
        <input type="hidden" name="command" value="add_image">
        <div class="col-5">
            <input type="file" form="saveParticipantChangesForm" class="custom-file-input" id="image" name="file"
                   accept=".jpg"
                   required>
            <label class="custom-file-label" for="image">${image}...</label>
        </div>
    </div>
    <button type="submit" form="saveParticipantChangesForm" name="participantId" value="${participant.id}"
            class="btn btn-success">${save}</button>

</div>
</body>
</html>
