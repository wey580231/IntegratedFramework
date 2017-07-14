/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: 'jsps/OrderManagement/OrderManagement.jsp',
            controller: 'OrderManagementController'
        })
    }])

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList, validate) {
        var operateId;
        var name;
        var arr = [];

        //加载页面时数据显示
        myHttpService.get(serviceList.ListOrder).then(function (response) {
            console.log(response.data);
            $scope.arr = response.data;
        });


        $('#addButton').click(function () {
            $("input").val('');
            $("input#add-name").removeClass("uk-form-success");
            $("input#add-origin").removeClass("uk-form-success");
            $("input#add-priority").removeClass("uk-form-success");
            $("input#add-quantity").removeClass("uk-form-success");
            $("input#add-name").removeClass("uk-form-danger");
            $("input#add-origin").removeClass("uk-form-danger");
            $("input#add-priority").removeClass("uk-form-danger");
            $("input#add-quantity").removeClass("uk-form-danger");
        });

        //新增订单
        var addOrder = function () {
            var nameVal = $("input#add-name").val();
            var originVal = $("input[name='add-origin']").val();
            var quantityVal = $("input[name='add-quantity']").val();
            var priorityVal = $("input[name='add-priority']").val();
            var t0Val = $("input[name='add-t0']").val();
            var t1Val = $("input[name='add-t1']").val();
            var t2Val = $("input[name='add-t2']").val();
            var params = {};

            params.name = nameVal;
            params.origin = originVal;
            params.quantity = quantityVal;
            params.priority = priorityVal;
            params.t1 = Date.parse(t1Val);
            params.t2 = Date.parse(t2Val);
            params.t0 = Date.parse(t0Val);
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddOrder, data).then(function successCallback(response) {
                //用强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("添加失败！");
            })
        };


        //删除订单
        $scope.deleteOrder = function () {
            getInfo();
            myHttpService.delete(serviceList.DeleteOrder, arr).then(function successCallback(response) {
                console.log(response.status);
                //强制刷新解决按钮不能连续响应
                //setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("删除失败！");
            });
        };

        //修改订单
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
            if (isCheck()) {
                getInfo();
                $scope.form = arr;
            } else {
                alert("请选择一条需要修改条目！");
                $("input").val('');
            }
        };

        var getInfo = function () {
            var rows = document.getElementById("table_value").rows;
            var a = document.getElementsByName("check");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log(row);
                    var params = {};
                    params.id = rows[row].cells[1].innerHTML;
                    params.name = rows[row].cells[2].innerHTML;
                    params.origin = rows[row].cells[3].innerHTML;
                    params.quantity = rows[row].cells[4].innerHTML;
                    params.priority = rows[row].cells[5].innerHTML;
                    params.t0 = rows[row].cells[6].innerHTML;
                    params.t1 = rows[row].cells[7].innerHTML;
                    params.t2 = rows[row].cells[8].innerHTML;
                    /*var a = document.getElementsByName("check");
                    var row=1;
                    for (var i = 0; i < a.length; i++) {
                        if (a[i].checked) {
                            //var row = a[i].parentElement.parentElement.rowIndex;
                            var params = {};
                            params.id =$("#table_value").find("tr").eq(row).find("td").eq(1).html();
                            params.name =$('#table_value').find("tr").eq(row).find("td").eq(2).html();
                            params.type = $('#table_value').find("tr").eq(row).find("td").eq(5).html();
                            params.origin = $('#table_value').find("tr").eq(row).find("td").eq(3).html();
                            params.quantity = $('#table_value').find("tr").eq(row).find("td").eq(4).html();
                            params.t0 = $('#table_value').find("tr").eq(row).find("td").eq(6).html();
                            params.t2 = $('#table_value').find("tr").eq(row).find("td").eq(7).html();
                            table.push(params);
                            console.log(table);
                            $scope.table=table;
                        }
                        row++;
                    }*/
                    console.log(params);
                    arr.push(params);
                    console.log(arr);
                }
            }
        }

        $scope.update = function () {
            var nameVal = $("input[name='edit-name']").val();
            var originVal = $("input[name='edit-origin']").val();
            var quantityVal = $("input[name='edit-quantity']").val();
            var priorityVal = $("input[name='edit-priority']").val();
            var t0Val = $("input[name='edit-t0']").val();
            var t1Val = $("input[name='edit-t1']").val();
            var t2Val = $("input[name='edit-t2']").val();
            var params = {};
            params.id = operateId;
            params.name = nameVal;
            params.origin = originVal;
            params.priority = priorityVal;
            params.t0 = Date.parse(t0Val);
            params.t1 = Date.parse(t1Val);
            params.t2 = Date.parse(t2Val);
            params.quantity = quantityVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateOrder, data).then(function successCallback(response) {
                setTimeout('window.location.reload();', 0.1);
            }, function errorCallback(response) {
                alert("更新失败！");
            });
        };

        //信息填写检验
        $scope.orderValidate = function () {
            var name = $("input#add-name").val();
            var origin = $("input#add-origin").val();
            var priority = $("input#add-priority").val();
            var t1 = $("input#add-t1").val();
            var t0 = $("input#add-t0").val();
            var t2 = $("input#add-t2").val();
            if (!validate.checkString(origin)) {
                $("input#add-origin").addClass("uk-form-danger");
            }

            if (!validate.checkNumber(priority)) {
                $("input#add-priority").addClass("uk-form-danger");
            }

            if (!validate.checkLength(name)) {
                $("input#add-name").addClass("uk-form-danger");
            }

            if (validate.checkLength(name) && validate.checkString(origin) && validate.checkNumber(priority)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addOrder();
                });
                return true;
            } else {
                alert("输入有误！");
                return false;
            }
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
            $("input").removeClass("uk-form-success");
            /*$("input#add-name").removeClass("uk-form-success");
             $("input#add-origin").removeClass("uk-form-success");
             $("input#add-priority").removeClass("uk-form-success");
             $("input#add-quantity").removeClass("uk-form-success");
             $("input#add-name").removeClass("uk-form-danger");
             $("input#add-origin").removeClass("uk-form-danger");
             $("input#add-priority").removeClass("uk-form-danger");
             $("input#add-quantity").removeClass("uk-form-danger");*/
        }

    });