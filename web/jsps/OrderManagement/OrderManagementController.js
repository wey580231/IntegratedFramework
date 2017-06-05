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
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        myHttpService.post(serviceList.ListOrder).then(function (response) {
            console.log(response);
            $scope.names = response.data;
        });

        $scope.Hide = function () {
            $("#add").hide();
            $("#edit").hide();
            $("#Cancel").hide();
        };
        /*$scope.GetOrder = function () {
         myHttpService.get(serviceList.ListOrder).then(function (response) {
         //console.log("aaaaaaa");
         console.log(response);
         $scope.names = response.data;

         })
         };*/

        $scope.AddOrder = function () {
            /*var idVal=$("#id").val();
             var nameVal=$("#name").val();
             var originVal=$("#origin").val();
             var typeVal=$("#type").val();
             var numberVal=$("#number").val();
             var priorityVal=$("#priority").val();
             var timeVal=$("#time").val();
             var earliestVal=$("#earliest").val();
             var latestVal=$("#latest").val();
             var params={"id":idVal,"name":nameVal,"origin":originVal,
             "type":typeVal,"number":numberVal,"priority":priorityVal,
             "time":timeVal,"earliest":earliestVal,"latest":latestVal};*/
            /*params["id"]=id;
             params["name"]=name;
             params["origin"]=origin;
             params["type"]=type;
             params["number"]=number;
             params["priority"]=priority;
             params["time"]=time;
             params["earliest"]=earliest;
             params["latest"]=latest;*/
            var idVal = $("input[name='id']").val();
            var nameVal = $("input[name='name']").val();
            var params = {"id": idVal, "name": nameVal};
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.post(serviceList.AddOrder, data).then(function (response) {
                console.log(response.status);
               // $scope.names = response.data;
                window.location.reload();//强制刷新
            })
        };

        var updateSelected = function (action, id) {
            operateId=id;
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

        $scope.DeleteOrder = function () {
           myHttpService.delete(serviceList.DeleteOrder + "?id=" +operateId).then(function (response) {
                console.log(serviceList.DeleteOrder + "?id=" +operateId);
                console.log(response.status);
                $("#first").remove();
                //window.location.reload();//强制刷新
            })
            //console.log(serviceList.DeleteOrder + "?id=" +operateId);
        };

        /*$scope.EditOrder = function () {
            var params;
            myHttpService.get(serviceList.ListOrder + "/" + operateId).then(function (response) {
                console.log(response.status);
                //$("#first").remove();
                //window.location.reload();//强制刷新
            })
        };*/

    })