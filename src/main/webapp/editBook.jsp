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
                <form action="/editBook" method="post">
                    <c:if test="${requestScope.emptyFields != null}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="alert.fillAllData"/>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.editBookSuccess != null}">
                        <div class="alert alert-success" role="alert">
                            <fmt:message key="success.editBook"/>
                        </div>
                    </c:if>

                    <div class="mb-2 row">
                        <label for="bookTitleEn" class="col-md-2 col-form-label"><fmt:message
                                key="label.bookTitleEn"/></label>
                        <div class="col-sm-10">
                            <input name="bookTitleEn" type="text" class="form-control" id="bookTitleEn"
                                   value="${sessionScope.bookEn.title}">
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="bookDescriptionEn" class="col-md-2 col-form-label"><fmt:message
                                key="label.bookDescriptionEn"/></label>
                        <div class="col-sm-10">
                            <textarea name="bookDescriptionEn" id="bookDescriptionEn" cols="100" rows="10">${sessionScope.bookEn.description}</textarea>
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="bookTitleRu" class="col-md-2 col-form-label"><fmt:message
                                key="label.bookTitleRu"/></label>
                        <div class="col-sm-10">
                            <input name="bookTitleRu" type="text" class="form-control" id="bookTitleRu"
                                   value="${sessionScope.bookRu.title}">
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="bookDescriptionRu" class="col-md-2 col-form-label"><fmt:message
                                key="label.bookDescriptionRu"/></label>
                        <div class="col-sm-10">
                            <textarea name="bookDescriptionRu" id="bookDescriptionRu" cols="100" rows="10">${sessionScope.bookRu.description}</textarea>
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="image" class="col-md-2 col-form-label"><fmt:message key="label.image"/></label>
                        <div class="col-sm-10">
                            <input name="image" type="text" class="form-control" id="image" value="${sessionScope.bookEn.image}">
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="author" class="col-md-2 col-form-label"><fmt:message key="label.authors"/></label>
                        <div class="col-sm-10">
                            <select class="form-select" name="author" id="author">
                                <option value="${sessionScope.bookEn.authorId}">${sessionScope.bookEn.authorName}</option>
                                <c:forEach var="author" items="${sessionScope.authorList}">
                                    <option value="${author.id}">${author.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="bookQuantity" class="col-md-2 col-form-label"><fmt:message
                                key="label.quantity"/></label>
                        <div class="col-sm-10">
                            <input name="bookQuantity" type="number" class="form-control" id="bookQuantity"
                                   value="${sessionScope.bookEn.quantity}">
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="genre" class="col-md-2 col-form-label"><fmt:message key="label.genres"/></label>
                        <div class="col-sm-10">
                            <select class="form-select" name="genre" id="genre">
                                <option value="${sessionScope.bookEn.genreId}">${sessionScope.bookEn.genre}</option>
                                <c:forEach var="genre" items="${sessionScope.genreList}">
                                    <option value="${genre.id}">${genre.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="publisher" class="col-md-2 col-form-label"><fmt:message
                                key="label.publisher"/></label>
                        <div class="col-sm-10">
                            <select class="form-select" name="publisher" id="publisher">
                                <option value="${sessionScope.bookEn.genreId}">${sessionScope.bookEn.publisherName}</option>
                                <c:forEach var="publisher" items="${sessionScope.publisherList}">
                                    <option value="${publisher.id}">${publisher.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-2 row">
                        <label for="bookPrice" class="col-md-2 col-form-label"><fmt:message key="label.price"/></label>
                        <div class="col-sm-10">
                            <input name="bookPrice" type="number" class="form-control" id="bookPrice" value="${sessionScope.bookEn.price}">
                        </div>
                    </div>


                    <button type="submit" class="btn btn-primary"><fmt:message key="button.edit"/></button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp"/>