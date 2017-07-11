/**
 * Created by zhaoqi on 2017/5/21.
 */
'use strict';
angular.module("IntegratedFramework.InteractiveController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Interactive', {
            templateUrl: 'pages/PlanSchedule/Interactive.html',
            controller: 'InteractiveController'
        })
    }])
    .controller("InteractiveController", function ($scope) {
    });