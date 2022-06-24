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
        <form action="#" method="post">

        </form>
    </div>
</main>

<jsp:include page="footer.jsp"/>