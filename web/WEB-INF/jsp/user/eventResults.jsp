<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<c:set var="eventResults" value="${requestScope.eventResults}"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-8">
            <div class="list-group">
                <c:forEach var="eventResult" items="${eventResults}">
                    <div class="border rounded bg-light m-2">
                        <div class="row justify-content-md-center">
                            <div class="col-4">
                                <div class="">${eventResult.event.startDateTime}</div>
                            </div>
                        </div>
                        <div class="row justify-content-between mb-2">
                            <div class="col-5 ml-3">
                                <div class="row">
                                    <div class="col-6">
                                        <c:choose>
                                            <c:when test="${eventResult.event.firstParticipant.image.equals('')}">
                                                <img width="100" height="100" src="img/default.jpg"
                                                     alt="${eventResult.event.firstParticipant.name}"
                                                     class="img-thumbnail">
                                            </c:when>
                                            <c:when test="${!eventResult.event.firstParticipant.image.equals('')}">
                                                <img src="${eventResult.event.firstParticipant.image}" width="100"
                                                     height="100"
                                                     alt="${eventResult.event.firstParticipant.name}"
                                                     class="img-thumbnail">
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div class="col-6 d-flex align-items-center">
                                        <a type="text">${eventResult.event.firstParticipant.name} ${eventResult.event.firstParticipant.surName}</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-1 d-flex align-items-center">
                                <div class="row"> ${eventResult.firstParticipantScore}
                                    : ${eventResult.secondParticipantScore}</div>
                            </div>
                            <div class="col-5 mr-3">
                                <div class="row">
                                    <div class="col-6 d-flex align-items-center">
                                        <a type="text">${eventResult.event.secondParticipant.name} ${eventResult.event.secondParticipant.surName}</a>
                                    </div>
                                    <div class="col-6">
                                        <c:choose>
                                            <c:when test="${eventResult.event.secondParticipant.image.toString().equals('')}">
                                                <img width="100" height="100" src="img/default.jpg"
                                                     alt="${eventResult.event.secondParticipant.name}"
                                                     class="img-thumbnail">
                                            </c:when>
                                            <c:when test="${!eventResult.event.secondParticipant.image.equals('')}">
                                                <img src="${eventResult.event.secondParticipant.image}" width="100"
                                                     height="100"
                                                     alt="${eventResult.event.secondParticipant.name}"
                                                     class="img-thumbnail">
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
