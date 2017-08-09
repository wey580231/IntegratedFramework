/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ShowController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/Show3D', {
            templateUrl: 'pages/3DView/Show3DView.html',
            controller: 'ShowController'
        })
    }])
    .controller('ShowController', function ($scope, $http, myHttpService, serviceList) {

        //实时
        $scope.realModel = function () {
            if (OpenRealTimeDialog == -1 && Global3DInstance == null) {
                OpenRealTimeDialog = layer.open({
                    type: 2,
                    title: 'Mes实时驱动',
                    maxmin: true,
                    shadeClose: true,
                    shade: false,
                    offset: 'l',
                    area: ['100%', '100%'],
                    content: "WebGL/RealTime.html",
                    cancel: function () {
                        OpenRealTimeDialog = -1;
                        Global3DInstance = null;
                    }
                });
            } else {
                if (OpenRealTimeDialog != -1) {
                    layer.restore(OpenRealTimeDialog);
                }
            }
        };

        //模拟
        $scope.emulateModel = function () {
            if (OpenEmulateDialog == -1) {
                OpenEmulateDialog = layer.open({
                    type: 2,
                    title: 'APS计划模拟',
                    maxmin: true,
                    shadeClose: true,
                    shade: false,
                    offset: 'l',
                    area: ['100%', '100%'],
                    content: "WebGL/index.html",
                    cancel: function () {
                        OpenEmulateDialog = -1;
                    }
                });
            } else {
                if (OpenEmulateDialog != -1) {
                    layer.restore(OpenEmulateDialog);
                }
            }
        };

        //双屏
        $scope.doubleView = function () {
            if (OpenRealTimeDialog == -1 && Global3DInstance == null) {
                OpenRealTimeDialog = layer.open({
                    type: 2,
                    title: 'Mes实时驱动',
                    maxmin: true,
                    shadeClose: true,
                    shade: false,
                    offset: 'l',
                    area: ['50%', '100%'],
                    content: "WebGL/RealTime.html",
                    cancel: function () {
                        OpenRealTimeDialog = -1;
                        Global3DInstance = null;
                    }
                });
            } else {
                if (OpenRealTimeDialog != -1) {
                    layer.restore(OpenRealTimeDialog);
                }
            }

            if (OpenEmulateDialog == -1) {
                OpenEmulateDialog = layer.open({
                    type: 2,
                    title: 'APS计划模拟',
                    maxmin: true,
                    shadeClose: true,
                    shade: false,
                    offset: 'r',
                    area: ['50%', '100%'],
                    content: "WebGL/index.html",
                    cancel: function () {
                        OpenEmulateDialog = -1;
                    }
                });
            } else {
                if (OpenEmulateDialog != -1) {
                    layer.restore(OpenEmulateDialog);
                }
            }
        }
    });