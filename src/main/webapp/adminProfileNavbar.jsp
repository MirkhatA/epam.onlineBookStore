<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>

<div class="d-flex flex-column flex-shrink-0 p-3" style="width: 280px;">
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">Get all books</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/addBook.jsp" class="nav-link link-dark">
                Add book
            </a>
        </li>
    </ul>
    <hr>
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">Get all users</span>
    </a>
    <hr>
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">Get all orders</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="#" class="nav-link link-dark">
                Add order
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