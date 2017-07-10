/**
 * Created by hanchangming on 2017/5/15.
 */
'use strict';
angular.module("IntegratedFramework", [
    'ngRoute',
    'IntegratedFramework.OrderManagementController',
    'IntegratedFramework.ResourceDistributionController',
    'IntegratedFramework.ResourceListController',
    'IntegratedFramework.ResourceGroupController',
    'IntegratedFramework.ResourceClassifyController',
    'IntegratedFramework.ResourceStationController',
    'IntegratedFramework.WorkListController',
    'IntegratedFramework.ScheduleSnapController',
    'IntegratedFramework.EquipmentMonitoringController'
])
    .config(['$routeProvider', function ($routeProvider, $locationProvider) {
        //     // $locationProvider.hashPrefix('!');
        $routeProvider.otherwise("/MainPage");
    }])
    .config(['$httpProvider', function ($httpProvider) {
        /*$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
         $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';*/
    }])
    .factory("myHttpService", ['$http', function ($http) {
        var service = {};
        var _get = function (servletUrl) {
            return $http({
                'method': 'get',
                'dataType': 'json',
                'url': servletUrl,
                'headers': {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            })
        };
        var _post = function (servletUrl, body) {
            return $http({
                'method': 'post',
                'dataType': 'json',
                'url': servletUrl,
                'data': body,
                'headers': {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            })
        };
        var _delete = function (servletUrl, body) {
            return $http({
                'method': 'delete',
                'dataType': 'json',
                'url': servletUrl,
                'data': body,
                'headers': {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            })
        };
        service.get = _get;
        service.post = _post;
        service.delete = _delete;
        return service;
    }])

    .factory("notification", function () {
        var service = {};
        service.sendNotification = function (notificationType, message) {
            // Create an instance of Notyf
            var notyf = new Notyf();
            if (notificationType === "alert") {
                // Display an alert notification
                notyf.alert(message);
            }
            if (notificationType === "confirm") {
                // Display a success notification
                notyf.confirm(message);
            }
        };
        return service;
    })

    .factory("serviceList", function () {
        var service = {};
        var backUrl = "";
        <!--订单信息-->
        service.ListOrder = backUrl + "orders/getAllOrders.action";
        service.DeleteOrder = backUrl + "orders/delete.action";
        service.UpdateOrder = backUrl + "orders/update.action";
        service.AddOrder = backUrl + "orders/save.action";

        service.GetOrderById = backUrl + "orders/findAllById.action";

        <!--工序信息-->
        service.ListProcess = backUrl + "process/getAllByIsRootNode.action";
        <!--工序资源信息-->
        service.ListAssisantProcess = backUrl + "assisantprocess/getAllAssisantProcess.action";
        service.DeleteAssisantProcess = backUrl + "assisantprocess/delete.action";
        service.UpdateAssisantProcess = backUrl + "assisantprocess/update.action";
        service.AddAssisantProcess = backUrl + "assisantprocess/save.action";
        service.GetAssisantProcessById = backUrl + "assisantprocess/findAllById.action";
        <!--资源信息-->
        service.ListResource = backUrl + "resource/getAllResource.action";
        service.DeleteResource = backUrl + "resource/delete.action";
        service.UpdateResource = backUrl + "resource/update.action";
        service.AddResource = backUrl + "resource/save.action";
        service.GetResourceById = backUrl + "resource/findAllById.action";
        <!--资源类型信息-->
        service.ListTypeRecource = backUrl + "typerescource/getAllTypeRescource.action";
        service.DeleteTypeResource = backUrl + "typerescource/delete.action";
        service.UpdateTypeResource = backUrl + "typerescource/update.action";
        service.AddTypeResource = backUrl + "typerescource/save.action";
        service.GetTypeResourceById = backUrl + "typerescource/findAllById.action";
        <!--资源工组信息-->
        service.ListGroupResource = backUrl + "groupresource/getAllGroupResource.action";
        service.DeleteGroupResource = backUrl + "groupresource/delete.action";
        service.UpdateGroupResource = backUrl + "groupresource/update.action";
        service.AddGroupResource = backUrl + "groupresource/save.action";
        service.GetGroupResourceById = backUrl + "groupresource/findAllById.action";
        <!--资源工位信息-->
        service.ListSite = backUrl + "site/getAllSite.action";
        service.DeleteSite = backUrl + "site/delete.action";
        service.UpdateSite = backUrl + "site/update.action";
        service.AddSite = backUrl + "site/save.action";
        service.GetSiteById = backUrl + "site/findAllById.action";
        <!--资源班次信息-->
        service.ListShift = backUrl + "shift/getAllShift.action";
        service.DeleteShift = backUrl + "shift/delete.action";
        service.UpdateShift = backUrl + "shift/update.action";
        service.AddShift = backUrl + "shift/save.action";
        service.GetShiftById = backUrl + "shift/findAllById.action";
        <!--3D信息-->
        service.query3DState = backUrl + "3d/query3DState.action";
        service.config3D = backUrl + "3d/config3D.action";
        service.set3DLayout = backUrl + "3d/set3DLayout.action";
        service.get3DReport = backUrl + "3d/get3DReport.action";
        <!--排程信息-->
        service.beginSchedule = backUrl + "schedule/beginSchedule.action";
        service.ListSchedule = backUrl + "schedule/getAllSchedules.action";

        service.getLastScheduleInfo = backUrl + "FullCalendar/getLastScheduleInfo.action";
        service.CurInfo = backUrl + "orders/findAllByisFinishedAndDate.action";
        <!--工序信息接口-->
        service.isRootNode = backUrl + "process/getAllByIsRootNode.action";

        service.isChildNode = backUrl + "process/getAllById.action";

        service.isRootLevel = backUrl + "snapshot/getAllByByLevel.action";

        <!--高级调整分析-->
        service.AdjustProcess = backUrl + "ExceptionList/getAllAdjustProcessException.action";
        service.AdjustOrder = backUrl + "ExceptionList/getAllAdjustOrderException.action";

        service.getAllPlan = backUrl + "plan/getAllPlanBySnapshotId.action";
        return service;
    })

    .factory("validate", function () {
        var service = {};
        service.checkString = function (s) {
            var SRegexp = /^[A-Za-z]+$/;
            if (!SRegexp.test(s)) {

                return false;
            }
            return true;
        };

        service.checkNumber = function (n) {
            var NRegexp = /^[0-9]+.?[0-9]*$/;
            if (!NRegexp.test(n)) {

                return false;
            }
            return true;
        };

        service.checkLength = function (l) {
            if (l == "") {

                return false;
            }
            return true;
        };

        service.checkChinese = function (c) {
            var CRegexp = /[^\u0000-\u00FF]/;
            if (!CRegexp.test(c)) {

                return false;
            }
            return true;
        };

        return service;
    });
