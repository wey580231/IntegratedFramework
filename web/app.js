/**
 * Created by hanchangming on 2017/5/15.
 */
'use strict';
angular.module("IntegratedFramework", [
    'ngRoute',
    'IntegratedFramework.OrderManagementController',
    'IntegratedFramework.BOMManagementController',
    'IntegratedFramework.ResourceDistributionController',
    'IntegratedFramework.ResourceListController',
    'IntegratedFramework.ResourceGroupController',
    'IntegratedFramework.ResourceClassifyController',
    'IntegratedFramework.ResourceStationController',
    'IntegratedFramework.WorkListController',
    'IntegratedFramework.OnlineManagementController',
    'IntegratedFramework.DeviceMonitorController',
    'IntegratedFramework.AdjustDeviceController',
    'IntegratedFramework.AdjustOrderController',
    'IntegratedFramework.AdjustFactoryController',
    'IntegratedFramework.AdjustProcedureController',
    'IntegratedFramework.ShowController',
    'IntegratedFramework.ScheduleGuideController',
    'IntegratedFramework.ScheduleSnapController',
    'IntegratedFramework.InteractiveController',
    'IntegratedFramework.GuideController'
])
/*.config(['$routeProvider', function ($routeProvider, $locationProvider) {
 // $locationProvider.hashPrefix('!');
 // $routeProvider.otherwise({redirectTo: '/jsps/OrderManagement/OnlineManagement'});
 }])*/
    .config(['$httpProvider', function ($httpProvider) {
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
    .factory("myHttpService", ['$http', function ($http) {
        var service = {};
        var _get = function (servletUrl) {
            return $http({
                'method': 'get',
                'dataType': 'json',
                'contentType': 'application/json;charset=UTF-8',
                'url': servletUrl,
            });
        };
        var _post = function (servletUrl, body) {
            return $http({
                'method': 'post',
                'dataType': 'json',
                'contentType': 'application/json;charset=UTF-8',
                'url': servletUrl,
                'data': body
            });
        };
        var _delete = function (servletUrl,body) {
            return $http({
                'method': 'delete',
                'dataType': 'json',
                'contentType': 'application/json;charset=UTF-8',
                'url': servletUrl,
                'data': body
            });
        }
        service.get = _get;
        service.post = _post;
        service.delete = _delete;
        return service;
    }])
    /* .factory("myHttpService", function ($http) {
     var service = {
     get : function () {
     $http({
     'method': 'get',
     'dataType': 'json',
     'contentType': 'application/json;charset=UTF-8',
     'url': servletUrl,
     'headers': {
     'url': requestUrl
     }
     });
     },

     post : function () {
     $http({
     'method': 'post',
     'dataType': 'json',
     'contentType': 'application/json;charset=UTF-8',
     'url': 'http://localhost:8080/orders/getAllOrders.action',
     'transformRequest': function (obj) {
     var str = [];
     for (var p in obj) {
     str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
     }
     return str.join("&");
     }
     }, "json")
     },

     delete : function () {
     $http({
     'method': 'delete',
     'dataType': 'json',
     'contentType': 'application/json;charset=UTF-8',
     'url': servletUrl,
     'headers': {
     'url': requestUrl
     }
     }, "json").then(function successCallback(response) {
     return response.data;
     });
     }};
     return service;
     })
     */

    .factory("serviceList", function () {
        var service = {};
        var backUrl = "http://localhost:8080/";
        service.ListOrder = backUrl + "orders/getAllOrders.action";
        return service;
    })

