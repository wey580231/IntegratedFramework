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
    'IntegratedFramework.ViewConfigureController',
    'IntegratedFramework.ScheduleGuideController',
    'IntegratedFramework.ScheduleSnapController',
    'IntegratedFramework.InteractiveController',
    'IntegratedFramework.GuideController'
]).config(['$routeProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.hashPrefix('!');
    // $routeProvider.otherwise({redirectTo: '/jsps/OrderManagement/OnlineManagement'});
}]);
