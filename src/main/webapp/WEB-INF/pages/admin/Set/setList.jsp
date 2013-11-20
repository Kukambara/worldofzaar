<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Date</th>
    </thead>
    <tbody>
    <c:forEach var="set" items="${sets}">
        <tr>
            <td>${set.set.setId}</td>

            <td><a href="/admin/set/edit/${set.set.setId}">${set.setName}</a></td>

            <td><fmt:formatDate value="${set.set.date}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>