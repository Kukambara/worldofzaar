<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../adminPageHeader.jsp"/>

<h3>Race pictures</h3>
<select>
    <option value="Male">Male</option>
    <option value="Female">Female</option>
</select>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span7">
            <div class="mycontent-left">
                <!--Body content-->
                TEST OF BODY CONTENT
            </div>
        </div>
        <div class="span5">
            <div class="mycontent-right">
                TEST OF SIDEBAR
                <!--Sidebar content-->
            </div>
        </div>
    </div>
</div>
<hr>
<jsp:include page="../AdminPageFooter.jsp"/>