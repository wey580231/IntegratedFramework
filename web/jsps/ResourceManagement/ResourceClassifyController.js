/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.ResourceClassifyController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceClassify', {
            templateUrl: '/jsps/ResourceManagement/ResourceClassify.jsp',
            controller: 'ResourceClassifyController'
        })
    }])
    .controller("ResourceClassifyController", function ($scope) {
    });
