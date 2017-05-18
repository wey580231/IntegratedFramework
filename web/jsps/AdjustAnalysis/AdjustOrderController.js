/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.AdjustOrderController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustOrder', {
            templateUrl: '/jsps/AdjustAnalysis/AdjustOrder.jsp',
            controller: 'AdjustOrderController'
        })
    }])
    .controller("AdjustOrderController", function ($scope) {
    });