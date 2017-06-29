/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OnlineManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OnlineManagement', {
            templateUrl: 'jsps/OnlineMonitor/OnlineManagement.jsp',
            controller: 'OnlineManagementController'
        })
    }])
    .controller("OnlineManagementController", function ($scope) {
    });