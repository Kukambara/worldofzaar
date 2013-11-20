<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Name</th>
    </thead>
    <tbody>
    <c:forEach var="cloth" items="${clothes}">
        <tr>
            <td>
                    ${cloth.clothId}
            </td>
            <td>
                <a href="/admin/class/blazon/edit/${cloth.clothId}"><img src="${cloth.clothPath}" class="picturePreview"></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../../AdminPageFooter.jsp"/>