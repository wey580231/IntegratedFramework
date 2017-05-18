/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ResourceDistributionController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceDistribution', {
            templateUrl: '/jsps/OrderManagement/ResourceDistribution.jsp',
            controller: 'ResourceDistributionController'
        })
    }])
    .controller("ResourceDistributionController", function ($scope) {
    });