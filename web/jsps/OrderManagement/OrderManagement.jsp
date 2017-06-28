<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/5/27
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">
    /*#box{
        height:214px;
        width:500px;
        overflow-y:auto;!** 必须，否则当表格数据过多时，不会产生滚动条，而是自动延长该div的高度 *!
        position:relative;!** 必须，若不设置，拷贝得来的表头将相对于其设置该属性为该值的父节点（或间接父节点）定位，如果没有，则相对于body *!
    }
    table,tr,td,th{
        border:1px solid #ccd;
        border-collapse:collapse;
    }*/
    /*table{
        width:100%;
    }
    td{
        height:24px;
        width:50px;!** 固定单元格宽度，防止分离表头后，表头与数据行错位（缺点） *!
        !*line-height:24px;*!
        line-height:28px;
        padding:3px 5px;
        word-break:break-all;!** 设置当文本过长时换行 *!

    }

    th{
        height:24px;
        width:50px;!** 不管是固定像素或是百分比，应与对应数据列的宽度一致 *!
        line-height:24px;
        background-color:#cfc;
    }*/

    #myTable > .uk-text-center > tr > td {
        padding-left: 8px;
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


    function checkAll() {
        var code_Values = document.getElementById("check");
        for (i = 0; i < code_Values.length; i++) {
            if (code_Values[i].type == "checkbox") {
                code_Values[i].checked = true;
            }
        }
    }


    /**
     * 功能：固定表头
     * 参数   viewid     表格的id
     *       scrollid   滚动条所在容器的id
     *       size       表头的行数（复杂表头可能不止一行）
     */
    function scroll(viewid, scrollid, size) {
        // 获取滚动条容器
        var container = document.getElementById(scrollid);
        // 将表格拷贝一份
        var tb2 = document.getElementById(viewid).cloneNode(true);
        // 获取表格的行数
        var len = tb2.rows.length;
        // 将拷贝得到的表格中非表头行删除
        for (var i = tb2.rows.length; i > size; i--) {
            // 每次删除数据行的第一行
            tb2.deleteRow(size);
        }
        // 创建一个div
        var bak = document.createElement("div");
        // 将div添加到滚动条容器中
        container.appendChild(bak);
        // 将拷贝得到的表格在删除数据行后添加到创建的div中
        bak.appendChild(tb2);
        // 设置创建的div的position属性为absolute，即绝对定于滚动条容器（滚动条容器的position属性必须为relative）
        bak.style.position = "absolute";
        // 设置创建的div的背景色与原表头的背景色相同（貌似不是必须）
        bak.style.backgroundColor = "#cfc";
        // 设置div的display属性为block，即显示div（貌似也不是必须，但如果你不希望总是显示拷贝得来的表头，这个属性还是有用处的）
        bak.style.display = "block";
        // 设置创建的div的left属性为0，即该div与滚动条容器紧贴
        bak.style.left = 0;
        // 设置div的top属性为0，初期时滚动条位置为0，此属性与left属性协作达到遮盖原表头
        bak.style.top = "0px";
        bak.style.width = "100%";
        // 给滚动条容器绑定滚动条滚动事件，在滚动条滚动事件发生时，调整拷贝得来的表头的top值，保持其在可视范围内，且在滚动条容器的顶端
        container.onscroll = function () {
            // 设置div的top值为滚动条距离滚动条容器顶部的距离值
            bak.style.top = this.scrollTop + "px";
        }
    }

    // 在页面加载完成后调用该方法
    window.onload = function () {
        scroll("tab", "box", 1);
    }

</script>

<div class="<%--uk-grid--%>"
     style="/*height: 8%;*/height: 45px;margin-top: 10px;background-color: white;margin-left: 0px;width: 100%;">


    <div style="float:left;">
        <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 45px;">
    </div>

    <div style="float:left;margin-top: 5px;">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">订单管理</span>
    </div>

    <div style="float:left;margin-left: 2%;">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>

    <div style="float:left;margin-left: 2%;">
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
    <div style="float:left;margin-left: 2%;">
        <form class="uk-form" style="margin-left: 2%;">
            <div class="" style="margin-top: 4px;">
                <select class="uk-grid" style="height: 30px;">
                    <option value="1">候选状态1</option>
                    <option value="2">候选状态2</option>
                </select>
            </div>
        </form>
    </div>

    <!--按钮-->

    <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
        <button class="uk-button uk-icon-plus uk-button-primary" id="addButton" data-uk-modal="{target:'#add'}">新增
        </button>
        <button class="uk-button uk-icon-edit uk-button-primary" id="editButton"
                data-uk-modal="{target:'#edit'}" ng-click="editOrder()">修改
        </button>
        <button class="uk-button uk-icon-trash uk-button-primary" ng-click="deleteOrder()">删除</button>
    </div>

</div>

<br/>


<!--右侧表格-->
<%--<div class="uk-width-4-5" style="width: 98%;border: 1px solid lightgray;/*margin-left: 1%;height: 85%;*/background-color: white;


top: 80px;
bottom: 20px;
position: absolute;

">--%>
<div class="uk-width-1-1" style="width: 99%;border: 1px solid lightgray;/*margin-left: 1%;height: 85%;*/background-color: white;

top: 80px;
bottom: 20px;
position: absolute;

">
    <div style="margin-top: 1%;height: 100%;">

        <!--tab-->
        <div style="width: 100%;border: 0px;margin-top: -10px;height: 100%;">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head">
                        <table id="myTable" class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>

                                    <div style="border: 1px solid lightgray;/*margin-left: 25%;*/margin-left: 38%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <%--<td>编码</td>--%>
                                <td>名称</td>
                                <td>来源</td>
                                <!--<td>产品名称</td>-->
                                <td>数量</td>
                                <td>优先级</td>
                                <td>下单时间</td>
                                <td>最早开工</td>
                                <td>最晚开工</td>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 79%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="table_value">
                            <tbody class="uk-text-center">

                            <tr id="first" ng-repeat="x in arr | orderBy: 'id':desc">
                                <td><input id="check" name="check" type="checkbox" ng-checked="isSelected(x.id)"
                                           ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                                <td id="orderId" style="display:none">{{x.id}}</td>
                                <td id="name">{{x.name}}</td>
                                <td id="origin">{{x.origin}}</td>
                                <!-- <td id="idProduct">{{x.idProduct}}</td>-->
                                <td id="quantity">{{x.quantity}}</td>
                                <td id="priority">{{x.priority}}</td>
                                <td id="t0">{{x.t0}}</td>
                                <td id="t1">{{x.t1}}</td>
                                <td id="t2">{{x.t2}}</td>
                            </tr>
                            </tbody>

                        </table>

                    </div>

                </div>


                <!--底部页码-->
                <div style="margin-top: -25px;">
                    <ul class="uk-pagination" <%--style="margin-top: 7%;"--%> data-uk-pagination="{currentPage:50}">
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
                </div>

            </div>

        </div>
    </div>


    <div class="uk-clearfix" style="/*margin-top: -4%;*/margin-top: -45px;">
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
    </div>
</div>

<!--填写新增订单信息-->
<div class="uk-modal uk-overflow-container" id="add">
    <div class="uk-modal-dialog">
        <button type="button" ng-click="reset()" class="uk-modal-close uk-close"></button>
        <div id="dialog-form" title="订单信息">
            <form class="uk-form uk-form-horizontal">
                <fieldset>
                    <label for="add-name">名称</label><br/>
                    <input type="text" name="add-name" id="add-name" class="ui-widget-content ui-corner-all"><br/>


                    <label for="add-origin">来源</label><br/>
                    <input type="text" name="add-origin" id="add-origin" title="请输入a-z/A-Z之间字母组成的字符串！" class="ui-widget-content ui-corner-all"><br/>


                    <label for="add-quantity">数量</label><br/>
                    <input type="text" name="add-quantity" id="add-quantity" title="请输入正确的数字！" class=" ui-widget-content ui-corner-all"><br/>


                    <label for="add-priority">优先级</label><br/>
                    <input type="text" name="add-priority" id="add-priority" title="请输入a-z/A-Z之间字母组成的字符串" class=" ui-widget-content ui-corner-all"><br/>

                    <label for="datepicker add-t0">下单时间</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input id="datepicker add-t0" name="add-t0" &lt;%&ndash;id="add-t0" &ndash;%&gt;
                               class=" ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" style="height: 30px;">
                    </div><br/>

                    <%--<input type="text" name="add-t0" id="add-t0" class="text ui-widget-content ui-corner-all"><br/>--%>
                    <label for="datepicker add-t1">最早开工</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input id="datepicker add-t1" name="add-t1" &lt;%&ndash;id="add-t0" &ndash;%&gt;
                               class=" ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" style="height: 30px;">
                    </div><br/>

                    <%--<input type="text" name="add-t1" id="add-t1" class="text ui-widget-content ui-corner-all"><br/>--%>
                    <label for="datepicker add-t2">最晚开工</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input id="datepicker add-t2" name="add-t2" &lt;%&ndash;id="add-t0" &ndash;%&gt;
                               class=" ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" style="height: 30px;">
                    </div><br/>

                    <%--<input type="text" name="add-t2" id="add-t2" class="text ui-widget-content ui-corner-all"><br/>--%>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" ng-click="reset()">Reset</button>
            <button type="button" class="uk-button" ng-click="orderValidate()">Add</button>
        </div>
    </div>
</div>

<!--修改订单信息-->
<div class="uk-modal uk-overflow-container" id="edit">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div id="dialog-form" title="订单信息">
            <form>
                <fieldset ng-repeat="x in form track by $index">
                    <!--<label for="edit-id">编码</label> <br/>
                    <input type="text" name="edit-id" id="edit-id" class="text ui-widget-content ui-corner-all" value="{{x.id}}"><br/>-->
                    <label for="edit-name">名称</label><br/>
                    <input type="text" name="edit-name" id="edit-name" class="ui-widget-content ui-corner-all"
                           value="{{x.name}}"><br/>
                    <label for="edit-origin">来源</label><br/>
                    <input type="text" name="edit-origin" id="edit-origin" class="ui-widget-content ui-corner-all"
                           value="{{x.origin}}"><br/>
                    <!--<label for="edit-idProduct">产品名称</label><br/>
                    <input type="text" name="edit-idProduct" id="edit-idProduct" class="text ui-widget-content ui-corner-all" value="{{x.idProduct}}"><br/>-->
                    <label for="edit-quantity">数量</label><br/>
                    <input type="text" name="edit-quantity" id="edit-quantity" class="ui-widget-content ui-corner-all"
                           value="{{x.quantity}}"><br/>
                    <label for="edit-priority">优先级</label><br/>
                    <input type="text" name="edit-priority" id="edit-priority" class="ui-widget-content ui-corner-all"
                           value="{{x.priority}}"><br/>
                    <label for="datepicker edit-t0">下单时间</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input type="datetime-local" id="datepicker edit-t0" name="edit-t0"
                               class="ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" value="{{x.t0}}" style="height: 30px;">
                    </div>
                    <br/>
                    <%--<input type="text" name="edit-t0" id="edit-t0" class="text ui-widget-content ui-corner-all" value="{{x.t0}}"><br/>--%>
                    <%--<label for="edit-t1">最早开工</label><br/>--%>
                    <label for="datepicker edit-t1">最早开工</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input type="datetime-local" id="datepicker edit-t1" name="edit-t1"
                               class="ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" value="{{x.t1}}" style="height: 30px;">
                    </div>
                    <br/>
                    <%--<input type="text" name="edit-t1" id="edit-t1" class="text ui-widget-content ui-corner-all" value="{{x.t1}}"><br/>--%>
                    <label for="datepicker edit-t2">最晚开工</label><br/>
                    <div class="uk-form-icon">
                        <i class="uk-icon-calendar"></i>
                        <input type="datetime-local" id="datepicker edit-t2" name="edit-t2"
                               class="ui-widget-content ui-corner-all" data-uk-datepicker="{format:'YYYY/MM/DD'}"
                               placeholder="2017/5/17" value="{{x.t2}}" style="height: 30px;">
                    </div>
                    <br/>
                    <%--<input type="text" name="edit-t2" id="edit-t2" class="text ui-widget-content ui-corner-all" value="{{x.t2}}"><br/>--%>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" ng-click="reset()">Reset</button>
            <button type="button" class="uk-button" ng-click="update()">Edit</button>
        </div>
    </div>
</div>
<!--<script type="text/javascript">
function checkOrigin() {
var origin = $("input#add-origin").val();
var OriginRegexp = /^[A-Za-z]+$/;
if (!OriginRegexp.test(origin)) {
$("input#add-origin").addClass("uk-form-danger");

document.getElementById('origin-span').innerHTML = '请输入a-z/A-Z之间字母组成的字符串！';
return false;
}
$("input#add-origin").addClass("uk-form-success");
return true;
};

function checkPriority() {
var priority = $("input#add-priority").val();
var PriorityRegexp = /^[A-Za-z]+$/;
if (!PriorityRegexp.test(priority)) {
$("input#add-priority").addClass("uk-form-danger");
document.getElementById('priority-span').innerHTML = '请输入a-z/A-Z之间字母组成的字符串！';
return false;
}
$("input#add-priority").addClass("uk-form-success");
return true;
};

function checkQuantity() {
var quantity = $("input#add-quantity").val();
var QuantityRegexp = /^[0-9]*$/;
if (!QuantityRegexp.test(quantity)) {
$("input#add-quantity").addClass("uk-form-danger");
document.getElementById('quantity-span').innerHTML = '请输入正确的数字！';
return false;
}
$("input#add-quantity").addClass("uk-form-success");
return true;
};

function checkT1() {
var t1 = $("input#add-t1").val();
var TimeRegexp = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/;
if (!TimeRegexp.test(t1)) {
$("input#add-t1").addClass("uk-form-danger");
document.getElementById('t1-span').innerHTML = '请输入正确的日期格式！';
return false;
}
$("input#add-t1").addClass("uk-form-success");
return true;
};

function checkT0() {
var t0 = $("input#add-t0").val();
var TimeRegexp = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/;
if (!TimeRegexp.test(t0)) {
$("input#add-t0").addClass("uk-form-danger");
document.getElementById('t0-span').innerHTML = '请输入正确的日期格式！';
return false;
}
$("input#add-t0").addClass("uk-form-success");
return true;
};

function checkT2() {
var t2 = $("input#add-t2").val();
var TimeRegexp = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/;
if (!TimeRegexp.test(t2)) {
$("input#add-t2").addClass("uk-form-danger");
document.getElementById('t2-span').innerHTML = '请输入正确的日期格式！';
return false;
}
$("input#add-t2").addClass("uk-form-success");
return true;
};

</script>-->

