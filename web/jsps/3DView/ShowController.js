/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ShowController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Show', {
            templateUrl: '/jsps/3DView/Show.jsp',
            controller: 'ShowController'
        })
    }])
    .controller("ShowController", function ($scope) {
    });