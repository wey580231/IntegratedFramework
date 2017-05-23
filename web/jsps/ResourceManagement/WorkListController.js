/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.WorkListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/WorkList', {
            templateUrl: '/jsps/ResourceManagement/WorkList.jsp',
            controller: 'WorkListController'
        })
    }])
    .controller("WorkListController", function ($scope) {
    });