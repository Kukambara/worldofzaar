<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Card</h3>
<c:if test="${engCardText.supportCard != null}">
    <c:set var="card" value="${engCardText.supportCard}"/>
    <c:set var="cardType" value="support"/>
</c:if>
<c:if test="${engCardText.warriorCard != null}">
    <c:set var="card" value="${engCardText.warriorCard}"/>
    <c:set var="cardType" value="warrior"/>
</c:if>

<a href="/admin/card/delete/${card.cardId}">delete</a>

<form action="/admin/card/edit/${cardId}" method="post" enctype="multipart/form-data">
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span1">

        </div>
        <div class="span11">
            <div class="control-group">
                <label class="control-label" for="energy">Energy</label>

                <div class="controls">
                    <input type="number" id="energy" name="energy" placeholder="Energy" value="${card.cardEnergy}"
                           required>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="pic">Picture</label>

                <div class="controls">

                    <input type="file" id="pic" name="picture" onchange="myFunc(this)"/>

                    <div id="preview_picture">
                        <img src="${card.cardPicture}" class="picturePreview">
                    </div>


                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="classId">Class</label>

                <select id="classId" name="classId" required>
                    <c:forEach var="classif" items="${classes}">
                        <option value="${classif.classification.classificationId}"
                                <c:if test="${card.classification.classificationId == classif.classification.classificationId}">
                                    selected
                                </c:if>
                                >
                                ${classif.className}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-group">
                <label class="control-label" for="classId">Property</label>

                <select id="propertyId" name="propertyId" required>
                    <c:forEach var="property" items="${properties}">
                        <option value="${property.propertyId}"
                                <c:if
                                        test="${card.property.propertyId == property.propertyId}">
                                    selected
                                </c:if>
                                >
                                ${property.propertySystemDescription}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="control-group">
                <label class="control-label" for="isElite">IsElite</label>

                <div class="controls">

                    <input type="checkbox" id="isElite" name="isElite"
                    <c:if test="${card.elite == 'true'}">
                           checked
                    </c:if>
                            >
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="propertyString">PropertyString</label>

                <div class="controls">
                    <input type="text" id="propertyString" name="propertyString" placeholder="Property string"
                           value="${card.propertySystemString}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Card type</label>

                <div class="controls">
                    <input type="radio" name="cardType" value="warrior"
                    <c:if test="${engCardText.warriorCard != null}">
                           checked
                    </c:if>
                           disabled> Warrior<br>
                    <input type="radio" name="cardType" value="support"
                    <c:if test="${engCardText.supportCard != null}">
                           checked
                    </c:if>
                           disabled> Support<br>
                </div>
            </div>
            <div id="warriorCardType">
                <div class="control-group">
                    <label class="control-label" for="armor">Armor</label>

                    <div class="controls">
                        <input type="number" id="armor" name="armor" placeholder="Armor"
                        <c:if test="${cardType == 'warrior'}">
                               value="${card.cardArmor}"
                        </c:if>
                                >
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="damage">Damage</label>

                    <div class="controls">
                        <input type="number" id="damage" name="damage" placeholder="Damage"
                        <c:if test="${cardType == 'warrior'}">
                               value="${card.cardDamage}"
                        </c:if>
                                >
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="health">Health</label>

                    <div class="controls">
                        <input type="number" id="health" name="health" placeholder="Health"
                        <c:if test="${cardType == 'warrior'}">
                               value="${card.cardHealth}"
                        </c:if>
                                >
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">Subset</label>

                <select id="subsetId" name="subsetId" required>
                    <c:forEach var="subset" items="${subsets}">
                        <option value="${subset.subsetId}"
                                <c:if
                                        test="${card.subset.subsetId == subset.subsetId}">
                                    selected
                                </c:if>
                                >
                                ${subset.subsetTechName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span1">
            <h4>Ru</h4>
        </div>
        <div class="span11">
            <div class="control-group">
                <label class="control-label" for="ruName">Название</label>

                <div class="controls">
                    <input type="text" id="ruName" name="ruName" placeholder="Название" required
                           value="${ruCardText.cardName}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="ruSlogan">Слоган</label>

                <div class="controls">
                    <textarea cols="80" id="ruSlogan" name="ruSlogan" placeholder="Слоган" rows="10">
                        ${ruCardText.cardSlogan}
                    </textarea>
                </div>
            </div>

        </div>
    </div>
</div>

<hr>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span1">
            <h4>Eng</h4>
        </div>
        <div class="span11">
            <div class="control-group">
                <label class="control-label" for="engName">Name</label>

                <div class="controls">
                    <input type="text" id="engName" name="engName" placeholder="Name"
                           required value="${engCardText.cardName}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="engSlogan">Slogan</label>

                <div class="controls">
                    <textarea cols="80" id="engSlogan" name="engSlogan" placeholder="Slogan"
                              rows="10">${engCardText.cardSlogan}</textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>