<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/6/1
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">
    .uk-table td {
        vertical-align: top;
        width: 75px;
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

<div class="block">

    <div class="leftpic">
        <img src="images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
    </div>

    <div class="title">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">调整工序</span>
    </div>

    <div class="search">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>


    <!--日历-->

    <%--<div class="uk-width-1-4" style="float: left;margin-top: 10px;margin-left: -70px;">--%>
    <div class="dateshow">
        <form class="uk-form" style="margin-left: 2%;margin-top: 5px;width: 135px;">
            <div class="uk-form-icon">
                <i class="uk-icon-calendar"></i>
                <input type="text" id="datepicker" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                       placeholder="2017/5/17" style="height: 30px;">
            </div>
        </form>
    </div>

    <%--</div>--%>

    <!--下拉框-->
    <div class="orderselect">
        <form class="uk-form" style="margin-left: 2%;">
            <div class="" style="margin-top: 4px;">
                <select class="uk-grid" style="height: 30px;">
                    <option value="1">所有异常</option>
                    <option value="2">异常1</option>
                </select>
            </div>
        </form>
    </div>


    <!--异常模拟-->
    <div class="uk-width-1-4" style="float: right;margin-top: 5px;">
        <div class="uk-clearfix">
            <button class="uk-button uk-float-right " <%--id="create-order"--%>
                    data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                    data-uk-tooltip="{pos:'top'}" title="快捷菜单">异常模拟
            </button>
            <div class=" uk-hidden uk-float-right" id="button">
                <div class="uk-panel uk-panel-box">
                    <div class="data-uk-button-radio">
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">MES</button>
                        <br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">手工模拟</button>
                        <%--<br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">异常3</button>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<hr class="uk-article-divider">--%>
<br/>
<%--<div class="uk-grid" style="margin-top: -25px;">--%>

<!--右侧表格-->
<div class="uk-width-1-1 tb">
    <div class="tbfir">

        <!--tab-->
        <div class="tbsec">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div class="selectpng" style="/*margin-left: 25%;*/">
                                        <img src="images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td>处理状态</td>
                                <td>订单编码</td>
                                <td>上报时间</td>
                                <td>工序码</td>
                                <td>指定时间</td>
                                <td>指定编码</td>
                                <td style="width: 95px;">异常来源</td>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">
                            <tbody class="uk-text-center">
                                <tr id="first" ng-repeat="x in arr | orderBy: 'id':desc">
                                    <td><input id="check" name="check" type="checkbox"  ng-checked="isSelected(x.id)"
                                               ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                                    <td id="state">{{x.state}}</td>
                                    <td id="idOrder">{{x.idOrder}}</td>
                                    <td id="reportTime">{{x.reportTime}}</td>
                                    <td id="idTask">{{x.idTask}}</td>
                                    <td id="appointStartTime">{{x.appointStartTime}}</td>
                                    <td id="appointResource">{{x.appointResource}}</td>
                                    <td id="origin">{{x.origin}}</td>
                                </tr>
                            </tbody>

                        </table>


                    </div>


                </div>

                <br/>

            </div>

        </div>
    </div>
</div>


<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>