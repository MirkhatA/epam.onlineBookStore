<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="mt-5 m-auto text-center container">
    <div class="row justify-content-md-center">
        <form class="col-md-3" action="/login" method="post">
            <c:if test="${requestScope.pleaseLogin != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.pleaseLogin"/>
                </div>
            </c:if>
            <c:if test="${requestScope.registerSuccess != null}">
                <div class="alert alert-success" role="alert">
                     <fmt:message key="success.registered"/>
                </div>
            </c:if>

            <c:if test="${requestScope.wrongData != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.wrongLoginOrPassword"/>
                </div>
            </c:if>


            <h1 class="h3 mb-3 fw-normal"><fmt:message key="label.signIn"/></h1>

            <div class="form-floating mb-1">
                <input name="login" type="text" class="form-control" id="login" placeholder="<fmt:message key="input.login"/>">
                <label for="login"><fmt:message key="input.login"/></label>
            </div>

            <div class="form-floating mb-1">
                <input name="password" type="password" class="form-control" id="passwordInput" placeholder="<fmt:message key="input.password"/>">
                <label for="passwordInput"><fmt:message key="input.password"/></label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mb-1 mt-4" type="submit"><fmt:message key="button.signIn"/></button>
            <p class="mb-3 text-muted"><a href="#"><fmt:message key="button.signUp"/></a></p>
        </form>
    </div>
</main>


<jsp:include page="footer.jsp"/>