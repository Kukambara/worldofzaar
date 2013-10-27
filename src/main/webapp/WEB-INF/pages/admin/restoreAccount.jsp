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
    <style type="text/css">
            /* Override some defaults */
        html, body {
            background-color: #787878;
        }

        body {
            padding-top: 40px;
        }

        .container {
            width: 300px;
        }

            /* The white background content wrapper */
        .container > .content {
            background-color: #fff;
            padding: 20px;
            margin: 0 -20px;
            -webkit-border-radius: 10px 10px 10px 10px;
            -moz-border-radius: 10px 10px 10px 10px;
            border-radius: 10px 10px 10px 10px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
        }

        .login-form {
            margin-left: 65px;
        }

        legend {
            margin-right: -50px;
            font-weight: bold;
            color: #404040;
        }

    </style>
</head>
<body>
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
    <div class="content">
        <div class="row">
            <div class="login-form">
                <h2>Restore account</h2>

                <form id="formLogin" action="/restoreAccount" method="post">
                    <fieldset>
                        <input name="email" id="username" type="text"
                               placeholder="Email">
                        <button type="submit" id="btnLogin" class="btn">Restore</button>
                        <li><a href="/adminSignUp">Sign up</a></li>
                        <li><a href="/adminSignIn">Sign in</a></li>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>