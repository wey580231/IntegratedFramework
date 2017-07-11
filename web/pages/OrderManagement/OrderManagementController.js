/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.OrderManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/OrderManagement', {
            templateUrl: 'pages/OrderManagement/OrderManagement.html',
            controller: 'OrderManagementController'
        })
    }])

    .controller('OrderManagementController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService) {
        var editData = [];//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        myHttpService.get(serviceList.ListOrder).then(function (response) {
            $scope.orderList = response.data;
        });

        //Date picker
        $('#modal-add-t0-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#modal-add-t2-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#modal-edit-t0-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });
        $('#modal-edit-t2-datepicker').datepicker({
            format: "yyyy/mm/dd",
            todayHighlight: true,
            autoclose: true
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //信息填写检验
        var orderAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.quantity = parseInt($("input[name='add-quantity']").val());
            params.origin = $("input[name='add-origin']").val();
            var t0 = $("input[id='modal-add-t0-datepicker']").val();
            var t2 = $("input[id='modal-add-t2-datepicker']").val();
            params.t0 = Date.parse($("input[id='modal-add-t0-datepicker']").val());
            params.t2 = Date.parse($("input[id='modal-add-t2-datepicker']").val());
            addData = JSON.stringify(params);
            if (!validate.checkChinese(params.origin) || !validate.checkLength(params.origin)) {
                $("#add-origin").removeClass(" has-success");
                $("#add-origin").addClass(" has-error");
            } else {
                $("#add-origin").removeClass(" has-error");
                $("#add-origin").addClass(" has-success");
            }


            if (!validate.checkLength(params.name) || validate.checkNumber(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.quantity) || !validate.checkLength(params.quantity)) {
                $("#add-quantity").removeClass("has-success");
                $("#add-quantity").addClass("has-error");
            } else {
                $("#add-quantity").removeClass("has-error");
                $("#add-quantity").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#add-t0").removeClass("has-success");
                $("#add-t0").addClass("has-error");
            } else {
                $("#add-t0").removeClass("has-error");
                $("#add-t0").addClass(" has-success");
            }
            if (!validate.checkLength(t2)) {
                $("#add-t2").removeClass("has-success");
                $("#add-t2").addClass("has-error");
            } else {
                $("#add-t2").removeClass("has-error");
                $("#add-t2").addClass(" has-success");
            }

            if (validate.checkChinese(params.origin) && validate.checkLength(params.origin) &&
                validate.checkLength(params.name) && !validate.checkNumber(params.name) && validate.checkLength(params.quantity) && validate.checkNumber(params.quantity) &&
                validate.checkLength(params.t0) && validate.checkLength(params.t2) && validate.checkLength(params.t1)) {
                return true;
            } else {

                return false;
            }
        };


        //信息填写检验
        var orderEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.quantity = parseInt($("input[name='edit-quantity']").val());
            params.origin = $("input[name='edit-origin']").val();
            var t0 = $("input[id='modal-edit-t0-datepicker']").val();
            var t2 = $("input[id='modal-edit-t2-datepicker']").val();
            params.t0 = Date.parse($("input[id='modal-edit-t0-datepicker']").val());
            params.t2 = Date.parse($("input[id='modal-edit-t2-datepicker']").val());
            editData = params;
            if (!validate.checkChinese(params.origin) || !validate.checkLength(params.origin)) {
                $("#edit-origin").removeClass(" has-success");
                $("#edit-origin").addClass(" has-error");
            } else {
                $("#edit-origin").removeClass(" has-error");
                $("#edit-origin").addClass(" has-success");
            }


            if (!validate.checkLength(params.name) || validate.checkNumber(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.quantity) || !validate.checkLength(params.quantity)) {
                $("#edit-quantity").removeClass("has-success");
                $("#edit-quantity").addClass("has-error");
            } else {
                $("#edit-quantity").removeClass("has-error");
                $("#edit-quantity").addClass(" has-success");
            }

            if (!validate.checkLength(t0)) {
                $("#edit-t0").removeClass("has-success");
                $("#edit-t0").addClass("has-error");
            } else {
                $("#edit-t0").removeClass("has-error");
                $("#edit-t0").addClass(" has-success");
            }
            if (!validate.checkLength(t2)) {
                $("#edit-t2").removeClass("has-success");
                $("#edit-t2").addClass("has-error");
            } else {
                $("#edit-t2").removeClass("has-error");
                $("#edit-t2").addClass(" has-success");
            }

            if (validate.checkChinese(params.origin) && validate.checkLength(params.origin) &&
                validate.checkLength(params.name) && !validate.checkNumber(params.name) && validate.checkLength(params.quantity) && validate.checkNumber(params.quantity) &&
                validate.checkLength(params.t0) && validate.checkLength(params.t2) && validate.checkLength(params.t1)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addOrder = function () {
            if (orderAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddOrder, addData).then(function successCallback() {
                    //用强制刷新解决按钮不能连续响应
                    //location.reload(true);
                    setTimeout('window.location.reload();', 0.1);
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
        };

        //获得表单信息

        var isCheck = function () {
            var count = 1;
            var a = document.getElementsByName("check");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    count++;
                }
            }
            if (count == 1 || count > 2) {
                notification.sendNotification("alert", "请重新选择！");
                return false;
            } else {
                return true;
            }
        };

        var getInfo = function () {
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
            if (isCheck()) {
                var a = document.getElementsByName("check");
                var row = 1;
                for (var i = 0; i < a.length; i++) {
                    if (a[i].checked) {
                        idVal = $("#table_value").find("tr").eq(row).find("td").eq(1).html();
                        id_params.id = idVal;
                    }
                    row++;
                }
                console.log("id信息");
                console.log(id_params);
                return true;
            } else {

                return false;
            }
        };

        //修改订单
        $scope.update = function () {
            if (getInfo()) {
                $("#modal-edit").modal('show');
                var idInfo = JSON.stringify(id_params);
                console.log("&&&&&&&&&&&");
                console.log(id_params);
                myHttpService.post(serviceList.GetOrderById, idInfo).then(function successCallback(response) {
                    var editList = [];//保存从数据库获取的需要修改的数据
                    editList.push(response.data);
                    edit_params = response.data;
                    $scope.editList = editList;
                }, function errorCallback(response) {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                $("#modal-edit").modal('hide');
            }
        };

        $scope.editOrder = function () {
            if (orderEditValidate()) {
                $("#modal-edit").modal('hide');
                //用获取到的数据代替从数据库取到的数据
                edit_params.name = editData.name;
                edit_params.quantity = editData.quantity;
                edit_params.origin = editData.origin;
                edit_params.t0 = editData.t0;
                edit_params.t2 = editData.t2;
                var update_data = angular.toJson(edit_params);
                myHttpService.post(serviceList.UpdateOrder, update_data).then(function successCallback() {
                    //location.reload(true);
                    setTimeout('window.location.reload();', 0.1);
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
        };


        //删除订单
        $scope.deleteOrder = function () {
            if (getInfo()) {
                var idInfo = JSON.stringify(id_params);
                console.log("删除的id信息");
                console.log(idInfo);
                myHttpService.delete(serviceList.DeleteOrder, idInfo).then(function successCallback() {
                    //location.reload(true);
                    setTimeout('window.location.reload();', 0.1);
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                });
            }
        };

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };

        function clicked(id) {
            var o, i;
            o = document.getElementById(id).rows;//表格所有行
            for (i = 0; i < o.length; i++) {
                o[i].ondblclick = function () { //设置事件
                    var oo;
                    oo = this.cells[1]; //取得第二列对象
                    alert(oo.innerHTML);
                }
            }
        }

        window.onload = function () {
            clicked('table_value'); //设置表格事件
        }
        /*    $scope.getInfo = function () {
         $("#modal-info").modal('show');
         var trs = document.getElementsByTagName('tr');
         for (var i = 1; i < trs.length; i++) {
         trs[i].onclick = function () {
         var id=this.children[1].innerHTML;
         myHttpService.post(serviceList.GetOrderById, id).then(function successCallback(response) {
         $scope.infoList = response.data;
         }, function errorCallback() {
         notification.sendNotification("alert", "请求失败");
         })
         break;
         }
         }
         }*/
    });