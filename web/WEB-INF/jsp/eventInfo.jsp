<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EventInfo</title>
</head>
<body>
<fmt:setBundle basename="locale"/>

<fmt:message key="event" var="event_title"/>
<fmt:message key="firstParticipant" var="firstParticipant"/>
<fmt:message key="secondParticipant" var="secondParticipant"/>
<fmt:message key="info" var="info"/>
<fmt:message key="edit" var="edit"/>
<fmt:message key="location" var="location"/>
<fmt:message key="info" var="info"/>
<fmt:message key="admin.addBet" var="addBet"/>
<fmt:message key="user.makeBet" var="makeBet"/>
<fmt:message key="admin.addResult" var="addResult"/>
<fmt:message key="p1" var="p1"/>
<fmt:message key="p2" var="p2"/>
<fmt:message key="delete" var="delete"/>


<c:set var="user" value="${sessionScope.user}"/>
<c:set var="event" value="${requestScope.event}"/>
<c:set var="eventBets" value="${requestScope.eventBets}"/>


<div class="container">
    <form id="makeBetForm" action="Controller" method="post">
        <input type="hidden" name="command" value="go_to_make_user_bet_page"/>
    </form>
    <form id="deleteBetForm" action="Controller" method="post">
        <input type="hidden" name="command" value="delete_bet"/>
        <input type="hidden" name="eventId" value="${event.id}"/>
    </form>
    <form id="goToAddResultForm" action="Controller" method="post">
        <input type="hidden" name="command" value="go_to_add_result_page"/>
    </form>
    <div class="row justify-content-center">
        <div class="col-8 border rounded bg-light">
            <div class="row justify-content-center">
                <div class="col text-center">${event_title}</div>
            </div>

            <div class="border rounded">
                <div class="row justify-content-between">
                    <div class="col-6">
                        <div class="row">
                            <div class="col-4 m-2">
                                <c:choose>
                                    <c:when test="${event.firstParticipant.image.equals('')}">
                                        <img width="100" height="100" src="img/default.jpg"
                                             alt="${event.firstParticipant.name}"
                                             class="img-thumbnail">
                                    </c:when>
                                    <c:when test="${!event.firstParticipant.image.equals('')}">
                                        <img src="${event.firstParticipant.image}" width="100" height="100"
                                             alt="${event.firstParticipant.name}" class="img-thumbnail">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="col d-flex align-items-center justify-content-start p-0">
                                <a type="text">${p1}
                                    : ${event.firstParticipant.name} ${event.firstParticipant.surName}</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="row">
                            <div class="col d-flex align-items-center justify-content-end p-0">
                                <a type="text">${event.secondParticipant.name} ${event.secondParticipant.surName}
                                    : ${p2}</a>
                            </div>
                            <div class="col-4 m-2">
                                <c:choose>
                                    <c:when test="${event.secondParticipant.image.toString().equals('')}">
                                        <img width="100" height="100" src="img/default.jpg"
                                             alt="${event.secondParticipant.name}"
                                             class="img-thumbnail">
                                    </c:when>
                                    <c:when test="${!event.secondParticipant.image.equals('')}">
                                        <img src="${event.secondParticipant.image}" width="100" height="100"
                                             alt="${event.secondParticipant.name}" class="img-thumbnail">
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="border-top border-bottom">
                    <div class="row justify-content-center">
                        <div class="col-5 m-2">
                            <div class="text" id="locationText">${location} : ${event.location}</div>
                        </div>
                        <div class="col-5 m-2">
                            <div class="text" id="infoText">${info} : ${event.info}</div>
                        </div>
                    </div>
                </div>
                <c:if test="${user.role==2}">
                    <div class="border-bottom">
                        <button class="btn btn-primary m-2" type="button" data-toggle="collapse"
                                data-target="#collapseAddBet"
                                aria-expanded="false">
                                ${addBet}
                        </button>
                        <div class="collapse" id="collapseAddBet">
                            <div class="card card-body p-0 bg-light">
                                <jsp:include page="admin/addBet.jsp"/>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${event.status.id == 1}">
                    <div class="border">
                        <div class="row">

                            <c:forEach var="bet" items="${eventBets}">
                                <div class="col-4">
                                        ${bet.outcome.type} - ${bet.offer}
                                    <c:if test="${user.role==1}">
                                        <button form="makeBetForm" class="btn btn-sm btn-primary" value="${bet.id}"
                                                name="betId"
                                                type="submit">
                                                ${makeBet}
                                        </button>
                                    </c:if>
                                    <c:if test="${user.role==2}">
                                        <button form="deleteBetForm" class="btn btn-sm btn-danger" value="${bet.id}"
                                                name="betId"
                                                type="submit">
                                                ${delete}
                                        </button>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <c:if test="${user.role==2}">
                    <div class="row justify-content-end">
                        <div class="col text-center">
                            <button form="goToAddResultForm" class="btn btn-primary" value="${event.id}" name="eventId"
                                    type="submit">
                                    ${addResult}
                            </button>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <%--TODO измененине--%>
    </div>
</body>
</html>
