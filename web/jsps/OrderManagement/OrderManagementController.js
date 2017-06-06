/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: '/jsps/OrderManagement/OrderManagement.jsp',
            controller: 'OrderManagementController'
        })
    }])

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList) {

        /*myHttpService.post(serviceList.ListOrder).then(function (response) {
         console.log(response);
         $scope.names = response.data;
         });*/

        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        $scope.GetOrder = function () {
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                //console.log("aaaaaaa");
                console.log(response);
                $scope.names = response.data;
            })
        };

        $scope.AddOrder = function () {
            var list = {id: "666", name: "cc"};
            myHttpService.post(serviceList.ListOrder, list).then(function (response) {
                //console.log("aaaaaaa");
                console.log(response);
                $scope.names = response.data;
            })
        };

        $scope.getDeleteId = function (id) {
            operateId = id;
        };

        $scope.DeleteOrder = function (name, id) {
            myHttpService.delete(serviceList.ListOrder + "/" + operateId).then(function (response) {
                console.log(response.status);
                $("#first").remove();
                //window.location.reload();//强制刷新
            })
        };

        //checkbox是否选中?
        $scope.isSelected = function (id) {
            return selectedCheckArray.indexOf(id) >= 0;
        };

        var updateSelected = function (action, id) {
            if (action == 'add' & selectedCheckArray.indexOf(id) == -1) {
                selectedCheckArray.push(id);
                console.log(id + "被选中1");
            }
            if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
                selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
                console.log(id + "被选中2");
            }
        };
        //用于监控点击事件，checkbox选择了就更新
        $scope.updateSelection = function ($event, id) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');
            updateSelected(action, id);
        };
    })