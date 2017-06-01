<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>

<!--新计划排程-->
<div class="uk-width-1-4">
    <div class="uk-clearfix">
        <button class="uk-button uk-float-right " id="create-order"
                data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                data-uk-tooltip="{pos:'top'}" title="快捷菜单">新计划排程
        </button>
        <div class=" uk-hidden uk-float-right" id="button">
            <div class="uk-panel uk-panel-box">
                <div class="data-uk-button-radio">
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">周报表</button>
                    <br>
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">资源排班</button>
                    <br>
                    <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">排程详细信息</button>
                </div>
            </div>
        </div>
    </div>
</div>

<tr>
    <th>序号</th>
    <th>排产名称</th>
    <th>完成进度</th>
    <th>排产日期</th>
    <th>开始计算时间</th>
    <th>计算完成时间</th>
    <th>排程状态</th>
</tr>

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
    <!--新计划排程-->
    <div class="uk-width-1-4">
        <div class="uk-clearfix">
            <button class="uk-button uk-float-right " id="create-order"
                    data-uk-toggle="{target:'#button'}" style="border-radius:15px; "
                    data-uk-tooltip="{pos:'top'}" title="快捷菜单">新计划排程
            </button>
            <div class=" uk-hidden uk-float-right" id="button">
                <div class="uk-panel uk-panel-box">
                    <div class="data-uk-button-radio">
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">周报表</button>
                        <br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">资源排班</button>
                        <br>
                        <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">排程详细信息</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<hr class="uk-article-divider">
<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-table-hover " id="order">
        <thead>
        <tr>
            <th>序号</th>
            <th>排产名称</th>
            <th>完成进度</th>
            <th>排产日期</th>
            <th>开始计算时间</th>
            <th>计算完成时间</th>
            <th>排程状态</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>KQD生产</td>
            <td class="uk-progress uk-progress-striped uk-active" style="width: 30px;">40%</td>
            <td>2017-05-17 08：00</td>
            <td>2017-05-17 08：00</td>
            <td>2017-05-17 08：00</td>
            <td>计算完成</td>
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

