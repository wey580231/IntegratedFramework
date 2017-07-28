/**
 * Created by XY on 2017/7/27.
 */
'use strict';
angular.module("IntegratedFramework.OnlineOrderController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OnlineOrder', {
            templateUrl: 'pages/OnlineMonitoringAndManagement/OnlineOrder.html',
            controller: 'OnlineOrderController'
        })
    }])

    .controller('OnlineOrderController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService, dispatchApsService, confirm) {


    });
