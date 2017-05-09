<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/3
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../lib/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    <script src="../lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="../lib/bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        .icon {
            width: 100px;
            height: 50px;
            float: left;
        }

        .search {
            width: 100px;
            height: 30px;
            float: left;
        }

    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid" style="border: 1px solid lightgray">
        <div class="span12">
            <div style="background:#3873D6;color: white; height:30px;">
                <h4 style="margin-left: 10px;">
                    资源工组管理
                </h4>
            </div>

        </div>


        <div class="icon">
            <form class="form-search" style="width: 110px;height: 30px">
                <input class="search" value="输入关键字" type="text" style=" width: 100px;height: 30px;
            float: left;"/>
            </form>
        </div>
        <div class="icon">
            <div class="btn-group">
                <input class="search" value="工组编码" type="text"/>
                <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li>
                        <a href="#">设置栏目</a>
                    </li>
                    <li>
                        <a href="#">更多设置</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="dropdown-submenu">
                        <a tabindex="-1" href="#">更多选项</a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">操作</a>
                            </li>
                            <li>
                                <a href="#">设置栏目</a>
                            </li>
                            <li>
                                <a href="#">更多设置</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="icon">
            <div class="btn-group">
                <input class="search" value="工作名称" type="text"/>
                <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li>
                        <a href="#">设置栏目</a>
                    </li>
                    <li>
                        <a href="#">更多设置</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="dropdown-submenu">
                        <a tabindex="-1" href="#">更多选项</a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">操作</a>
                            </li>
                            <li>
                                <a href="#">设置栏目</a>
                            </li>
                            <li>
                                <a href="#">更多设置</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>


        <div class="icon">
            <div class="btn-group">
                <input class="search" value="初始位置编码" type="text"/>
                <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li>
                        <a href="#">设置栏目</a>
                    </li>
                    <li>
                        <a href="#">更多设置</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="dropdown-submenu">
                        <a tabindex="-1" href="#">更多选项</a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">操作</a>
                            </li>
                            <li>
                                <a href="#">设置栏目</a>
                            </li>
                            <li>
                                <a href="#">更多设置</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="icon">
            <div class="btn-group">
                <input class="search" value="可工作位置" type="text"/>
                <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">操作</a>
                    </li>
                    <li>
                        <a href="#">设置栏目</a>
                    </li>
                    <li>
                        <a href="#">更多设置</a>
                    </li>
                    <li class="divider">
                    </li>
                    <li class="dropdown-submenu">
                        <a tabindex="-1" href="#">更多选项</a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">操作</a>
                            </li>
                            <li>
                                <a href="#">设置栏目</a>
                            </li>
                            <li>
                                <a href="#">更多设置</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>


        <div class="span1">
            <button class="btn btn-info" type="button">新增</button>
        </div>
        <div class="span1">
            <button class="btn btn-info" type="button">修改</button>
        </div>
        <div class="span1">
            <button class="btn btn-info" type="button">删除</button>
        </div>
        <div class="span1">
            <button class="btn btn-info" type="button">查询</button>
        </div>


        <div class="row-fluid">
            <div class="span12">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>
                        </th>
                        <th>
                            工组编码
                        </th>
                        <th>
                            工作名称
                        </th>
                        <th>
                            初始位置编码
                        </th>
                        <th>
                            状态
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                        </td>
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Default
                        </td>
                    </tr>
                    <tr class="success">
                        <td>
                        </td>
                        <td>
                            1
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            01/04/2012
                        </td>
                        <td>
                            Approved
                        </td>
                    </tr>
                    <tr class="error">
                        <td>
                        </td>
                        <td>
                            2
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            02/04/2012
                        </td>
                        <td>
                            Declined
                        </td>
                    </tr>
                    <tr class="warning">
                        <td>
                        </td>
                        <td>
                            3
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            03/04/2012
                        </td>
                        <td>
                            Pending
                        </td>
                    </tr>
                    <tr class="info">
                        <td>
                        </td>
                        <td>
                            4
                        </td>
                        <td>
                            TB - Monthly
                        </td>
                        <td>
                            04/04/2012
                        </td>
                        <td>
                            Call in to confirm
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="pagination pagination-centered">
                    <ul>
                        <li>
                            <a href="#">上一页</a>
                        </li>
                        <li>
                            <a href="#">1</a>
                        </li>
                        <li>
                            <a href="#">2</a>
                        </li>
                        <li>
                            <a href="#">3</a>
                        </li>
                        <li>
                            <a href="#">4</a>
                        </li>
                        <li>
                            <a href="#">5</a>
                        </li>
                        <li>
                            <a href="#">下一页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>


    </div>
</div>
</body>
</html>