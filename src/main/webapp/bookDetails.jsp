<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <img width="100%" src="${sessionScope.book.image}" alt="">
        </div>
        <div class="col-md-6 pt-3">
            <h1>${sessionScope.book.title}</h1>
            <small>${sessionScope.book.authorName}</small>
            <p class="pt-3">${sessionScope.book.description}</p>

            <div class="border mt-4 rounded p-2">
                <h4><fmt:message key="label.addToCart"/></h4>
                <p><fmt:message key="label.price">: ${sessionScope.book.price}</p>
                <a type="button" href="#" class="btn btn-primary"><fmt:message key="button.add"/></a>
            </div>

            <table class="table table-hover mt-5">
                <tr>
                    <th scope="col"><fmt:message key="label.quantity"/></th>
                    <th scope="col">${sessionScope.book.quantity}</th>
                </tr>
                <tr>
                    <th scope="col"><fmt:message key="label.author"/>:</th>
                    <th scope="col"><a href="#">${sessionScope.book.authorName}</a></th>
                </tr>
                <tr>
                    <th scope="col">Жанр:</th>
                    <th scope="col"><a href="#">${sessionScope.book.genre}</a></th>
                </tr>
                <tr>
                    <th scope="col">Производитель:</th>
                    <th scope="col"><a href="#">${sessionScope.book.publisherName}</a></th>
                </tr>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>