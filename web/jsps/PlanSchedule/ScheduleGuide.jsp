<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">计划排成向导</a></li>
        <li><a href="#tabs-2">订单管理</a></li>
        <li><a href="#tabs-3">参数配置管理</a></li>

    </ul>

    <div id="tabs-3">

    </div>
    <div id="tabs-1">
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
            <table class="uk-table uk-table-striped uk-table-hover " id="order2">
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

                <ul class="uk-pagination" data-uk-pagination="{items:20, itemsOnPage:5, currentPage:50}">
                    <li><a class="uk-icon-angle-double-left"></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a class="uk-icon-angle-double-right"></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="tabs-2">
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
                    <input type="text" id="datepicker1" data-uk-datepicker="{format:'DD.MM.YYYY'}" placeholder="2017/5/17">
                </form>
            </div>
            <div class="uk-width-1-4">
                <div class="data-uk-button-radio">
                    <button class="uk-button">修改</button>
                    <button class="uk-button">新增</button>
                    <button class="uk-button">删除</button>
                </div>
            </div>
        </div>

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

                <ul class="uk-pagination" data-uk-pagination="{items:20, itemsOnPage:5, currentPage:50}">
                    <li><a class="uk-icon-angle-double-left"></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a class="uk-icon-angle-double-right"></a></li>
                </ul>
            </div>
        </div>
    </div>

</div>


<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>
<script>
    $(function() {
        $( "#tabs" ).tabs();
    });
</script>
