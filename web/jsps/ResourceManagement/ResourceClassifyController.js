/**
 * Created by zhaoqi on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.ResourceClassifyController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceClassify', {
            templateUrl: '/jsps/ResourceManagement/ResourceClassify.jsp',
            controller: 'ResourceClassifyController'
        })
    }])

    .controller('ResourceClassifyController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;

        //加载页面时数据显示
        myHttpService.get(serviceList.ListTypeRecource).then(function (response) {
            console.log(response);
            $scope.arr = response.data;
        });


        //新增订单
        var addTypeRecource = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var ratioVal = $("input[name='add-ratio']").val();
            var attributeVal = $("input[name='add-attribute']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.ratio = ratioVal;
            params.attribute = attributeVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddTypeResource, data).then(function successCallback(response) {
                console.log(response.status);
                //用强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();',1);
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
        $scope.deleteTypeRecource = function () {
            var params = {};
            //var idVal = operateId;
            params.id = operateId;
            params.name = "";
            params.ratio = "";
            params.attribute = "";
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.delete(serviceList.DeleteTypeResource, data).then(function successCallback(response) {
                console.log(response.status);
                setTimeout('window.location.reload();',1);
                //setTimeout(reload(),3000);
            });
        }

        //修改订单
        $scope.editTypeRecource = function () {
            var rows = document.getElementById("table_value").rows;
            var a = document.getElementsByName("check");
            var table = document.getElementById("table_value");

            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log(row);
                    var params = {};
                    var arr = new Array();
                    params.id = rows[row].cells[1].innerHTML;
                    params.name = rows[row].cells[2].innerHTML;
                    params.ratio = rows[row].cells[3].innerHTML;
                    params.attribute = rows[row].cells[4].innerHTML;
                    console.log(params);
                    arr.push(params);
                    $scope.form = arr;
                }
            }
        };
        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var ratioVal = $("input[name='edit-ratio']").val();
            var attributeVal = $("input[name='edit-attribute']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.ratio = ratioVal;
            params.attribute = attributeVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateTypeResource, data).then(function (response) {
                console.log(response.status);
                setTimeout('window.location.reload();',1);
            })
        };
        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (check(id, name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addTypeRecource();
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

    })