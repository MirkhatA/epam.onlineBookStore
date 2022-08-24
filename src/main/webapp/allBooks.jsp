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

                <table class="table table-hover">
                    <tr>
                        <th>id</th>
                        <th><fmt:message key="label.bookTitle"/></th>
                        <th><fmt:message key="label.bookTitle"/></th>
                        <th><fmt:message key="label.author"/></th>
                        <th><fmt:message key="label.genre"/></th>
                        <th><fmt:message key="label.quantity"/></th>
                        <th><fmt:message key="label.price"/></th>
                        <th><fmt:message key="label.publisher"/></th>
                        <th><fmt:message key="label.status"/></th>
                        <th></th>
                    </tr>

                    <c:forEach var="book" items="${sessionScope.bookList}">
                        <tr>
                            <td>${book.id}</td>
                            <td>${book.title}</td>
                            <td>
                                <p class="book-description">
                                        ${book.description}
                                </p>
                            </td>
                            <td>${book.authorName}</td>
                            <td>${book.genre}</td>
                            <td>${book.quantity}</td>
                            <td>${book.price}</td>
                            <td>${book.publisherName}</td>
                            <td>${book.status}</td>
                            <td>
                                <a href="/showEditBook?id=${book.id}" type="button" class="btn btn-warning mb-1"><fmt:message key="button.edit"/></a>
                                <a href="/deleteBook?id=${book.id}" type="button" class="btn btn-danger"><fmt:message key="button.delete"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>