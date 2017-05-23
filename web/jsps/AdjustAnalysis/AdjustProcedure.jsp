<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="uk-form-icon">
                <i class="uk-icon-calendar"></i>
                <input type="text" id="datepicker"  data-uk-datepicker="{format:'DD.MM.YYYY'}"
                       placeholder="2017/5/17">
            </div>
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
    <div class="uk-overflow-container">
        <table class="uk-table uk-table-striped uk-table-hover " id="order">
            <thead>
            <tr>
                <th></th>
                <th>订单状态</th>
                <th>订单编码</th>
                <th>上报时间</th>
                <th>工序吗</th>
                <th>原始选用资源编码</th>
                <th>原始开始时间</th>
                <th>指定资源编码</th>
                <th>指定开始时间</th>
                <th>处理方法</th>
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



<div class="uk-clearfix">
    <button class="uk-button uk-float-right " id="create-order" data-uk-sticky="{top:500,boundary:'#add-a-delay'}"
            data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
            data-uk-tooltip="{pos:'top'}" title="快捷菜单">+
    </button>
    <div class=" uk-hidden uk-float-right" id="button">
        <div class="uk-panel uk-panel-box">
            <div class="data-uk-button-radio">
                <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">结果对比</button>
            </div>
        </div>
    </div>
</div>

<div class="uk-modal" id="group">
    <div class="uk-modal-dialog">
        <table class="uk-table uk-table-striped uk-table-hover uk-overflow-container">
            <thead>
            <tr>
                <th></th>
                <th>项目一</th>
                <th>项目二</th>
                <th>项目三</th>
                <th>项目四</th>
                <th>项目五</th>
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
            </tr>
            <tr>
                <td><input type="checkbox"></td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
                <td>表格项目二</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>
