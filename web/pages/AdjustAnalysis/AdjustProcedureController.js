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

    .controller('AdjustProcedureController', function ($scope, $http, myHttpService, serviceList,renderTableService) {
        //加载页面时数据显示
        myHttpService.get(serviceList.AdjustProcess).then(function (response) {
            $scope.arr = response.data;
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };
    });