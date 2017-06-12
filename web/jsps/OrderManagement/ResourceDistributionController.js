/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ResourceDistributionController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceDistribution', {
            templateUrl: '/jsps/OrderManagement/ResourceDistribution.jsp',
            controller: 'ResourceDistributionController'
        })
    }])
    .controller('ResourceDistributionController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById('check').checked = false;
            $("input").val('');
            myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
                $scope.arr = response.data;
            });
        };

        //新增订单
        var addAssisantProcess = function () {
            var idVal = $("input[name='add-id']").val();
            var grpVal = $("input[name='add-grp']").val();
            var typeSiteVal = $("input[name='add-TypeSite']").val();
            var idSiteVal = $("input[name='add-IdSite']").val();
            var minResourceVal = $("input[name='add-minResource']").val();
            var maxResourceVal = $("input[name='add-maxResource']").val();
            var weightParallelVal = $("input[name='add-weightParallel']").val();
            var params = {};
            params.id = idVal;
            params.grp = grpVal;
            params.TypeSite = typeSiteVal;
            params.IdSite = idSiteVal;
            params.minResource = minResourceVal;
            params.maxResource = maxResourceVal;
            params.weightParallel = weightParallelVal;
            var data = JSON.stringify(params);
            alert(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddAssisantProcess, data).then(function successCallback(response) {
                console.log(response.status);
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
        $scope.deleteAssisantProcess = function () {
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
            params.grp = "";
            params.TypeSite = "";
            params.IdSite = "";
            params.minResource = "";
            params.maxResource = "";
            params.weightParallel = "";
            var data = JSON.stringify(params);
            alert(data);
            myHttpService.delete(serviceList.DeleteAssisantProcess, data).then(function successCallback(response) {
                console.log(response.status);
                reload();
            }, function errorCallback(response) {
                alert("请求失败！");
            })
        };

        //修改订单
        $scope.editAssisantProcess = function () {
            var params = {};
            //var idVal = operateId;
            params.id = operateId;
            params.grp = "";
            params.TypeSite = "";
            params.IdSite = "";
            params.minResource = "";
            params.maxResource = "";
            params.weightParallel = "";
            var data = JSON.stringify(params);
            alert(data);
            myHttpService.get(serviceList.ListAssisantProcess, data).then(function successCallback(response) {
                console.log(response);
                $scope.form = response.data;
            }, function errorCallback(response) {
                alert("请求失败！");
            })
        };
        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var grpVal = $("input[name='edit-grp']").val();
            var typeSiteVal = $("input[name='edit-TypeSite']").val();
            var idSiteVal = $("input[name='edit-IdSite']").val();
            var minResourceVal = $("input[name='edit-minResource']").val();
            var maxResourceVal = $("input[name='edit-maxResource']").val();
            var weightParallelVal = $("input[name='edit-weightParallel']").val();
            var params = {};
            params.id = idVal;
            params.grp = grpVal;
            params.TypeSite = typeSiteVal;
            params.IdSite = idSiteVal;
            params.minResource = minResourceVal;
            params.maxResource = maxResourceVal;
            params.weightParallel = weightParallelVal;
            var data = JSON.stringify(params);
            alert(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateAssisantProcess, data).then(function (response) {
                console.log(response.status);
                reload();
            }, function errorCallback(response) {
                alert("请求失败！");
            })
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                grp = $("input#add-grp").val();
            console.log(id + name);
            if (checkGrp(grp) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addAssisantProcess();
                });
                return true;
            } else {
                UIkit.modal.alert('请填写完整！');
                return false;
            }
        };
        var checkGrp = function (grp) {
            if (grp == "") {
                $("input#add-grp").addClass("uk-form-danger");
                return false;
            }
            $("input#add-grp").addClass("uk-form-success");
            return true;
        }
        var checkId = function (id) {
            if (id == "") {
                $("input#add-id").addClass("uk-form-danger");
                return false;
            }
            $("input#add-id").addClass("uk-form-success");
            return true;
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
        }

    })