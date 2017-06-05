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

    <%--<img src="../../images/bom_img/shu.png" style="margin-left: -35px;width: 40px;">--%>
    <%--<div class="uk-width-1-4 ">--%>
    <div style="float:left;">
        <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
    </div>

    <div style="float:left;margin-top: 5px;">
        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">订单管理</span>
    </div>

    <%--</div>--%>
    <%--<img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">


&nbsp;
<span style="font-size: 18px;font-weight: 700;margin-top: 10px;/*margin-left: -24px;*/font-family: 微软雅黑">制造BOM管理</span>--%>
    <!--搜索-->
    <%--<div class="uk-width-1-4 " >--%>
    <%--<div class="uk-autocomplete" data-uk-autocomplete="{source:'/jsps/OrderManagement/_Aotu.json' }">--%>
    <div style="float:left;margin-left: 2%;">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>

    <%--            </div>--%>
    <%--</div>--%>

    <!--日历-->

    <%--<div class="uk-width-1-4" style="float: left;margin-top: 10px;margin-left: -70px;">--%>
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
    <%--<div class="uk-width-1-4" style="margin-left: 36%;">--%>
    <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
        <button class="uk-button uk-icon-plus uk-button-primary" ng-click="AddOrder()">新增</button>
        <button class="uk-button uk-icon-edit uk-button-primary" data-uk-modal="{target:'#edit'}">修改</button>
        <button class="uk-button uk-icon-trash uk-button-primary" ng-click="DeleteOrder()">删除</button>
    </div>
    <%--</div>--%>
</div>
<%--<hr class="uk-article-divider">--%>
<br/>
<%--<div class="uk-grid" style="margin-top: -25px;">--%>

<!--右侧表格-->
<div class="uk-width-4-5"
     style="width: 98%;border: 1px solid lightgray;margin-left: 1%;height: 85%;background-color: white;">
    <div style="margin-top: 1%;">

        <!--tab-->
        <div style="width: 100%;border: 0px;margin-top: -10px;">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div style="border: 1px solid lightgray;margin-left: 25%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td>编码</td>
                                <td>名称</td>
                                <td>来源</td>
                                <td>产品名</td>
                                <td>数量</td>
                                <td>优先级</td>
                                <td>下单时间</td>
                                <td>最早开工</td>
                                <td>最晚开工</td>
                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 83%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">
                            <%-- <thead type="hidden">
                             &lt;%&ndash;<tbody>&ndash;%&gt;
                             <tr style="background-color: #e1eaf1;">
                                 <td>
                                     <div style="border: 1px solid lightgray;width: 30px;height: 15px;background-color: #cddae3;">
                                         <img src="../../images/bom_img/select.png" style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                     </div>

                                 </td>
                                 <td>编码</td>
                                 <td>名称</td>
                                 <td>来源</td>
                                 <td>产品名</td>
                                 <td>数量</td>
                                 <td>优先级</td>
                                 <td>下单时间</td>
                                 <td>最早开工时间</td>
                                 <td>最晚开工时间</td>
                             </tr>
                             </thead>--%>
                            <tbody class="uk-text-center">
                            <tr id="first" ng-repeat="x in names track by $index">
                                <td><input type="checkbox" ng-checked="isSelected(server.id)"
                                           ng-click="updateSelection($event,x.id)" onclick="changeColor(this)"></td>
                                <td>{{x.id}}</td>
                                <td>{{x.name}}</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>...</td>
                                <td>待定</td>
                                <td>4</td>
                                <td>5</td>
                                <td>待定</td>
                                <td>待定</td>
                                <td>待定</td>

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
        <div class=" uk-hidden uk-float-right" id="button">
            <div class="uk-panel uk-panel-box">
                <div class="data-uk-button-radio">
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">DAG面板</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--</div>--%>

<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>