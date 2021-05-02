<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.participants" var="participants"/>
<fmt:message key="delete" var="delete"/>
<fmt:message key="admin.addParticipant" var="addParticipant"/>
<fmt:message key="edit" var="edit"/>
<form id="goToAddParticipantForm" action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_add_participant_page">
</form>
<form id="goToEditParticipantForm" action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_edit_participant_page">
</form>
<div class="container">
    <div class="row justify-content-center m-2">
        <b class="col-3 text-center">${participants}</b>
        <button class="col-2 btn btn-sm btn-info" form="goToAddParticipantForm">${addParticipant}</button>
    </div>
    <div class="row justify-content-center">
        <div class="col-6">
            <c:set var="participants" value="${requestScope.participants}"/>
            <c:forEach var="participant" items="${participants}">
                <div class="border rounded bg-light m-2">
                    <div class="row">
                        <div class="col-3">
                            <c:choose>
                                <c:when test="${participant.image.equals('')}">
                                    <img width="100" height="100" src="img/default.jpg"
                                         alt="${participant.name}"
                                         class="img-thumbnail">
                                </c:when>
                                <c:when test="${!participant.image.equals('')}">
                                    <img src="${participant.image}" width="100" height="100"
                                         alt="${participant.name}" class="img-thumbnail">
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="col-3 d-flex align-items-center">
                            <a type="text">${participant.name} ${participant.surName}</a>
                        </div>
                        <div class="col-3 d-flex align-items-center">
                            <button class="btn btn-sm btn-info" form="goToEditParticipantForm" name="participantId"
                                    value="${participant.id}">${edit}</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

</body>
</html>
