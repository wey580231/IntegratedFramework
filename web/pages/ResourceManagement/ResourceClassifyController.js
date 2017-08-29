/**
 * Created by zhaoqi on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.ResourceClassifyController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceClassify', {
            templateUrl: 'pages/ResourceManagement/ResourceClassify.html',
            controller: 'ResourceClassifyController'
        })
    }])

    .controller('ResourceClassifyController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService, dispatchApsService, confirm,enter) {

        layer.load(0);

        enter.enterDown();

        var editData = {};//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        $(function () {
            loadRightFloatMenu();

            myHttpService.get(serviceList.ListTypeRecource).then(function (response) {
                $scope.typeList = response.data;

                hideLoadingPage();
            });
        });

        //确认下发APS
        function confirmDispatchAps() {
            layer.load();
            setTimeout(function () {
                notification.sendNotification("confirm", "已下发");
                hideLoadingPage();
            }, 2000);
        }

        //取消下发APS
        function resetDispatchAps() {
            notification.sendNotification("alert", "取消下发");
        }

        //将选中记录下发APS
        $scope.dispatchRecord = function () {
            dispatchApsService.dispatchAps(confirmDispatchAps, resetDispatchAps);
        };

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //信息填写检验
        var typeAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.attribute = $("input[name='add-attribute']").val();
            addData = JSON.stringify(params);
            if (!validate.checkLength(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (validate.checkNumber(params.attribute) || !validate.checkLength(params.attribute)) {
                $("#add-attribute").removeClass("has-success");
                $("#add-attribute").addClass("has-error");
            } else {
                $("#add-attribute").removeClass("has-error");
                $("#add-attribute").addClass(" has-success");
            }

            if (validate.checkLength(params.name)&& validate.checkLength(params.attribute)) {
                return true;
            } else {

                return false;
            }
        };


        //信息填写检验
        var typeEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.attribute = $("input[name='edit-attribute']").val();
            editData = params;
            if (!validate.checkLength(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }

            if (validate.checkNumber(params.attribute) || !validate.checkLength(params.attribute)) {
                $("#edit-attribute").removeClass("has-success");
                $("#edit-attribute").addClass("has-error");
            } else {
                $("#edit-attribute").removeClass("has-error");
                $("#edit-attribute").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkLength(params.attribute)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addType = function () {
            if (typeAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddTypeResource, addData).then(function successCallback() {
                    myHttpService.get(serviceList.ListTypeRecource).then(function (response) {
                        $scope.typeList = response.data;
                        notification.sendNotification("confirm", "添加成功");
                    })
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
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
                console.log("id信息");
                console.log(id_params);
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
                myHttpService.post(serviceList.GetTypeResourceById, idInfo).then(function successCallback(response) {
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

        $scope.editType = function () {
            if (confirm.confirmEdit()) {
                if (typeEditValidate()) {
                    $("#modal-edit").modal('hide');
                    //用获取到的数据代替从数据库取到的数据
                    edit_params.name = editData.name;
                    edit_params.attribute = editData.attribute;
                    var update_data = angular.toJson(edit_params);
                    console.log(update_data);
                    myHttpService.post(serviceList.UpdateTypeResource, update_data).then(function successCallback() {
                        myHttpService.get(serviceList.ListTypeRecource).then(function (response) {
                            $scope.typeList = response.data;
                            notification.sendNotification("confirm", "修改成功");
                        })
                    }, function errorCallback() {
                        notification.sendNotification("alert", "请求失败");
                    })
                } else {
                    notification.sendNotification("alert", "输入有误");
                }
            }
        };


        //删除订单
        $scope.deleteType = function () {
            if (getInfo()) {
                if (confirm.confirmDel()) {
                    var params = {};
                    params.id = idVal;
                    var idInfo = JSON.stringify(params);
                    console.log("删除的id信息");
                    console.log(idInfo);
                    myHttpService.delete(serviceList.DeleteTypeResource, idInfo).then(function successCallback() {
                        myHttpService.get(serviceList.ListTypeRecource).then(function (response) {
                            $scope.typeList = response.data;
                            notification.sendNotification("confirm", "删除成功");
                        })
                    }, function errorCallback() {
                        notification.sendNotification("alert", "请求失败");
                    });
                }
            }
        };

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        }
    });