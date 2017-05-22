<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/16
  Time: 08:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    ul, li, i {
        list-style: none;
        padding: 0;
        margin: 0;
        font-style: normal;
    }

    i {
        width: 14px;
        height: 14px;
        float: left;
    }

    .box {
        width: 100%;
        max-width: 300px;
        margin: 0 auto;
    }

    .nav-ml {
        width: 100%;
    }

    .nav-ml ul {
        margin-left: 20px;
    }

    .nav-ml i {
        width: 14px;
        height: 14px;
        background: no-repeat 0 0;
        margin-right: 10px;
    }

    .nav-ml i.unfold {
        width: 14px;
        height: 14px;
        background: no-repeat -14px 0;
    }

    .nav-ml a {
        display: block;
        font-size: 14px;
        height: 20px;
        padding: 3px 0;
        color: #666;
        overflow: hidden;
    }

    .nav-first, .nav-second, .nav-three {
        margin-left: 20px;
    }

    .nav-three li {
        background: no-repeat 0 8px;
        padding-left: 10px;
    }

    .fold {
        display: none;
    }

    .nav-three li:hover {
        background-color: #fffceb;
    }
</style>

<div class="uk-grid">
    <div class="uk-width-1-4">
        <form class="uk-search" data-uk-search>
            <input class="uk-search-field" type="search" placeholder="请输入关键字">
        </form>
    </div>
    <div class="uk-width-1-4">
        <div class="uk-button uk-form-select uk-active" data-uk-form-select>
            <span>请选择</span>
            <select>
                <option value="1">选项一</option>
                <option value="2">选项二</option>
                <option value="2">选项三</option>
            </select>
        </div>
    </div>
    <div class="uk-width-1-4">
        <form class="uk-form">
            <input type="text" id="datepicker" class="uk-icon-calendar" data-uk-datepicker="{format:'DD.MM.YYYY'}"
                   placeholder="2017/5/17">
            <i class="uk-icon-calendar"></i>
        </form>
    </div>
    <div class="uk-width-1-4">
        <div class="data-uk-button-radio">
            <button class="uk-button uk-icon-edit" data-uk-modal="{target:'#edit'}">修改</button>
            <button class="uk-button uk-icon-plus">新增</button>
            <button class="uk-button uk-icon-trash">删除</button>
        </div>
    </div>
</div>
<hr class="uk-article-divider">
<div class="uk-grid">
    <div class="uk-width-1-5">

        <div class="uk-form-row">
            <select class="uk-grid" style="margin-left: 3px;width: 80px">
                <option value="1">产品A</option>
                <option value="2">产品B</option>
            </select>
        </div>
        <p></p>
        <div class="uk-panel uk-panel-box" style="height: 610px">
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
    <div class="uk-width-4-5">
        <form class="uk-form">
            <fieldset data-uk-margin>
                <div class="uk-form-row" style="margin: 3px">
                    <label style="margin-right: 3px">产品名称</label>
                    <input type="text" placeholder="零件1">
                    <label style="margin-right: 3px">产品编码</label>
                    <input type="text" placeholder="CH1">
                    <label style="margin-right: 3px">规格型号</label>
                    <input type="text" placeholder="60*80">
                </div>
                <br>
                <div class="uk-form-row" style="margin: 3px">
                    <label style="margin-right: 3px">计量单位</label>
                    <input type="text" placeholder="套">
                    <label style="margin-right: 3px">物料单位</label>
                    <input type="text" placeholder="1">
                </div>
                <br>
            </fieldset>
        </form>

        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">工序</a></li>
                <li><a href="#tabs-2">DAG图</a></li>

            </ul>
            <div id="tabs-1">

                <div class="uk-overflow-container">
                    <table class="uk-table uk-table-striped uk-table-hover " id="order1">
                        <thead>
                        <tr>
                            <th></th>
                            <th>工序准备时间</th>
                            <th>单件加工时间</th>
                            <th>冷却时间</th>
                            <th>校验时间</th>
                            <th>资源类型</th>
                            <th>资源数量</th>
                            <th>工时</th>
                            <th>首选加工时间</th>
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
                            <th>工序准备时间</th>
                            <th>单件加工时间</th>
                            <th>冷却时间</th>
                            <th>校验时间</th>
                            <th>资源类型</th>
                            <th>资源数量</th>
                            <th>工时</th>
                            <th>首选加工时间</th>
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
