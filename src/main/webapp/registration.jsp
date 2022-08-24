<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="mt-5 m-auto text-center container">
    <div class="row justify-content-md-center">
        <form class="col-md-3" action="/registration" method="post">
            <c:if test="${requestScope.passwordsAreDifferent != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.samePasswords"/>
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
            <c:if test="${requestScope.emptyFields != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.fillAllData"/>
                </div>
            </c:if>


            <h1 class="h3 mb-3 fw-normal"><fmt:message key="label.signUp"/></h1>

            <div class="form-floating mb-1">
                <input name="firstName" type="text" class="form-control" id="nameInput"
                       placeholder="<fmt:message key="input.name"/>" autocomplete="off">
                <label for="nameInput"><fmt:message key="input.name"/></label>
            </div>
            <div class="form-floating mb-1">
                <input name="mobile" type="tel" class="form-control tel" id="phoneInput" autocomplete="off">
                <label for="phoneInput"><fmt:message key="input.phone"/></label>
            </div>
            <div class="form-floating mb-1">
                <input name="email" type="email" class="form-control" id="emailInput"
                       placeholder="<fmt:message key="input.email"/>" autocomplete="off">
                <label for="emailInput"><fmt:message key="input.email"/></label>
            </div>
            <div class="form-floating mb-1">
                <input name="password" type="password" class="form-control" id="passwordInput"
                       placeholder="<fmt:message key="input.password"/>" autocomplete="off">
                <label for="passwordInput"><fmt:message key="input.password"/></label>
            </div>
            <div class="form-floating mb-1">
                <input name="rePassword" type="password" class="form-control" id="rePasswordInput"
                       placeholder="<fmt:message key="input.repeatPassword"/>" autocomplete="off">
                <label for="rePasswordInput"><fmt:message key="input.repeatPassword"/></label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mb-1 mt-4" type="submit"><fmt:message
                    key="button.signUp"/></button>
            <p class="mb-3 text-muted"><a href="login.jsp"><fmt:message key="button.signIn"/></a></p>
        </form>
    </div>
</main>


<jsp:include page="footer.jsp"/>