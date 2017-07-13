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

    .controller('AdjustOrderController', function ($scope, $http, myHttpService, serviceList,renderTableService) {
        myHttpService.get(serviceList.AdjustOrder).then(function (response) {
            $scope.adjustOrder = response.data;
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };
    });