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

        layer.load(0);

        $(function () {
            //获取异常状态
            myHttpService.get(serviceList.findAllExceptionUrl).then(function (response) {
                $scope.exceptionNum = response.data;
            });
            //获取EventLog状态
            myHttpService.get(serviceList.getAllEventLogUrl).then(function (response) {

                if (response.data.data != undefined && response.data.data.length > 0) {

                    var data = response.data.data;
                    var DateArray = [];

                    var lastDate = "";
                    for (var i = 0; i < data.length; i++) {
                        var eventLog = data[i];
                        var createTime = eventLog.creatTime.split(" ", 2);

                        if (createTime.length == 2) {
                            if (lastDate != createTime[0]) {
                                var obj = new Object();
                                obj.type = "date";
                                obj.currDate = createTime[0];

                                lastDate = createTime[0];
                                DateArray.push(obj);
                            }

                            var obj = new Object();
                            obj.type = "log";
                            obj.data = eventLog;

                            DateArray.push(obj);
                        }
                    }

                    $scope.dateArray = DateArray;
                }
                else {
                    $scope.dateArray = [];
                }
                hideLoadingPage();
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

    })
;
