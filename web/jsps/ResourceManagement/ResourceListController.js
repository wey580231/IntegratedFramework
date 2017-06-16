/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.ResourceListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceList', {
            templateUrl: '/jsps/ResourceManagement/ResourceList.jsp',
            controller: 'ResourceListController'
        })
    }])

    .controller('ResourceListController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListResource).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById("check").checked = false;
            $("input").val('');
            myHttpService.get(serviceList.ListResource).then(function (response) {
                $scope.arr = response.data;
            });
        }

        //新增订单
        var addResource = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var typeSiteVal = $("input[name='add-TypeSite']").val();
            var idSiteGroupResourceVal = $("input[name='add-idSiteGroupResource']").val();
            //var mobilityVal = $("input[name='add-mobility']").val();
            var nameShiftVal = $("input[name='add-nameShift']").val();
            var stateVal = $("input[name='add-state']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.typeSite = typeSiteVal;
            params.idSiteGroupResource = idSiteGroupResourceVal;
            //params.mobility = parseInt(mobilityVal);
            params.nameShift = nameShiftVal;
            params.state = stateVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddResource, data).then(function successCallback(response) {
                console.log(response.status);
                reload();
            })
            window.location.reload();
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
        $scope.deleteResource = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            params.name = "";
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.delete(serviceList.DeleteResource, data).then(function successCallback(response) {
                console.log(response.status);
                reload();
            });
           window.location.reload();
        }

        //修改订单
        $scope.editResource = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            params.name = "";
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.post(serviceList.ListResource, data).then(function successCallback(response) {
                console.log(response);
                $scope.form = response.data;
            });
        }
        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var typeSiteVal = $("input[name='edit-TypeSite']").val();
            var idSiteGroupResourceVal = $("input[name='edit-idSiteGroupResource']").val();
            //var mobilityVal = $("input[name='edit-mobility']").val();
            var nameShiftVal = $("input[name='edit-nameShift']").val();
            var stateVal = $("input[name='edit-state']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.typeSite = typeSiteVal;
            params.idSiteGroupResource = idSiteGroupResourceVal;
            //params.mobility = mobilityVal;
            params.nameShift = nameShiftVal;
            params.state = stateVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateResource, data).then(function (response) {
                console.log(response.status);
                reload();
            })
            window.location.reload();
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (checkName(name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addResource();
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


