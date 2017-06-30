/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.AdjustProcedureController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustProcedure', {
            templateUrl: 'jsps/AdjustAnalysis/AdjustProcedure.jsp',
            controller: 'AdjustProcedureController'
        })
    }])
    .controller("AdjustProcedureController", function ($scope, $http, myHttpService, serviceList) {
        //加载页面时数据显示
        myHttpService.get(serviceList.AdjustProcess).then(function (response) {
            console.log(response.data);
            $scope.arr = response.data;
        });
    });