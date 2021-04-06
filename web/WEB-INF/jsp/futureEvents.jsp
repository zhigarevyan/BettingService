<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Future events</title>
</head>
<body>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.addParticipant" var="addParticipant"/>
<fmt:message key="name" var="name"/>
<fmt:message key="showEvent" var="showEvent"/>
<fmt:message key="delete" var="delete"/>

<c:set var="user" value="${sessionScope.user}"/>

<div class="container">
    <form id="eventFormAdmin" action="Controller" method="post">
        <input type="hidden" name="command" value="go_to_event_info_page_admin"/>
    </form>
    <form id="deleteEventFormAdmin" action="Controller" method="post">
        <input type="hidden" name="command" value="delete_event"/>
    </form>
    <form id="eventFormUser" action="Controller" method="post">
        <input type="hidden" name="command" value="go_to_event_info_page_user"/>
    </form>


    <div class="row justify-content-center">
        <div class="col-md-8">
            <c:set var="events" value="${requestScope.events}"/>
            <c:forEach var="event" items="${events}">
                <div class="form-group border rounded bg-light">

                    <div class="row justify-content-md-center">
                        <div class="col-4">
                            <div class="">${event.startDateTime}</div>
                        </div>
                    </div>
                    <div class="row justify-content-between">
                        <div class="col-5 ml-3">
                            <div class="row">
                                <div class="col-6">
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
                                <div class="col-6 d-flex align-items-center">
                                    <a type="text">${event.firstParticipant.name} ${event.firstParticipant.surName}</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-5 mr-3">
                            <div class="row">
                                <div class="col-6 d-flex align-items-center">
                                    <a type="text">${event.secondParticipant.name} ${event.secondParticipant.surName}</a>
                                </div>
                                <div class="col-6">
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
                    <div class="row justify-content-end  p-2">
                        <c:if test="${user.role==2}">
                            <div class="col-2">
                                <button form="deleteEventFormAdmin" name="eventId" type="submit"
                                        class="btn btn-danger"
                                        value="${event.id}">${delete}
                                </button>
                            </div>
                            <div class="col-4">
                                <button form="eventFormAdmin" name="eventId" type="submit"
                                        class="btn btn-secondary"
                                        value="${event.id}">${showEvent}
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${user.role==1}">
                            <div class="col-4">
                                <button form="eventFormUser" name="eventId" type="submit"
                                        class="btn btn-secondary"
                                        value="${event.id}">${showEvent}
                                </button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
