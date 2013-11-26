<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Energy</th>
    <th>Type</th>
    <th>Health</th>
    <th>Armor</th>
    <th>Damage</th>

    </thead>
    <tbody>
    <c:forEach var="cardText" items="${cards}">
        <c:if test="${cardText.supportCard != null}">

            <c:set var="card" value="${cardText.supportCard}"/>
            <c:set var="cardType" value="Support"/>
        </c:if>
        <c:if test="${cardText.warriorCard != null}">
            <c:set var="card" value="${cardText.warriorCard}"/>
            <c:set var="cardType" value="Warrior"/>
        </c:if>

        <tr>
            <td>
                    ${card.cardId}
            </td>
            <td>
                <a href="/admin/card/edit/${card.cardId}">${cardText.cardName}</a>
            </td>
            <td>
                    ${card.cardEnergy}
            </td>
            <td>
                    ${cardType}
                <c:if test="${card.elite=='true'}">
                    <c:out value=", elite"/>
                </c:if>
            </td>
            <td>
                <c:if test="${cardText.warriorCard != null}">
                    ${card.cardHealth}
                </c:if>
            </td>
            <td>
                <c:if test="${cardText.warriorCard != null}">
                    ${card.cardArmor}
                </c:if>
            </td>
            <td>
                <c:if test="${cardText.warriorCard != null}">
                    ${card.cardDamage}
                </c:if>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>