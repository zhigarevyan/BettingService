<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddResult</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.chooseWinner" var="chooseWinner"/>
<fmt:message key="winnerScore" var="firstParticipantScore"/>
<fmt:message key="loserScore" var="secondParticipantScore"/>
<fmt:message key="outcome" var="outcome"/>
<fmt:message key="status" var="status"/>
<fmt:message key="admin.addResult" var="addResult"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.requiredField" var="regex_requiredField"/>

<form id="addResultForm" method="post" action="Controller">
    <input type="hidden" name="command" value="add_result">
</form>
<c:set var="event" value="${requestScope.event}"/>
<c:set var="eventBets" value="${requestScope.eventBets}"/>
<c:set var="betStatuses" value="${requestScope.betStatuses}"/>

<div class="container justify-content-center row">
    <div class="col-6 border rounded bg-light">
        <div class="row justify-content-md-center ">
            <b class="login-label">${addResult}</b>
        </div>
        <div class="row">
            <div class="form-group m-2">
                <label for="winnerSelect">${chooseWinner} : </label>
                <select class="mdb-select md-form" form="addResultForm" id="winnerSelect" name="winnerId">
                    <option value="${event.firstParticipant.id}">${event.firstParticipant.name} ${event.firstParticipant.surName}</option>
                    <option value="${event.secondParticipant.id}">${event.secondParticipant.name} ${event.secondParticipant.surName}</option>
                </select>
            </div>
        </div>
        <div class="row justify-content-between">
            <div class="form-group m-2">
                <label for="firstParticipantScoreInput">${firstParticipantScore}</label>
                <input type="number" required pattern="${regex_requiredField}" min="1" form="addResultForm"
                       id="firstParticipantScoreInput" name="firstParticipantScore"
                       class="form-control">
            </div>
            <div class="form-group m-2">
                <label for="secondParticipantScoreInput">${secondParticipantScore}</label>
                <input type="number" required pattern="${regex_requiredField}" min="1" form="addResultForm"
                       id="secondParticipantScoreInput" name="secondParticipantScore"
                       class="form-control">
            </div>
        </div>
        <div class="row">
            <c:forEach var="eventBet" items="${eventBets}">
                <div class="col-4 border rounded mb-2">
                    <label for="outcomeType">${outcome} : </label>
                    <div id="outcomeType">${eventBet.outcome.type}</div>
                    <label for="betResult">${status} : </label>
                    <select class="mdb-select md-form" form="addResultForm" id="betResult" name="${eventBet.id}">
                        <c:forEach var="betStatus" items="${betStatuses}">
                            <option value="${betStatus.id}">${betStatus.status}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:forEach>
        </div>
        <button form="addResultForm" type="submit" name="eventId" value="${event.id}" class="btn btn-success">
            ${addResult}
        </button>
    </div>
</div>
</body>
</html>
