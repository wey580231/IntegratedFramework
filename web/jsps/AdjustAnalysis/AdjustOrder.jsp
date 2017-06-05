<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/6/2
  Time: 09:21
  To change this template use File | Settings | File Templates.
--%>

<!--tab表格-->
<%--<div id="tabs">
    <ul>
        <li><a href="#tabs-1">交期承诺分析</a></li>
        <li><a href="#tabs-2">紧急插单分析</a></li>

    </ul>
    <div id="tabs-1">

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-table-hover " id="order1">
                <thead>
                <tr>
                    <th></th>
                    <th>处理状态</th>
                    <th>编码</th>
                    <th>名称</th>
                    <th>来源</th>
                    <th>产品名称</th>
                    <th>数量</th>
                    <th>优先级</th>
                    <th>下单时间</th>
                    <th>最早开工时间</th>
                    <th>最晚交付时间</th>
                    <th>租户名</th>
                    <th>处理操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                </tr>
                </tbody>

            </table>

            <div>
                <ul class="uk-pagination" style="margin-top:280px " data-uk-pagination="{currentPage:50}">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">上一页</a></li>
                    <li><a href="#">下一页</a></li>
                    <li><a href="#">末页</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="tabs-2">

        <div class="uk-overflow-container">
            <table class="uk-table uk-table-striped uk-table-hover " id="order">
                <thead>
                <tr>
                    <th></th>
                    <th>编码</th>
                    <th>名称</th>
                    <th>资源类型</th>
                    <th>移动速度（米/秒）</th>
                    <th>串行能力</th>
                    <th>并行能力下限</th>
                    <th>并行能力上限</th>
                    <th>能力恢复（秒）</th>
                    <th>正常班次</th>
                    <th>状态信息</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                    <td>表格项目一</td>
                </tr>
                <tr>
                    <td><input type="checkbox"></td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                    <td>表格项目二</td>
                </tr>
                </tbody>

            </table>

            <div>
                <ul class="uk-pagination" style="margin-top:280px " data-uk-pagination="{currentPage:50}">
                    <li><a href="#">首页</a></li>
                    <li><a href="#">上一页</a></li>
                    <li><a href="#">下一页</a></li>
                    <li><a href="#">末页</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">
    .uk-table td {
        vertical-align: top;
        width: 85px;
        margin-left: 11px;
    }

    .ui-tabs {
        position: unset;
        padding: .2em;
    }

</style>


<script>
    function changeColor(obj) {
        var f = obj.checked;
        var chkColor = "#c1edfa"; //选中后颜色
        var ouColor = "#f3f8fb";  //偶数行取消选中后的颜色
        var jiColor = "#FFFFFF";
        if(f)
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
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">调整订单任务</span>
    </div>

    <div style="float:left;margin-left: 2%;">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>


    <%--</div>--%>

    <!--下拉框-->
    <div style="float:left;margin-left: 2%;">
        <form class="uk-form"  style="margin-left: 2%;">
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
<div class="uk-width-4-5" style="width: 98%;border: 1px solid lightgray;margin-left: 1%;height: 85%;background-color: white;">
    <div style="margin-top: 1%;">

        <!--tab-->
        <div id="tabs" style="width: 99%;border: 0px;margin-top: -10px;height: 98%;">
            <ul>
                <li><a href="#tabs-1">交期承诺分析</a></li>
                <li><a href="#tabs-2">紧急插单分析</a></li>

            </ul>
            <div id="tabs-1" style="width: 100%;height: 86%;margin-left: -2%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head" style="height: 15%;">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td style="width: 80px;">
                                    <div style="border: 1px solid lightgray;margin-left: 20%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png" style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td>处理状态</td>
                                <td>编码</td>
                                <td>名称</td>
                                <td>来源</td>
                                <td>产品名称</td>
                                <td>数量</td>
                                <td>优先级</td>
                                <td>最早下单时间</td>
                                <td>最晚开工时间</td>
                                <td>交付时间</td>
                                <td>租户名</td>
                                <td>处理操作</td>
                                <td>来源</td>

                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 85%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">

                            <tbody class="uk-text-center">
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>未处理</td>
                                <td>1001</td>
                                <td>待定</td>
                                <td>未知</td>
                                <td>待定</td>
                                <td>500</td>
                                <td>5</td>
                                <td>2017-6-2</td>
                                <td>2017-6-2</td>
                                <td>2017-6-2</td>
                                <td>未知</td>
                                <td>交期承诺</td>
                                <td>MES</td>

                            </tr>
                            </tbody>

                        </table>

                        <!--底部页码-->
                        <div style="margin-top: -25px;">
                            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                                <li><button class="uk-button" style="background-image: url('../../images/bom_img/ye1.png');color: white;"><a href="" style="color: white;">首页</a></button></li>
                                <li><button class="uk-button my"><a href="">上一页</a></button></li>
                                <li><button class="uk-button my"><a href="">下一页</a></button></li>
                                <li><button class="uk-button my"><a href="">尾页</a></button></li>
                                <li>共88页</li>&nbsp;
                                <li>
                                    到第<input type="text" value="2" style="width: 28px;background-color: #EEF7FC;">页
                                </li>
                                <li>
                                    <button class="uk-button" style="background-image: url('../../images/bom_img/ye2.png');color: white;">确定</button>
                                </li>
                            </ul>
                        </div>

                    </div>

                </div>

            </div>
            <div id="tabs-2" style="width: 100%;height: 86%;margin-left: -2%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 96%;">
                    <div class="fixtable-head" style="height: 15%;">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div style="border: 1px solid lightgray;margin-left: 33%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png" style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td>编码</td>
                                <td>名称</td>
                                <td>资源类型</td>
                                <td style="width: 90px;">移动速度</td>
                                <td>串行能力</td>
                                <td style="width: 80px;">并行能力下限</td>
                                <td style="width: 80px;">并行能力上限</td>
                                <td>能力恢复</td>
                                <td>正常班次</td>
                                <td style="width: 110px;">状态信息</td>

                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 85%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">

                            <tbody class="uk-text-center">
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" onclick="changeColor(this)"></td>
                                <td>1001</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>100</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>未知</td>
                                <td>50</td>
                                <td>待定</td>
                                <td>未知</td>

                            </tr>
                            </tbody>

                        </table>

                        <!--底部页码-->
                        <div style="margin-top: -25px;">
                            <ul class="uk-pagination" style="margin-top: 7%;" data-uk-pagination="{currentPage:50}">
                                <li><button class="uk-button" style="background-image: url('../../images/bom_img/ye1.png');color: white;"><a href="" style="color: white;">首页</a></button></li>
                                <li><button class="uk-button my"><a href="">上一页</a></button></li>
                                <li><button class="uk-button my"><a href="">下一页</a></button></li>
                                <li><button class="uk-button my"><a href="">尾页</a></button></li>
                                <li>共88页</li>&nbsp;
                                <li>
                                    到第<input type="text" value="2" style="width: 28px;background-color: #EEF7FC;">页
                                </li>
                                <li>
                                    <button class="uk-button" style="background-image: url('../../images/bom_img/ye2.png');color: white;">确定</button>
                                </li>
                            </ul>
                        </div>
                    </div>


                </div>

            </div>

        </div>

    </div>


    <div class="uk-clearfix" style="margin-top: -3%;">
        <button class="uk-button uk-float-right " id="create-order" style="background-image: url('../../images/kuaijie.png');background-size: 100% 100%;"
                title="快捷菜单">
        </button>
        <div class=" uk-hidden uk-float-right" <%--id="button"--%>>
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

<script>
    $(function () {
        $("#Order").tabs();
    });
</script>