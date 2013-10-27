<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Class</h3>

<form action="/admin/class/createClass" method="post">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span1"></div>
            <div class="span11">
                <div class="control-group">
                    <label class="control-label" for="ruName">Race</label>

                    <div class="controls">
                        <select name="raceId" required>
                            <c:forEach var="race" items="${races}">
                                <option value="${race.race.raceId}">${race.raceName}</option>
                            </c:forEach>

                        </select>
                    </div>
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
                        <input type="text" id="ruName" name="ruName" placeholder="Название" required>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="ruDescription">Описание</label>

                    <div class="controls">
                        <textarea cols="80" id="ruDescription" name="ruDescription" placeholder="Описание" rows="10"
                                  required></textarea>
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
                        <input type="text" id="engName" name="engName" placeholder="Name" required>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="engDescription">Description</label>

                    <div class="controls">
                        <textarea cols="80" id="engDescription" name="engDescription" placeholder="Description"
                                  rows="10"
                                  required></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>