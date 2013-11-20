<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Property</h3>
<a href="/admin/property/delete/${property.propertyId}">delete</a>

<form action="/admin/property/edit/${property.propertyId}" method="post">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span1">
                <h4>Ru</h4>
            </div>
            <div class="span11">
                <div class="control-group">
                    <label class="control-label" for="realization">Realization</label>

                    <div class="controls">
                        <input type="text" id="realization" name="realization" placeholder="Realization"
                               value="${property.propertyRealization}" required>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="description">Description</label>

                    <div class="controls">
                        <textarea cols="80" id="description" name="description" placeholder="Description" rows="10"
                                  required>${property.propertySystemDescription}</textarea>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>