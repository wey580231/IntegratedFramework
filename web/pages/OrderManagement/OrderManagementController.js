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

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList, validate, notification) {
        var data = [];//保存新增和修改的信息
        var checkedInfo = [];//保存选中的记录信息
        myHttpService.get(serviceList.ListOrder).then(function (response) {
            console.log(response.data);
            $scope.orderList = response.data;
            console.log("@@@@@@@");
        });

        //Date picker
        $('#add-t1-datepicker').datepicker({
            autoclose: true,
            format: 'yyyy/mm/dd'
        });
        $('#add-t2-datepicker').datepicker({
            autoclose: true,
            format: 'yyyy/mm/dd'
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            if ($last) {
                //Enable iCheck plugin for checkboxes
                //iCheck for checkbox and radio inputs
                $('.mailbox-messages input[type="checkbox"]').iCheck({
                    checkboxClass: 'icheckbox_flat-blue',
                    radioClass: 'iradio_flat-blue'
                });

                //Enable check and uncheck all functionality
                $(".checkbox-toggle").click(function () {
                    var clicks = $(this).data('clicks');
                    if (clicks) {
                        //Uncheck all checkboxes
                        $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
                        $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
                    } else {
                        //Check all checkboxes
                        $(".mailbox-messages input[type='checkbox']").iCheck("check");
                        $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
                    }
                    $(this).data("clicks", !clicks);
                });
            }
        };

        //信息填写检验
        var orderValidate = function () {
            var params = {};
            params.name = $("input[name='name']").val();
            params.type = $("input[name='type']").val();
            params.quantity = $("input[name='quantity']").val();
            params.origin = $("input[name='origin']").val();
            var t0 = $("input[name='t0']").val();
            var t2 = $("input[name='t2']").val();
            params.t0 = Date.parse($("input[name='t0']").val());
            params.t2 = Date.parse($("input[name='t2']").val());
            data = JSON.stringify(params);
            if (!validate.checkString(params.origin) || !validate.checkLength(params.origin)) {
                $("#add-origin").removeClass(" has-success");
                $("#add-origin").addClass(" has-error");
                $("#edit-origin").removeClass(" has-success");
                $("#edit-origin").addClass(" has-error");
            } else {
                $("#add-origin").removeClass(" has-error");
                $("#add-origin").addClass(" has-success");
                $("#edit-origin").removeClass(" has-success");
                $("#edit-origin").addClass(" has-error");
            }

            if (!validate.checkString(params.type) || !validate.checkLength(params.type)) {
                $("#add-type").removeClass("has-success");
                $("#add-type").addClass("has-error");
                $("#edit-type").removeClass("has-success");
                $("#edit-type").addClass("has-error");
            } else {
                $("#add-priority").removeClass("has-error");
                $("#add-priority").addClass(" has-success");
                $("#edit-priority").removeClass("has-error");
                $("#edit-priority").addClass(" has-success");
            }

            if (!validate.checkLength(params.name) || !validate.checkString(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.quantity) || !validate.checkLength(params.quantity)) {
                $("#add-quantity").removeClass("has-success");
                $("#add-quantity").addClass("has-error");
                $("#edit-quantity").removeClass("has-success");
                $("#edit-quantity").addClass("has-error");
            } else {
                $("#add-quantity").removeClass("has-error");
                $("#add-quantity").addClass(" has-success");
                $("#edit-quantity").removeClass("has-error");
                $("#edit-quantity").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#add-t0").removeClass("has-success");
                $("#add-t0").addClass("has-error");
                $("#edit-t0").removeClass("has-success");
                $("#edit-t0").addClass("has-error");
            } else {
                $("#add-t0").removeClass("has-error");
                $("#add-t0").addClass(" has-success");
                $("#edit-t0").removeClass("has-error");
                $("#edit-t0").addClass(" has-success");
            }
            if (!validate.checkLength(t2)) {
                $("#add-t2").removeClass("has-success");
                $("#add-t2").addClass("has-error");
                $("#edit-t2").removeClass("has-success");
                $("#edit-t2").addClass("has-error");
            } else {
                $("#add-t2").removeClass("has-error");
                $("#add-t2").addClass(" has-success");
                $("#edit-t2").removeClass("has-error");
                $("#edit-t2").addClass(" has-success");
            }

            if (validate.checkString(params.origin) && validate.checkLength(params.origin) && validate.checkString(params.type) && validate.checkLength(params.priority) &&
                validate.checkLength(params.name) && validate.checkString(params.name) && validate.checkLength(params.quantity) && validate.checkNumber(params.quantity) &&
                validate.checkLength(params.t0) && validate.checkLength(params.t0)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addOrder = function () {
            if (orderValidate()) {
                $("#modal-add").hide();
                myHttpService.post(serviceList.AddOrder, data).then(function successCallback(response) {
                    data.splice(0, data.length);
                    //用强制刷新解决按钮不能连续响应
                    //setTimeout('window.location.reload();', 0.1);
                }, function errorCallback(response) {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
        };

        //获得表单信息
        $scope.getInfo = function () {
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
            var a = document.getElementsByName("check");
            var row = 1;
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var params = {};
                    params.id = $("#table_value").find("tr").eq(row).find("td").eq(1).html();
                    Information.push(params);
                    console.log(Information);
                }
                row++;
            }
        };

        //修改订单
        var update = function () {
            getInfo();
            myHttpService.post(serviceList.AddOrder, checkedInfo).then(function successCallback(response) {
                checkedInfo.splice(0, checkedInfo.length);
                //用强制刷新解决按钮不能连续响应
                //setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                notification.sendNotification("alert", "请求失败");
            })
        };

        $scope.editOrder = function () {
            if (orderValidate()) {
                $("#modal-edit").hide();
                myHttpService.post(serviceList.UpdateOrder, data).then(function successCallback(response) {
                    data.splice(0, data.length);
                    //setTimeout('window.location.reload();', 0.1);
                }, function errorCallback(response) {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
        };


        //删除订单
        $scope.deleteOrder = function () {
            getInfo();
            myHttpService.delete(serviceList.DeleteOrder, checkedInfo).then(function successCallback(response) {
                checkedInfo.splice(0, checkedInfo.length);
                //强制刷新解决按钮不能连续响应
                //setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                notification.sendNotification("alert", "请求失败");
            });
        };


        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        }
    });