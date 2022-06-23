<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <c:if test="${requestScope.itemAddedToCart != null}">
            <div class="alert alert-success" role="alert">
                <fmt:message key="success.itemAddedToCart"/>
            </div>
        </c:if>

        <h1><fmt:message key="label.cart"/></h1>
        <div class="col-md-10 border">
            <table class="table table-hover">
                <tr>
                    <th scope="col">Book title</th>
                    <th scope="col">Price</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Total price</th>
                </tr>

                <c:forEach var="cartItem" items="${sessionScope.cartList}">
                    <tr>
                        <td>
                            <a href="/showBookDetails?id=${cartItem.bookId}">${cartItem.title}</a><br>
                            <a href="/deleteItem?id=${cartItem.bookId}">delete</a>
                        </td>
                        <td>${cartItem.price}</td>
                        <td>
                            <a href="/decreaseItemInCart?id=${cartItem.bookId}">-</a>
                                ${cartItem.quantity}
                            <a href="/addItemToCart?id=${cartItem.bookId}">+</a>
                        </td>
                        <td>${cartItem.totalPrice}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="col-md-2 border">
            <b class="mt-4 mb-4">Checkout</b>

            <h6 class="mt-4 mb-4">Total price: </h6>

            <button type="submit" class="btn btn-warning">Make order</button>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>