<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${sessionScope.user}"/>
<jsp:include page="header.jsp"/>

<main class="d-flex flex-nowrap">
    <c:choose>
        <c:when test="${empty sessionScope.userId}">
            <div class="alert alert-danger ms-5" role="alert">
                <fmt:message key="alert.forbidden"/>
            </div>
        </c:when>
        <c:otherwise>
            <jsp:include page="adminProfileNavbar.jsp"/>

            <div class="col-md-7 ms-5 mt-5">
                <form action="/addGenre" method="post">
                    <c:if test="${requestScope.emptyFields != null}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="alert.fillAllData"/>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.createGenreSuccess != null}">
                        <div class="alert alert-success" role="alert">
                            <fmt:message key="success.createGenre"/>
                        </div>
                    </c:if>

                    <div class="mb-2 row">
                        <label for="genreNameEn" class="col-md-2 col-form-label"><fmt:message key="label.genreEn"/></label>
                        <div class="col-sm-10">
                            <input name="genreNameEn" type="text" class="form-control" id="genreNameEn">
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="genreNameRu" class="col-md-2 col-form-label"><fmt:message key="label.genreRu"/></label>
                        <div class="col-sm-10">
                            <input name="genreNameRu" type="text" class="form-control" id="genreNameRu">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary"><fmt:message key="button.add"/></button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>