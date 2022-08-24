<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Book store</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    <style>
        <%@include file='style/style.css' %>
    </style>

</head>
<body>

<header class="py-3 mb-3 border-bottom">
    <div class="container-fluid d-grid gap-3 align-items-center" style="grid-template-columns: 1fr 2fr;">
        <div class="dropdown">
            <h5><a href="/index.jsp">Book store</a></h5>
        </div>

        <div class="d-flex align-items-center">
            <form class="w-100 me-3 m-0" role="search" action="/searchBook">
                <input type="search" class="form-control" name="search" placeholder="<fmt:message key="label.search"/>" aria-label="Search">
            </form>

            <ul class="navbar-nav me-3">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <c:out value="${sessionScope.languageName}"/>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="/changeLanguage?id=1">English</a></li>
                        <li><a class="dropdown-item" href="/changeLanguage?id=2">Русский</a></li>
                    </ul>
                </li>
            </ul>

            <div class="flex-shrink-0 dropdown">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
                    <div class="row">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg>
                    </div>
                </a>

                <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
                    <c:if test="${not empty sessionScope.email}">
                        <li>
                            <a class="dropdown-item" href="profile.jsp">
                                <c:if test="${not empty sessionScope.email}">
                                    ${sessionScope.email}
                                </c:if>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="/cart">
                                <fmt:message key="label.cart"/>
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item" href="/logout">
                                <fmt:message key="label.logout"/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.isAdmin}">
                            <li>
                                <a class="dropdown-item" href="/adminPanel.jsp">
                                    <c:if test="${not empty sessionScope.email}">
                                        Admin panel
                                    </c:if>
                                </a>
                            </li>
                        </c:if>
                    </c:if>
                    <c:if test="${empty sessionScope.email}">
                    <li><a class="dropdown-item" href="/login.jsp"><fmt:message key="label.login"/></a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="/registration.jsp"><fmt:message key="label.register"/></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</header>
