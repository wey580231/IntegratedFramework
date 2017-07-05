/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.ResourceListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceList', {
            templateUrl: 'jsps/ResourceManagement/ResourceList.jsp',
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
                setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("添加失败！");
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

        //删除订单
        $scope.deleteResource = function () {
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            params.name = "";
            params.typeSite = "";
            params.idSiteGroupResource = "";
            //params.mobility = "";
            params.nameShift = "";
            params.state = "";
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.delete(serviceList.DeleteResource, data).then(function successCallback(response) {
                console.log(response.status);
                setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("删除失败！");
            });
        };

        var isCheck = function () {
            var count = 1;
            var a = document.getElementsByName("check");
            for (var i = 0; i < a.length; i++) {
                console.log(a[i].checked);
                if (a[i].checked) {
                    count++;
                }
            }
            if (count > 1) {
                return true;
            } else {
                return false;
            }
        };

        $scope.edit = function () {
            console.log(check());
            if (isCheck()) {
                //$('#edit').modal('show');
                editResource();
            } else {
                alert("请选择一条需要修改条目！");
                $("input").val('');
            }

        };

        //修改订单
        $scope.editResource = function () {
            var rows = document.getElementById("table_value").rows;
            var a = document.getElementsByName("check");

            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log(row);
                    var params = {};
                    var arr = [];
                    params.id = rows[row].cells[1].innerHTML;
                    params.name = rows[row].cells[2].innerHTML;
                    params.typeSite = rows[row].cells[3].innerHTML;
                    params.idSiteGroupResource = rows[row].cells[4].innerHTML;
                    params.nameShift = rows[row].cells[5].innerHTML;
                    params.state = rows[row].cells[6].innerHTML;
                    console.log(params);
                    arr.push(params);
                    $scope.form = arr;
                }
            }
        };
        $scope.update = function () {
            //var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var typeSiteVal = $("input[name='edit-TypeSite']").val();
            var idSiteGroupResourceVal = $("input[name='edit-idSiteGroupResource']").val();
            //var mobilityVal = $("input[name='edit-mobility']").val();
            var nameShiftVal = $("input[name='edit-nameShift']").val();
            var stateVal = $("input[name='edit-state']").val();
            var params = {};
            params.id = operateId;
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
                setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("更新失败！");
            });
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (check(id, name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addResource();
                });
                return true;
            } else {

                return false;
            }
        };
        var check = function (id, name) {
            if (name == "" && id == "") {
                $("input#add-id").addClass("uk-form-danger");
                $("input#add-name").addClass("uk-form-danger");
                UIkit.modal.alert('请填写完整！');
                return false;
            }
            $("input#add-id").addClass("uk-form-success");
            $("input#add-name").addClass("uk-form-success");
            return true;
        };

        var checkId = function (id) {
            var mytable = document.getElementById("table_value");
            var rows = document.getElementById("table_value").rows;
            for (var i = 0, row = mytable.rows.length; i < row; i++) {
                if (rows[i].cells[1].innerHTML == id) {
                    UIkit.modal.confirm('id已经存在，请重新填写！', function () {
                        $("input").val('');
                        $("input#add-id").removeClass("uk-form-success");
                        $("input#add-name").removeClass("uk-form-success");
                    });
                    return false;
                }
            }
            return true;
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
            $("input#add-id").removeClass("uk-form-success");
            $("input#add-name").removeClass("uk-form-success");
        }


    });


