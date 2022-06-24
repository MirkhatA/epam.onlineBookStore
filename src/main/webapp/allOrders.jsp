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
                <th><fmt:message key="label.receiverName"/></th>
                <th><fmt:message key="label.address"/></th>
                <th><fmt:message key="label.mobile"/></th>
                <th><fmt:message key="label.comment"/></th>
                <th><fmt:message key="label.createdDate"/></th>
                <th><fmt:message key="label.totalPrice"/></th>
                <th><fmt:message key="label.paidStatus"/></th>
                <th><fmt:message key="label.orderStatus"/></th>
                <th></th>
            </tr>

            <c:forEach var="order" items="${sessionScope.orderList}">
                <form action="/editOrder" method="post">
                    <tr>
                        <input type="hidden" name="orderId" value="${order.id}">
                        <td>${order.id}</td>
                        <td>${order.fullName}</td>
                        <td>${order.address}</td>
                        <td>${order.mobile}</td>
                        <td>${order.comment}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.totalPrice}</td>
                        <td>
                            <select class="form-select" name="isPaid" id="isPaid">
                                <option value="${order.isPaidId}">${order.isPaid}</option>
                                <c:forEach var="paidStatus" items="${sessionScope.paidStatuses}">
                                    <option value="${paidStatus.id}">${paidStatus.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select class="form-select" name="orderStatus" id="orderStatus">
                                <option value="${orderStatus.statusId}">${order.status}</option>
                                <c:forEach var="orderStatus" items="${sessionScope.orderStatuses}">
                                    <option value="${orderStatus.id}">${orderStatus.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary">save</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</main>

<jsp:include page="footer.jsp"/>