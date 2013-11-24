<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<table class="table table-hover">
    <thead>
    <th>Id</th>
    <th>CardId</th>
    <th>Level</th>
    <th>Price</th>
    <th>Donate Price</th>
    </thead>
    <tbody>
    <c:forEach var="masterCard" items="${masterCards}">
        <tr>
            <td>
                    ${masterCard.mastersCardId}
            </td>
            <td>
                <a href="/admin/mastercard/edit/${masterCard.mastersCardId}">${masterCard.supportCard.cardId}${ masterCard.warriorCard.cardId}</a>
            </td>
            <td>
                    ${masterCard.cardLevel}
            </td>
            <td>
                    ${masterCard.price}
            </td>
            <td>
                    ${masterCard.donatePrice}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../AdminPageFooter.jsp"/>