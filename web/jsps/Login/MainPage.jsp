<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/15
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="IntegratedFramework">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-3.0/css/uikit.min.css"/>
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
                <li><a href="#!/OrderManagement">订单管理</a></li>
                <li><a href="#!/BOMManagement">制造BOM管理</a></li>
                <li><a href="#">工序资源分配管理</a></li>
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
                <li><a href="#">计划排程向导</a></li>
                <li><a href="#">计划排程快照管理</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="#">在线监控管理</a>
            <ul class="uk-nav-sub">
                <li><a href="#">设备监控</a></li>
                <li><a href="#">订单执行监控</a></li>
            </ul>
        </li>
        <li class="uk-parent">
            <a href="#">高级调整分析</a>
            <ul class="uk-nav-sub">
                <li><a href="#">调整工序</a></li>
                <li><a href="#">调整订单任务</a></li>
                <li><a href="#">调整设备资源</a></li>
                <li><a href="#">调整工厂布局</a></li>
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
    <ng-view></ng-view>
</div>

<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-3.0/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-3.0/js/uikit-icons.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/Angular-1.6.4/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/Angular-1.6.4/angular-route.min.js"></script>
<script src="/app.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OrderManagement/OrderManagementController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OrderManagement/BOMManagementController.js"></script>
</body>
</html>
