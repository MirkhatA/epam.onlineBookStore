<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <h2><fmt:message key="label.pleaseCheckOrderData"/></h2>

        <div class="col-md-8 mt-5">
            <b><fmt:message key="label.address"/></b>
            <a href="/makeOrder"><fmt:message key="label.change"/></a><br>
            <p><fmt:message key="label.firstName"/>: ${sessionScope.receiverName}</p>
            <p><fmt:message key="label.address"/>: ${sessionScope.receiverAddress}</p>
            <p><fmt:message key="label.mobile"/>: ${sessionScope.receiverMobile}</p>
            <p><fmt:message key="label.comment"/>: ${sessionScope.comment}</p>

            <b><fmt:message key="label.paymentWay"/></b>
            <a href="/makeOrder"><fmt:message key="label.change"/></a><br>
            <p>${sessionScope.paymentWay}</p>
        </div>

        <div class="col-md-4 mt-5">
            <b><fmt:message key="label.totalPrice"/>: ${sessionScope.totalPrice}</b><br>
            <a class="btn btn-warning" href="/submitOrder"><fmt:message key="button.submitOrder"/></a>

            <table class="table table-hover">
                <tr>
                    <th scope="col">Book title</th>
                    <th scope="col">Price</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Total price</th>
                </tr>

                <c:forEach var="cartItem" items="${sessionScope.cartList}">
                    <tr>
                        <td>${cartItem.title}</td>
                        <td>${cartItem.price}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.totalPrice}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>