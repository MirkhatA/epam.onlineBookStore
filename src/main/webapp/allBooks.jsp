<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setLocale value="${sessionScope.user}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<main class="d-flex flex-nowrap">
    <jsp:include page="adminProfileNavbar.jsp"/>

    <div class="col-md-7 ms-5 mt-5">
        <table class="table table-hover">
            <tr>
                <th>id</th>
                <th><fmt:message key="label.bookTitle"/></th>
                <th>Description</th>
                <th>Author</th>
                <th>Genre</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Publisher</th>
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
                    <td>
                        <a href="" type="button" class="btn btn-warning mb-1">Edit</a>
                        <a href="" type="button" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

<jsp:include page="footer.jsp"/>