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
    <div class="uk-width-1-4">
        <form class="uk-form">
            <input type="text" id="datepicker" data-uk-datepicker="{format:'DD.MM.YYYY'}" placeholder="请选择查找日期">
        </form>
    </div>
    <div class="uk-width-1-4">
        <div class="uk-button-group">
            <button class="uk-button">修改</button>
            <button class="uk-button">新增</button>
            <button class="uk-button">删除</button>
        </div>
    </div>
</div>

<div class="uk-overflow-container" >
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
    <button class="uk-button" id="create-order" style="border-radius:15px;" >+</button>

    <ul class="uk-pagination uk-pagination-right">
        <li><a class="uk-icon-angle-double-left"></a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a class="uk-icon-angle-double-right"></a></li>
    </ul>
    </div>
</div>

<div id="dialog-form" title="创建新表单">
    <p class="validateTips">所有的表单字段都是必填的。</p>
    <form>
        <fieldset>
            <lable for="number">编号</lable>
            <input type="text" name="number" id="number" clsss="text ui-widget-content ui-corner-all">
            <label for="name">名字</label>
            <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
        </fieldset>
    </form>
</div>

<div ng-app="groupApp" ng-controller="groupCtrl">
    <hr class="uk-article-divider">
    <button ng-click="toggle()">工组资源</button>
    <table ng-show="groupVar" class="uk-table uk-table-striped uk-table-hover uk-overflow-container">
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
<script>
    $(function () {
        $("#datepicker").datepicker();
    });
</script>
<script>
    var app = angular.module('groupApp', []);
    app.controller('groupCtrl', function ($scope) {
        $scope.groupVar = true;
        $scope.toggle = function () {
            $scope.groupVar = !$scope.groupVar;
        };
    });

</script>
<script>
    $(function() {
        var number = $( "#number" ),
            name = $( "#name" ),
            allFields = $( [] ).add( number ).add( name ),
            tips = $( ".validateTips" );

        function updateTips( t ) {
            tips
                .text( t )
                .addClass( "ui-state-highlight" );
            setTimeout(function() {
                tips.removeClass( "ui-state-highlight", 1500 );
            }, 500 );
        }

        function checkLength( o, n, min, max ) {
            if ( o.val().length > max || o.val().length < min ) {
                o.addClass( "ui-state-error" );
                updateTips( "" + n + " 的长度必须在 " +
                    min + " 和 " + max + " 之间。" );
                return false;
            } else {
                return true;
            }
        }

        function checkRegexp( o, regexp, n ) {
            if ( !( regexp.test( o.val() ) ) ) {
                o.addClass( "ui-state-error" );
                updateTips( n );
                return false;
            } else {
                return true;
            }
        }

        $( "#dialog-form" ).dialog({
            autoOpen: false,
            height: 300,
            width: 350,
            modal: true,
            buttons: {
                "+": function() {
                    var bValid = true;
                    allFields.removeClass( "ui-state-error" );

                    bValid = bValid && checkLength( number, "number", 3, 16 );
                    bValid = bValid && checkLength( name, "name", 6, 80 );

                    bValid = bValid && checkRegexp( number, /^([0-9a-zA-Z])+$/, "编号只允许： a-z 0-9" );
                    bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "用户名必须由 a-z、0-9、下划线组成，且必须以字母开头。" );
                    // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/

                    if ( bValid ) {
                        $( "#order tbody" ).append( "<tr>" +
                            "<td>" + number.val() + "</td>" +
                            "<td>" + name.val() + "</td>" + +
                                "</tr>" );
                        $( this ).dialog( "close" );
                    }
                },
                Cancel: function() {
                    $( this ).dialog( "close" );
                }
            },
            close: function() {
                allFields.val( "" ).removeClass( "ui-state-error" );
            }
        });

        $( "#create-order" )
            .button()
            .click(function() {
                $( "#dialog-form" ).dialog( "open" );
            });
    });
</script>


