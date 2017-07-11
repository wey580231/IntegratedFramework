/**
 * Created by zhaoqi on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.ResourceGroupController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceGroup', {
            templateUrl: 'pages/ResourceManagement/ResourceGroup.html',
            controller: 'ResourceGroupController'
        })
    }])

    .controller('ResourceGroupController', function ($scope, $http, myHttpService, serviceList, validate, notification) {
        var editData = {};//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        myHttpService.get(serviceList.ListGroupResource).then(function (response) {
            $scope.groupList = response.data;
        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            if ($last) {
                //Enable iCheck plugin for checkboxes
                //iCheck for checkbox and radio inputs
                $('.mailbox-messages input[type="checkbox"]').iCheck({
                    checkboxClass: 'icheckbox_flat-blue',
                    radioClass: 'iradio_flat-blue'
                });

                //Enable check and uncheck all functionality
                $(".checkbox-toggle").click(function () {
                    var clicks = $(this).data('clicks');
                    if (clicks) {
                        //Uncheck all checkboxes
                        $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
                        $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
                    } else {
                        //Check all checkboxes
                        $(".mailbox-messages input[type='checkbox']").iCheck("check");
                        $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
                    }
                    $(this).data("clicks", !clicks);
                });
            }
        };

        //信息填写检验
        var groupAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.state = parseInt($("input[name='add-state']").val());
            params.idSite0 = $("input[name='add-idSite0']").val();
            addData = JSON.stringify(params);
            if (!validate.checkLength(params.name) || !validate.checkString(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.state) || !validate.checkLength(params.state)) {
                $("#add-state").removeClass("has-success");
                $("#add-state").addClass("has-error");
            } else {
                $("#add-state").removeClass("has-error");
                $("#add-state").addClass(" has-success");
            }

            if (!validate.checkNumber(params.idSite0) || !validate.checkLength(params.idSite0)) {
                $("#add-idSite0").removeClass("has-success");
                $("#add-idSite0").addClass("has-error");
            } else {
                $("#add-idSite0").removeClass("has-error");
                $("#add-idSite0").addClass(" has-success");
            }

            if (validate.checkLength(params.state) && validate.checkNumber(params.state) && validate.checkString(params.name) && validate.checkLength(params.name) &&
                validate.checkLength(params.idSite0) && validate.checkNumber(params.idSite0)) {
                return true;
            } else {

                return false;
            }
        };


        //信息填写检验
        var groupEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.state = parseInt($("input[name='edit-state']").val());
            params.idSite0 = $("input[name='edit-idSite0']").val();
            editData = params;
            if (!validate.checkLength(params.name) || !validate.checkString(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.state) || !validate.checkLength(params.state)) {
                $("#edit-state").removeClass("has-success");
                $("#edit-state").addClass("has-error");
            } else {
                $("#add-state").removeClass("has-error");
                $("#add-state").addClass(" has-success");
            }

            if (!validate.checkNumber(params.idSite0) || !validate.checkLength(params.idSite0)) {
                $("#edit-idSite0").removeClass("has-success");
                $("#edit-idSite0").addClass("has-error");
            } else {
                $("#edit-idSite0").removeClass("has-error");
                $("#edit-idSite0").addClass(" has-success");
            }

            if (validate.checkLength(params.state) && validate.checkNumber(params.state) && validate.checkString(params.name) && validate.checkLength(params.name) &&
                validate.checkLength(params.idSite0) && validate.checkNumber(params.idSite0)) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addGroup = function () {
            if (groupAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddGroupResource, addData).then(function successCallback() {
                    //用强制刷新解决按钮不能连续响应
                    //location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
            //addData.splice(0, addData.length);
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
                myHttpService.post(serviceList.GetGroupResourceById, idInfo).then(function successCallback(response) {
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

        $scope.editGroup = function () {
            if (groupEditValidate()) {
                $("#modal-edit").modal('hide');
                //用获取到的数据代替从数据库取到的数据
                edit_params.name = editData.name;
                edit_params.state = editData.state;
                edit_params.idSite0 = editData.idSite0;
                var update_data = angular.toJson(edit_params);
                myHttpService.post(serviceList.UpdateGroupResource, update_data).then(function successCallback() {
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
        };


        //删除订单
        $scope.deleteGroup = function () {
            if (getInfo()) {
                var params = {};
                params.id = idVal;
                var idInfo = JSON.stringify(params);
                console.log("删除的id信息");
                console.log(idInfo);
                myHttpService.delete(serviceList.DeleteGroupResource, idInfo).then(function successCallback() {
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                });
            }
        };

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        }
    });