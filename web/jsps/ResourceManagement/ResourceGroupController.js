/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.ResourceGroupController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceGroup', {
            templateUrl: '/jsps/ResourceManagement/ResourceGroup.jsp',
            controller: 'ResourceGroupController'
        })
    }])

    .controller('ResourceGroupController', function ($scope, $http, myHttpService, serviceList) {
        myHttpService.post(serviceList.ListGroupResource).then(function (response) {
            console.log(response);
            $scope.names = response.data;
        });

    })