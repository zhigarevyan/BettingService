<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUSerBet</title>
</head>
<body>
<fmt:setBundle basename="locale"/>


<fmt:message key="user.betAmount" var="betAmount"/>
<fmt:message key="firstParticipant" var="firstParticipant"/>
<fmt:message key="secondParticipant" var="secondParticipant"/>
<fmt:message key="outcome" var="outcome"/>
<fmt:message key="user.makeBet" var="makeBet"/>


<div class="container row justify-content-center">
    <form id="addUserBetForm" action="Controller" method="post">
        <input type="hidden" name="command" value="add_user_bet"/>
    </form>
    <c:set var="event" value="${requestScope.event}"/>
    <c:set var="bet" value="${requestScope.bet}"/>
    <div class="col-6 border rounded bg-light">
        <div class="row justify-content-between">
            <div class="col-6">
                <label for="firstParticipantText">${firstParticipant} : </label>
                <b id="firstParticipantText">${event.firstParticipant.name} ${event.firstParticipant.surName}</b>
            </div>
            <div class="col-6">
                <label for="secondParticipantText">${secondParticipant} : </label>
                <b id="secondParticipantText">${event.secondParticipant.name} ${event.secondParticipant.surName}</b>
            </div>
        </div>
        <div class="row justify-content-start">
            <div class="col-4">
                <label for="outcomeTypeText">${outcome} : </label>
                <b id="outcomeTypeText">${bet.outcome.type}</b>
            </div>
        </div>
        <div class="row">
            <div class="col-6 form-group">
                <label for="betAmountInput">${betAmount} : </label>
                <input type="number" min="1" id="betAmountInput" name="betAmount" form="addUserBetForm" required>
            </div>
        </div>
        <button form="addUserBetForm" name="betId" value="${bet.id}"
                type="submit" class="btn btn-success">${makeBet.toUpperCase()}</button>
    </div>
</div>
</body>
</html>
