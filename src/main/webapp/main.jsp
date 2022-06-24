<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.languageName}"/>
<fmt:setBundle basename="messages"/>
<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block sidebar collapse">
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">

                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted text-uppercase">
                        <span><fmt:message key="label.genres"/></span>
                    </h6>
                    <ul class="nav flex-column mb-2">
                        <c:forEach var="genre" items="${sessionScope.genres}">
                            <li class="nav-item">
                                <a class="nav-link" href="/showBooksByGenreId?id=${genre.id}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-tag" viewBox="0 0 16 16">
                                        <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0z"/>
                                        <path d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1zm0 5.586 7 7L13.586 9l-7-7H2v4.586z"/>
                                    </svg>
                                        ${genre.name}
                                </a>
                            </li>
                        </c:forEach>
                        <li class="nav-item">
                            <a class="nav-link" href="/reset">
                                <fmt:message key="label.reset" />
                            </a>
                        </li>
                    </ul>


                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted text-uppercase">
                        <span><fmt:message key="label.authors"/></span>
                    </h6>
                    <ul class="nav flex-column mb-2">
                        <c:forEach var="author" items="${sessionScope.authors}">
                            <li class="nav-item">
                                <a class="nav-link" href="/showBooksByAuthorId?id=${author.id}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-person-check" viewBox="0 0 16 16">
                                        <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                        <path fill-rule="evenodd"
                                              d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
                                    </svg>
                                        ${author.fullName}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </ul>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="chartjs-size-monitor">
                <div class="chartjs-size-monitor-expand">
                    <div class=""></div>
                </div>
                <div class="chartjs-size-monitor-shrink">
                    <div class=""></div>
                </div>
            </div>

            <c:if test="${requestScope.itemAddedToCart != null}">
                <div class="alert alert-success" role="alert">
                    <fmt:message key="success.itemAddedToCart"/>
                </div>
            </c:if>

            <h2><fmt:message key="label.catalog"/></h2>
            <div class="row">
                <c:forEach var="book" items="${sessionScope.books}">
                    <div class="card me-2 mt-2" style="width: 18rem;">
                        <img src="${book.image}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><a href="/showBookDetails?id=${book.id}">${book.title}</a></h5>
                            <p class="card-text">${book.authorName}</p>
                        </div>
                        <div class="card-body">
                            <a href="/addItemToCart?id=${book.id}" class="btn btn-warning card-link">
                                <fmt:message key="button.add"/>
                            </a>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </main>
    </div>
</div>

<jsp:include page="footer.jsp"/>