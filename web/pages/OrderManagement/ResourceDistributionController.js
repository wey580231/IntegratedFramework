/**
 * Created by zhaoqi on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.ResourceDistributionController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceDistribution', {
            templateUrl: 'pages/OrderManagement/ResourceDistribution.html',
            controller: 'ResourceDistributionController'
        })
    }])

    .controller('ResourceDistributionController', function ($scope, $http, myHttpService, serviceList, validate, notification, dispatchApsService, confirm) {

        var editData = {};//保存新增和修改的信息
        var addData = [];
        var edit_params = {};//获取需改后的数据
        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        layer.load(0);

        $(function () {
            myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
                $scope.resourcedisList = response.data;

                hideLoadingPage();

                loadRightFloatMenu();
            });
        });

        //确认下发APS
        function confirmDispatchAps() {
            layer.load();
            setTimeout(function () {
                notification.sendNotification("confirm", "已下发");
                hideLoadingPage();
            }, 2000);
        }

        //取消下发APS
        function resetDispatchAps() {
            notification.sendNotification("alert", "取消下发");
        }

        //将选中记录下发APS
        $scope.dispatchRecord = function () {
            dispatchApsService.dispatchAps(confirmDispatchAps, resetDispatchAps);
        };

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
        var resourcedisAddValidate = function () {
            var params = {};
            params.grp = $("input[name='add-grp']").val();
            params.idSite = $("input[name='add-idSite']").val();
            params.minResource = $("input[name='add-minResource']").val();
            params.maxResource = $("input[name='add-maxResource']").val();
            params.weightParallel = $("input[name='add-weightParallel']").val();
            addData = JSON.stringify(params);
            if (!validate.checkLength(params.grp)) {
                $("#add-grp").removeClass("has-success");
                $("#add-grp").addClass("has-error");
            } else {
                $("#add-grp").removeClass("has-error");
                $("#add-grp").addClass(" has-success");
            }

            if ( !validate.checkLength(params.idSite)) {
                $("#add-idSite").removeClass("has-success");
                $("#add-idSite").addClass("has-error");
            } else {
                $("#add-idSite").removeClass("has-error");
                $("#add-idSite").addClass(" has-success");
            }
            if (!validate.checkNumber(params.maxResource) || !validate.checkLength(params.maxResource)) {
                $("#add-maxResource").removeClass("has-success");
                $("#add-maxResource").addClass("has-error");
            } else {
                $("#add-maxResource").removeClass("has-error");
                $("#add-maxResource").addClass(" has-success");
            }
            if (!validate.checkNumber(params.minResource) || !validate.checkLength(params.minResource)) {
                $("#add-minResource").removeClass("has-success");
                $("#add-minResource").addClass("has-error");
            } else {
                $("#add-minResource").removeClass("has-error");
                $("#add-minResource").addClass(" has-success");
            }
            if (!validate.checkNumber(params.weightParallel) || !validate.checkLength(params.weightParallel)) {
                $("#add-weightParallel").removeClass("has-success");
                $("#add-weightParallel").addClass("has-error");
            } else {
                $("#add-weightParallel").removeClass("has-error");
                $("#add-weightParallel").addClass(" has-success");
            }


            if (validate.checkLength(params.grp)&&
                validate.checkLength(params.idSite) && validate.checkLength(params.maxResource) && validate.checkNumber(params.maxResource) &&
                validate.checkLength(params.minResource) && validate.checkNumber(params.minResource) && validate.checkLength(params.weightParallel) && validate.checkNumber(params.weightParallel)) {
                return true;
            } else {

                return false;
            }
        };


        //信息填写检验
        var resourcedisEditValidate = function () {
            var params = {};
            params.grp = $("input[name='edit-grp']").val();
            params.idSite = $("input[name='edit-idSite']").val();
            params.minResource = $("input[name='edit-minResource']").val();
            params.maxResource = $("input[name='edit-maxResource']").val();
    /*        params.weightParallel = $("input[name='edit-weightParallel']").val();*/
            editData = params;
            if (!validate.checkLength(params.grp)) {
                $("#edit-grp").removeClass("has-success");
                $("#edit-grp").addClass("has-error");
            } else {
                $("#edit-grp").removeClass("has-error");
                $("#edit-grp").addClass(" has-success");
            }

            if (!validate.checkLength(params.idSite)) {
                $("#edit-idSite").removeClass("has-success");
                $("#edit-idSite").addClass("has-error");
            } else {
                $("#edit-idSite").removeClass("has-error");
                $("#edit-idSite").addClass(" has-success");
            }
            if (!validate.checkNumber(params.maxResource) || !validate.checkLength(params.maxResource)) {
                $("#edit-maxResource").removeClass("has-success");
                $("#edit-maxResource").addClass("has-error");
            } else {
                $("#edit-maxResource").removeClass("has-error");
                $("#edit-maxResource").addClass(" has-success");
            }
            if (!validate.checkNumber(params.minResource) || !validate.checkLength(params.minResource)) {
                $("#edit-minResource").removeClass("has-success");
                $("#edit-minResource").addClass("has-error");
            } else {
                $("#edit-minResource").removeClass("has-error");
                $("#edit-minResource").addClass(" has-success");
            }
        /*    if (!validate.checkNumber(params.weightParallel) || !validate.checkLength(params.weightParallel)) {
                $("#edit-weightParallel").removeClass("has-success");
                $("#edit-weightParallel").addClass("has-error");
            } else {
                $("#edit-weightParallel").removeClass("has-error");
                $("#edit-weightParallel").addClass(" has-success");
            }*/


            if (validate.checkLength(params.grp) &&
                validate.checkLength(params.idSite)&& validate.checkLength(params.maxResource) && validate.checkNumber(params.maxResource) &&
                validate.checkLength(params.minResource) && validate.checkNumber(params.minResource) /*&& validate.checkLength(params.weightParallel) && validate.checkNumber(params.weightParallel)*/) {
                return true;
            } else {

                return false;
            }
        };

        //新增订单
        $scope.addAssisantProcess = function () {
            if (resourcedisAddValidate()) {
                $("#modal-add").modal('hide');
                myHttpService.post(serviceList.AddAssisantProcess, addData).then(function successCallback() {
                    myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
                        $scope.resourcedisList = response.data;
                        notification.sendNotification("confirm", "添加成功");
                    })
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                })
            } else {
                notification.sendNotification("alert", "参数错误");
            }
            //addData.splice(0, addData.length);
        };

        //获得表单信息
        var getInfo = function () {
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
            if (hasCheckRows()) {
                var a = document.getElementsByName("check");
                var row = 1;
                for (var i = 0; i < a.length; i++) {
                    if (a[i].checked) {
                        idVal = $("#table_value").find("tr").eq(row).find("td").eq(1).html();
                        id_params.id = idVal;
                    }
                    row++;
                }
                return true;
            } else {
                notification.sendNotification("alert", "请重新选择！");
                return false;
            }
        };

        //修改订单
        $scope.update = function () {
            if (getInfo()) {
                $("#modal-edit").modal('show');
                var idInfo = JSON.stringify(id_params);
                myHttpService.post(serviceList.GetAssisantProcessById, idInfo).then(function successCallback(response) {
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

        $scope.editAssisantProcess = function () {
            if (confirm.confirmEdit()) {
                if (resourcedisEditValidate()) {
                    $("#modal-edit").modal('hide');
                    //用获取到的数据代替从数据库取到的数据
                    var temps = edit_params.id;
                    delete(edit_params.id);
                    edit_params.id = temps;
                    edit_params.grp = editData.grp;
                    edit_params.typeSite = editData.typeSite;
                    edit_params.idSite = editData.idSite;
                    edit_params.maxResource = editData.maxResource;
                    edit_params.minResource = editData.minResource;
                    edit_params.weightParallel = editData.weightParallel;
                    var update_data = angular.toJson(edit_params);
                    myHttpService.post(serviceList.UpdateAssisantProcess, update_data).then(function successCallback() {
                        myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
                            $scope.resourcedisList = response.data;
                            notification.sendNotification("confirm", "修改成功");
                        })
                    }, function errorCallback() {
                        notification.sendNotification("alert", "请求失败");
                    })
                } else {
                    notification.sendNotification("alert", "输入有误");
                }
            }
        };


        //删除订单
        $scope.deleteAssisantProcess = function () {
            if (getInfo()) {
                if (confirm.confirmEdit()) {
                    var params = {};
                    params.id = idVal;
                    var idInfo = JSON.stringify(params);
                    myHttpService.delete(serviceList.DeleteAssisantProcess, idInfo).then(function successCallback() {
                        myHttpService.get(serviceList.ListAssisantProcess).then(function (response) {
                            $scope.resourcedisList = response.data;
                            notification.sendNotification("confirm", "删除成功");
                        })
                    }, function errorCallback() {
                        notification.sendNotification("alert", "请求失败");
                    });
                }
            }
        };

        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        }
    });