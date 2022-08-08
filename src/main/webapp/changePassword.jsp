<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="d-flex flex-nowrap">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="width: 280px;">
        <jsp:include page="profileNavbar.jsp"/>
    </div>

    <div class="col-md-7 ms-5 mt-5">
        <form action="/editPassword" method="post">
            <c:if test="${requestScope.passwordsAreDifferent != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.samePasswords"/>
                </div>
            </c:if>
            <c:if test="${requestScope.emptyFields != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.fillAllData"/>
                </div>
            </c:if>
            <c:if test="${requestScope.oldPasswordIsIncorrect != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.oldPasswordIncorrect"/>
                </div>
            </c:if>
            <c:if test="${requestScope.editPasswordSuccess != null}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="success.editPassword"/>
                </div>
            </c:if>

            <div class="mb-2 row">
                <label for="oldPassword" class="col-md-2 col-form-label"><fmt:message key="input.oldPassword"/></label>
                <div class="col-sm-10">
                    <input name="oldPassword" type="password"  class="form-control" id="oldPassword">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="passwordRepeat" class="col-md-2 col-form-label"><fmt:message key="input.repeatPassword"/></label>
                <div class="col-sm-10">
                    <input name="passwordRepeat" type="password" class="form-control" id="passwordRepeat">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="password" class="col-md-2 col-form-label"><fmt:message key="input.newPassword"/></label>
                <div class="col-sm-10">
                    <input name="newPassword" type="password"  class="form-control" id="password">
                </div>
            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="button.edit"/></button>
        </form>
    </div>
</main>

<jsp:include page="footer.jsp"/>