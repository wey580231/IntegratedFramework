/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.AdjustProcedureController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustProcedure', {
            templateUrl: 'jsps/AdjustAnalysis/AdjustProcedure.jsp',
            controller: 'AdjustProcedureController'
        })
    }])
    .controller("AdjustProcedureController", function ($scope) {
    });