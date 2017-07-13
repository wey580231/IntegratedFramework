/**
 * Created by XY on 2017/7/12.
 */
'use strict';
angular.module("IntegratedFramework.AdjustOrderController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustOrder', {
            templateUrl: 'pages/AdjustAnalysis/AdjustProcedure.html',
            controller: 'AdjustOrderController'
        })
    }])

    .controller('AdjustOrderController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService) {
        var editData = [];//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        myHttpService.get(serviceList.ListOrder).then(function (response) {
            $scope.orderList = response.data;
        });
    });