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
    #chooseDialog {
        width: 950px;
    }
    #choosedDialog {
        width: 950px;
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

<div class="block">

    <div style="float:left;">
        <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
    </div>

    <div style="float:left;margin-top: 5px;">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">计划排程管理</span>
    </div>


    <!--新计划排程-->
    <div class="uk-width-1-4" style="float: right;margin-top: 5px;">
        <!--<div class="uk-clearfix">
            <button class="uk-button uk-float-right " <%--id="create-order"--%>
                    data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                    data-uk-tooltip="{pos:'top'}" title="快捷菜单" >新计划排程
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
        </div>-->
        <button class="uk-button uk-float-right "
                data-uk-modal="{target:'#schedule'}" id="scheduleButton" style="border-radius:15px; ">新计划排程
        </button>
    </div>
</div>
<%--<hr class="uk-article-divider">--%>
<br/>
<%--<div class="uk-grid" style="margin-top: -25px;">--%>

<!--右侧表格-->
<div class="uk-width-1-1 tb">
    <div class="tbfir">

        <div class="tbsec">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div class="selectpng" style="/*margin-left: 35%;*/">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>
                                </td>
                                <%-- <td style="width: 55px;">序号</td>--%>
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
                    <div class="fixtable-body">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">
                            <tbody class="uk-text-center">

                            </tbody>
                        </table>
                    </div>
                </div>

                <br/>

                <!--底部页码-->
                <%--<div style="margin-top: -25px;">
                    <ul class="uk-pagination" &lt;%&ndash;style="margin-top: 7%;"&ndash;%&gt; data-uk-pagination="{currentPage:50}">
                        <li>
                            <button class="uk-button"
                                    style="background-image: url('../../images/bom_img/ye1.png');color: white;"><a
                                    href="" style="color: white;">首页</a></button>
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
                        <li>共10页</li>&nbsp;
                        <li>
                            到第<input type="text" value="1" style="width: 28px;background-color: #EEF7FC;">页
                        </li>
                        <li>
                            <button class="uk-button"
                                    style="background-image: url('../../images/bom_img/ye2.png');color: white;">确定
                            </button>
                        </li>
                    </ul>
                </div>--%>

            </div>

        </div>
    </div>

    <!--快捷键-->
    <%--<div class="uk-clearfix" style="margin-top: -3%;">
        <button class="uk-button uk-float-right " id="create-order"
                style="background-image: url('../../images/kuaijie.png');background-size: 100% 100%;"
                title="快捷菜单">
        </button>
        <div class=" uk-hidden uk-float-right"&lt;%&ndash; id="button"&ndash;%&gt;>
            <div class="uk-panel uk-panel-box">
                <div class="data-uk-button-radio">
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">DAG面板</button>
                </div>
            </div>
        </div>
    </div>--%>
</div>


<!--选择订单弹框-->
<div class="uk-modal uk-overflow-container" id="chooseOrder" style="width:100%">
    <div class="uk-modal-dialog" id="chooseDialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3 class="validateTips">请选择需要排程的订单</h3>
        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-table-hover ">
                <thead class="uk-text-center">
                <tr style="background-color: #e1eaf1;">
                    <td></td>
                    <td>名称</td>
                    <td>来源</td>
                    <td>优先级</td>
                    <td>下单时间</td>
                    <td>最早开工</td>
                    <td>最晚开工</td>
                </tr>
                </thead>
            </table>
            <table class="uk-table uk-table-striped uk-table-hover " id="orders" style="width:100%">
                <tbody class="uk-text-center">
                <tr ng-repeat="x in lastarray">
                    <td><input name="check1" type="checkbox" ng-checked="isSelected(x.id)"
                               ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                    <td style="display:none">{{x.id}}</td>
                    <td>{{x.name}}</td>
                    <td>{{x.origin}}</td>
                    <td>{{x.priority}}</td>
                    <td>{{x.t0}}</td>
                    <td>{{x.t1}}</td>
                    <td>{{x.t2}}</td>
                </tr>
                <tr ng-repeat="x in curinfo">
                    <td><input name="check1" type="checkbox" ng-checked="isSelected(x.id)"
                               ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                    <td style="display:none">{{x.id}}</td>
                    <td>{{x.name}}</td>
                    <td>{{x.origin}}</td>
                    <td>{{x.priority}}</td>
                    <td>{{x.t0}}</td>
                    <td>{{x.t1}}</td>
                    <td>{{x.t2}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--底部页码-->

        <div class="uk-modal-footer uk-text-right">
            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                <li>
                    <button class="uk-button my" data-uk-modal="{target:'#schedule'}"><a href="">上一页</a>
                    </button>
                </li>
                <li>
                    <button class="uk-button my" ng-click="choosedOrder();orderHide()"
                            data-uk-modal="{target:'#color_table'}"><a href="">下一页</a></button>
                </li>
            </ul>
        </div>
    </div>
</div>
</div>
</div>


<!--排程参数输入弹框-->
<div class="uk-modal uk-overflow-container" id="schedule">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3 class="validateTips">请填写参数</h3>
        <form class="uk-form uk-form-horizontal">
            <fieldset>
                <label for="add-name">名称</label><br/>
                <input type="text" name="add-name" id="add-name" clsss="text ui-widget-content ui-corner-all"><br/>
                <label for="add-rollTime">滚动周期</label><br/>
                <input type="text" name="add-rollTime" id="add-rollTime"
                       class="text ui-widget-content ui-corner-all"><br/>
                <label for="add-scheduleDays">当前排程时间长度</label><br/>
                <input type="text" name="add-scheduleDays" id="add-scheduleDays"
                       class="text ui-widget-content ui-corner-all"><br/>
                <label for="add-t0">开始时间</label><br/>
                <input type="text" name="add-t0" id="add-t0" class="text ui-widget-content ui-corner-all"><br/>
                <label for="add-t2">结束时间</label><br/>
                <input type="text" name="add-t2" id="add-t2" class="text ui-widget-content ui-corner-all"><br/>
            </fieldset>
        </form>
        <div class="uk-modal-footer uk-text-right">
            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                <li>
                    <button class="uk-button my" data-uk-modal="{target:'#choose'}"><a href="">上一页</a></button>
                </li>
                <li>
                    <button class="uk-button my" ng-click="showCurInfo();"
                            data-uk-modal="{target:'#chooseOrder'}"><a href="">下一页</a></button>
                </li>
                <li>
                    <button class="uk-button my" ng-click="showSchedule();"><a href="">确定</a></button>
                </li>
            </ul>
        </div>
        <div class="uk-overflow-container">
            <div id='calendar'></div>
        </div>
    </div>
</div>


<!--第三步-->
<div class="uk-modal uk-overflow-container" id="color_table">
    <div class="uk-modal-dialog" id="choosedDialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3 class="validateTips">进行排程的订单</h3>
        <div class="uk-overflow-container">
            <table id="runTable" class="uk-table uk-table-striped uk-table-hover ">
                <thead class="uk-text-center">
                <tr style="background-color: #e1eaf1;">
                    <td></td>

                    <td>名称</td>
                    <td>来源</td>
                    <!--<td>产品名</td>
                    <td>数量</td>-->
                    <td>优先级</td>
                    <td>下单时间</td>
                    <td>最早开工</td>
                    <td>最晚开工</td>
                </tr>
                </thead>
            </table>
            <table class="uk-table uk-table-striped uk-table-hover " id="ordered" style="width:100%">
                <tbody class="uk-text-center">
                <tr id="first" ng-repeat="x in form">
                    <td><input id="check" name="check" type="checkbox" ng-checked="isSelected(x.id)"
                               ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                    <td style="display:none">{{x.id}}</td>
                    <td>{{x.name}}</td>
                    <td>{{x.origin}}</td>
                    <td>{{x.priority}}</td>
                    <td>{{x.t0}}</td>
                    <td>{{x.t1}}</td>
                    <td>{{x.t2}}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <!--底部页码-->

        <div class="uk-modal-footer uk-text-right">
            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                <li>
                    <button class="uk-button my" data-uk-modal="{target:'#chooseOrder'}"><a href="">上一页</a>
                    </button>
                </li>
                <li>
                    <button class="uk-button my" ng-click="configAPS();hide()"><a href="">开始排程</a></button>
                </li>
            </ul>
        </div>

    </div>
</div>


</div>
