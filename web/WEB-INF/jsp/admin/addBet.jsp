<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addBet</title>
</head>
<body>

<fmt:setBundle basename="locale"/>

<fmt:message key="admin.outcomeTypes" var="outcomeTypes_text"/>
<fmt:message key="admin.chooseOutcomeType" var="chooseOutcomeType"/>
<fmt:message key="admin.addOutcomeType" var="addOutcomeType"/>
<fmt:message key="offer" var="offer"/>
<fmt:message key="add" var="add"/>
<fmt:message key="offerPattern" var="offerPattern"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.offer" var="regex_offer"/>

<form id="addBetForm" action="Controller" method="post">
    <input type="hidden" name="command" value="add_bet"/>
</form>
<c:set var="outcomeTypes" value="${requestScope.outcomeTypes}"/>
<c:set var="event" value="${requestScope.event}"/>
<div class="row">
    <div class="col-6 p-0">
        <div class="row m-2">
            <div class="col">
                <label for="outcomeTypeInput">${outcomeTypes_text} : </label>
            </div>
            <div class="col">
                <select class="mdb-select md-form" form="addBetForm" id="outcomeTypeInput" name="outcomeType"
                        required>
                    <option value="" disabled selected>${chooseOutcomeType}</option>
                    <c:forEach var="outcomeType" items="${outcomeTypes}">
                        <option value="${outcomeType.id}">${outcomeType.type} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="row m-2">
            <div class="col">
                <label for="eventOfferInput">${offer} : </label>
            </div>
            <div class="col">
                <input type="text" required pattern="${regex_offer}" form="addBetForm" id="eventOfferInput"
                       placeholder="${offerPattern}" class="form-control" name="offer"/>
            </div>
        </div>
        <div class="row m-2">
            <div class="col-2">
                <button form="addBetForm" name="eventId" value="${event.id}"
                        type="submit" class="btn btn-success">${add.toUpperCase()}</button>
            </div>
        </div>
    </div>
    <div class="col-6">
        <button class="btn btn-primary m-2" type="button" data-toggle="collapse"
                data-target="#collapseAddOutcomeType" aria-expanded="false" aria-controls="collapseExample">
            ${addOutcomeType}
        </button>
        <div class="collapse" id="collapseAddOutcomeType">
            <div class="card card-body p-0 bg-light">
                <jsp:include page="addOutcomeType.jsp"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
