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

    .controller('AdjustOrderController', function ($scope, $http, myHttpService, serviceList, validate, renderTableService, notification) {
        layer.load(0);
        //页面初始化是加载的请求
        $(function () {
            var adjustOrderByType = {};
            adjustOrderByType.adjustOrderType = "紧急插单分析";
            var jsonString = JSON.stringify(adjustOrderByType);
            myHttpService.post(serviceList.getALLAdjustOrderByType, jsonString).then(function (response) {
                $scope.adjustOrder = response.data;
                hideLoadingPage();
            });
        });
        //根据Tab页来进行切换请求。
        $('#myUl').find('a').click(function (e) {
            layer.load(0);
            var adjustOrderByType = {};
            adjustOrderByType.adjustOrderType = $(this).text();
            var jsonString = JSON.stringify(adjustOrderByType);
            myHttpService.post(serviceList.getALLAdjustOrderByType, jsonString).then(function (response) {
                $scope.adjustOrder = response.data;
                console.log(response.data);
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
                        processError(event);
                    } else {
                        notification.sendNotification("alert", "APS正在计算中，无法操作");
                    }
                } else {
                    notification.sendNotification("alert", "查询APS状态失败，请重试");
                }
            });
        };

        //处理一样
        function processError(event) {
            var e = event || window.event;
            var target = e.target || e.srcElement;
            if (target.parentNode.tagName.toLowerCase() == "td") {
                var rowIndex = target.parentNode.parentNode.rowIndex;
                var id = document.getElementById("table_adjust").rows[rowIndex].cells[0].innerHTML;
                myHttpService.get(serviceList.orderExceptionHandling + "?id=" + id).then(function successCallback(response) {
                    if (response.data.result == "ok") {
                        notification.sendNotification("confirm", "紧急插单处理中...");
                    } else {
                        notification.sendNotification("alert", "请求失败");
                    }
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            }
        }

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };
    });