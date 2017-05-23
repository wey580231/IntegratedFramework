/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ResourceStationController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceStation', {
            templateUrl: '/jsps/ResourceManagement/ResourceStation.jsp',
            controller: 'ResourceStationController'
        })
    }])
    .controller("ResourceStationController", function ($scope) {
    });