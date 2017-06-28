<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/31
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">


    .uk-table td {
        vertical-align: top;
        width: 190px;
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

    <div style="float:left;">
        <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
    </div>

    <div style="float:left;margin-top: 5px;">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">资源工作班次管理</span>
    </div>

    <div style="float:left;margin-left: 2%;">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>


    <!--按钮-->
    <%--<div class="uk-width-1-4" style="margin-left: 36%;">--%>
    <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
        <button class="uk-button uk-icon-plus uk-button-primary" data-uk-modal="{target:'#add'}">新增</button>
        <button class="uk-button uk-icon-edit uk-button-primary"  ng-click="editShift()" data-uk-modal="{target:'#edit'}">修改</button>
        <button class="uk-button uk-icon-trash uk-button-primary" ng-click="deleteShift()">删除</button>
    </div>
    <%--</div>--%>
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
                                    <div class="selectpng" style="/*margin-left: 38%;*/">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td style="width: 132px;">班次编码</td>
                                <td>班次名称</td>
                                <td>班次类型</td>
                                <td>时间段</td>
                                <td>加班</td>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body">
                        <table class="uk-table uk-table-striped uk-table-hover " id="table_value">
                            <tbody class="uk-text-center">
                            <tr ng-repeat="x in arr | orderBy: 'id':desc">
                                <td><input name="check" type="checkbox"  ng-checked="isSelected(x.id)"
                                           ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                                <td id="id">{{x.id}}</td>
                                <td id="name">{{x.name}}</td>
                                <td id="type">{{x.type}}</td>
                                <td id="Slot">{{x.slot}}</td>
                                <td id="extra">{{x.extra}}</td>
                            </tr>
                            </tbody>

                        </table>
                    </div>


                </div>

                <br/>

                <!--底部页码-->
                <%--<div style="margin-top: -25px;">
                    <ul class="uk-pagination" &lt;%&ndash;style="margin-top: 7%;"&ndash;%&gt; data-uk-pagination="{currentPage:50}">
                        <li><button class="uk-button" style="background-image: url('../../images/bom_img/ye1.png');color: white;"><a href="" style="color: white;">首页</a></button></li>
                        <li><button class="uk-button my"><a href="">上一页</a></button></li>
                        <li><button class="uk-button my"><a href="">下一页</a></button></li>
                        <li><button class="uk-button my"><a href="">尾页</a></button></li>
                        <li>共10页</li>&nbsp;
                        <li>
                            到第<input type="text" value="1" style="width: 28px;background-color: #EEF7FC;">页
                        </li>
                        <li>
                            <button class="uk-button" style="background-image: url('../../images/bom_img/ye2.png');color: white;">确定</button>
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
        <div class=" uk-hidden uk-float-right" id="button">
            <div class="uk-panel uk-panel-box">
                <div class="data-uk-button-radio">
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">DAG面板</button>
                </div>
            </div>
        </div>
    </div>--%>
</div>
<!--填写新增订单信息-->
<div class="uk-modal uk-overflow-container" id="add">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div id="dialog-form" title="资源班次信息">
            <form class="uk-form uk-form-horizontal">
                <fieldset>
                    <label for="add-id">班次编码</label> <br/>
                    <input type="text" name="add-id" id="add-id" class="text ui-widget-content ui-corner-all" ><br/>
                    <label for="add-name">班次名称</label><br/>
                    <input type="text" name="add-name" id="add-name" class="text ui-widget-content ui-corner-all" ><br/>
                    <label for="add-type">班次类型</label><br/>
                    <input type="text" name="add-type" id="add-type" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-Slot">时间段</label><br/>
                    <input type="text" name="add-Slot" id="add-Slot" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="add-extra">加班</label><br/>
                    <input type="text" name="add-extra" id="add-extra" class="text ui-widget-content ui-corner-all"><br/>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" ng-click="reset()">Reset</button>
            <button type="button" class="uk-button" ng-click="formValidate()">Add</button>
        </div>
    </div>
</div>

<!--修改订单信息-->
<div class="uk-modal uk-overflow-container" id="edit">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div id="dialog-form" title="资源班次信息">
            <form>
                <fieldset ng-repeat="x in form track by $index">
                    <label for="edit-id">班次编码</label> <br/>
                    <input type="text" name="edit-id" id="edit-id" class="text ui-widget-content ui-corner-all" value="{{x.id}}"><br/>
                    <label for="edit-name">班次名称</label><br/>
                    <input type="text" name="edit-name" id="edit-name" class="text ui-widget-content ui-corner-all" value="{{x.name}}"><br/>
                    <label for="edit-type">班次类型</label><br/>
                    <input type="text" name="edit-type" id="edit-type" class="text ui-widget-content ui-corner-all" value="{{x.type}}"><br/>
                    <label for="edit-Slot">时间段</label><br/>
                    <input type="text" name="edit-Slot" id="edit-Slot" class="text ui-widget-content ui-corner-all" value="{{x.slot}}"><br/>
                    <label for="edit-extra">加班</label><br/>
                    <input type="text" name="edit-extra" id="edit-extra" class="text ui-widget-content ui-corner-all" value="{{x.extra}}"><br/>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" ng-click="reset()">Reset</button>
            <button type="button" class="uk-button" ng-click="update()">Edit</button>
        </div>
    </div>
</div>




