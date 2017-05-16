/**
 * Created by XY on 2017/5/15.
 */
angular.module("app.order", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/order', {
            templateUrl: 'jsps/order/order.html',
            controller: 'orderController'
        })
    }])
    .controller('orderController', function ($scope) {

    });

