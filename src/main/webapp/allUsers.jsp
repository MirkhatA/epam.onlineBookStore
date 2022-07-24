<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="d-flex flex-nowrap">
    <jsp:include page="adminProfileNavbar.jsp"/>

    <c:if test="${requestScope.hasOrders}">
        <div class="alert alert-danger" role="alert">
            <p>You cant delete this user because he has orders</p>
        </div>
    </c:if>

    <div class="col-md-7 ms-5 mt-5">
        <table class="table table-hover">
            <tr>
                <th>id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Mobile</th>
                <th>Registered date</th>
                <th>Status</th>
                <th></th>
            </tr>

            <c:forEach var="user" items="${sessionScope.userList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.address}</td>
                    <td>${user.mobile}</td>
                    <td>${user.registeredAt}</td>
                    <td>${user.status}</td>
                    <th><a href="/deleteUser?id=${user.id}" class="btn btn-danger">Delete</a></th>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

<jsp:include page="footer.jsp"/>