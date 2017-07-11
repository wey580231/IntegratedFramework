/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ShowController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Show3D', {
            templateUrl: 'pages/3DView/Show3DView.html',
            controller: 'ShowController'
        })
    }])
    .controller('ShowController', function ($scope, $http, myHttpService, serviceList) {

    })