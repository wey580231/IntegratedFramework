/**
 * Created by hanchangming on 2017/5/15.
 */
'use strict';
angular.module("app", [
    'ngRoute',
    'app.order'
]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: 'jsps/order/order.html'});
}]);