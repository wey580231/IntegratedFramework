/**
 * Created by hanchangming on 2017/5/15.
 */
'use strict';
angular.module("app", [
    'ngRoute',
    'app.OrderManagementController'
]).config(['$routeProvider', function ($routeProvider) {
    // $routeProvider.otherwise({redirectTo: '/OrderManagement'});
}]);