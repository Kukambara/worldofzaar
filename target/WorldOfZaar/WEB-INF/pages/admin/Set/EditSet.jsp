<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Set</h3>
<a href="/admin/set/delete/${engText.set.setId}">delete set</a>

<form action="/admin/set/edit/${engText.set.setId}" method="post">

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span1">

            </div>
            <div class="span11">
                <div class="control-group">
                    <label class="control-label" for="date">Date</label>

                    <div class="controls">
                        <input type="date" id="date" name="date" required
                               value="<fmt:formatDate value="${engText.set.date}" pattern="yyyy-MM-dd"/>">
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
                        <input type="text" id="ruName" name="ruName" placeholder="Название" required
                               value="${ruText.setName}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="ruDescription">Описание</label>

                    <div class="controls">
                        <textarea cols="80" id="ruDescription" name="ruDescription" placeholder="Описание" rows="10"
                                  required>${ruText.setInfo}</textarea>
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
                        <input type="text" id="engName" name="engName" placeholder="Name" required
                               value="${engText.setName}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="engDescription">Description</label>

                    <div class="controls">
                        <textarea cols="80" id="engDescription" name="engDescription" placeholder="Description"
                                  rows="10"
                                  required>${engText.setInfo}</textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>