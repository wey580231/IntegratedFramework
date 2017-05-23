/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: '/jsps/OrderManagement/OrderManagement.jsp',
            controller: 'OrderManagementController'
        })
    }])
    .controller("OrderManagementController", function ($scope) {
    });