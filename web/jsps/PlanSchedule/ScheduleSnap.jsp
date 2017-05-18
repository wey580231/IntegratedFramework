<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="uk-container">
    <form>
        <fieldset>
            <div class="uk-toolbar" style="border-bottom:0;">
                <div class="uk-button"><i class="uk-icon-bolt">恢复快照</i></div>
                <div class="uk-button"><i class="uk-icon-cogs">快照分析</i></div>
            </div>
            <hr class="uk-article-divider">
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
                        <input type="text" id="datepicker" data-uk-datepicker="{format:'DD.MM.YYYY'}"
                               placeholder="2017/5/17">
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
                    <caption>工序异常信息</caption>
                    <thead>
                    <tr>
                        <th></th>
                        <th>类型</th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>延迟（订单）</th>
                        <th>延迟（天）</th>
                        <th>关键资源跨度（天）</th>
                        <th>计划最早开工时间</th>
                        <th>计划最晚开工时间</th>
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
            </div>
        </fieldset>
    </form>
</div>

<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>