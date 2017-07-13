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

    .controller('AdjustProcedureController', function ($scope, $http, myHttpService, serviceList) {

    });