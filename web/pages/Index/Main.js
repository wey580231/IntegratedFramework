/**
 * Created by hanchangming on 2017/5/22.
 */
'use strict';
angular.module("IntegratedFramework.MainPage", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/MainPage', {
            templateUrl: 'pages/Index/MainPage.html',
            controller: 'MainPageController'
        })
    }])
    .controller("MainPageController", function ($scope, $http, myHttpService, serviceList) {
        $(function () {
            //获取异常状态
            myHttpService.get(serviceList.findAllExceptionUrl).then(function (response) {
                $scope.exceptionNum = response.data;
            });
            //获取EventLog状态
            myHttpService.get(serviceList.getAllEventLogUrl).then(function (response) {
                $scope.eventLogList = response.data;
            });
        });
    });
