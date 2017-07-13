/**
 * Created by zhaoqi on 2017/7/12.
 */
'use strict';
angular.module("IntegratedFramework.AdjustFactoryController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustFactory', {
            templateUrl: 'pages/AdjustAnalysis/AdjustFactory.html',
            controller: 'AdjustFactoryController'
        })
    }])
    .controller("AdjustFactoryController", function ($scope) {

        /* $("#wheel-button").wheelmenu({
         animation: "fly",
         animationSpeed: "medium",
         angle: "all"
         });*/

    });