<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Realization</th>
    <th>Description</th>
    </thead>
    <tbody>
    <c:forEach var="property" items="${properties}">
        <tr>
            <td>
                    ${property.propertyId}
            </td>
            <td>
                <a href="/admin/property/edit/${property.propertyId}">${property.propertyRealization}</a>
            </td>
            <td>
                    ${property.propertySystemDescription}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>