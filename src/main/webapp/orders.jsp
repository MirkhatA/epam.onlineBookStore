<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${sessionScope.user}"/>
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
                    <th>id</th>
                    <th><fmt:message key="label.receiverName"/></th>
                    <th><fmt:message key="label.address"/></th>
                    <th><fmt:message key="label.mobile"/></th>
                    <th><fmt:message key="label.comment"/></th>
                    <th><fmt:message key="label.createdDate"/></th>
                    <th><fmt:message key="label.totalPrice"/></th>
                    <th><fmt:message key="label.paidStatus"/></th>
                    <th><fmt:message key="label.orderStatus"/></th>
                </tr>

                <c:forEach var="order" items="${sessionScope.orderList}">
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

