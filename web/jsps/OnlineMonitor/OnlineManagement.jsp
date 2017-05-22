<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/17
  Time: 9:16
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
            <input class="uk-search-field" data-uk-datepicker="{format:'DD.MM.YYYY'}" placeholder="请选择查找日期">
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
<div>
    <table class="uk-table uk-table-striped uk-table-hover">
        <thead>
        <tr>
            <th></th>
            <th>项目一</th>
            <th>项目二</th>
            <th>项目三</th>
            <th>项目四</th>
            <th>项目五</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox">主计划滚动报表</td>
            <td>表格项目一</td>
            <td>表格项目一</td>
            <td>表格项目一</td>
            <td>表格项目一</td>
            <td>表格项目一</td>
            <td>详情
                <button class="uk-button" data-uk-modal="{target:'#Order'}">...</button>
            </td>
        </tr>
        <tr>
            <td><input type="checkbox">车间日报表</td>
            <td>表格项目二</td>
            <td>表格项目二</td>
            <td>表格项目二</td>
            <td>表格项目二</td>
            <td>表格项目二</td>
            <td>详情
                <button class="uk-button" data-uk-modal="{target:'#Order'}">...</button>
            </td>
        </tr>
        <tr>
            <td><input type="checkbox">工序日报表</td>
            <td>表格项目三</td>
            <td>表格项目三</td>
            <td>表格项目三</td>
            <td>表格项目三</td>
            <td>表格项目三</td>
            <td>详情
                <button class="uk-button" data-uk-modal="{target:'#Order'}">...</button>
            </td>
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

<div id="Order" class="uk-modal">
    <div class="uk-modal-dialog">
        <a class="uk-modal-close uk-close"></a>
        <ul>
            <li><a href="#Order-1">主计划滚动报表</a></li>
            <li><a href="#Order-2">车间日报表</a></li>
            <li><a href="#Order-3">工序日报表</a></li>
        </ul>
        <div id="Order-1">
            <table>
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
                </tbody>
            </table>
        </div>
        <div id="Order-2">
            <table>
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
                </tbody>
            </table>
        </div>
        <div id="Order-3">
            <table>
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
                </tbody>
            </table>
        </div>
    </div>
</div>


<div class="uk-modal" id="edit">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div class="uk-modal-header">
            <h2>修改</h2>
        </div>
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
            </tbody>
        </table>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button">Cancel</button>
            <button type="button" class="uk-button" onclick="UIkit.modal.confirm('确定修改吗？',function(){UIkit.modal.alert('修改成功！');

            });">Edit
            </button>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>

<script>
    $(function () {
        $("#Order").tabs();
    });
</script>