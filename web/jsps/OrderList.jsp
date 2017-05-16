<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/9
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="../lib/UIKit/css/uikit.min.css"/>

    <style type="text/css">
        #hs {
            float: left;
            margin-left: 10px;
            height: 35px;
        }
    </style>
</head>
<body>
<div>
    <div>
        <%--搜索框--%>
        <div class="uk-margin" id="hs">
            <form class="uk-search uk-search-default">
                <span class="uk-search-icon-flip" uk-search-icon></span>
                <input class="uk-search-input" type="search" placeholder="Search..." style="height: 35px;">
            </form>
        </div>
        <%--下拉菜单--%>

        <div class="uk-button-group" id="hs">
            <button class="uk-button uk-button-default">订单编码</button>
            <div class="uk-inline">
                <button class="uk-button uk-button-default" type="button" style="height: 35px;">
                    <span uk-icon="icon:  triangle-down"></span></button>
                <div uk-dropdown="mode: click; boundary: ! .uk-button-group; boundary-align: true;">
                    <ul class="uk-nav uk-dropdown-nav">
                        <li class="uk-active"><a href="#">Active</a></li>
                        <li><a href="#">Item</a></li>
                        <li class="uk-nav-header">Header</li>
                        <li><a href="#">Item</a></li>
                        <li><a href="#">Item</a></li>
                        <li class="uk-nav-divider"></li>
                        <li><a href="#">Item</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="uk-button-group" id="hs">
            <button class="uk-button uk-button-default">订单状态</button>
            <div class="uk-inline">
                <button class="uk-button uk-button-default" type="button" style="height: 35px;">
                    <span uk-icon="icon:  triangle-down"></span></button>
                <div uk-dropdown="mode: click; boundary: ! .uk-button-group; boundary-align: true;">
                    <ul class="uk-nav uk-dropdown-nav">
                        <li class="uk-active"><a href="#">Active</a></li>
                        <li><a href="#">Item</a></li>
                        <li class="uk-nav-header">Header</li>
                        <li><a href="#">Item</a></li>
                        <li><a href="#">Item</a></li>
                        <li class="uk-nav-divider"></li>
                        <li><a href="#">Item</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <%--按钮--%>
        <div id="hs">
            <p uk-margin style="margin-left: 50%;">
                <button class="uk-button uk-button-primary uk-button-small">修改</button>
                <button class="uk-button uk-button-primary uk-button-small">删除</button>
                <button class="uk-button uk-button-primary uk-button-small">新增</button>
            </p>
        </div>


    </div>
</div>
<table class="uk-table uk-table-striped uk-table-hover">
    <thead>
    <tr>
        <th></th>
        <th>项目一</th>
        <th>项目二</th>
        <th>项目三</th>
        <th>项目四</th>
        <th>项目五</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><input type="checkbox"></td>
        <td>表格项目一</td>
        <td>表格项目一</td>
        <td>表格项目一</td>
        <td>表格项目一</td>
        <td>表格项目一</td>
    </tr>
    <tr>
        <td><input type="checkbox"></td>
        <td>表格项目二</td>
        <td>表格项目二</td>
        <td>表格项目二</td>
        <td>表格项目二</td>
        <td>表格项目二</td>
    </tr>
    <tr>
        <td><input type="checkbox"></td>
        <td>表格项目三</td>
        <td>表格项目三</td>
        <td>表格项目三</td>
        <td>表格项目三</td>
        <td>表格项目三</td>
    </tr>
    </tbody>
</table>
<script src="../lib/jquery/jquery-3.2.1.min.js"></script>
<script src="../lib/UIKit/js/uikit.min.js"></script>
<script src="../lib/UIKit/js/uikit-icons.min.js"></script>
</body>
</html>
