<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/3
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
    <script language="javascript">

        var bar1 = new Q6MenuBar("bar1", "任务管理");
        function load() {

            bar1.colorStyle = 2;
            /*
             指定图片目录
             */
            bar1.config.imgDir = "<c:url value='/menu/img/'/>";
            /*
             菜单之间是否相互排斥
             */
            bar1.config.radioButton = true;
            /*
             分类管理：指定要添加的菜单名称（如果这个名称的菜单已经存在，不会重复添加）
             查看分类：指定要添加的菜单项名称
             点击菜单项时要请求的地址
             body：结果的显示框架页名称
             */
            bar1.add("订单任务管理", "", "", "body");

            bar1.add("资源设备管理", "资源列表管理", "", "body");
            bar1.add("资源设备管理", "资源分类管理", "<c:url value=''/>", "body");
            bar1.add("资源设备管理", "资源工组管理", "", "body");
            bar1.add("资源设备管理", "资源工位管理", "", "body");
            bar1.add("资源设备管理", "资源工作班次管理", "", "body");

            bar1.add("计划排程管理", "", "", "body");
            bar1.add("在线监控管理", "", "", "body");
            bar1.add("高级调整分析", "", "", "body");
            bar1.add("三维可视化", "", "", "body");

            //获取div元素
            var d = document.getElementById("menu");
            //把菜单对象转换成字符串，赋给<div>元素做内容
            d.innerHTML = bar1.toString();
        }
    </script>
</head>
<body onload="load()" style="margin: 0px; background: rgb(151,173,254);">
<div id="menu"></div>

</body>
</html>
