/**
 * Created by wey580231 on 2017/1/12.
 */
angular.module("app.viewRepos", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/viewRepos', {
            templateUrl: '/jsps/OrderManagement/viewAllRepos.html',
            controller: 'viewReposCtrl'
        });
    }])
    .controller('viewReposCtrl', function ($scope) {
    });
