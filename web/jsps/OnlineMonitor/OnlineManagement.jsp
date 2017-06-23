<%--
  Created by IntelliJ IDEA.
  User: XY
  Date: 2017/6/1
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">


    .uk-table td {
        vertical-align: top;
        width: 82px;
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
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;font-family: 微软雅黑">订单执行监控</span>
    </div>

    <div style="float:left;margin-left: 2%;">
        <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
            <input class="uk-search-field" type="search" placeholder="请输入搜索项"
                   style="width: 150px;border: 1px solid lightgray;">
        </form>
    </div>


</div>

<br/>

<!--右侧表格-->
<div class="uk-width-4-5"
     style="width: 98%;border: 1px solid lightgray;margin-left: 1%;height: 85%;background-color: white;">
    <div style="margin-top: 1%;">

        <div style="width: 100%;border: 0px;margin-top: -10px;">


            <div id="tabs-2" style="width: 100%;height: 100%;">

                <!--表格-->
                <div class="uk-overflow-container" style="height: 90%;">
                    <div class="fixtable-head">
                        <table class="uk-table uk-table-striped uk-table-hover ">
                            <thead class="uk-text-center">
                            <tr style="background-color: #e1eaf1;">
                                <td>
                                    <div style="border: 1px solid lightgray;margin-left: 42%;width: 30px;height: 15px;background-color: #cddae3;">
                                        <img src="../../images/bom_img/select.png"
                                             style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                    </div>

                                </td>
                                <td style="width: 99px;">订单编码</td>
                                <td>订单名称</td>
                                <td style="width: 95px;">订单来源</td>
                                <td>订单总量</td>
                                <td style="width: 95px;">目前完成情况</td>
                                <td>是否延期</td>
                                <td style="width: 95px;">是否提前</td>
                                <td style="width: 94px;">计划交付日期</td>
                                <td style="width: 105px;">预计交付时间</td>

                            </tr>
                            </thead>
                        </table>
                    </div>

                    <div class="fixtable-body" style="height: 78%;">
                        <table class="uk-table uk-table-striped uk-table-hover " id="order">
                            <tbody class="uk-text-center">


                            </tbody>

                        </table>

                        <!--底部页码-->
                        <%--<div style="margin-top: -25px;">
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
                        </div>--%>
                    </div>


                </div>

                <br/>

                <!--底部页码-->
                <div style="margin-top: -25px;">
                    <ul class="uk-pagination" <%--style="margin-top: 7%;"--%> data-uk-pagination="{currentPage:50}">
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

