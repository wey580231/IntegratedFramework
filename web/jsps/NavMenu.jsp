<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/9
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../lib/UIKit/css/uikit.min.css"/>
</head>
<body>
<div class="uk-width-1-2@s uk-width-2-5@m">
    <ul class="uk-nav-default uk-nav-parent-icon" uk-nav="multiple: true">
        <li class="uk-active"><a href="#">集成框架</a></li>
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
<script src="../lib/jquery/jquery-3.2.1.min.js"></script>
<script src="../lib/UIKit/js/uikit.min.js"></script>
<script src="../lib/UIKit/js/uikit-icons.min.js"></script>
</body>
</html>
