/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.BOMManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/BOMManagement', {
            templateUrl: '/jsps/OrderManagement/BOMManagement.jsp',
            controller: 'BOMManagementController'
        })
    }])
    .controller("BOMManagementController", function ($scope) {
    });