/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.ResourceGroupController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceGroup', {
            templateUrl: '/jsps/ResourceManagement/ResourceGroup.jsp',
            controller: 'ResourceGroupController'
        })
    }])

    .controller('ResourceGroupController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListGroupResource).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById("check").checked = false;
            $("input").val('');
            myHttpService.get(serviceList.ListShift).then(function (response) {
                $scope.arr = response.data;
            });
        }

        //新增订单
        var addGroupResource = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var idSite0Val = $("input[name='add-idSite0']").val();
            var stateVal = $("input[name='add-state']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.idSite0 = idSite0Val;
            params.state = stateVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddGroupResource, data).then(function successCallback(response) {
                alert(response.status);
                window.location.reload();
                reload();
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
        $scope.deleteGroupResource = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            console.log(params);
            var data = JSON.stringify(params);
            myHttpService.delete(serviceList.DeleteGroupResource, data).then(function successCallback(response) {
                console.log(response.status);
                window.location.reload();
                reload();
            });
        }

        //修改订单
        $scope.editGroupResource = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            console.log(params);
            var data = JSON.stringify(params);
            myHttpService.post(serviceList.ListGroupResource, data).then(function successCallback(response) {
                console.log(response);
                $scope.form = response.data;
            });
        }

        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var idSite0Val = $("input[name='edit-idSite0']").val();
            var stateVal = $("input[name='edit-state']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.idSite0 = idSite0Val;
            params.state = stateVal;
            console.log(params);
            var data = JSON.stringify(params);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateGroupResource, data).then(function (response) {
                console.log(response.status);
                window.location.reload();
                reload();
            });
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (checkName(name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addGroupResource();
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