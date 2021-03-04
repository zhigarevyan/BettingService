<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/main.css">
</head>

<fmt:setBundle basename="locale"/>

<fmt:message key="header.line" var="line"/>
<fmt:message key="header.results" var="results"/>
<fmt:message key="header.about" var="about"/>
<fmt:message key="header.enter" var="enter"/>
<fmt:message key="header.exit" var="exit"/>
<fmt:message key="header.profile" var="profile"/>


<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">LUCKYBET</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a type="button" class="btn cl-white" href="#">${line}</a>
                </li>
                <li class="nav-item">
                    <a type="button" class="btn cl-white" href="#">${results}</a>
                </li>
                <li class="nav-item">
                    <a type="button" class="btn cl-white" href="#">${about}</a>
                </li>
                <c:set var="user" value="${sessionScope.user}"/>
                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a type="button" class="btn cl-white" href="Controller?command=go_to_sign_in_page">${enter}</a>
                    </li>
                </c:if>
                <c:if test="${user != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle cl-white" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                                ${user.name}
                        </a>

                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a type="button" class="btn dropdown-item" href="Controller?command=go_to_personal_page_command">${profile}</a>

                            <div class="dropdown-divider"></div>

                            <button form="headerForm" class="btn btn-outline-danger dropdown-item" type="submit"
                                    name="command" value="logout">${exit}
                            </button>

                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<form id="headerForm" action="Controller" method="post"></form>
</body>
</html>
