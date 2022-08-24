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

                <c:if test="${requestScope.orderDeleted != null}">
                    <div class="alert alert-success" role="alert">
                        <fmt:message key="success.editBook"/>
                    </div>
                </c:if>

                <table class="table table-hover">
                    <tr>
                        <th>id</th>
                        <th><fmt:message key="label.name"/></th>
                        <th></th>
                    </tr>

                    <c:forEach var="genre" items="${sessionScope.genreList}">
                        <tr>
                            <td>${genre.id}</td>
                            <td>${genre.name}</td>
                            <td>
                                <a href="/showEditGenre?id=${genre.id}" type="button" class="btn btn-warning mb-1"><fmt:message key="button.edit"/></a>
                                <a href="/deleteGenre?id=${genre.id}" type="button" class="btn btn-danger"><fmt:message key="button.delete"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>