/**
 * Created by zhaoqi on 2017/5/17.
 */
'use strict';
angular.module("IntegratedFramework.ResourceGroupController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceGroup', {
            templateUrl: 'jsps/ResourceManagement/ResourceGroup.jsp',
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
                //用强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();', 0.1);
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
            params.name = "";
            params.idSite0 = "";
            params.state = "";
            console.log(params);
            var data = JSON.stringify(params);
            myHttpService.delete(serviceList.DeleteGroupResource, data).then(function successCallback(response) {
                console.log(response.status);
                setTimeout('window.location.reload();', 0.1);
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
                editGroupResource();
            } else {
                alert("请选择一条需要修改条目！");
                $("input").val('');
            }

        };

        //修改订单
        $scope.editGroupResource = function () {
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
                    params.idSite0 = rows[row].cells[3].innerHTML;
                    params.state = rows[row].cells[4].innerHTML;
                    console.log(params);
                    arr.push(params);
                    $scope.form = arr;
                }
            }
        };

        $scope.update = function () {
            //var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var idSite0Val = $("input[name='edit-idSite0']").val();
            var stateVal = $("input[name='edit-state']").val();
            var params = {};
            params.id = operateId;
            params.name = nameVal;
            params.idSite0 = idSite0Val;
            params.state = stateVal;
            console.log(params);
            var data = JSON.stringify(params);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateGroupResource, data).then(function (response) {
                console.log(response.status);
                setTimeout('window.location.reload();', 0.1);
            });
        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (check(id, name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addGroupResource();
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