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
        var name;
        var date;
        //加载页面时数据显示
        myHttpService.get(serviceList.ListOrder).then(function (response) {
            console.log(response.data);
            $scope.arr = response.data;
            var obj = response.data;
            //var data = eval('(' + obj + ')');
            /*name=obj[0].id;
            console.log(name);
            console.log(parseInt(name));
            var date1 = new Date();
            var date2 = new Date(obj[0].t2);
            date=(date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
            console.log(date);*/
        });

        //重新加载页面信息
        var reload = function () {
            //取消checkbox选中状态
            document.getElementById("check").checked = false;
            $("input").val('');
            $("input#add-id").removeClass("uk-form-success");
            $("input#add-name").removeClass("uk-form-success");
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                $scope.arr = response.data;
            });
        }

        //新增订单
        var addOrder = function () {
            var idVal = $("input[name='add-id']").val();
            var nameVal = $("input[name='add-name']").val();
            var originVal = $("input[name='add-origin']").val();
            //var idProductVal = $("input[name='add-idProduct']").val();
            var quantityVal = $("input[name='add-quantity']").val();
            var priorityVal = $("input[name='add-priority']").val();
            var t0Val = $("input[name='add-t0']").val();
            var t1Val = $("input[name='add-t1']").val();
            var t2Val = $("input[name='add-t2']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.origin = originVal;
            // params.idProduct = parseInt(idProductVal);
            params.quantity = quantityVal;
            params.priority = priorityVal;
            params.t1 = Date.parse(t1Val);
            params.t2 = Date.parse(t2Val);
            params.t0 = Date.parse(t0Val);
            /*document.write(params.t0);*/
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddOrder, data).then(function successCallback(response) {
                console.log(response.data);
                //用强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();',1);
                //setTimeout(reload(),3000);
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
        $scope.deleteOrder = function () {
            /*console.log(selectedCheckArray);
             var Array = [];
             var deleteArray = [];
             for (var i = 0; i < selectedCheckArray.length; i++) {
             var params = {};
             var idVal = selectedCheckArray[i];
             params.id = idVal;
             var data = JSON.stringify(params);
             Array.push(params);
             }
             var data = JSON.stringify(Array);
             console.log(data);
             myHttpService.delete(serviceList.DeleteOrder, data).then(function successCallback(response) {
             console.log(response.status);
             window.location.reload();
             reload();
             }, function errorCallback(response) {
             alert("请求失败！");
             });*/
            var params = {};
            var idVal = operateId;
            params.id = idVal;
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.delete(serviceList.DeleteOrder, data).then(function successCallback(response) {
                console.log(response.status);
               //强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();',1);
                //setTimeout(reload(),3000);
            });
        }

        //修改订单
        $scope.editOrder = function () {
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
                    params.origin = rows[row].cells[3].innerHTML;
                    params.quantity = rows[row].cells[4].innerHTML;
                    params.priority = rows[row].cells[5].innerHTML;
                    params.t0 = rows[row].cells[6].innerHTML;
                    params.t1 = rows[row].cells[7].innerHTML;
                    params.t2 = rows[row].cells[8].innerHTML;
                    console.log(params);
                    arr.push(params);
                    $scope.form = arr;
                }
            }
        };

        $scope.update = function () {
            var idVal = $("input[name='edit-id']").val();
            var nameVal = $("input[name='edit-name']").val();
            var originVal = $("input[name='edit-origin']").val();
            //var idProductVal = $("input[name='edit-idProduct']").val();
            var quantityVal = $("input[name='edit-quantity']").val();
            var priorityVal = $("input[name='edit-priority']").val();
            var t0Val = $("input[name='edit-t0']").val();
            var t1Val = $("input[name='edit-t1']").val();
            var t2Val = $("input[name='edit-t2']").val();
            var params = {};
            params.id = idVal;
            params.name = nameVal;
            params.origin = originVal;
            params.priority = priorityVal;
            params.t0 = t0Val;
            params.t1 = t1Val;
            params.t2 = t2Val;
            params.quantity = quantityVal;
            //params.idProduct = idProductVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateOrder, data).then(function (response) {
                console.log(response.status);
                setTimeout('window.location.reload();',1);
                //setTimeout(reload(),3000);
            });
        };

        //信息填写检验
        $scope.orderValidate = function () {
            var id = $("input#add-id").val(),
                name = $("input#add-name").val();
            console.log(id + name);
            if (check(id, name) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addOrder();
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