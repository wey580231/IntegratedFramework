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
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-3.0/css/uikit.min.css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/uikit.gradient.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/search.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/datepicker.min.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/form-select.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/sticky.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/progress.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/accordion.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/UIKit-2.27.4/css/components/autocomplete.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/jqueryui/jquery-ui.min.css"/>

        <style>
            .uk-nav-side > li > a{
                color: white;
            }
            ul.uk-nav-sub{
                margin-left: 30px;
            }
            ul.uk-nav-sub > li{
                height: 35px;
            }
            .uk-nav-side ul a{
                color: white;
            }
            /*
                里层li中超链接的背景
            */
            .uk-nav-side ul a:hover {
                background: url("../../images/left_img/click.png") no-repeat 30px;
                color: #ecc412;
            }
            /*
                里层li的背景
            */
            /*.uk-nav-sub ul li:hover {
                background: url("../../images/left_img/click.png") no-repeat 30px;
                color: #ecc412;
            }*/
            /*
                ！！！！
            */
            .uk-nav-side ul a:checked {
                background: url("../../images/left_img/click.png") no-repeat 30px;
                color: #ecc412;
            }
            /*
                外层li中的超链接悬浮
            */
            .uk-nav-side > li > a:focus, .uk-nav-side > li > a:hover{
                background: #1d5880;
                color: white;
                text-shadow: 0 0px 0 #fff;
            }

            /*
                外层li高度
            */
            .uk-nav-side > li > a{
                height: 30px;
            }
            /*
                外层li中的div样式
            */
            .uk-nav li > a > div{
                margin-top: 8px;
                float: left;
                font-size: 14px;
                line-height: 10px;
            }
            /*
                li右侧箭头
            */
            .uk-nav-parent-icon > .uk-parent > a::after {
                content: "\f104";
                width: 20px;
                margin-right: -10px;
                float: right;
                font-family: FontAwesome;
                text-align: center;
                margin-top: 4px;
            }
            /*
                li底线
            */
            .uk-list-line > li:nth-child(n+2) {
                margin-top: 5px;
                padding-top: 5px;
                border-top: 1px solid #335d78;
            }
        </style>
</head>
<body>
<!--头部-->
<%--<div style="height: 70px;border-bottom: 1px solid gray;">
    <div  class="uk-width-1-6" style="background-color: #1c4f7a; height: 70px;float: left;">
        <img src="../../images/logo2.png" style="margin-top: 16px;margin-left: 16px;">
    </div>
    <div style="float: left;width: 80%;height: 65px;margin-top: 5px;">
        <img src="../../images/user.png" style="margin-left: 87%;"> 欢迎登录
    </div>
    <div style="height: 70px;">
        <img src="../../images/point.png" style="margin-top: 23px;margin-left: 20px;">
    </div>

</div>--%>
<!--左侧-->
<div class="uk-width-1-6" style="float: left;height: 900px;background-color: #164360;">
    <!--图标-->
    <div  class="uk-width-1-1" style="background-color: #1c4f7a; height: 60px;">
        <img src="../../images/logo2.png" style="margin-top: 16px;margin-left: 16px;">
    </div>
    <!--左侧列表-->
    <div  class="uk-width-1-1" style="margin-top: 10px;">
        <ul class="uk-nav uk-nav-parent-icon uk-nav-side  uk-list-line" data-uk-nav="{multiple:false}">
            <li class="uk-parent">
                <a href="#">
                    <div>
                        <img src="../../images/left_img/order.png">  <span style="margin-left: 10px;">订单任务管理</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/OrderManagement">订单管理</a></li>
                    <li><a href="#!/BOMManagement">制造BOM管理</a></li>
                    <li><a href="#!/ResourceDistribution">工序资源分配管理</a></li>
                </ul>
            </li>
            <li class="uk-parent">
                <a href="#">
                    <div>
                        <img src="../../images/left_img/resource.png">    <span style="margin-left: 10px;">资源设备管理</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/ResourceList">资源列表管理</a></li>
                    <li><a href="#!/ResourceClassify">资源分类管理</a></li>
                    <li><a href="#!/ResourceGroup">资源工组管理</a></li>
                    <li><a href="#!/ResourceStation">资源工位管理</a></li>
                    <li><a href="#!/WorkList">资源工作班次管理</a></li>
                </ul>
            </li>
            <li class="uk-parent">
                <a href="#" target="body">
                    <div>
                        <img src="../../images/left_img/plan.png">    <span style="margin-left: 10px;">计划排程管理</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/ScheduleGuide">计划排程向导</a></li>
                    <li><a href="#!/ScheduleSnap">计划排程快照管理</a></li>
                    <li><a href="#!/Interactive">交互式优化与结果可视化</a></li>
                </ul>
            </li>
            <li class="uk-parent">
                <a href="#">
                    <div>
                        <img src="../../images/left_img/online.png">    <span style="margin-left: 10px;">在线监控管理</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/DeviceMonitor">设备监控</a></li>
                    <li><a href="#!/OnlineManagement">订单执行监控</a></li>
                </ul>
            </li>
            <li class="uk-parent">
                <a href="#">
                    <div>
                        <img src="../../images/left_img/adjust.png">    <span style="margin-left: 10px;">高级调整分析</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/AdjustProcedure">调整工序</a></li>
                    <li><a href="#!/AdjustOrder">调整订单任务</a></li>
                    <li><a href="#!/AdjustDevice">调整设备资源</a></li>
                    <li><a href="#!/AdjustFactory">调整工厂布局</a></li>
                </ul>
            </li>
            <li class="uk-parent">
                <a href="#">
                    <div>
                        <img src="../../images/left_img/3D.png">    <span style="margin-left: 10px;">三维可视化</span>
                    </div>
                </a>
                <ul class="uk-nav-sub">
                    <li><a href="#!/Show">可视化显示</a></li>
                    <li><a href="#!/ViewConfigure">可视化配置</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<!--右侧-->
<div class="uk-width-5-6" style="float: left;">
    <!--欢迎登录-->
    <div style="float: left;width: 95%;height: 55px;margin-top: 5px;">
        <img src="../../images/user.png" style="margin-left: 87%;width: 50px;height: 50px;"> 欢迎登录
    </div>
    <div style="height: 60px;">
        <img src="../../images/point.png" style="margin-top: 23px;margin-left: 20px;">
    </div>
    <div style="float:right;background-color: #bdcbd8;height: 830px;width: 100%;">

        <div  style="margin-top: 15px;border: 1px solid lightgray;margin-left: 1%;width: 98%;height: 800px;background-color: #dfe8ef;">
            <ng-view></ng-view>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/lib/UIKit-3.0/js/uikit.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/lib/UIKit-3.0/js/uikit-icons.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/uikit.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/search.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/form-select.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/accordion.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/sticky.js"></script>
<script src="${pageContext.request.contextPath}/lib/UIKit-2.27.4/js/components/autocomplete.js"></script>

<script src="${pageContext.request.contextPath}/lib/Angular-1.6.4/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/Angular-1.6.4/angular-route.min.js"></script>

<script src="${pageContext.request.contextPath}/lib/jqueryui/jquery-ui.min.js"></script>

<script src="/app.js"></script>

<script src="${pageContext.request.contextPath}/jsps/3DView/ShowController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/AdjustAnalysis/AdjustOrderController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/AdjustAnalysis/AdjustProcedureController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/AdjustAnalysis/AdjustDeviceController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/AdjustAnalysis/AdjustFactoryController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OnlineMonitor/OnlineManagementController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OnlineMonitor/DeviceMonitorController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OrderManagement/OrderManagementController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OrderManagement/BOMManagementController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/OrderManagement/ResourceDistributionController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/PlanSchedule/ScheduleGuideController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/PlanSchedule/ScheduleSnapController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/PlanSchedule/InteractiveController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/ResourceListController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/ResourceClassifyController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/ResourceStationController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/WorkListController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/ResourceGroupController.js"></script>
<script src="${pageContext.request.contextPath}/jsps/Login/GuideController.js"></script>


</body>
</html>
