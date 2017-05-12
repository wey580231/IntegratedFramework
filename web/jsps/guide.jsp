<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/11
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>计划排程向导</title>

    <link rel="stylesheet" href="../lib/UIKit/css/uikit.min.css"/>
    <script src="../lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="../lib/UIKit/js/uikit.min.js"></script>
    <script src="../lib/UIKit/js/uikit-icons.min.js"></script>
    <script type="text/javascript" src="check.js"></script>
</head>
<body>
<br/>
<div id="all" style="margin-left: 3%;">
    <div id="hs">
        <p uk-margin>
            <button class="uk-button uk-button-primary uk-button-small" style="background-color: #3873D6">Step 1
            </button>
            >
            <button class="uk-button uk-button-primary uk-button-small">Step 2</button>
            >
            <button class="uk-button uk-button-primary uk-button-small">Step 3</button>
        </p>
    </div>
    <br/>
    <div style="border: 1px solid lightgray;width: 90%;">
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
                <td><input type="checkbox" name="order"></td>
                <td>表格项目一</td>
                <td>表格项目一</td>
                <td>表格项目一</td>
                <td>表格项目一</td>
                <td>表格项目一</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="order"></td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="order"></td>
                <td>表格项目三</td>
                <td>表格项目三</td>
                <td>表格项目三</td>
                <td>表格项目三</td>
                <td>表格项目三</td>
            </tr>
            </tbody>
        </table>
    </div>
    <br/>
    <div id="next">
        <c:if test="SelectFalse==true">

        </c:if>
        <%--<a href="step2.jsp">--%>
        <button class="uk-button uk-button-primary uk-button-small" style="background-color: orange;color: white;"
                onclick="Submit()">下一步
        </button>

    </div>
</div>

</body>
</html>
