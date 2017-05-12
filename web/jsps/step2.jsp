<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/11
  Time: 16:03
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
    <style type="text/css">
        .uk-form-horizontal .uk-form-label {
            width: 100px;
        }

        .uk-form-horizontal .uk-form-controls {
            margin-left: 100px;
        }

        .uk-input {
            width: 250px;
        }

        .uk-select {
            width: 250px;
        }
    </style>
</head>
<body>
<br/>
<div id="all" style="margin-left: 10px;">
    <div id="hs">
        <p uk-margin>
            <button class="uk-button uk-button-primary uk-button-small">Step 1</button>
            >
            <button class="uk-button uk-button-primary uk-button-small" style="background-color: #3873D6">Step 2
            </button>
            >
            <button class="uk-button uk-button-primary uk-button-small">Step 3</button>
        </p>
    </div>
    <br/>
    <div style="border: 1px solid lightgray;width: 500px;height: 400px;">
        <div style="width: 90%;margin-left: 10%;margin-top: 10%;">
            <form class="uk-form-horizontal uk-margin-large">

                <div class="uk-margin">
                    <label class="uk-form-label" for="text1">IP地址</label>
                    <div class="uk-form-controls">
                        <input class="uk-input" id="text1" type="text" placeholder="请输入您的ip...">
                    </div>
                </div>

                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">订单编号</label>
                    <div class="uk-form-controls">
                        <input class="uk-input" id="form-horizontal-text" type="text" placeholder="请输入您的订单编号...">
                    </div>
                </div>

                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-select">Select</label>
                    <div class="uk-form-controls">
                        <select class="uk-select" id="form-horizontal-select">
                            <option>Option 01</option>
                            <option>Option 02</option>
                        </select>
                    </div>
                </div>

                <div class="uk-margin">
                    <div class="uk-form-label">Radio</div>
                    <div class="uk-form-controls uk-form-controls-text">
                        <label><input class="uk-radio" type="radio" name="radio1"> Option 01</label><br>
                        <label><input class="uk-radio" type="radio" name="radio1"> Option 02</label>
                    </div>
                </div>

            </form>
        </div>

    </div>
    <br/>
    <div id="next">
        <a href="guide.jsp">
            <button class="uk-button uk-button-primary uk-button-small" style="background-color: orange;color: white;">
                上一步
            </button>
        </a>
        <a href="step3.jsp">
            <button class="uk-button uk-button-primary uk-button-small" style="background-color: orange;color: white;">
                下一步
            </button>
        </a>
    </div>
</div>


</body>
</html>

