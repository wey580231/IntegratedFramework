<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/10
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit/css/uikit.min.css"/>
</head>
<body>
<div style="width:100%;text-align:center">
    <form>
        <div class="uk-margin">
            <div class="uk-form-controls">
                <div class="uk-inline uk-width-1-2">
                    <span class="uk-form-icon" uk-icon="icon: user"></span>
                    <input class="uk-input" id="username" type="text" placeholder="Username">
                </div>
            </div>
            <div class="uk-form-controls">
                <div class="uk-inline uk-width-1-2">
                    <span class="uk-form-icon" uk-icon="icon: lock"></span>
                    <input class="uk-input" id="password" type="text" placeholder="Password">
                </div>
            </div>
            <button class="uk-button uk-button-default">Submit</button>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit-icons.min.js"></script>
</body>
</html>
