<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit/css/uikit.min.css"/>
</head>
<body>
<div>
    <h1 class="uk-heading-divider uk-text-center">集成框架</h1>
</div>
<div class="uk-width-1-6" style="float: left;">
    <ul class="uk-nav-default uk-nav-parent-icon" uk-nav="multiple: true">
        <li class="uk-parent">
            <a href="#">订单任务管理</a>
            <ul class="uk-nav-sub">
                <li><a href="#">Sub item</a></li>
                <li><a href="#">Sub item</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="body.jsp">资源设备管理</a>
            <ul class="uk-nav-sub">
                <li><a href="#">资源列表管理</a></li>
                <li><a href="#">资源分类管理</a></li>
                <li><a href="#">资源工组管理</a></li>
                <li><a href="#">资源工位管理</a></li>
                <li><a href="#">资源工作班次管理</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="body2.jsp" target="body">计划排程管理</a>
            <ul class="uk-nav-sub">
                <li><a href="#">Sub item</a></li>
                <li><a href="#">Sub item</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="#">在线监控管理</a>
            <ul class="uk-nav-sub">
                <li><a href="#">Sub item</a></li>
                <li><a href="#">Sub item</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="#">高级调整分析</a>
            <ul class="uk-nav-sub">
                <li><a href="#">Sub item</a></li>
                <li><a href="#">Sub item</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="#">三维可视化</a>
            <ul class="uk-nav-sub">
                <li><a href="#">Sub item</a></li>
                <li><a href="#">Sub item</a></li>
            </ul>
        </li>
    </ul>
</div>
<div class="uk-width-5-6" style="float:right">
    <ng-view>
        
    </ng-view>
</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit/js/uikit-icons.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/Angular-1.6.4/angular.min.js"></script>
</body>
</html>
