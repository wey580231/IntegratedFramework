/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ResourceDistributionController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ResourceDistribution', {
            templateUrl: 'jsps/OrderManagement/ResourceDistribution.jsp',
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


        //新增工序资源信息
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
            params.typeSite = typeSiteVal;
            params.idSite = idSiteVal;
            params.minResource = minResourceVal;
            params.maxResource = maxResourceVal;
            params.weightParallel = weightParallelVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#add").hide();
            myHttpService.post(serviceList.AddAssisantProcess, data).then(function successCallback(response) {
                console.log(response.status);
                //用强制刷新解决按钮不能连续响应
                setTimeout('window.location.reload();', 1);
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

        //删除工序资源信息
        $scope.deleteAssisantProcess = function () {
            var params = {};
            var idVal = operateId + "";
            params.id = idVal;
            params.grp = "";
            params.typeSite = "";
            params.idSite = "";
            params.minResource = "";
            params.maxResource = "";
            params.weightParallel = "";
            var data = JSON.stringify(params);
            console.log(data);
            myHttpService.delete(serviceList.DeleteAssisantProcess, data).then(function successCallback(response) {
                console.log(response.status);
                setTimeout('window.location.reload();', 1);
            })
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
                editAssisantProcess();
            } else {
                alert("请选择一条需要修改条目！");
                $("input").val('');
            }

        };

        //修改工序资源信息
        $scope.editAssisantProcess = function () {
            var rows = document.getElementById("table_value").rows;
            var a = document.getElementsByName("check");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log(row);
                    var params = {};
                    var arr = [];
                    params.id = rows[row].cells[1].innerHTML;
                    params.grp = rows[row].cells[2].innerHTML;
                    params.typeSite = rows[row].cells[3].innerHTML;
                    params.idSite = rows[row].cells[4].innerHTML;
                    params.minResource = rows[row].cells[5].innerHTML;
                    params.maxResource = rows[row].cells[6].innerHTML;
                    params.weightParallel = rows[row].cells[7].innerHTML;
                    console.log(params);
                    arr.push(params);
                    $scope.form = arr;
                }
            }
        };
        $scope.update = function () {
            //var idVal = $("input[name='edit-id']").val();
            var grpVal = $("input[name='edit-grp']").val();
            var typeSiteVal = $("input[name='edit-TypeSite']").val();
            var idSiteVal = $("input[name='edit-IdSite']").val();
            var minResourceVal = $("input[name='edit-minResource']").val();
            var maxResourceVal = $("input[name='edit-maxResource']").val();
            var weightParallelVal = $("input[name='edit-weightParallel']").val();
            var params = {};
            params.id = operateId;
            params.grp = grpVal;
            params.typeSite = typeSiteVal;
            params.idSite = idSiteVal;
            params.minResource = minResourceVal;
            params.maxResource = maxResourceVal;
            params.weightParallel = weightParallelVal;
            var data = JSON.stringify(params);
            console.log(data);
            $("#edit").hide();
            myHttpService.post(serviceList.UpdateAssisantProcess, data).then(function (response) {
                console.log(response.status);
                setTimeout('window.location.reload();', 1);
            })

        };

        //信息填写检验
        $scope.formValidate = function () {
            var id = $("input#add-id").val(),
                grp = $("input#add-grp").val();
            console.log(id + grp);
            if (check(id, grp) && checkId(id)) {
                UIkit.modal.confirm('确定添加吗？', function () {
                    addAssisantProcess();
                });
                return true;
            } else {

                return false;
            }
        };
        var check = function (id, grp) {
            if (id == "" && grp == "") {
                $("input#add-id").addClass("uk-form-danger");
                $("input#add-grp").addClass("uk-form-danger");
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