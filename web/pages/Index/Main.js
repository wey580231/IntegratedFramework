/**
 * Created by hanchangming on 2017/5/22.
 */
'use strict';
angular.module("IntegratedFramework.MainPage", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/MainPage', {
            templateUrl: 'pages/Index/MainPage.html',
            controller: 'MainPageController'
        })
    }])
    .controller("MainPageController", function ($scope, $http, myHttpService, serviceList) {
        $(function () {
            //加载页面时数据显示
            myHttpService.get(serviceList.AdjustProcess).then(function (response) {
                var y=response.data.length;//总数
                var n=0;//未处理
                for(var i=0;i<response.data.length;i++){
                    if(response.data[i].state == 1){
                        n++;
                    }
                }
                var params={};
                params.y=y;
                params.n=n;
                var process=[];
                process.push(params);
                $scope.processArr = process;
            });

            myHttpService.get(serviceList.AdjustOrder).then(function (response) {
                var y=response.data.length;
                var n=0;
                for(var i=0;i<response.data.length;i++){
                    if(response.data[i].state == 1){
                        n++;
                    }
                }
                var params={};
                params.y=y;
                params.n=n;
                var urgent=[];
                urgent.push(params);
                $scope.urgentArr = urgent;
            });

            myHttpService.get(serviceList.getAllAdjustDeviceException).then(function (response) {
                var y=response.data.length;
                var n=0;
                for(var i=0;i<response.data.length;i++){
                    if(response.data[i].state == 1){
                        n++;
                    }
                }
                var params={};
                params.y=y;
                params.n=n;
                var device=[];
                device.push(params);
                $scope.deviceArr = device;
            });
        });
    });
