<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Experience</h3>

<a href="/admin/mastercard/delete/${masterCard.mastersCardId}">delete</a>

<form action="/admin/mastercard/edit/${masterCard.mastersCardId}" method="post">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span1">

            </div>
            <div class="span11">

                <div class="control-group">
                    <label class="control-label" for="cardId">Card</label>

                    <div class="controls">
                        <select name="cardId" id="cardId" required>
                            <c:forEach var="card" items="${cards}">
                                <option value="${card.supportCard.cardId}${card.warriorCard.cardId}"
                                        <c:if test="${card.supportCard.cardId == masterCard.supportCard.cardId || card.warriorCard.cardId == masterCard.warriorCard.cardId}">
                                            selected
                                        </c:if>
                                        >
                                        ${card.cardName}
                                </option>
                            </c:forEach>
                        </select>

                    </div>
                </div>


                <div class="control-group">
                    <label class="control-label" for="cardLevel">Level</label>

                    <div class="controls">
                        <input type="number" id="cardLevel" name="cardLevel" placeholder="Level"
                               value="${masterCard.cardLevel}" required>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="price">Price</label>

                    <div class="controls">
                        <input type="number" id="price" name="price" placeholder="Price"
                               value="${masterCard.price}">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="donatePrice">Donate price</label>

                    <div class="controls">
                        <input type="number" id="donatePrice" name="donatePrice" placeholder="Donate price"
                               value="${masterCard.donatePrice}">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>