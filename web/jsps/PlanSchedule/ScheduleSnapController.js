/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleSnapController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleSnap', {
            templateUrl: '/jsps/PlanSchedule/ScheduleSnap.jsp',
            controller: 'ScheduleSnapController'
        })
    }])
    .controller("ScheduleSnapController", function ($scope) {
    });