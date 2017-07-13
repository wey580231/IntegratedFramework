/**
<<<<<<< HEAD
 * Created by zhaoqi on 2017/7/12.
=======
 * Created by XY on 2017/7/12.
>>>>>>> 2737b5cc55a7fc0b418b365a69f8cf1594c219b9
 */
'use strict';
angular.module("IntegratedFramework.AdjustProcedureController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustProcedure', {
            templateUrl: 'pages/AdjustAnalysis/AdjustProcedure.html',
            controller: 'AdjustProcedureController'
        })
    }])
<<<<<<< HEAD
    .controller("AdjustProcedureController", function ($scope, $http, myHttpService, serviceList) {
        //加载页面时数据显示
        myHttpService.get(serviceList.AdjustProcess).then(function (response) {
            console.log(response.data);
            $scope.arr = response.data;
        });
=======

    .controller('AdjustProcedureController', function ($scope, $http, myHttpService, serviceList,renderTableService) {
        //加载页面时数据显示
        myHttpService.get(serviceList.AdjustProcess).then(function (response) {
            $scope.arr = response.data;
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };
>>>>>>> 2737b5cc55a7fc0b418b365a69f8cf1594c219b9
    });