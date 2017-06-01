/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: '/jsps/OrderManagement/OrderManagement.jsp',
            controller: 'OrderManagementController'
        })
    }])
    /* .config(['$httpProvider', function ($httpProvider) {
     $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
     $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
     $httpProvider.defaults.transformRequest = function (obj) {
     var str = [];
     for (var p in obj) {
     str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
     }
     return str.join("&");
     }
     }])

     .controller('OrderManagementController', function ($scope, $http) {
     $http({
     method: 'post',
     dataType: 'json',
     contentType: 'application/json;charset=UTF-8',
     data: JSON.stringify(),
     url: 'http://localhost:8080/orders/getAllOrders.action',
     transformRequest: function (obj) {
     var str = [];
     for (var p in obj) {
     str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
     }
     return str.join("&");
     }
     }, "json").then(function successCallback(response) {
     console.log(response);
     $scope.names = response.data;
     })
     })
     */

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList) {
        myHttpService.post(serviceList.ListOrder).then(function (response) {
            console.log(response);
            $scope.names = response.data;
        });

    })