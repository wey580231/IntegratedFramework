/**
 * Created by zhaoqi on 2017/5/21.
 */
'use strict';
angular.module("IntegratedFramework.InteractiveController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Interactive', {
            templateUrl: 'pages/PlanSchedule/Interactive.html',
            controller: 'InteractiveController'
        })
    }])
    .controller("InteractiveController", function ($scope, myHttpService, serviceList, notification) {

        layer.load(0);

        $(function () {
            myHttpService.get(serviceList.currSheduleInfo).then(function (response) {
                if (response.data.result == "ok") {
                    $scope.schedule = response.data;
                    hideLoadingPage();

                } else {
                    notification.sendNotification("alert", "获取排程信息失败");
                    hideLoadingPage();
                    $("#getInteractiveResult").attr("disabled", true);
                    $("#zoomin").attr("disabled", true);
                    $("#zoomout").attr("disabled", true);
                }
            }, function (response) {
                hideLoadingPage();
            });
        });

        //交互优化计算
        $scope.emulateRecvApsInterResult = function () {
            myHttpService.get(serviceList.emulateApsInterResult).then(function (response) {
                if (response.data.result == "ok") {
                    notification.sendNotification("confirm", "结果信息转换中...");
                } else if ("emergency_ok") {
                    notification.sendNotification("confirm", "交互优化计算中...");
                } else if ("emergency_error") {
                    notification.sendNotification("alert", "交互优化失败");
                } else {
                    notification.sendNotification("alert", "结果信息转换失败");
                }
            }, function (response) {

            });
        }
    });