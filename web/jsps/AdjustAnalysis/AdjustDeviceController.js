/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.AdjustDeviceController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustDevice', {
            templateUrl: '/jsps/AdjustAnalysis/AdjustDevice.jsp',
            controller: 'AdjustDeviceController'
        })
    }])
    .controller("AdjustDeviceController", function ($scope) {
    });