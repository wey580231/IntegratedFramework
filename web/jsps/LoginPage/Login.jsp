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
    <form method="post" onsubmit="return formValidate()">
        <div class="uk-margin">
            <div class="uk-inline uk-width-1-2">
                <span class="uk-form-icon" uk-icon="icon: mail"></span>
                <input class="uk-input" id="EMailAddress" type="email" placeholder="E-Mail">
            </div>
        </div>

        <div class="uk-margin">
            <div class="uk-inline uk-width-1-2">
                <span class="uk-form-icon" uk-icon="icon: lock"></span>
                <input class="uk-input" id="password" type="password" placeholder="Password">
            </div>
        </div>
        <button class="uk-button uk-button-primary uk-width-1-2" type="submit">登录</button>
    </form>
    <div class="uk-margin uk-align-center uk-width-1-2">
        <button class="uk-button uk-button-text uk-align-left">用户注册</button>
        <button class="uk-button uk-button-text uk-align-right" type="submit">忘记密码</button>
    </div>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit-icons.min.js"></script>
<script src="FormValidate.js"></script>
</body>
</html>
