<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="screen">
    <script src="/resources/js/jquery-1.10.2.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <!--<script src="/resources/js/jquery.ui.widget.js"></script>
    <script src="/resources/js/jquery.iframe-transport.js"></script>
    <script src="/resources/js/fileupload.js"></script>-->
    <script src="/resources/js/myjs.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
<div class="wrapper">
    <div class="container">
    <h3>World Of zaar</h3>

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


        <div class="container-fluid">
        <div class="row-fluid">
            <div class="span7">
                <div class="mycontent-left">
                    <h4>Sign In</h4>

                    <form action="/signIn" method="post">
                        <fieldset>
                            <input name="email" id="username" type="text"
                                   placeholder="Email"> <br>
                            <input name="password" type="password"
                                   placeholder="Password"><br>
                            <button type="submit" class="btn">Sign in</button>
                            <li><a href="/restoreAccount">Forgot password</a></li>
                        </fieldset>
                    </form>
                </div>
            </div>
            <div class="span5">
                <div class="mycontent-right">
                    <h4>Sign Up</h4>

                    <form action="/signUp" method="post">
                        <fieldset>
                            <input name="email" type="text"
                                   placeholder="Email">     <br>
                            <input name="pass" type="password"
                                   placeholder="Password"><br>
                            <button type="submit" id="btnLogin" class="btn">Sign up</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <div class="push"><!--//--></div>
</div>
</div>

</body>
</html>