<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/10
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../lib/bootstrap/css/bootstrap.css">
    <script src="../lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="../lib/bootstrap/js/bootstrap.min.js"></script>

</head>

<!-- 触发模态框 -->
<div style="padding-top: 35px;" style="padding-right:60%;">
    <button class="btn btn-primary btn-lg " data-toggle="modal" data-target="#myModal">
        增加
    </button>
    <div class="container">
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" style="width: 600px" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>项目</th>
                                <th>项目</th>
                                <th>项目</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>产品</td>
                                <td>待定</td>
                                <td>待定</td>
                            </tr>
                            <tr>
                                <td>产品</td>
                                <td>待定</td>
                                <td>待定</td>
                            </tr>
                            <tr>
                                <td>产品</td>
                                <td>待定</td>
                                <td>待定</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>

                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>
</div>

<!-- 弹出框-->
<div class="container" style="padding: 490px 1px 1px 1300px;">
    <button type="button" class="btn btn-primary popover-show btn-circle"
            data-container="body" data-toggle="popover" data-html="true" data-placement="left"
            data-content='<button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-trash"></span> 删除
                    </button>
                        <button type="button" class="btn btn-default btn-lg ">
                        <span class="glyphicon glyphicon-edit"></span> 修改
                    </button>
                    <br>
                    <button type="button" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-trash"></span> 新增
                    </button>
                        <button type="button" class="btn btn-default btn-lg ">
                        <span class="glyphicon glyphicon-edit"></span> 查询
                    </button>'>
        &#10006
    </button>
</div>


<script>
    $(function () {
        $("[data-toggle='popover']").popover();
    });
    //$(function () { $('.popover-show').popover('show');});

</script>

</body>
</html>

