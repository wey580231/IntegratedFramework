/**
 * Created by zhaoqi on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.ResourceListController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceList', {
            templateUrl: 'pages/ResourceManagement/ResourceList.html',
            controller: 'ResourceListController'
        })
    }])

    .controller('ResourceListController', function ($scope, $http, myHttpService, serviceList, validate, notification) {
        var editData = {};//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        myHttpService.get(serviceList.ListResource).then(function (response) {
            $scope.resourceList = response.data;
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
        var resourceAddValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.idSiteGroupResource = $("input[name='add-siteGroupResource']").val();
            params.nameShift = $("input[name='add-nameShift']").val();
            params.state = $("input[name='add-state']").val();
            addData = JSON.stringify(params);

            if (!validate.checkLength(params.name) || !validate.checkString(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }


            if (!validate.checkNumber(params.idSiteGroupResource) || !validate.checkLength(params.idSiteGroupResource)) {
                $("#add-idSiteGroupResource").removeClass("has-success");
                $("#add-idSiteGroupResource").addClass("has-error");
            } else {
                $("#add-idSiteGroupResource").removeClass("has-error");
                $("#add-idSiteGroupResource").addClass(" has-success");
            }

            if (!validate.checkNumber(params.nameShift) || !validate.checkLength(params.nameShift)) {
                $("#add-nameShift").removeClass("has-success");
                $("#add-nameShift").addClass("has-error");
            } else {
                $("#add-nameShift").removeClass("has-error");
                $("#add-nameShift").addClass(" has-success");
            }

            if (!validate.checkNumber(params.state) || !validate.checkLength(params.state)) {
                $("#add-state").removeClass("has-success");
                $("#add-state").addClass("has-error");
            } else {
                $("#add-state").removeClass("has-error");
                $("#add-state").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkString(params.name) &&
                validate.checkLength(params.idSiteGroupResource) && validate.checkNumber(params.idSiteGroupResource) && validate.checkLength(params.nameShift) && validate.checkNumber(params.nameShift) &&
                validate.checkLength(params.state) && validate.checkNumber(params.state)) {
                return true;
            } else {
                return false;
            }
        };


        //信息填写检验
        var resourceEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.idSiteGroupResource = $("input[name='edit-siteGroupResource']").val();
            params.nameShift = $("input[name='edit-nameShift']").val();
            params.state = $("input[name='edit-state']").val();
            editData = params;

            if (!validate.checkLength(params.name) || !validate.checkString(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }


            if (!validate.checkNumber(params.idSiteGroupResource) || !validate.checkLength(params.idSiteGroupResource)) {
                $("#edit-idSiteGroupResource").removeClass("has-success");
                $("#edit-idSiteGroupResource").addClass("has-error");
            } else {
                $("#edit-idSiteGroupResource").removeClass("has-error");
                $("#edit-idSiteGroupResource").addClass(" has-success");
            }

            if (!validate.checkNumber(params.nameShift) || !validate.checkLength(params.nameShift)) {
                $("#edit-nameShift").removeClass("has-success");
                $("#edit-nameShift").addClass("has-error");
            } else {
                $("#edit-nameShift").removeClass("has-error");
                $("#edit-nameShift").addClass(" has-success");
            }

            if (!validate.checkNumber(params.state) || !validate.checkLength(params.state)) {
                $("#edit-state").removeClass("has-success");
                $("#edit-state").addClass("has-error");
            } else {
                $("#edit-state").removeClass("has-error");
                $("#edit-state").addClass(" has-success");
            }

            if (validate.checkLength(params.name) && validate.checkString(params.name) &&
                validate.checkLength(params.idSiteGroupResource) && validate.checkNumber(params.idSiteGroupResource) && validate.checkLength(params.nameShift) && validate.checkNumber(params.nameShift) &&
                validate.checkLength(params.state) && validate.checkNumber(params.state)) {
                return true;
            } else {
                return false;
            }
        };

        //新增订单
        $scope.addResource = function () {
            if (resourceAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddResource, addData).then(function successCallback() {
                    //用强制刷新解决按钮不能连续响应
                    location.reload();
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
                        id_params.idR = idVal;
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
                myHttpService.post(serviceList.GetResourceById, idInfo).then(function successCallback(response) {
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

        $scope.editResource = function () {
            if (resourceEditValidate()) {
                $("#modal-edit").modal('hide');
                //用获取到的数据代替从数据库取到的数据
                edit_params.name = editData.name;
                edit_params.idSiteGroupResource = editData.idSiteGroupResource;
                edit_params.nameShift = editData.nameShift;
                edit_params.state = editData.state;
                var update_data = angular.toJson(edit_params);
                console.log(update_data);
                myHttpService.post(serviceList.UpdateResource, update_data).then(function successCallback() {
                    location.reload();
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "输入有误");
            }
        };


        //删除订单
        $scope.deleteResource = function () {
            if (getInfo()) {
                var params = {};
                params.id = idVal;
                var idInfo = JSON.stringify(params);
                console.log("删除的id信息");
                console.log(idInfo);
                myHttpService.delete(serviceList.DeleteResource, idInfo).then(function successCallback() {
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