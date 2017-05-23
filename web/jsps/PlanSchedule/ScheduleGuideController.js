/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleGuideController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleGuide', {
            templateUrl: '/jsps/PlanSchedule/ScheduleGuide.jsp',
            controller: 'ScheduleGuideController'
        })
    }])
    .controller("ScheduleGuideController", function ($scope) {
    });