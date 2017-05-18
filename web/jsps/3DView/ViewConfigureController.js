/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ViewConfigureController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ViewConfigure', {
            templateUrl: '/jsps/3DView/ViewConfigure.jsp',
            controller: 'ViewConfigureController'
        })
    }])
    .controller("ViewConfigureController", function ($scope) {
    });