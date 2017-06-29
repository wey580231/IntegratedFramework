<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/21
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/myjs/aps/ipServer.js"></script>
<script src="${pageContext.request.contextPath}/myjs/aps/Interactive.js"></script>

<div id="content" style="width:100%;height:100%">
    <div id="menuBar" class="block"
         style="height: 45px;margin-top: 10px;background-color: white;margin-left: 0px;width: 100%;">

        <div style="float:left;">
            <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
        </div>

        <div style="float:left;margin-top: 5px;">
            &nbsp;
            <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">交互优化</span>
        </div>

        <!--按钮-->
        <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
            <select style="margin-right:20px;height:30px" id="activeSelector">
                <option value="">选择交互控件</option>
                <option value="TableOrderTask.view">订单派工单</option>
                <option value="TableResourceTask.view">资源派工单</option>
                <option value="TableSiteTask.view">工位派工单</option>

                <option value="GanttOrderTaskMenu.view">订单工序甘特图</option>
                <option value="GanttResourceTaskMenu.view">资源工序甘特图</option>
                <option value="GanttJobTaskMenu.view">作业工序甘特图</option>

                <option value="DataTableOrder.view">订单信息表</option>
                <option value="DataTableGroupResource.view">工组信息表</option>
                <option value="DataTableResource.view">资源信息表</option>
                <option value="DataTableProcess.view">工艺信息表</option>
            </select>
            <button class="uk-button uk-button-primary" onclick="loginAps()">APS登录</button>
        </div>
    </div>

    <div id="div_center" data-options="region:'center'" style="background-color: #FFFFFF; overflow: hidden;">

        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="CheckData" name="CheckData" height="40" width="60" border="0" hspace="0">
        </object>

        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="AutoScheduling" name="AutoScheduling" height="40" width="60" border="0" hspace="0">
        </object>

        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="ResumeScheduling" name="ResumeScheduling" height="40" width="60" border="0" hspace="0">
        </object>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="RegenerateExcel" name="RegenerateExcel" height="40" width="60" border="0" hspace="0">
        </object>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="RegenerateView" name="RegenerateView" height="40" width="60" border="0" hspace="0">
        </object>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="Login" name="Login" height="40" width="60" border="0" hspace="0">
        </object>

        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="Logout" name="Logout" height="40" width="60" border="0" hspace="0">
        </object>


        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="TableObjective" name="TableObjective" height="100%" width="100%">
        </object>


        <img id="CloseWindow1" src="../../images/close.png" onclick="CloseView(1)"
             style="position:absolute;right:2px;top:5px;z-index:1;display:none"/>
        <img id="CloseWindow2" src="../../images/close.png" onclick="CloseView(2)"
             style="position:absolute;right:2px;top:5px;z-index:1;display:none"/>
        <img id="CloseWindow3" src="../../images/close.png" onclick="CloseView(3)"
             style="position:absolute;right:2px;top:5px;z-index:1;display:none"/>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="ComView1" name="ComView1" height="100%" width="100%" style="display:none">
        </object>
        <span onmousedown="positionRecord1();" id="heightResizeBar1"
              style="background-color:#E6EEF8; border:1px solid #95B8E8"></span>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="ComView2" name="ComView2" height="100%" width="100%" style="display:none">
        </object>
        <span onmousedown="positionRecord2();" id="heightResizeBar2"
              style="background-color:#E6EEF8; border:1px solid #95B8E8"></span>
        <object classid="clsid:63BF6082-F9AF-457E-AABB-C7C9E125A1A9"
                id="ComView3" name="ComView3" height="100%" width="100%" style="display:none">
        </object>
        <div id="welcome_page" align="center">
            <img id="welcome_image" alt="" src="../../images/welcome.jpg" style="padding-top:24px">
        </div>
        <span class="get_view_ok"></span>
    </div>

</div>
