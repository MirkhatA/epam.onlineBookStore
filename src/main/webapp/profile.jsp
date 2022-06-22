<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="d-flex flex-nowrap">
    <jsp:include page="profileNavbar.jsp"/>

    <div class="col-md-7 ms-5 mt-5">
        <form action="/editProfile" method="post">
            <c:if test="${requestScope.emptyFields != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.fillAllData"/>
                </div>
            </c:if>
            <c:if test="${requestScope.emailIsTaken != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.emailIsTaken"/>
                </div>
            </c:if>
            <c:if test="${requestScope.mobileIsTaken != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.phoneIsTaken"/>
                </div>
            </c:if>
            <c:if test="${requestScope.editProfileSuccess != null}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="success.editProfile"/>
                </div>
            </c:if>

            <div class="mb-2 row">
                <label for="firstName" class="col-md-2 col-form-label"><fmt:message key="label.firstName"/></label>
                <div class="col-sm-10">
                    <input name="firstName" type="text"  class="form-control" id="firstName" value="${sessionScope.firstName}">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="lastName" class="col-md-2 col-form-label"><fmt:message key="label.lastName"/></label>
                <div class="col-sm-10">
                    <input name="lastName" type="text" class="form-control" id="lastName" value="${sessionScope.lastName}">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="email" class="col-md-2 col-form-label"><fmt:message key="label.email"/></label>
                <div class="col-sm-10">
                    <input name="email" type="email" class="form-control" id="email" value="${sessionScope.email}">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="address" class="col-md-2 col-form-label"><fmt:message key="label.address"/></label>
                <div class="col-sm-10">
                    <input name="address" type="text" class="form-control" id="address" value="${sessionScope.address}">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="mobile" class="col-md-2 col-form-label"><fmt:message key="label.mobile"/></label>
                <div class="col-sm-10">
                    <input name="mobile" type="text" class="form-control" id="mobile" value="${sessionScope.mobile}">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="registeredDate" class="col-md-2 col-form-label"><fmt:message key="label.registeredDate"/></label>
                <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="registeredDate" value="${sessionScope.registeredDate}">
                </div>
            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="button.edit"/></button>
        </form>
    </div>
</main>

<jsp:include page="footer.jsp"/>