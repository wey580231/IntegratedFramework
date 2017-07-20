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
    .controller("InteractiveController", function ($scope, myHttpService, serviceList) {

        layer.load(0);

        $(function () {
            myHttpService.get(serviceList.currSheduleInfo).then(function (response) {
                if (response.data.result == "ok") {
                    $scope.schedule = response.data;
                    hideLoadingPage();
                } else {
                    notification.sendNotification("alert", "获取排程信息失败");
                    // layer.msg('获取排程信息失败!', {icon: 2});
                }
            }, function (response) {
                hideLoadingPage();
            });
        });

        $scope.emulateRecvApsInterResult = function () {
            myHttpService.get(serviceList.emulateApsInterResult).then(function (response) {
                if (response.data.result == "ok") {
                    layer.msg('结果信息转换中!', {icon: 1});
                } else {
                    layer.msg('结果信息转换失败!', {icon: 2});
                }
            }, function (response) {

            });
        }
    });