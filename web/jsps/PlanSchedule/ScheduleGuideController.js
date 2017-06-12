/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleGuideController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleGuide', {
            templateUrl: '/jsps/PlanSchedule/ScheduleGuide.jsp',
            controller: 'ScheduleGuideController'
        })
    }])
    .controller('ScheduleGuideController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListOrder).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById("check").checked = false;
            $("input").val('');
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                $scope.arr = response.data;
            });
        }

        var updateSelected = function (action, id) {
            operateId = id;
            if (action == 'add' & selectedCheckArray.indexOf(id) == -1) {
                selectedCheckArray.push(id);
                console.log(id + "被选中");
            }
            if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
                selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
                console.log(id + "取消选中");
            }
        };
        //用于监控点击事件，checkbox选择了就更新
        $scope.updateSelection = function ($event, id) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');
            updateSelected(action, id);
        };
        $scope.isSelected = function (id) {
            return selectedCheckArray.indexOf(id) >= 0;
        };

        //APSconfig
        var configAPS = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var valueVal = $("input[name='add-value']").val();
            var descriptionVal = $("input[name='add-description']").val();
            var editableVal = $("input[name='add-editable']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.value = valueVal;
            params.description = descriptionVal;
            params.editable = editableVal;
            var data = JSON.stringify(params);
            alert(data);
            $("#config").hide();
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                console.log(response.status);
            }, function errorCallback(response) {
                alert("请求错误！");
            })
        };

        var chooseOrder = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            params.name = "";
            params.origin = "";
            params.priority = "";
            params.advance = "";
            params.delay = "";
            params.quantity = "";
            params.t0 = "";
            params.ord = "";
            console.log(params);
            var data = JSON.stringify(params);
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                console.log(response);
                $scope.form = response.data;
            }, function errorCallback(response) {
                alert("请求失败！");
            });
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
        }

    })