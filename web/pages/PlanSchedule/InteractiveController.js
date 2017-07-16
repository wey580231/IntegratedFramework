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
                    layer.msg('获取排程信息失败!', {icon: 2});
                }
            }, function (response) {
                hideLoadingPage();
            });
        });
    });