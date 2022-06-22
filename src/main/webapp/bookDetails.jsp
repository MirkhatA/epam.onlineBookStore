<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.language}"/>
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
                <h4>Добавить в корзину</h4>
                <p>Цена: ${sessionScope.book.price}</p>
                <a type="button" href="#" class="btn btn-primary">Добавить в корзину</a>
            </div>

            <table class="table table-hover mt-5">
                <tr>
                    <th scope="col">Количество на складе:</th>
                    <th scope="col">${sessionScope.book.quantity}</th>
                </tr>
                <tr>
                    <th scope="col">Автор:</th>
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