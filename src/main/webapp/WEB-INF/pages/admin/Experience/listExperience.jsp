<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Level</th>
    <th>Experience</th>
    </thead>
    <tbody>
    <c:forEach var="experience" items="${experiences}">
        <tr>
            <td>
                    ${experience.experienceId}
            </td>
            <td>
                <a href="/admin/experience/edit/${experience.experienceId}">${experience.level}</a>
            </td>
            <td>
                    ${experience.levelExperience}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>