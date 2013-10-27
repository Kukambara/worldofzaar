<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Name</th>
    </thead>
<<<<<<< HEAD
    <tbody>
    <c:forEach var="race" items="${races}">
        <tr>
            <td>
                    ${race.raceName}
=======
    <tbody><c:forEach var="race" items="${races}">
        <tr>
            <td>
                    ${race.name}
>>>>>>> 374fabb858f6202e7464eb59895297932d925317
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>