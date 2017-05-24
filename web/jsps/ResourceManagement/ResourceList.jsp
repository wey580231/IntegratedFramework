<%--
  Created by IntelliJ IDEA.
  User: zhaoqi
  Date: 2017/5/16
  Time: 16:56
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
    <div class="uk-width-1-4 ">
        <form class="uk-form">
            <div class="uk-form-icon">
                <i class="uk-icon-calendar"></i>
                <input type="text" id="datepicker" data-uk-datepicker="{format:'DD.MM.YYYY'}"
                       placeholder="2017/5/17">
            </div>
        </form>
    </div>
    <div class="uk-width-1-4">
        <div class="data-uk-button-radio">
            <button class="uk-button uk-icon-edit" data-uk-modal="{target:'#edit'}">修改</button>
            <button class="uk-button uk-icon-plus" data-uk-modal="{target:'#add'}">新增</button>
            <button class="uk-button uk-icon-trash" data-uk-modal="{target:'#remove'}">删除</button>
        </div>
    </div>
</div>
<hr class="uk-article-divider">
<div class="uk-overflow-container">
    <table class="uk-table uk-table-striped uk-table-hover " id="order">
        <thead>
        <tr>
            <th></th>
            <th>编码</th>
            <th>名称</th>
            <th>资源类型</th>
            <th>工组</th>
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
            <td>1</td>
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
            <td>表格项目一</td>
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

<!--<div id="dialog-form" title="创建新表单">
<p class="validateTips">所有的表单字段都是必填的。</p>
<form>
<fieldset>
<lable for="number">编号</lable>
<br/>
<input type="text" name="number" id="number" clsss="text ui-widget-content ui-corner-all"><br/>
<label for="name">名字</label><br/>
<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all"><br/>
</fieldset>
</form>
</div>-->

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


<div class="uk-modal uk-overflow-container" id="add">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <div class="uk-modal-header">
            <h2>新建</h2>
        </div>
        <div id="dialog-form" title="创建新表单">
            <p class="validateTips">所有的表单字段都是必填的。</p>
            <form>
                <fieldset>
                    <lable for="number">编码</lable>
                    <br/>
                    <input type="text" name="number" id="number" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="name">名称</label><br/>
                    <input type="text" name="name" id="name" clsss="text ui-widget-content ui-corner-all"><br/>
                    <label for="type">资源类型</label><br/>
                    <input type="text" name="type" id="type" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="groups">工组</label><br/>
                    <input type="text" name="groups" id="groups" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="speed">移动速度（米/秒）</label><br/>
                    <input type="text" name="speed" id="speed" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="power">串行能力</label><br/>
                    <input type="text" name="power" id="power" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="lower">并行能力下限</label><br/>
                    <input type="text" name="lower" id="lower" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="upper">并行能力上限</label><br/>
                    <input type="text" name="upper" id="upper" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="recover">能力恢复（秒）</label><br/>
                    <input type="text" name="recover" id="recover" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="formal">正常班次</label><br/>
                    <input type="text" name="formal" id="formal" class="text ui-widget-content ui-corner-all"><br/>
                    <label for="state">状态信息</label><br/>
                    <input type="text" name="state" id="state" class="text ui-widget-content ui-corner-all"><br/>
                </fieldset>
            </form>
        </div>
        <div class="uk-modal-footer uk-text-right">
            <button type="button" class="uk-button" onclick="">Cancel</button>
            <button type="button" id="plus" class="uk-button" onclick="return formValidate()">Add
            </button>
        </div>
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
                <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">资源排班面板</button>
                <br>
                <button class="uk-button" style="margin: 3px" data-uk-modal="{target:'#group'}">3D车间面板</button>
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

<script src="${pageContext.request.contextPath}/jsps/ResourceManagement/Check.js"></script>
<!--<script>
$(function () {
var number = $("#number"),
name = $("#name"),
type = $("#type"),
groups = $("#groups"),
speed = $("#speed"),
power = $("#power"),
lower = $("#lower"),
upper = $("#upper"),
recover = $("#recover"),
formal = $("#formal"),
state = $("#state"),
allFields = $([]).add(number).add(name).add(type).add(groups).add(speed).add(power).add(lower).add(upper).add(recover).add(formal).add(state),
tips = $(".validateTips");

function updateTips(t) {
tips
.text(t)
.addClass("ui-state-highlight");
setTimeout(function () {
tips.removeClass("ui-state-highlight", 1500);
}, 500);
}

function checkLength(o, n, min, max) {
if (o.val().length > max || o.val().length < min) {
o.addClass("ui-state-error");
updateTips("" + n + " 的长度必须在 " +
min + " 和 " + max + " 之间。");
return false;
} else {
return true;
}
}

function checkRegexp(o, regexp, n) {
if (!( regexp.test(o.val()) )) {
o.addClass("ui-state-error");
updateTips(n);
return false;
} else {
return true;
}
}

$("#dialog-form").dialog({
autoOpen: false,
height: 300,
width: 350,
modal: true,
buttons: {
"Add": function () {
var bValid = true;
allFields.removeClass("ui-state-error");

bValid = bValid && checkLength(number, "number", 3, 16);
bValid = bValid && checkLength(name, "name", 6, 80);

bValid = bValid && checkRegexp(number, /^([0-9a-zA-Z])+$/, "编号只允许： a-z 0-9");
bValid = bValid && checkRegexp(name, /^[a-z]([0-9a-z_])+$/i, "用户名必须由 a-z、0-9、下划线组成，且必须以字母开头。");
// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/

if (bValid) {
$("#order tbody").append("<tr>" +
"<td>" + +"</td>" +
"<td>" + number.val() + "</td>" +
"<td>" + name.val() + "</td>" +
"<td>" + type.val() + "</td>" +
"<td>" + groups.val() + "</td>" +
"<td>" + speed.val() + "</td>" +
"<td>" + power.val() + "</td>" +
"<td>" + lower.val() + "</td>" +
"<td>" + upper.val() + "</td>" +
"<td>" + recover.val() + "</td>" +
"<td>" + formal.val() + "</td>" +
"<td>" + state.val() + "</td>" +
"</tr>");
$(this).dialog("close");
}
},
Cancel: function () {
$(this).dialog("close");
}
},
close: function () {
allFields.val("").removeClass("ui-state-error");
}
});

$("#add-order")
.button()
.click(function () {
$("#dialog-form").dialog("open");
});
});
</script>
-->


