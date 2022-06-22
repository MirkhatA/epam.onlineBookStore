<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<div class="d-flex flex-column flex-shrink-0 p-3" style="width: 280px;">
    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">Profile</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/profile.jsp" class="nav-link link-dark">
                <fmt:message key="label.profile"/>
            </a>
        </li>
        <li>
            <a href="/cart.jsp" class="nav-link link-dark">
                <fmt:message key="label.cart"/>
            </a>
        </li>
        <li>
            <a href="#" class="nav-link link-dark">
                <fmt:message key="label.orders"/>
            </a>
        </li>
        <li>
            <a href="/changePassword.jsp" class="nav-link link-dark">
                <fmt:message key="label.changePassword"/>
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