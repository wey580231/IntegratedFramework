/**
 * Created by zhaoqi on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.WorkListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/WorkList', {
            templateUrl: 'pages/ResourceManagement/WorkList.html',
            controller: 'WorkListController'
        })
    }])

    .controller('WorkListController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService, dispatchApsService) {

        layer.load(0);

        var editData = {};//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息
        var slot;

        $(function () {
            loadRightFloatMenu();

            myHttpService.get(serviceList.ListShift).then(function (response) {
                $scope.workList = response.data;

                hideLoadingPage();
            });
        });

        //确认下发APS
        function confirmDispatchAps() {
            layer.load();
            setTimeout(function () {
                layer.msg('已下发', {icon: 1});
                hideLoadingPage();
            }, 2000);
        }

        //取消下发APS
        function resetDispatchAps() {
            layer.msg('取消下发', {icon: 2});
        }

        //将选中记录下发APS
        $scope.dispatchRecord = function () {
            dispatchApsService.dispatchAps(confirmDispatchAps,resetDispatchAps);
        };

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        $("#select").change(function () {

            slot = $(this).children('option:selected').val();

        })

        //信息填写检验
        var workAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.type = $("input[name='add-type']").val();
             params.slot = $("input[name='add-slot']").val();
            /* params.slot = $(this).children('option:selected').val();*/
            params.slot = slot;
            params.extra = parseInt($("input[name='add-extra']").val());
            addData = JSON.stringify(params);
            if (!validate.checkString(params.name) || !validate.checkLength(params.name)) {
                $("#add-name").removeClass(" has-success");
                $("#add-name").addClass(" has-error");
            } else {
                $("#add-name").removeClass(" has-error");
                $("#add-name").addClass(" has-success");
            }
             if (!validate.checkNumber(params.slot) || !validate.checkLength(params.slot)) {
                 $("#add-slot").removeClass("has-success");
                 $("#add-slot").addClass("has-error");
             } else {
                 $("#add-slot").removeClass("has-error");
                 $("#add-slot").addClass(" has-success");
             }

            if (!validate.checkNumber(params.extra) || !validate.checkLength(params.extra)) {
                $("#add-extra").removeClass("has-success");
                $("#add-extra").addClass("has-error");
            } else {
                $("#add-extra").removeClass("has-error");
                $("#add-extra").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkString(params.name) &&
                validate.checkLength(params.slot) && validate.checkNumber(params.slot) && validate.checkLength(params.extra) && validate.checkNumber(params.extra)) {
                return true;
            } else {

                return false;
            }
        };

        //信息填写检验
        var workEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            /*params.slot = $("input[name='edit-slot']").val();*/
            params.slot = slot;
            params.extra = parseInt($("input[name='edit-extra']").val());
            editData = params;

            if (!validate.checkString(params.name) || !validate.checkLength(params.name)) {
                $("#edit-name").removeClass(" has-success");
                $("#edit-name").addClass(" has-error");
            } else {
                $("#edit-name").removeClass(" has-error");
                $("#edit-name").addClass(" has-success");
            }

            /* if (!validate.checkNumber(params.slot) || !validate.checkLength(params.slot)) {
             $("#edit-slot").removeClass("has-success");
             $("#edit-slot").addClass("has-error");
             } else {
             $("#edit-slot").removeClass("has-error");
             $("#edit-slot").addClass(" has-success");
             }*/

            if (!validate.checkNumber(params.extra) || !validate.checkLength(params.extra)) {
                $("#edit-extra").removeClass("has-success");
                $("#edit-extra").addClass("has-error");
            } else {
                $("#edit-extra").removeClass("has-error");
                $("#edit-extra").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkString(params.name) &&
                /* validate.checkLength(params.slot) && validate.checkNumber(params.slot) &&*/ validate.checkLength(params.extra) && validate.checkNumber(params.extra)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addWork = function () {
            if (workAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddShift, addData).then(function successCallback() {
                    //用强制刷新解决按钮不能连续响应
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
            //addData.splice(0, addData.length);
        };

        //获得表单信息
        var getInfo = function () {
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
            if (hasCheckRows()) {
                var a = document.getElementsByName("check");
                var row = 1;
                for (var i = 0; i < a.length; i++) {
                    if (a[i].checked) {
                        idVal = $("#table_value").find("tr").eq(row).find("td").eq(1).html();
                        id_params.id = idVal;
                    }
                    row++;
                }
                return true;
            } else {
                notification.sendNotification("alert", "请重新选择！");
                return false;
            }
        };

        //修改订单
        $scope.update = function () {
            if (getInfo()) {
                $("#modal-edit").modal('show');
                var idInfo = JSON.stringify(id_params);
                myHttpService.post(serviceList.GetShiftById, idInfo).then(function successCallback(response) {
                    var editList = [];//保存从数据库获取的需要修改的数据
                    editList.push(response.data);
                    edit_params = response.data;
                    $scope.editList = editList;
                }, function errorCallback(response) {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                $("#modal-edit").modal('hide');
            }
        };

        $scope.editWork = function () {
            if (workEditValidate()) {
                $("#modal-edit").modal('hide');
                //用获取到的数据代替从数据库取到的数据
                edit_params.name = editData.name;
                edit_params.type = editData.type;
                edit_params.slot = editData.slot;
                edit_params.extra = editData.extra;
                var update_data = angular.toJson(edit_params);
                myHttpService.post(serviceList.UpdateShift, update_data).then(function successCallback() {
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
        };


        //删除订单
        $scope.deleteWork = function () {
            if (getInfo()) {
                var params = {};
                params.id = idVal;
                var idInfo = JSON.stringify(params);
                console.log("删除的id信息");
                console.log(idInfo);
                myHttpService.delete(serviceList.DeleteShift, idInfo).then(function successCallback() {
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                });
            }
        };

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        }
    });