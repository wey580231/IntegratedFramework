/**
 * Created by XY on 2017/7/12.
 */
'use strict';
angular.module("IntegratedFramework.AdjustOrderController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustOrder', {
            templateUrl: 'pages/AdjustAnalysis/AdjustOrder.html',
            controller: 'AdjustOrderController'
        })
    }])

    .controller('AdjustOrderController', function ($scope, $http, myHttpService, serviceList,validate,renderTableService) {

        var addData = [];

        myHttpService.get(serviceList.AdjustOrder).then(function (response) {
            $scope.adjustOrder = response.data;
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //Date picker
        $('#modal-add-t0-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#modal-add-t1-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#modal-add-t2-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });

        //信息填写检验
        var orderAddValidate = function () {
            var params = {};
            var t0 = $("input[id='modal-add-t0-datepicker']").val();
            var t1 = $("input[id='modal-add-t1-datepicker']").val();
            var t2 = $("input[id='modal-add-t2-datepicker']").val();
            params.t0 = Date.parse($("input[id='modal-add-t0-datepicker']").val());
            params.t1 = Date.parse($("input[id='modal-add-t1-datepicker']").val());
            params.t2 = Date.parse($("input[id='modal-add-t2-datepicker']").val());
            addData = JSON.stringify(params);

            if (!validate.checkLength(t0)) {
                $("#add-t0").removeClass("has-success");
                $("#add-t0").addClass("has-error");
            } else {
                $("#add-t0").removeClass("has-error");
                $("#add-t0").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#add-t1").removeClass("has-success");
                $("#add-t1").addClass("has-error");
            } else {
                $("#add-t1").removeClass("has-error");
                $("#add-t1").addClass(" has-success");
            }

            if (!validate.checkLength(t2)) {
                $("#add-t2").removeClass("has-success");
                $("#add-t2").addClass("has-error");
            } else {
                $("#add-t2").removeClass("has-error");
                $("#add-t2").addClass(" has-success");
            }

            if (validate.checkLength(params.t0) && validate.checkLength(params.t1) && validate.checkLength(params.t2)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addAdjustOrder = function () {
            if (orderAddValidate()) {
                $("#modal-add").modal('hide');
                console.log(addData);
                myHttpService.post(serviceList.AddAdjustOrder, addData).then(function successCallback() {
                    //setTimeout('window.location.reload();', 0.1);
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
        };


        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };
    });