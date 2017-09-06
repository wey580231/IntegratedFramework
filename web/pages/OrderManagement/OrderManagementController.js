/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: 'pages/OrderManagement/OrderManagement.html',
            controller: 'OrderManagementController'
        })
    }])

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService, dispatchApsService, confirm,enter) {
        layer.load(0);

        enter.enterDown();

        $(function () {
            loadRightFloatMenu();

            myHttpService.get(serviceList.ListOrder).then(function (response) {
                $scope.orderList = response.data;

                hideLoadingPage();
            });
        });

        var editData = [];//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息


        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

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
            notification.sendNotification("confirm", "取消下发");
        }

        //将选中记录下发APS
        $scope.dispatchRecord = function () {
            dispatchApsService.dispatchAps(confirmDispatchAps, resetDispatchAps);
        };

        //将字符串转换成date
        function convertDateFromString(dateString) {
            if (dateString) {
                var arr1 = dateString.split(" ");
                var sdate = arr1[0].split('-');
                var stime = arr1[1].split(':');
                var date = new Date(sdate[0], sdate[1] - 1, sdate[2], stime[0], stime[1], stime[2]);
                return date;
            }
        }

        //信息填写检验
        var orderAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.quantity = parseInt($("input[name='add-quantity']").val());

            //Yang 修复将日期字符串转换成日期对象
            var t0 = convertDateFromString($("input[id='modal-add-t0-datepicker']").val());
            var t2 = convertDateFromString($("input[id='modal-add-t2-datepicker']").val());
            params.t0 = t0.getTime();
            params.t2 = t2.getTime();

            var orderType = $("select#orderSelect").val();
            params.orderType = orderType;

            addData = JSON.stringify(params);

            if (!validate.checkLength(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.quantity) || !validate.checkLength(params.quantity)) {
                $("#add-quantity").removeClass("has-success");
                $("#add-quantity").addClass("has-error");
            } else {
                $("#add-quantity").removeClass("has-error");
                $("#add-quantity").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#add-t0").removeClass("has-success");
                $("#add-t0").addClass("has-error");
            } else {
                $("#add-t0").removeClass("has-error");
                $("#add-t0").addClass(" has-success");
            }
            if (!validate.checkLength(t2)) {
                $("#add-t2").removeClass("has-success");
                $("#add-t2").addClass("has-error");
            } else {
                $("#add-t2").removeClass("has-error");
                $("#add-t2").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkLength(params.quantity) && validate.checkNumber(params.quantity) &&
                validate.checkLength(params.t0) && validate.checkLength(params.t2) && validate.checkLength(params.t1)) {
                return true;
            } else {

                return false;
            }
        };

        //信息填写检验
        var orderEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.quantity = parseInt($("input[name='edit-quantity']").val());
            var t0 = $("input[id='modal-edit-t0-datepicker']").val();
            var t2 = $("input[id='modal-edit-t2-datepicker']").val();

            params.t0 = (new Date($("input[id='modal-edit-t0-datepicker']").val())).getTime();
            params.t2 = (new Date($("input[id='modal-edit-t2-datepicker']").val())).getTime();
            params.state = parseInt($("input[name='edit-state']").val());
            var state = $("input[name='edit-state']").val();
            editData = params;

            if (!validate.checkLength(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.quantity) || !validate.checkLength(params.quantity)) {
                $("#edit-quantity").removeClass("has-success");
                $("#edit-quantity").addClass("has-error");
            } else {
                $("#edit-quantity").removeClass("has-error");
                $("#edit-quantity").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#edit-t0").removeClass("has-success");
                $("#edit-t0").addClass("has-error");
            } else {
                $("#edit-t0").removeClass("has-error");
                $("#edit-t0").addClass(" has-success");
            }
            if (!validate.checkLength(t2)) {
                $("#edit-t2").removeClass("has-success");
                $("#edit-t2").addClass("has-error");
            } else {
                $("#edit-t2").removeClass("has-error");
                $("#edit-t2").addClass(" has-success");
            }
            if (validate.checkLength(params.name) && validate.checkLength(params.quantity) && validate.checkNumber(params.quantity) &&
                validate.checkLength(params.t0) && validate.checkLength(params.t2) && validate.checkLength(params.t1)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addOrder = function () {
            if (orderAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddOrder, addData).then(function successCallback() {
                    myHttpService.get(serviceList.ListOrder).then(function (response) {
                        $scope.orderList = response.data;
                    })
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
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
                myHttpService.post(serviceList.GetOrderById, idInfo).then(function successCallback(response) {
                    var editList = [];//保存从数据库获取的需要修改的数据
                    editList.push(response.data);
                    edit_params = response.data;
                    $scope.editList = editList;
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                $("#modal-edit").modal('hide');
            }
        };

        //编辑订单
        $scope.editOrder = function () {
            if (confirm.confirmEdit()) {
                if (orderEditValidate()) {
                    $("#modal-edit").modal('hide');
                    //用获取到的数据代替从数据库取到的数据
                    edit_params.name = editData.name;
                    edit_params.quantity = editData.quantity;
                    edit_params.t0 = editData.t0;
                    edit_params.t2 = editData.t2;
                    edit_params.state = editData.state;
                    var update_data = angular.toJson(edit_params);
                    myHttpService.post(serviceList.UpdateOrder, update_data).then(function successCallback() {
                        myHttpService.get(serviceList.ListOrder).then(function (response) {
                            $scope.orderList = response.data;
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
        $scope.deleteOrder = function () {
            if (getInfo()) {
                if (confirm.confirmDel()) {
                    var params = {};
                    params.id = idVal;
                    var idInfo = JSON.stringify(params);
                    myHttpService.delete(serviceList.DeleteOrder, idInfo).then(function successCallback() {
                        myHttpService.get(serviceList.ListOrder).then(function (response) {
                            $scope.orderList = response.data;
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
        };

    });