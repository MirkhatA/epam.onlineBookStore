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
        <form action="/addBook" method="post">
            <c:if test="${requestScope.emptyFields != null}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.fillAllData"/>
                </div>
            </c:if>
            <c:if test="${requestScope.editProfileSuccess != null}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="success.editProfile"/>
                </div>
            </c:if>

            <div class="mb-2 row">
                <label for="bookTitle" class="col-md-2 col-form-label"><fmt:message key="label.bookTitle"/></label>
                <div class="col-sm-10">
                    <input name="bookTitle" type="text"  class="form-control" id="bookTitle">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="image" class="col-md-2 col-form-label"><fmt:message key="label.image"/></label>
                <div class="col-sm-10">
                    <input name="image" type="text"  class="form-control" id="image">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="author" class="col-md-2 col-form-label"><fmt:message key="label.authors"/></label>
                <div class="col-sm-10">
                    <select class="form-select" name="author" id="author">
                        <c:forEach var="author" items="${sessionScope.authors}">
                            <option value="${author.fullName}">${author.fullName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="mb-2 row">
                <label for="bookDescription" class="col-md-2 col-form-label"><fmt:message key="label.bookDescription"/></label>
                <div class="col-sm-10">
                    <textarea name="bookDescription" id="bookDescription" cols="100" rows="10"></textarea>
                </div>
            </div>

            <div class="mb-2 row">
                <label for="bookQuantity" class="col-md-2 col-form-label"><fmt:message key="label.quantity"/></label>
                <div class="col-sm-10">
                    <input name="bookQuantity" type="number"  class="form-control" id="bookQuantity">
                </div>
            </div>

            <div class="mb-2 row">
                <label for="genre" class="col-md-2 col-form-label"><fmt:message key="label.genres"/></label>
                <div class="col-sm-10">
                    <select class="form-select" name="genre" id="genre">
                        <c:forEach var="genre" items="${sessionScope.genres}">
                            <option value="${genre.name}">${genre.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="mb-2 row">
                <label for="bookPrice" class="col-md-2 col-form-label"><fmt:message key="label.price"/></label>
                <div class="col-sm-10">
                    <input name="bookPrice" type="number"  class="form-control" id="bookPrice">
                </div>
            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="button.add"/></button>
        </form>
    </div>
</main>

<jsp:include page="footer.jsp"/>