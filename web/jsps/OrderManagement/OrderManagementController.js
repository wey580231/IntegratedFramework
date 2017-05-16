/**
 * Created by hanchangming on 2017/5/16.
 */
angular.module("app.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: 'jsps/OrderManagement/OrderManagement.jsp',
            controller: 'OrderManagementController'
        })
    }])
    .controller("OrderManagementController", function ($scope) {
        alert("controller");
    });