<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>

<div class="d-flex flex-column flex-shrink-0 p-3" style="width: 280px;">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/adminShowAllBooksService" class="nav-link link-dark"><fmt:message key="button.getAllBooks"/></a>
        </li>
        <li class="nav-item">
            <a href="/addBook.jsp" class="nav-link link-dark"><fmt:message key="button.addBook"/></a>
        </li>
        <li class="nav-item">
            <a href="/allUsers" class="nav-link link-dark"><fmt:message key="button.getAllUsers"/></a>
        </li>
        <li class="nav-item">
            <a href="/allOrders" class="nav-link link-dark"><fmt:message key="button.getAllOrders"/></a>
        </li>
        <hr>
        <li class="nav-item">
            <a href="/allGenres" class="nav-link link-dark"><fmt:message key="button.getAllGenres"/></a>
        </li>
        <li class="nav-item">
            <a href="/addGenre.jsp" class="nav-link link-dark"><fmt:message key="button.addGenre"/></a>
        </li>
        <li>
            <a href="/logout" class="nav-link link-dark">
                <fmt:message key="label.logout"/>
            </a>
        </li>
    </ul>
</div>