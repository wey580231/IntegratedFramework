/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.WorkListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/WorkList', {
            templateUrl: '/jsps/ResourceManagement/WorkList.jsp',
            controller: 'WorkListController'
        })
    }])
    .controller('WorkListController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListShift).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById('check').checked = false;
            $("input").val('');
            myHttpService.get(serviceList.ListShift).then(function (response) {
                $scope.arr = response.data;
            });
        }

        //新增订单
        var addShift = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var typeVal = $("input[name='add-type']").val();
            var slotVal = $("input[name='add-Slot']").val();
            var extraVal = $("input[name='add-extra']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.type = typeVal;
            params.Slot = slotVal;
            params.extra = extraVal;
            var data = JSON.stringify(params);
            alert(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddShift, data).then(function successCallback(response) {
                alert(response.status);
                reload();
            }, function errorCallback(response) {
                alert("请求错误！");
            })
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

        //删除订单
        $scope.deleteShift = function () {
            /*console.log(selectedCheckArray);
             var Array = [];
             var deleteArray = [];
             for (var i = 0; i < selectedCheckArray.length; i++) {
             var params = {};
             var idVal = selectedCheckArray[i];
             params.id = idVal;
             params.name = "";
             params.origin = "";
             params.priority = "";
             params.advance = "";
             params.delay = "";
             params.quantity = "";
             params.t0 = "";
             params.ord = "";
             //var data = JSON.stringify(params);
             Array.push(params);
             }
             var data = JSON.stringify(Array);
             console.log(data);
             myHttpService.delete(serviceList.DeleteOrder, data).then(function successCallback(response) {
             console.log(response.status);
             reload();
             }, function errorCallback(response) {
             alert("请求失败！");
             });*/
            var params = {};
            //var idVal = operateId;
            params.id = operateId;
            params.name = "";
            params.type = "";
            params.Slot = "";
            params.extra = "";
            var data = JSON.stringify(params);
            alert(data);
            myHttpService.delete(serviceList.DeleteShift, data).then(function successCallback(response) {
                console.log(response.status);
                reload();
            }, function errorCallback(response) {
                alert("请求失败！");
            });
        }

        //修改订单
        $scope.editShift = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            params.name = "";
            params.type = "";
            params.Slot = "";
            params.extra = "";
            var data = JSON.stringify(params);
            alert(data);
            myHttpService.get(serviceList.ListShift, data).then(function successCallback(response) {
                console.log(response);
                $scope.form = response.data;
            }, function errorCallback(response) {
                alert("请求失败！");
            });
        }
        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var typeVal = $("input[name='edit-type']").val();
            var slotVal = $("input[name='edit-Slot']").val();
            var extraVal = $("input[name='edit-extra']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.type = typeVal;
            params.Slot = slotVal;
            params.extra = extraVal;
            var data = JSON.stringify(params);
            alert(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateShift, data).then(function (response) {
                console.log(response.status);
                reload();
            })
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (checkName(name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addShift();
                });
                return true;
            } else {
                UIkit.modal.alert('请填写完整！');
                return false;
            }
        };
        var checkName = function (name) {
            if (name == "") {
                $("input#add-name").addClass("uk-form-danger");
                return false;
            }
            $("input#add-name").addClass("uk-form-success");
            return true;
        }
        var checkId = function (id) {
            if (id == "") {
                $("input#add-id").addClass("uk-form-danger");
                return false;
            }
            $("input#add-id").addClass("uk-form-success");
            return true;
        }

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
        }

    })