<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <c:if test="${requestScope.orderCreated != null}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="success.yourOrderCreated"/>
            </div>
        </c:if>

        <h1><fmt:message key="label.orders"/></h1>
        <div class="col-md-10 border">
            <table class="table table-hover">
                <tr>
                    <th>Order id</th>
                    <th>Receiver name</th>
                    <th>Address</th>
                    <th>Mobile number</th>
                    <th>Comment</th>
                    <th>Created date</th>
                    <th>Total price</th>
                    <th>Paid status</th>
                    <th>Order status</th>
                </tr>

                <c:forEach var="order" items="${sessionScope.orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.fullName}</td>
                    <td>${order.address}</td>
                    <td>${order.mobile}</td>
                    <td>${order.comment}</td>
                    <td>${order.createdAt}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.isPaid}</td>
                    <td>${order.status}</td>
                </tr>
                </c:forEach>
            </table>
        </div>

    </div>

</div>

<jsp:include page="footer.jsp"/>

