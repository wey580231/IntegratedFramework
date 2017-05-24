<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">

    .nav-ml ul {
        margin-bottom: 0px;
    }

    .nav-ml ul > li {
        list-style-type: none;
        margin: 0;
        padding: 10px 0px 5px 5px;
        position: relative;
        left: 0px;
        font-size: 12px;
        cursor: pointer;
        width: 440px;
    }

    .nav-ml li::before, .nav-ml li::after {
        content: '';
        left: -40px;
        position: absolute;
        right: auto
    }

    .nav-ml li::before {
        border-left: 1px solid #213299;
        bottom: 50px;
        height: 100%;
        top: 0px;
        width: 1px
    }

    .nav-ml li::after {
        border-top: 1px solid #d34738;
        height: 20px;
        top: 25px;
        width: 20px
    }

    .nav-ml li a {
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        border: 1px solid #f5f215;
        border-radius: 5px;
        display: inline-block;
        padding: 3px 8px;
        text-decoration: none
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

    .uk-form input:not([type="radio"]):not([type="checkbox"]), .uk-form select {
        vertical-align: middle;
        background-color: #eef7fc;
        height: 23px;
    }
</style>

<div class="uk-grid" style="height: 40px;margin-top: 10px;">

    &nbsp;&nbsp;
    <span style="font-size: 18px;font-weight: 700;margin-top: 10px;">制造BOM管理</span>
    <!--搜索-->
    <div class="uk-width-1-4 " >
        <div class="uk-autocomplete" data-uk-autocomplete="{source:'/jsps/OrderManagement/_Aotu.json' }">
            <form class="uk-search" data-uk-search style="margin-top: 5px;background-color: #e8edf1;">
                <input class="uk-search-field" type="search" placeholder="搜索某个零部件"
                       style="width: 150px;border: 1px solid lightgray;">
            </form>
        </div>
    </div>
    <!--按钮-->
    <div class="uk-width-1-4" style="margin-left: 33%;">
        <div class="data-uk-button-radio" style="margin-top: 5px;">
            <button class="uk-button uk-icon-plus uk-button-primary">新增</button>
            <button class="uk-button uk-icon-edit uk-button-primary" data-uk-modal="{target:'#edit'}">修改</button>
            <button class="uk-button uk-icon-trash uk-button-primary">删除</button>
        </div>
    </div>
</div>
<hr class="uk-article-divider">
<div class="uk-grid">
    <!--BOM树-->
    <div  class="uk-width-1-5" style="width: 23%;">
        <div style="border-bottom: 1px solid lightgray;">
            <form class="uk-form uk-form-horizontal">
                <fieldset data-uk-margin>
                    <div  class="bomdiv" style="float: left;margin-left: 10px;margin-top: 5px;">
                        <span class="bomspan" style="">BOM  </span>&nbsp;&nbsp;
                    </div>

                    <div class="uk-form-row">
                        <select class="uk-grid" style="width: 130px;height: 27px;">
                            <option value="1">产品A</option>
                            <option value="2">产品B</option>
                        </select>
                    </div>

                </fieldset>
            </form>
        </div>

        <p></p>
        <div class="uk-panel uk-panel-box uk-overflow-container" style="height: 610px">
            <div class="uk-form-row">
                <div class="wrapper">
                    <div class="nav-ml">
                        <ul>
                            <li>
                                <ul class="nav-first">
                                    <li><a class="item-1"><i></i>一级</a>
                                        <ul class="nav-second fold ">
                                            <li><a class="item-2"><i></i>二级</a>
                                                <ul class="nav-three fold">
                                                    <li><a>三级</a></li>
                                                    <li><a>三级</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <ul class="nav-first">
                                    <li><a class="item-1"><i></i>一级</a>
                                        <ul class="nav-second fold ">
                                            <li><a class="item-2"><i></i>二级</a>
                                                <ul class="nav-three fold">
                                                    <li><a>三级</a></li>
                                                    <li><a>三级</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--右侧表格-->
    <div class="uk-width-4-5" style="width: 75%;border: 1px solid lightgray;margin-left: 15px;height: 714px;">
       <div style="margin-top: 15px;margin-left: -10px;">
           <!--表格上部-->
           <form class="uk-form">
               <fieldset data-uk-margin>
                   <div class="uk-form-row" style="margin: 3px">
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
           <div style="width: 98%;border: 0px;margin-top: -10px;">
               <ul class="uk-tab uk-tab-grid uk-tab-bottom" data-uk-tab>
                   <li><a href="#tabs-1">工序</a></li>
                   <li><a href="#tabs-2">DAG图</a></li>

               </ul>

               <div id="tabs-2" style="width: 100%;">

                   <div class="uk-overflow-container">
                       <table class="uk-table uk-table-striped uk-table-hover " id="order">
                           <thead>
                           <tr style="background-color: #e1eaf1;">
                               <th>
                                   <img src="../../images/bom_img/select.png" style="width: 15px;">
                               </th>
                               <th>准备时间</th>
                               <th>单件时间</th>
                               <th>冷却时间</th>
                               <th>校验时间</th>
                               <th>资源类型</th>
                               <th>资源数量</th>
                               <th>工时</th>
                               <th>首选时间</th>
                           </tr>
                           </thead>
                           <tbody>
                           <tr>
                               <td><input type="checkbox"></td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>

                           </tr>
                           <tr>
                               <td><input type="checkbox"></td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>

                           </tr>
                           <tr>
                               <td><input type="checkbox"></td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>

                           </tr>
                           <tr>
                               <td><input type="checkbox"></td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>
                               <td>1</td>

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

           </div>
       </div>


        <div class="uk-clearfix">
            <button class="uk-button uk-float-right " id="create-order"
                    data-uk-sticky="{top:500,boundary:'#add-a-delay'}"
                    data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                    data-uk-tooltip="{pos:'top'}" title="快捷菜单">+
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

</script>
