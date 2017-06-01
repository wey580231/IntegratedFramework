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

        myHttpService.post(serviceList.ListOrder).then(function (response) {
            console.log(response);
            $scope.names = response.data;
        });

        /*var selectedCheckArray = [];    //选中的checkbox的id值集合

         $scope.AddOrder = function () {
         myHttpService.post(serviceList.ListOrder).then(function (response) {
         console.log("aaaaaaa");
         console.log(response);
         $scope.names = response.data;
         });
         }

         $scope.DeleteOrder = function () {
         myHttpService.delete(serviceList.ListOrder, '')
         .then(function (response) {
         $route.reload();
         });
         };

         var updateSelected = function (action, id) {
         if (action == 'add' & selectedCheckArray.indexOf(id) == -1) {
         selectedCheckArray.push(id);
         }
         if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
         selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
         }
         };

         //点击某个checkbox按钮，更新当前的状态
         $scope.updateSelection = function ($event, id) {
         var checkbox = $event.target;
         var action = (checkbox.checked ? 'add' : 'remove');
         updateSelected(action, id);
         };
         */

    })