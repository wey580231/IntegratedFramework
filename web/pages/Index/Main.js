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
            //获取异常状态
            myHttpService.get(serviceList.findAllExceptionUrl).then(function (response) {
                $scope.exceptionNum = response.data;
            });
            //获取EventLog状态
            myHttpService.get(serviceList.getAllEventLogUrl).then(function (response) {
                $scope.eventLogList = response.data;
            });
        });

        //显示当前布局的放大图
        $scope.showDetail = function (event) {
            // var e = event || window.event;
            // var target = e.target || e.srcElement;
            // var content = "<img src='" + target.src + "' style='width:100%;height:100%;'>";
            // layer.open({
            //     type: 1,
            //     title: false,
            //     closeBtn: 0,
            //     area: ['1030px', '730px'],
            //     skin: 'layui-layer-nobg', //没有背景色
            //     shadeClose: true,
            //     content: content
            // });
        }

    });
