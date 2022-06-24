<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>

<div class="d-flex flex-column flex-shrink-0 p-3" style="width: 280px;">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/allBooks" class="nav-link link-dark">
                Get all books
            </a>
        </li>
        <li class="nav-item">
            <a href="/addBook.jsp" class="nav-link link-dark">
                Add book
            </a>
        </li>
        <li class="nav-item">
            <a href="/allUsers" class="nav-link link-dark">
                Get all users
            </a>
        </li>
        <li class="nav-item">
            <a href="/allOrders" class="nav-link link-dark">
                Get all orders
            </a>
        </li>
    </ul>

    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li>
            <a href="/logout" class="nav-link link-dark">
                <fmt:message key="label.logout"/>
            </a>
        </li>
    </ul>
</div>