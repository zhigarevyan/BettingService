<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addOutcomeType</title>
</head>
<body>
<fmt:setBundle basename="locale"/>


<fmt:message key="title" var="title"/>
<fmt:message key="add" var="add"/>

<fmt:setBundle basename="locale"/>
<fmt:message key="regex.requiredField" var="regex_requiredField"/>

<c:set var="event" value="${requestScope.event}"/>

<div class="container">
    <form id="addOutcomeTypeForm" action="Controller" method="post">
        <input type="hidden" name="command" value="add_outcome_type"/>
    </form>
    <div class="row">
        <div class="col-4">
            <label for="outcomeTypeInput">${title} : </label>
        </div>
        <div class="col">
            <input type="text" form="addOutcomeTypeForm" required pattern="${regex_requiredField}" id="outcomeTypeInput"
                   class="form-control" name="outcomeType"/>
        </div>
    </div>
</div>
<div class="m-2">
    <button form="addOutcomeTypeForm" name="eventId" value="${event.id}"
            type="submit" class="btn btn-success">${add.toUpperCase()}</button>
</div>

</body>
</html>
