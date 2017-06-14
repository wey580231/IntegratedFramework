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
        var ordId;
        var resId;
        var resGroId;
        var siteId;

        //重新加载页面
        $scope.reload = function () {
            //取消checkbox选中状态
            document.getElementById("check").checked = false;
            $("input").val('');
        }

        //各表单选择时信息显示
        $scope.showOrder = function () {
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                console.log(response);
                $scope.ord = response.data;
            });
        };

        $scope.showResource = function () {
            myHttpService.get(serviceList.ListResource).then(function (response) {
                console.log(response);
                $scope.res = response.data;
            });
        };

        $scope.showGroupResource = function () {
            myHttpService.get(serviceList.ListGroupResource).then(function (response) {
                console.log(response);
                $scope.resGro = response.data;
            });
        };

        $scope.showSite = function () {
            myHttpService.get(serviceList.ListSite).then(function (response) {
                console.log(response);
                $scope.site = response.data;

            });
        };

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


        //勾选订单后，记录所选id
        $scope.checkOrId = function () {

            ordId = operateId;
            alert(ordId);

        }
        $scope.orderHide = function () {
            $("#chooseOrder").hide()
        }

        //勾选资源后，记录所选id
        $scope.checkReId = function () {

            resId = operateId;
            alert(resId);

        }
        $scope.orderHide = function () {
            $("#chooseResource").hide()
        }

        //勾选资源工组后，记录所选id
        $scope.checkGrReId = function () {

            resGroId = operateId;
            alert(resGroId);

        }
        $scope.orderHide = function () {
            $("#chooseGroupResource").hide()
        }

        //勾选资源工位后，记录所选id
        $scope.checkSiId = function () {

            siteId = operateId;
            alert(siteId);

        }
        $scope.orderHide = function () {
            $("#chooseSite").hide()
        }

        //排程
        $scope.configAPS = function () {
            alert("开始");
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var scheduleVal = $("input[name='add-schedule']").val();
            var rollTimeVal = $("input[name='add-rollTime']").val();
            var t0Val = $("input[name='add-t0']").val();
            var t2Val = $("input[name='add-t2']").val();

            var APSconfigs = {};
            APSconfigs.t0 = t0Val;
            APSconfigs.t2 = parseInt(t2Val);

            /*var orders = {};
             orders.id = ordId;

             var layouts = {};
             layout.id = layoutId;*/

            var resourceArr = [];
            var resources = {};
            resources.id = parseInt(resId);
            resourceArr.push(resources);

            var groupResourcesArr = [];
            var groupResources = {};
            groupResources.id = parseInt(resGroId);
            groupResourcesArr.push(groupResources);

            var sitesArr = []
            var sites = {};
            sites.id = parseInt(siteId);
            sitesArr.push(sites);


            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.scheduleWindow = parseInt(scheduleVal);
            alert(params.scheduleWindow);
            params.rollTime = parseInt(rollTimeVal);
            //params.order=orders;
            //params.layout = layouts;
            params.APSconfig = APSconfigs;
            params.resource = resourceArr;
            params.groupResource = groupResourcesArr;
            params.site = sitesArr;
            var data = JSON.stringify(params);
            alert(data);
            $("#schedule").hide();
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