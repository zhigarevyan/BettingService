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
    <link rel="stylesheet" href="css/main.css"/>
</head>

<fmt:setBundle basename="locale"/>

<fmt:message key="header.about" var="about"/>
<fmt:message key="enter" var="enter"/>
<fmt:message key="exit" var="exit"/>
<fmt:message key="header.profile" var="profile"/>

<body>
<c:set var="user" value="${sessionScope.user}"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

            <c:if test="${user==null}">
                <div class="col-5">
                    <a class="navbar-brand" href="Controller?command=go_to_welcome_page">LUCKYBET</a>
                </div>
            </c:if>
            <c:if test="${user.role ==1}">
                <div class="col-5">
                    <a class="navbar-brand" href="Controller?command=go_to_user_page">LUCKYBET</a>
                </div>
            </c:if>
            <c:if test="${user.role ==2}">
                <div class="col-5">
                    <a class="navbar-brand" href="Controller?command=go_to_admin_page">LUCKYBET</a>
                </div>
            </c:if>
            <div class="col-5">
                <a type="button" class="btn cl-white" href="#">${about}</a>
            </div>
            <div class="col-2">
                <div class="collapse navbar-collapse " id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <c:if test="${user == null}">
                            <li class="nav-item text-center">
                                <a type="button" class="btn cl-white"
                                   href="Controller?command=go_to_sign_in_page">${enter}</a>
                            </li>
                        </c:if>
                        <c:if test="${user != null}">
                            <c:set var="account" value="${sessionScope.account}"/>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle cl-white" href="#" id="navbarDropdown" role="button"
                                   data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                        ${user.name}
                                    <c:if test="${user.role==1}">
                                        ,${account.balance} rub
                                    </c:if>
                                </a>

                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                                    <a type="button" class="btn dropdown-item"
                                       href="Controller?command=go_to_user_info_page">${profile}</a>

                                    <div class="dropdown-divider"></div>

                                    <a class="btn dropdown-item" href="Controller?command=logout"
                                            type="submit">${exit}
                                    </a>

                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <%--<form class="navbar-form navbar-right">
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a type="button" class="btn cl-white" href="#">${about}</a>
                        </li>
                        <c:if test="${user == null}">
                            <li class="nav-item navbar-right">
                                <a type="button" class="btn cl-white"
                                   href="Controller?command=go_to_sign_in_page">${enter}</a>
                            </li>
                        </c:if>
                        <c:if test="${user != null}">
                            <c:set var="account" value="${sessionScope.account}"/>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle cl-white" href="#" id="navbarDropdown" role="button"
                                   data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                        ${user.name}
                                    <c:if test="${user.role==1}">
                                        ,${account.balance} rub
                                    </c:if>
                                </a>

                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                                    <a type="button" class="btn dropdown-item"
                                       href="Controller?command=go_to_user_info_page">${profile}</a>

                                    <div class="dropdown-divider"></div>

                                    <button form="headerForm" class="btn btn-outline-danger dropdown-item" type="submit"
                                            name="command" value="logout">${exit}
                                    </button>

                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </form>--%>
        </div>

</nav>
</body>
</html>
