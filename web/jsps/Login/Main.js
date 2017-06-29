/**
 * Created by hanchangming on 2017/5/22.
 */
'use strict';
angular.module("IntegratedFramework.MainPage", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/MainPage', {
            templateUrl: '/jsps/Login/Main.jsp',
            controller: 'MainPageController'
        })
    }])
    .controller("MainPageController", function ($scope) {
    });
