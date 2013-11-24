<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>
<h3>Experience</h3>

<form action="/admin/experience/edit/${experience.experienceId}" method="post">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span1">

            </div>
            <div class="span11">
                <div class="control-group">
                    <label class="control-label" for="level">Level</label>

                    <div class="controls">
                        <input type="number" id="level" name="level" placeholder="Level" value="${experience.level}"
                               required>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="experience">Experience in level</label>

                    <div class="controls">
                        <input type="number" id="experience" name="experience" placeholder="Experience"
                               value="${experience.levelExperience}"
                               required>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <button type="submit" class="btn">Save</button>
</form>
<jsp:include page="../AdminPageFooter.jsp"/>