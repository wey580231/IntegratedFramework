<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="../../mycss/mycss.css" type="text/css" rel="stylesheet">

<style type="text/css">

    .nav-ml ul {
        margin-bottom: 0px;
    }

    .nav-ml ul > li {
        list-style-type: none;
        margin: -5px;
        padding: 10px 0px 5px 5px;
        position: relative;
        left: 0px;
        font-size: 12px;
        cursor: pointer;
        /*width: 440px;*/
    }

    .nav-ml li::before, .nav-ml li::after {
        content: '';
        left: -40px;
        position: absolute;
        right: auto
    }


    .nav-ml li a {
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        border: 1px solid lightgrey;
        border-radius: 5px;
        display: inline-block;
        padding: 3px 8px;
        text-decoration: none;
        background-color: #f9fcfc;
    }

    .fold {
        display: none;
    }

    .nav-ml li.nav-first_li > a {
        cursor: pointer
    }

    .nav-ml > ul > li::before, .nav-ml > ul > li::after {
        border: 1px;
    }

    .nav-ml li:last-child::before {
        height: 30px;
    }

    .bomspan{
        font-size: 16px;
        /*background: url("../../images/bom_img/bomqie.png");*/

    }
    .bomdiv{
        background: url("../../images/bom_img/bomqie.png") no-repeat;
        background-size: 100% 100%;
    }

    /*
        所有input的背景色
    */
    /*.uk-form input:not([type="radio"]):not([type="checkbox"]), .uk-form select {
        vertical-align: middle;
        background-color: #eef7fc;
        height: 23px;
    }*/
    /*
        表格奇数行颜色
    */
    /*.uk-table-striped tbody tr:nth-of-type(2n+1) {
        background: #f3f8fb;
    }*/

    /*
        BOM树字体颜色
    */
    /*.uk-link, a {
        color: black;
        cursor: pointer;
    }*/
    /*
        表格行高
    */
    /*tr{
        height: 45px;
    }*/

    /*
        tab样式
    */
    /*.uk-tab-grid > li:first-child >a:hover {
        background: url("../../images/bom_img/gongxu.png") no-repeat 30px;
        background-position: 15px;
    }

    .uk-tab > li:nth-child(n+2)> a:hover {
          background: url("../../images/bom_img/gongxu.png") no-repeat 30px;
          background-position: 15px;
      }
    .uk-tab > li:nth-child(n+2) > a:focus{
        background: url("../../images/bom_img/gongxu.png") no-repeat 30px;
        background-position: 15px;

    }
    .uk-tab-grid > li:first-child >a:focus {
        background: url("../../images/bom_img/gongxu.png") no-repeat 30px;
        background-position: 15px;

    }*/

    /*
        BOM树左侧的线条（竖线）
    */
    .nav-ml li::before {
        border-left: 2px solid white;
        bottom: 50px;
        height: 100%;
        top: 0px;
        width: 1px;
        margin-top: -2px;
    }
    /*
        BOM树左侧的线条（横线）
    */
    .nav-ml li::after {
        border-top: 2px solid white;
        height: 20px;
        top: 25px;
        width: 20px;
    }
    .bg{
        background-color: #c1edfa;
    }
    /*
           页码li样式
    */
    /*.uk-pagination > li {
        display: inline-block;
        font-size: 1rem;
        vertical-align: middle;
    }*/

    /*
           页码样式
    */
    /*.my {
        background-color: #009dd8;
        color: #fff;
        background-image:url("../../images/bom_img/ye3.png");
        border-color: rgba(0,0,0,.2);
        border-bottom-color: rgba(0,0,0,.4);
        text-shadow: 0 -1px 0 rgba(0,0,0,.2);
    }*/
    /*.my:hover{
        background-color: #50FCF9;;
    }*/

    /*
        表格固定
    */

    /*.fixtable-head table,.table-body table{width:100%;text-align:left;}
    .fixtable-body{width:100%; height:250px;overflow-y:scroll;text-align:left;}

    .uk-table td {
        vertical-align: top;
        width: 40px;
    }*/

    /*#main{
        width:1230px;
        height:500px;
        MARGIN-RIGHT: auto;
        MARGIN-LEFT: auto;
    }*/

    /*
        BOM树下部panel
    */
    .uk-panel-box {
        padding: 15px;
        background: #fafafa;
        color: #444;
        border: 1px solid #ddd;
        border-radius: 0px;
    }

    .uk-table td {
        vertical-align: top;
        width: 25px;
        margin-left: 11px;
    }

    /*
        form表单的位置固定
    */
    .uk-form{
        display: inherit;
    }
</style>



<%--<script type="text/javascript">
    //除了表头（第一行）以外所有的行添加click事件.
    $("tr").first().nextAll().click(function () {
        //如果没有某个样式则加上，否则去除
        $(this).children().toggleClass("bg");
        if ($(this).children().hasClass("bg")){//如果有某个样式则表明，这一行已经被选中
            $(this).children().first().children().attr("checked", true);
        } else {                                  //如果没有被选中
            $(this).children().first().children().attr("checked", false);
        }
    });
</script>--%>

<script>
    function changeColor(obj) {
        var f = obj.checked;
        var chkColor = "#c1edfa"; //选中后颜色
        //var back = obj.parentElement.parentElement.style.backgroundColor;  //偶数行取消选中后的颜色
        var jiColor = "#FFFFFF";
        if(f)
            obj.parentElement.parentElement.style.backgroundColor = chkColor;
        else
            obj.parentElement.parentElement.style.backgroundColor = jiColor;
    }
</script>

<%--<div id="main">--%>
    <div class="<%--uk-grid--%>" style="height: 8%;margin-top: 10px;background-color: white;margin-left: 0px;width: 100%;">

        <%--<img src="../../images/bom_img/shu.png" style="margin-left: -35px;width: 40px;">--%>
            <%--<div class="uk-width-1-4 ">--%>
                <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">


                &nbsp;
                <span style="font-size: 18px;font-weight: 700;margin-top: 10px;/*margin-left: -24px;*/font-family: 微软雅黑">制造BOM管理</span>
            <%--</div>--%>
            <%--<img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">


        &nbsp;
        <span style="font-size: 18px;font-weight: 700;margin-top: 10px;/*margin-left: -24px;*/font-family: 微软雅黑">制造BOM管理</span>--%>
        <!--搜索-->
        <%--<div class="uk-width-1-4 " >--%>
            <%--<div class="uk-autocomplete" data-uk-autocomplete="{source:'/jsps/OrderManagement/_Aotu.json' }">--%>
                <form class="uk-search" data-uk-search style="margin-left: 2%;margin-top: 5px;background-color: #e8edf1;">
                    <input class="uk-search-field" type="search" placeholder="搜索某个零部件"
                           style="width: 150px;border: 1px solid lightgray;">
                </form>
<%--            </div>--%>
        <%--</div>--%>
        <!--按钮-->
        <%--<div class="uk-width-1-4" style="margin-left: 36%;">--%>
            <div class="data-uk-button-radio" style="margin-top: 5px;float: right;margin-right: 1%;">
                <button class="uk-button uk-icon-plus uk-button-primary">新增</button>
                <button class="uk-button uk-icon-edit uk-button-primary" data-uk-modal="{target:'#edit'}">修改</button>
                <button class="uk-button uk-icon-trash uk-button-primary">删除</button>
            </div>
        <%--</div>--%>
    </div>
    <%--<hr class="uk-article-divider">--%>
    <br/>

    <div class="uk-grid" style="height: 88%;">
        <!--BOM树-->
        <div  class="uk-width-1-5" style="width: 23%;height: 100%;">
            <!--BOM树上部-->
            <div style="border-bottom: 1px solid lightgray;background-color: white;height: 10%;">
                <form class="uk-form uk-form-horizontal">
                    <fieldset data-uk-margin>
                        <div  class="bomdiv" style="float: left;margin-left: 10px;margin-top: 10px;">
                            <span class="bomspan" style="margin-top: 8px;">BOM  </span>&nbsp;&nbsp;
                        </div>

                        <div class="uk-form-row" style="margin-top: 6px;">
                            <select class="uk-grid" style="width: 130px;height: 27px;">
                                <option value="1">产品A</option>
                                <option value="2">产品B</option>
                            </select>
                        </div>

                    </fieldset>
                </form>
            </div>

            <%--<p></p>--%>
            <!--BOM树下部-->
            <div class="uk-panel uk-panel-box uk-overflow-container" style="height: 82%;background-color: #e2ebf2;">
                <div class="uk-form-row">
                    <div class="wrapper">
                        <div class="nav-ml">
                            <%--<ul>
                                <img src="../../images/bom_img/2.png" style="margin-left: -20px;">
                                <li>--%>
                            <ul class="nav-first">
                                <li>
                                    <img src="../../images/bom_img/1.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                    <a class="item-1"><i></i>零件1</a>
                                    <ul class="nav-second fold ">
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件A</a>
                                            <ul class="nav-three fold">
                                                <li>
                                                    <img src="../../images/bom_img/3.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                                    <a>自产零件A1</a></li>


                                                <li>
                                                    <img src="../../images/bom_img/3.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                                    <a>自产零件A2</a></li>
                                                <li>
                                                    <img src="../../images/bom_img/3.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                                    <a>自产零件A3</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件B</a></li>
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件C</a></li>
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件D</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <%-- </li>
                             <img src="../../images/bom_img/2.png" style="margin-left: -20px;">
                             <li>--%>
                            <ul class="nav-first">
                                <li>
                                    <img src="../../images/bom_img/1.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                    <a class="item-1"><i></i>零件2</a>
                                    <ul class="nav-second fold ">
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>子零件2-1</a>
                                            <ul class="nav-three fold">
                                                <li>
                                                    <img src="../../images/bom_img/4.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                                    <a>外购部件AA</a></li>
                                                <li>
                                                    <img src="../../images/bom_img/4.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                                    <a>外购部件BB</a></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件CD</a></li>
                                        <li>
                                            <img src="../../images/bom_img/2.png" style="margin-left: -20px;">&nbsp;&nbsp;
                                            <a class="item-2"><i></i>自产零件DD</a></li>
                                    </ul>
                                </li>
                            </ul>
                            </li>
                            <%--</ul>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--右侧表格-->
        <div class="uk-width-4-5" style="width: 75.5%;border: 1px solid lightgray;margin-left: 15px;height:  98%;background-color: white;">
            <div style="margin-top: 15px;margin-left: -25px;height: 95%;">
                <!--表格上部-->
                <form class="uk-form" style="height: 22%;">
                    <fieldset data-uk-margin>
                        <div class="uk-form-row" style="margin: 3px;height: 10px;">
                            <label style="margin-right: 3px;">产品名称</label>
                            <input type="text" placeholder="零件1">&nbsp;
                            <label style="margin-right: 3px">产品编码</label>
                            <input type="text" placeholder="CH1">&nbsp;
                            <label style="margin-right: 3px">规格型号</label>
                            <input type="text" placeholder="60*80">
                        </div>
                        <br>
                        <div class="uk-form-row" style="margin: 3px">
                            <label style="margin-right: 3px">计量单位</label>
                            <input type="text" placeholder="套">&nbsp;
                            <label style="margin-right: 3px">物料单位</label>
                            <input type="text" placeholder="1">
                        </div>
                        <br>
                    </fieldset>
                </form>
                <!--tab-->
                <div style="width: 99%;border: 0px;margin-top: -15px;">
                    <img src="../../images/bom_img/shu.png" style="width: 5px;float: left;height: 35px;">
                    <ul class="uk-tab uk-tab-grid uk-tab-bottom" data-uk-tab style="height: 10%;">
                        <li><a href="#tabs-1">工序</a></li>
                        <li><a href="#tabs-2">DAG图</a></li>

                    </ul>

                    <div id="tabs-2" style="width: 100%;">

                        <!--表格-->
                        <div class="uk-overflow-container" style="height: 72%;">
                            <div class="fixtable-head">
                                <table class="uk-table uk-table-striped uk-table-hover ">
                                    <thead class="uk-text-left">
                                    <tr style="background-color: #e1eaf1;">
                                        <td>
                                            <div style="border: 1px solid lightgray;margin-left: 25%;width: 30px;height: 15px;background-color: #cddae3;">
                                                <img src="../../images/bom_img/select.png" style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                            </div>

                                        </td>
                                        <td>准备时间</td>
                                        <td>单件时间</td>
                                        <td>冷却时间</td>
                                        <td>校验时间</td>
                                        <td>资源类型</td>
                                        <td>资源数量</td>
                                        <td>工时</td>
                                        <td>首选时间</td>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="fixtable-body" style="height: 69%;">
                                <table class="uk-table uk-table-striped uk-table-hover " id="order">
                                    <%-- <thead>
                                     <tr style="background-color: #e1eaf1;">
                                         <td>
                                             <div style="border: 1px solid lightgray;width: 30px;height: 15px;background-color: #cddae3;">
                                                 <img src="../../images/bom_img/select.png" style="width: 15px;width: 12px;margin-left: 16px;margin-top: 3px;">
                                             </div>

                                         </td>
                                         <td>准备时间</td>
                                         <td>单件时间</td>
                                         <td>冷却时间</td>
                                         <td>校验时间</td>
                                         <td>资源类型</td>
                                         <td>资源数量</td>
                                         <td>工时</td>
                                         <td>首选时间</td>
                                     </tr>
                                     </thead>--%>
                                    <tbody class="uk-text-center">
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" onclick="changeColor(this)"></td>
                                        <td>1</td>
                                        <td>12</td>
                                        <td>4</td>
                                        <td>4</td>
                                        <td>待定</td>
                                        <td>80</td>
                                        <td>78</td>
                                        <td>待定</td>

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
                                        <%--<li><a href="#">上一页</a></li>
                                        <li><a href="#">下一页</a></li>
                                        <li><a href="#">尾页</a></li>--%>
                                        <li>共88</li>&nbsp;
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
                <button class="uk-button uk-float-right " id="create-order"
                <%--data-uk-sticky="{top:500,boundary:'#add-a-delay'}"
                data-uk-toggle="{target:'#button'}"
                data-uk-tooltip="{pos:'top'}"--%>
                        style="border-radius:50%;background: rgba(0,0,0,0.3);color: #fff;top:220px;width: 45px;height: 45px;margin-top: -8px;"
                        title="快捷菜单">+
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
    </div>
<%--</div>--%>


<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>

<script>
    $(".item-1").click(function () {
        $(this).parent().find(".nav-second").slideToggle(500);
        $(this).children("i").toggleClass("unfold");
    });
    $(".item-2").click(function () {
        $(this).parent().find(".nav-three").slideToggle(500);
        $(this).children("i").toggleClass("unfold");
    });
    /*$(".item-3").click(function () {
        $(this).parent().find(".nav-four").slideToggle(500);
        $(this).children("i").toggleClass("unfold");
    });*/

</script>
