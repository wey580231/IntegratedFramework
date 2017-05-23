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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/uikit.gradient.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/notify.gradient.min.css"/>
</head>
<body>
<div style="width:100%;text-align:center">
    <form class="uk-form" method="post" action="/UsersAction.action" onsubmit="return formValidate()">
        <div class="uk-margin">
            <input class="uk-input uk-width-1-4" name="username" id="EMailAddress" type="email"
                   placeholder="E-Mail">
        </div>
        <div class="uk-margin">
            <input class="uk-input uk-width-1-4" name="password" id="password" type="password" placeholder="Password">
        </div>
        <div class="uk-margin">
            <button class="uk-button uk-width-1-4">登录</button>
        </div>
    </form>
</div>
<div class="uk-margin uk-align-center  uk-width-1-4">
    <button class="uk-button uk-button-link uk-align-left">用户注册</button>
    <button class="uk-button uk-button-link uk-align-right">忘记密码</button>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/notify.min.js"></script>
<script src="${pageContext.request.contextPath}/jsps/Login/FormValidate.js"></script>
</body>
</html>
