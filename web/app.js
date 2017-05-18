/**
 * Created by hanchangming on 2017/5/15.
 */
'use strict';
angular.module("IntegratedFramework", [
    'ngRoute',
    'IntegratedFramework.OrderManagementController',
    'IntegratedFramework.BOMManagementController',
    'IntegratedFramework.ResourceListController',
    'IntegratedFramework.OnlineManagementController',
    'IntegratedFramework.ResourceClassifyController',
    'IntegratedFramework.WorkListController',
    'IntegratedFramework.AdjustOrderController',
    'IntegratedFramework.ResourceGroupController'
]).config(['$routeProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.hashPrefix('!');
    // $routeProvider.otherwise({redirectTo: '/jsps/OrderManagement/OnlineManagement'});
}]);
