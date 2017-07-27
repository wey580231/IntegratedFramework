/**
 * Created by XY on 2017/7/27.
 */
'use strict';
angular.module("IntegratedFramework.FactoryLayoutController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/FactoryLayout', {
            templateUrl: 'pages/3DView/FactoryLayout.html',
            controller: 'FactoryLayoutController'
        })
    }])

    .controller('FactoryLayoutController', function ($scope, $http, myHttpService, serviceList) {
        layer.load(0);
        $(function () {
            loadRightFloatMenu();

            myHttpService.get(serviceList.ListFactoryLayout).then(function (response) {
                $scope.factoryLayoutList = response.data;

                hideLoadingPage();
            });
        });

    });
