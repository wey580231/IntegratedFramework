/**
 * Created by Administrator on 2017/7/13.
 */

'use strict';
angular.module("IntegratedFramework.AdjustDeviceController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/AdjustDevice', {
            templateUrl: 'pages/AdjustAnalysis/AdjustDevice.html',
            controller: 'AdjustDeviceController'
        })
    }])

    .controller('AdjustDeviceController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService) {

        layer.load(0);


        $(function () {
            myHttpService.get(serviceList.getAllAdjustDeviceException).then(function (response) {
                $scope.adjustDeviceList = response.data;
                hideLoadingPage();
            });

        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

       /* myHttpService.get(serviceList.queryApsState).then(function (response) {
            if (response.data.result == "ok") {
                if (response.data.data.state == 0) {
                    $("#modal-add").modal({show: 'true'});
                } else {
                    notification.sendNotification("alert", "APS正在计算中，无法排程");
                    // layer.msg('APS正在计算中，无法排程!', {icon: 2});
                }
            } else {
                notification.sendNotification("alert", "查询APS状态失败，请重试");
                // layer.msg('查询APS状态失败，请重试!', {icon: 2});
            }
        });*/

    });