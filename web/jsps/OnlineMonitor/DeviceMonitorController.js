/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.DeviceMonitorController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/DeviceMonitor', {
            templateUrl: '/jsps/OnlineMonitor/DeviceMonitor.jsp',
            controller: 'DeviceMonitorController'
        })
    }])
    .controller("DeviceMonitorController", function ($scope) {
    });
