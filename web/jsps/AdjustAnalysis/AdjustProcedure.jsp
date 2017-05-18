<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/18
  Time: 10:57
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
        <caption>工序异常信息</caption>
        <thead>
        <tr>
            <th></th>
            <th>编号</th>
            <th>名称</th>
            <th>处理状态</th>
            <th>订单</th>
            <th>上报时间</th>
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

    <div>
        <button class="uk-button">异常处理</button>
        <ul class="uk-pagination" data-uk-pagination="{items:20, itemsOnPage:5, currentPage:50}">
            <li><a class="uk-icon-angle-double-left"></a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a class="uk-icon-angle-double-right"></a></li>
        </ul>
    </div>
</div>

<hr class="uk-article-divider">
<button class="uk-button" data-uk-modal="{target:'#dialog-form'}"
        style="border-radius:15px;">对比
</button>

<div id="dialog-form" class="uk-modal" title="前后对比图">
    <div class="uk-modal-dialog">
        <a class="uk-modal-close uk-close"></a>
        <form>
            <fieldset>
                <div class="uk-toolbar" style="border-bottom:0;">
                    <a class="uk-button" plain="true" onclick="load()"><i class="uk-icon-refresh"></i>加载</a>
                    <a class="uk-button" plain="true" onclick="save()"><i class="uk-icon-save"></i>保存</a>
                    <a class="uk-button" plain="true" onclick="project.print()"><i class="uk-icon-print"></i>打印</a>
                    <span class="separator"></span>
                    <a class="uk-button" plain="true" onclick="addTask()"><i class="uk-icon-plus"></i>增加</a>
                    <a class="uk-button" plain="true" onclick="updateTask()"><i class="uk-icon-edit"></i>修改</a>
                    <a class="uk-button" plain="true" onclick="removeTask()"><i class="uk-icon-trash"></i>删除</a>
                    <span class="uk-countdown-separator"></span>
                    <a class="uk-button" plain="true" onclick="upgradeTask()"><i class="uk-icon-arrow-up"></i>升级</a>
                    <a class="uk-button" plain="true" onclick="downgradeTask()"><i calss="uk-icon-arrow-down"></i>降级</a>
                    <span class="separator"></span>
                    <a class="uk-button" plain="true" onclick="onLockClick" checkOnClick="true"><i
                            class="uk-icon-lock"></i>锁定列</a>
                    <span class="separator"></span>
                    <a class="uk-button" plain="true" onclick="zoomIn()"><i class="uk-icon-plus"></i>放大</a>
                    <a class="uk-button" plain="true" onclick="zoomOut()">缩小</a>
                    <span class="separator"></span>
                    <a class="uk-button" onclick="editTaskByTaskWindow()">任务面板</a>
                    <a class="uk-button" onclick="showCalendars()"><i class="uk-icon-calendar"></i>日历面板</a>
                </div>
                <div class="container-fluid">
                    <div class="uk-grid uk-grid-divider uk-text-center" data-uk-grid-margin>
                        <div class="uk-width-medium-1-2">

                        </div>
                        <div class="uk-width-medium-1-2"></div>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>

    <!--<p class="validateTips"></p>
    <form>
        <fieldset>
            <lable for="number">编号</lable>
            <br/>
            <input type="text" name="number" id="number" clsss="text ui-widget-content ui-corner-all"><br/>
            <label for="name">名字</label><br/>
            <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all"><br/>
        </fieldset>
    </form>-->
</div>

<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>

<script src="${pageContext.request.contextPath}/jsps/AdjustAnalysis/PlusProject.js"></script>


