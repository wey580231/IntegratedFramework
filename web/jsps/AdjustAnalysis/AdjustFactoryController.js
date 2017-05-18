/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.AdjustFactoryController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustFactory', {
            templateUrl: '/jsps/AdjustAnalysis/AdjustFactory.jsp',
            controller: 'AdjustFactoryController'
        })
    }])
    .controller("AdjustFactoryController", function ($scope) {
    });