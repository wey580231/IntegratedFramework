/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.ResourceListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceList', {
            templateUrl: '/jsps/ResourceManagement/ResourceList.jsp',
            controller: 'ResourceListController'
        })
    }])

    .controller('ResourceListController', function ($scope, $http, myHttpService, serviceList) {
        myHttpService.post(serviceList.ListResource).then(function (response) {
            console.log(response);
            $scope.names = response.data;
        });

    })


