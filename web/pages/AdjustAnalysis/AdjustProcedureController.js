/**
 * Created by XY on 2017/7/12.
 */
'use strict';
angular.module("IntegratedFramework.AdjustProcedureController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustProcedure', {
            templateUrl: 'pages/AdjustAnalysis/AdjustProcedure.html',
            controller: 'AdjustProcedureController'
        })
    }])
    .controller('AdjustProcedureController', function ($scope, $http, myHttpService, serviceList, renderTableService, notification) {
        layer.load(0);

        $(function () {
            //加载页面时数据显示
            myHttpService.get(serviceList.AdjustProcess).then(function (response) {
                $scope.arr = response.data;

                hideLoadingPage();
            });
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //异常处理
        $scope.exceptionHandling = function (event) {

            myHttpService.get(serviceList.queryApsState).then(function (response) {
                if (response.data.result == "ok") {
                    if (response.data.data.state == 0) {
                        processError();
                    } else {
                        layer.msg('APS正在计算中，无法排程!', {icon: 2});
                    }
                } else {
                    layer.msg('查询APS状态失败，请重试!', {icon: 2});
                }
            });
        };

        function processError() {
            var idInfo;
            var e = event || window.event;
            var target = e.target || e.srcElement;
            if (target.parentNode.tagName.toLowerCase() == "td") {
                var rowIndex = target.parentNode.parentNode.rowIndex;
                var id = document.getElementById("table_adjust").rows[rowIndex].cells[0].innerHTML;
                myHttpService.get(serviceList.processExceptionHandling + "?id=" + id, idInfo).then(function successCallback(response) {
                    if (response.data.result == "ok") {
                        notification.sendNotification("confirm", "异常处理中...");
                    } else {
                        notification.sendNotification("alert", "请求失败");
                    }
                }, function errorCallback(response) {
                    notification.sendNotification("alert", "请求失败");
                })
            }
        }
    });