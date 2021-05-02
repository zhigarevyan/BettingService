<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>addEvent</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.addEvent" var="addEvent"/>
<fmt:message key="admin.addParticipant" var="addParticipant"/>
<fmt:message key="firstParticipant" var="firstParticipant"/>
<fmt:message key="secondParticipant" var="secondParticipant"/>
<fmt:message key="admin.chooseParticipant" var="chooseParticipant"/>
<fmt:message key="location" var="location"/>
<fmt:message key="info" var="info"/>
<fmt:message key="start_date" var="start_date"/>
<fmt:message key="add" var="add"/>

<fmt:setBundle basename="regex"/>
<fmt:message key="regex.requiredField" var="regex_requiredField"/>


<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate var="currentDate" value="${date}" pattern="yyyy-MM-dd"/>

<div class="container">
    <div class="row justify-content-center mt-2">
        <div class="col-md-8 border rounded bg-light">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="add_event"/>
                <c:set var="participants" value="${requestScope.participants}"/>
                <div class="form-group mt-2">
                    <label for="firstParticipantInput">${firstParticipant} : </label>
                    <select class="mdb-select md-form" id="firstParticipantInput" name="first_participant" required>
                        <option value="" disabled selected>${chooseParticipant}</option>
                        <c:forEach var="participant" items="${participants}">
                            <option value="${participant.id}">${participant.name} ${participant.surName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="secondParticipantInput">${secondParticipant} : </label>
                    <select class="mdb-select md-form" id="secondParticipantInput" name="second_participant"
                            required>
                        <option disabled selected>${chooseParticipant}</option>
                        <c:forEach var="participant" items="${participants}">
                            <option value="${participant.id}">${participant.name} ${participant.surName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="eventLocationInput">${location} : </label>
                    <input type="text" id="eventLocationInput" required pattern="${regex_requiredField}"
                           class="form-control" name="location"/>
                </div>

                <div class="form-group">
                    <label for="eventInfoInput">${info} : </label>
                    <input type="text" id="eventInfoInput" required pattern="${regex_requiredField}"
                           class="form-control input-lg" name="info"/>
                </div>
                <div class="form-group">
                    <label for="startDateTimeInput">${start_date} : </label>
                    <input type="date" value="${currentDate}" min="${currentDate}" class="form-control"
                           name="start_date" id="startDateTimeInput">
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
