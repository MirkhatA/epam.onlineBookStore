<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${sessionScope.user}"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-7 ms-5 mt-5">
            <c:if test="${requestScope.emptyFields != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.fillAllData"/>
                </div>
            </c:if>

            <form action="setOrderData" method="post">
                <h1><fmt:message key="label.address"/></h1>


                <div class="form-floating mb-1">
                    <input name="fullName" type="text" class="form-control" id="fullName"
                           placeholder="<fmt:message key="label.fullName"/>"
                           value="${sessionScope.firstName} ${sessionScope.lastName}">
                    <label for="fullName"><fmt:message key="label.fullName"/></label>
                </div>


                <div class="form-floating mb-1">
                    <input name="mobile" type="text" class="form-control" id="mobile"
                           placeholder="<fmt:message key="label.mobile"/>" value="${sessionScope.mobile}">
                    <label for="mobile"><fmt:message key="label.mobile"/></label>
                </div>


                <div class="form-floating mb-1">
                    <input name="address" type="text" class="form-control" id="address"
                           placeholder="<fmt:message key="label.address"/>" value="${sessionScope.address}">
                    <label for="address"><fmt:message key="label.address"/></label>
                </div>


                <div class="form-floating mb-1">
                    <input name="comment" type="text" class="form-control" id="comment"
                           placeholder="<fmt:message key="label.comment"/>" value="${sessionScope.comment}">
                    <label for="comment"><fmt:message key="label.comment"/></label>
                </div>

                <div class="form-floating mt-1">
                    <p><fmt:message key="label.paymentWay"/></p>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentWay" id="paymentCard" checked value="card" required>
                        <label class="form-check-label" for="paymentCard">
                            <b><fmt:message key="label.paymentCard"/></b>
                            <p><fmt:message key="label.paymentByCard"/></p>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="paymentWay" id="paymentCash" value="cash" required>
                        <label class="form-check-label" for="paymentCash">
                            <b><fmt:message key="label.cashPayment"/> </b>
                            <p><fmt:message key="label.paymentByCash"/></p>
                        </label>
                    </div>
                </div>

                <button class="btn btn-lg btn-primary mb-1 mt-4" type="submit"><fmt:message key="button.submit"/></button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>