<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<div id="content" style="width:100%;height:100%">

    <div id="menuBar" class="block"
         style="height: 45px;margin-top: 10px;background-color: white;margin-left: 0px;width: 100%;">

        <div style="float:left;">
            <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
        </div>

        <div style="float:left;margin-top: 5px;">
            &nbsp;
            <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">3D可视化</span>
        </div>

        <!--按钮-->
        <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
            <div style="margin: 3px;float:left">
                <label style="margin-right: 3px">驱动模式</label>
                <button class="uk-button uk-button-primary">3D实施驱动</button>
                <button class="uk-button  uk-button-primary">3D模拟驱动</button>
                <label style="margin-right: 3px">驱动设置</label>
                <button class="uk-button  uk-button-primary">单屏</button>
                <button class="uk-button uk-button-primary">双屏</button>
            </div>
        </div>
    </div>

    <div id="d3view" style="width:100%;bottom:1px;margin:0px;padding:0px;">
        <iframe id="frame" src="http://localhost:8080/WebGL"
                style="width:100%;height:100% ;margin:0px;padding:0px"></iframe>
    </div>

</div>

<script>

    function resizeD3ViewSize() {

        var height = $("#content").height() - $("#menuBar").height();
        $("#d3view").css("height", height);
        $("#frame").css("height", height);
    }

    $(document).ready(function () {
        resizeD3ViewSize();
    });

    $(window).resize(function () {
        resizeD3ViewSize();
    });

</script>
