<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Damage</th>
    <th>Armor</th>
    <th>Health</th>
    <th>Energy</th>

    </thead>
    <tbody>
    <c:forEach var="card" items="${cards}">
        <tr>
            <td>
                    ${card.cardId}
            </td>
            <td>
                <a href="/admin/race/picture/edit/${race.race.raceId}">${race.raceName}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>