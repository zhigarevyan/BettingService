<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LUCKYBET</title>
</head>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<jsp:include page="header.jsp"/>


<body>
<fmt:setBundle basename="locale"/>

<fmt:message key="admin.addEvent" var="addEvent"/>
<fmt:message key="admin.participants" var="participant"/>
<fmt:message key="admin.futureEvents" var="futureEvents"/>

<div class="container admin_window" id="body">
    <div class="row">
        <div class="col-3">
            <c:if test="${message_success != null}">
                <div class="col bg-success rounded">
                    <b class="message_label">${message_success}</b>
                </div>
            </c:if>
            <c:if test="${message_error != null}">
                <div class="col bg-danger rounded">
                    <b class="message_label">${message_error}</b>
                </div>
            </c:if>
        </div>
        <div class="col-6 justify-content-center d-flex">
            <div class="btn-toolbar" role="toolbar">
                <div class="btn-group" role="group">
                    <a class="btn btn-secondary" type="button"
                       href="Controller?command=go_to_future_events_page_admin">${futureEvents}</a>
                    <a class="btn btn-secondary" type="button"
                       href="Controller?command=go_to_add_event_page">${addEvent}</a>
                    <a class="btn btn-secondary" type="button"
                       href="Controller?command=go_to_participants_page">${participant}</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row pb-20">
        <jsp:include page="${admin_content}"/>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
