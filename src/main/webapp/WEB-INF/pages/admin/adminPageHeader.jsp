<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="screen">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/myjs.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<div class="wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <!-- Be sure to leave the brand out there if you want it shown -->
                <a class="brand" href="/admin/">WorldOfZaar adminPanel</a>

                <!-- Everything you want hidden at 940px or less, place within here -->
                <div class="nav-collapse">
                    <ul class="nav">
                        <jsp:include page="adminMenu.jsp"/>
                    </ul>
                    <ul class="nav pull-right">
                        <li><p class="navbar-text">Hi, <c:out value="${sessionScope.userEmail}"/></p></li>
                        <li><a href="/signOut">Sign out</a></li>
                    </ul>
                </div>

            </div>
        </div>
    </div>
    <div class="container">
        <c:if test="${param.errorMessage != null}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>Error!</strong> ${param.errorMessage}
        </div>
        </c:if>
        <c:if test="${param.infoMessage != null}">
        <div class="alert alert-info">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>Information!</strong> ${param.infoMessage}
        </div>
        </c:if>
        <c:if test="${errorMessage != null}">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>Error!</strong> ${errorMessage}
        </div>
        </c:if>
        <c:if test="${infoMessage != null}">
        <div class="alert alert-info">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>Information!</strong> ${infoMessage}
        </div>
        </c:if>




