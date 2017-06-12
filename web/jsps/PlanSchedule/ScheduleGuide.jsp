<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/6/01
  Time: 09:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">


    .uk-table td {
        vertical-align: top;
        width: 72px;
        margin-left: 11px;
    }

</style>


<script>
    function changeColor(obj) {
        var f = obj.checked;
        var chkColor = "#c1edfa"; //选中后颜色
        var ouColor = "#f3f8fb";  //偶数行取消选中后的颜色
        var jiColor = "#FFFFFF";
        if (f)
            obj.parentElement.parentElement.style.backgroundColor = chkColor;
        else
            obj.parentElement.parentElement.style.backgroundColor = jiColor;
    }
</script>

<div class="<%--uk-grid--%>" style="height: 8%;margin-top: 10px;background-color: white;margin-left: 0px;width: 100%;">

    <div style="float:left;">
        <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
    </div>

    <div style="float:left;margin-top: 5px;">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">计划排程管理</span>
    </div>


    <!--新计划排程-->
    <div class="uk-width-1-4" style="float: right;margin-top: 5px;">
        <div class="uk-clearfix">
            <button class="uk-button uk-float-right " <%--id="create-order"--%>
                    data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                    data-uk-tooltip="{pos:'top'}" title="快捷菜单">新计划排程
            </button>
            <div class=" uk-hidden uk-float-right" id="button">
                <div class="uk-panel uk-panel-box">
                    <div class="data-uk-button-radio">
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">周报表</button>
                        <br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">资源排班</button>
                        <br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">排程详细信息</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--按钮-->
    <%--<div class="uk-width-1-4" style="margin-left: 36%;">--%>
    <%--<div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
        <button class="uk-button uk-icon-plus uk-button-primary">新增</button>
        <button class="uk-button uk-icon-edit uk-button-primary" data-uk-modal="{target:'#edit'}">修改</button>
        <button class="uk-button uk-icon-trash uk-button-primary">删除</button>
    </div>--%>
    <%--</div>--%>
</div>
<%--<hr class="uk-article-divider">--%>
<br/>
<%--<div class="uk-grid" style="margin-top: -25px;">--%>

<!--右侧表格-->
<div class="uk-width-4-5"
     style="width: 98%;border: 1px solid lightgray;margin-left: 1%;height: 85%;background-color: white;">
    <div style="margin-top: 1%;">

        <div style="width: 100%;border: 0px;margin-top: -10px;">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div style="border: 1px solid lightgray;margin-left: 35%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td style="width: 55px;">序号</td>
                                <td>排产名称</td>
                                <td>完成进度</td>
                                <td>排产日期</td>
                                <td>开始计算时间</td>
                                <td>计算完成时间</td>
                                <td style="width: 85px;">排程状态</td>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 83%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">
                            <tbody class="uk-text-center">
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1</td>
                                <td>KQD生产</td>
                                <td>40%</td>
                                <td>2017-05-17 08:00</td>
                                <td>2017-05-17 08:00</td>
                                <td>2017-05-17 08:01</td>
                                <td>计算完成</td>

                            </tr>

                            </tbody>

                        </table>

                        <!--底部页码-->
                        <div style="margin-top: -25px;">
                            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                                <li>
                                    <button class="uk-button"
                                            style="background-image: url('../../images/bom_img/ye1.png');color: white;">
                                        <a href="" style="color: white;">首页</a></button>
                                </li>
                                <li>
                                    <button class="uk-button my"><a href="">上一页</a></button>
                                </li>
                                <li>
                                    <button class="uk-button my"><a href="">下一页</a></button>
                                </li>
                                <li>
                                    <button class="uk-button my"><a href="">尾页</a></button>
                                </li>
                                <li>共88页</li>&nbsp;
                                <li>
                                    到第<input type="text" value="2" style="width: 28px;background-color: #EEF7FC;">页
                                </li>
                                <li>
                                    <button class="uk-button"
                                            style="background-image: url('../../images/bom_img/ye2.png');color: white;">
                                        确定
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>


                </div>
            </div>

        </div>
    </div>


    <div class="uk-clearfix" style="margin-top: -3%;">
        <button class="uk-button uk-float-right " id="create-order"
                style="background-image: url('../../images/kuaijie.png');background-size: 100% 100%;"
                title="快捷菜单">
        </button>
        <div class=" uk-hidden uk-float-right"<%-- id="button"--%>>
            <div class="uk-panel uk-panel-box">
                <div class="data-uk-button-radio">
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">DAG面板</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="uk-modal uk-overflow-container" id="schedule">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div id="dialog-form" title="订单信息">
            <form class="uk-form uk-form-horizontal">
                <fieldset>
                    <label for="add-id">序号</label> <br/>
                    <input type="text" name="add-id" id="add-id" class="text ui-widget-content ui-corner-all" ><br/>
                    <label for="add-nameTask">排产名称</label><br/>
                    <input type="text" name="add-nameTask" id="add-nameTask" clsss="text ui-widget-content ui-corner-all" ><br/>
                    <label for="add-nameProces">完成进度</label><br/>
                    <input type="text" name="add-nameProces" id="add-nameProces" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-CalendarTask">排产日期</label><br/>
                    <input type="text" name="add-CalendarTask" id="add-CalendarTask" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-initTimeTask">开始计算时间</label><br/>
                    <input type="text" name="add-initTimeTask" id="add-initTimeTask" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-unitTimeTask">计算完成时间</label><br/>
                    <input type="text" name="add-unitTimeTask" id="add-unitTimeTask" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-state">排程状态</label><br/>
                    <input type="text" name="add-state" id="add-state" class="text ui-widget-content ui-corner-all"><br/>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" ng-click="reset()">Reset</button>
            <button type="button" class="uk-button" ng-click="formValidate()">Add</button>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>



