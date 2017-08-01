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

    .controller('FactoryLayoutController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService) {
        layer.load(0);
        $(function () {
            loadRightFloatMenu();

            myHttpService.get(serviceList.ListFactoryLayout).then(function (response) {
                $scope.factoryLayoutList = response.data;

                hideLoadingPage();
            });
        });


        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        $scope.Detail = function (event) {
            var e = event || window.event;
            var target = e.target || e.srcElement;
            if (target.parentNode.tagName.toLowerCase() == "td") {
                var rowIndex = target.parentNode.parentNode.rowIndex;
                //alert(rowIndex);
                var id = document.getElementById("table_layout").rows[rowIndex].cells[1].innerHTML;
                //alert(id);
                var parm={};
                parm.id=id;
                var info=JSON.stringify(parm);
                myHttpService.post(serviceList.LayoutDetail,info).then(function successCallback(response) {
                    $scope.layoutDetailList = response.data;

                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })

            }
        };

        //显示当前布局的放大图
        $scope.showDetail = function (event) {
            var e = event || window.event;
            var target = e.target || e.srcElement;
            var content = "<img src='" + target.src + "' style='width:100%;height:100%;'>";
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: ['830px', '730px'],
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: content
            });
        }

    });
