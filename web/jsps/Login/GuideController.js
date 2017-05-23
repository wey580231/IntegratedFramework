/**
 * Created by hanchangming on 2017/5/22.
 */
'use strict';
angular.module("IntegratedFramework.GuideController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Guide', {
            templateUrl: '/jsps/Login/Guide.jsp',
            controller: 'GuideController'
        })
    }])
    .controller("GuideController", function ($scope) {
    });
