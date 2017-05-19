<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 21:11
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
            <input type="text" id="datepicker" data-uk-datepicker="{format:'DD.MM.YYYY'}" placeholder="2017/5/17">
            <i class="uk-icon-calendar"></i>
        </form>

    </div>
    <div class="uk-width-1-4">
        <div class="data-uk-button-radio">
            <button class="uk-button uk-icon-edit">修改</button>
            <button class="uk-button uk-icon-plus">新增</button>
            <button class="uk-button uk-icon-trash">删除</button>
        </div>
    </div>
</div>

<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-table-hover " id="order">
        <caption></caption>
        <thead>
        <tr>
            <th></th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
            <th>项目一</th>
        </tr>
        </thead>
        <tfoot>

        </tfoot>
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

        <ul class="uk-pagination" data-uk-pagination="{items:20, itemsOnPage:5, currentPage:50}">
            <li><a class="uk-icon-angle-double-left"></a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a class="uk-icon-angle-double-right"></a></li>
        </ul>
    </div>
</div>


<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>