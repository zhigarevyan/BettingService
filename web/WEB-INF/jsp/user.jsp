<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LUCKYBET</title>
</head>
<jsp:include page="header.jsp"/>


<body>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="user.line" var="line"/>
<fmt:message key="user.results" var="results"/>
<fmt:message key="user.bets" var="bets"/>
<b>${message}</b>
<div class="container user_window" id="body">
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
                       href="Controller?command=go_to_future_events_page_user">${line}</a>
                    <a class="btn btn-secondary" type="button"
                       href="Controller?command=go_to_event_results_page">${results}</a>
                    <a class="btn btn-secondary" type="button"
                       href="Controller?command=go_to_user_bets_page">${bets}</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row pb-20">
        <jsp:include page="${user_content}"/>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
