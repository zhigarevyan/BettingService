<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userBets</title>
</head>
<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="showEvent" var="showEvent"/>
<fmt:message key="outcome" var="outcome"/>
<fmt:message key="user.betAmount" var="betAmount"/>
<fmt:message key="user.statusAccepted" var="statusAccepted"/>
<fmt:message key="user.statusLost" var="statusLost"/>
<fmt:message key="user.statusPaid" var="statusPaid"/>
<fmt:message key="user.statusReturn" var="statusReturn"/>
<fmt:message key="user.wonAmount" var="wonAmount"/>
<fmt:message key="status" var="status"/>

<c:set var="userBets" value="${requestScope.userBets}"/>
<div class="container">
    <form id="goToEventForm" action="Controller" method="post">
        <input type="hidden" name="command" value="go_to_event_info_page_user">
    </form>
    <div class="row justify-content-center">
        <div class="col-8">
            <div class="list-group">
                <c:forEach var="userBet" items="${userBets}">
                    <div class="list-group-item bg-light">
                        <div class="row justify-content-md-center">
                            <div class="col-4">
                                <div class="">${userBet.bet.event.startDateTime}</div>
                            </div>
                        </div>
                        <div class="row justify-content-start">
                            <div class="col-6">${userBet.bet.event.firstParticipant.name} ${userBet.bet.event.firstParticipant.surName}
                                - ${userBet.bet.event.secondParticipant.name} ${userBet.bet.event.secondParticipant.surName}</div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="col-4">${outcome} : ${userBet.bet.outcome.type}</div>
                            <div class="col-4">${userBet.bet.offer}</div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="col-4">${betAmount} :</div>
                            <div class="col-4"> ${userBet.betAmount}</div>
                        </div>
                        <c:choose>
                            <c:when test="${userBet.bet.status.id == 1}">
                                <div class="row justify-content-between">
                                    <div class="col-4">
                                            ${status} :
                                    </div>
                                    <div class="col-4 font-weight-bold text-primary ">
                                            ${statusAccepted}
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${userBet.bet.status.id == 2}">
                                <div class="row justify-content-between">
                                    <div class="col-4">
                                            ${wonAmount} :
                                    </div>
                                    <div class="col-4">
                                            ${userBet.betAmount * userBet.bet.offer}
                                    </div>
                                </div>
                                <div class="row justify-content-between">
                                    <div class="col-4">
                                            ${status} :
                                    </div>
                                    <div class="col-4 font-weight-bold text-success ">
                                            ${statusPaid}
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${userBet.bet.status.id == 3}">
                                <div class="row justify-content-between">
                                    <div class="col-4">
                                            ${status} :
                                    </div>
                                    <div class="col-4 font-weight-bold text-danger ">
                                            ${statusLost}
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${userBet.bet.status.id == 4}">
                                <div class="row justify-content-between">
                                    <div class="col-4">
                                            ${status} :
                                    </div>
                                    <div class="col-4 font-weight-bold text-info">
                                            ${statusReturn}
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="row justify-content-end mt-2    ">
                            <button form="goToEventForm" name="eventId" type="submit"
                                    class="btn btn-info"
                                    value="${userBet.bet.event.id}">${showEvent}
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
