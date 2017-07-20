/**
 * Created by XY on 2017/7/6.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleSnapController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleSnap', {
            templateUrl: 'pages/PlanSchedule/ScheduleSnap.html',
            controller: 'ScheduleSnapController'
        })
    }])
    .controller('ScheduleSnapController', function ($scope, $http, myHttpService, serviceList,notification) {
        layer.load(0);

        var zNodes = [];
        var idVal;//所点击的节点id值
        var rootData = [];
        var planList = [];
        var currTreeSelectedId = null;

        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            var dataTrue = {"level": "top"};
            myHttpService.post(serviceList.isRootLevel, dataTrue).then(function successCallback(response) {
                rootData = response.data;
                $scope.dataArr = response.data;

                loadRightFloatMenu();
                hideLoadingPage();
            });
        });

        //3D车间查看转换结果
        $scope.viewIn3DFactory = function () {
            if (currTreeSelectedId == null) {
                notification.sendNotification("alert", "未选择节点信息");
                return;
            }

            layer.load();
            myHttpService.get(serviceList.view3DEmulate + "?id=" + currTreeSelectedId).then(function success(response) {
                if (response.data.result == "ok") {
                    notification.sendNotification("confirm", "数据转换成功,3D车间启动中...");
                    setTimeout(function () {
                        layer.open({
                            type: 2,
                            title: '3D车间',
                            maxmin: true,
                            shadeClose: true,
                            shade: false,
                            offset: 'l',
                            area: ['100%', '100%'],
                            content: "http://localhost:8080/WebGL"
                        });
                    }, 1500);
                } else {
                    notification.sendNotification("alert", "数据转换失败");
                }
                hideLoadingPage();
            });
        };

        //将选中结果下发MES
        $scope.dispatchMes = function () {
            layer.confirm('将结果下发MES？', {
                btn: ['下发', '取消'] //按钮
            }, function () {
                layer.load();
                setTimeout(function () {
                    notification.sendNotification("confirm", "已下发");
                    hideLoadingPage();
                }, 2000);
            }, function () {
                notification.sendNotification("alert", "取消下发");
            });
        };

        //下拉框事件改变
        $("#select").change(function () {
            zNodes.splice(0, zNodes.length);
            planList.splice(0, planList.length);
            var idRoot;
            currTreeSelectedId = null;
            var val = $(this).children('option:selected').val();
            if (val.length > 0) {
                for (var i = 0; i < rootData.length; i++) {
                    if (rootData[i].name == val) {
                        idRoot = rootData[i].id;
                        break;
                    }
                }
                var params = {};
                params.id = idRoot;
                var id = JSON.stringify(params);

                layer.load();
                myHttpService.post(serviceList.getTree, id).then(function successCallback(response) {
                    var datas = response.data;
                    $scope.snapShotData = response.data;
                    var count = 0;
                    if (datas.hasOwnProperty("childs") == true) {
                        count++;
                        var two = datas.childs;
                        for (var i = 0; i < two.length; i++) {
                            if (two[i].hasOwnProperty("childs") == true) {
                                count++;
                                break;
                            }
                        }
                    }

                    if (datas.hasOwnProperty("childs") == true) {
                        var childList = datas.childs;
                        for (var j = 0; j < childList.length; j++) {
                            var childLists = childList[j];
                            if (childLists.hasOwnProperty("childs") == true) {
                                var temps = childLists.childs;
                                delete(childLists.childs);
                                childLists.children = temps;
                                childList[j] = childLists;
                            }
                        }
                        var temp = datas.childs;
                        delete(datas.childs);
                        datas.children = temp;
                    }
                    zNodes.push(datas);
                    loadTree();

                    hideLoadingPage();
                })
            } else {
                document.getElementById("treeDemo").style.display = "none";
                $("#table_value  tr:not(:first)").html("");
            }
        });

        function loadTree() {
            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon: false,
                    showLine: false
                },
                callback: {
                    onClick: zTreeOnClick
                },
                data: {
                    keep: {
                        parent: true
                    }
                },
                edit: {
                    enable: true,
                    showRenameBtn: false,
                    showRemoveBtn: false
                },
                check: {
                    enable: true
                }
            };

            //单击显示表格信息
            function zTreeOnClick(e, treeId, treeNode) {
                var tree = [];
                var params = {};
                params.id = zNodes[0].id;
                params.name = zNodes[0].name;
                params.level = zNodes[0].level;
                tree.push(params);

                if (zNodes[0].hasOwnProperty("children") == true) {
                    for (var i = 0; i < zNodes[0].children.length; i++) {
                        var dataMiddle = zNodes[0].children[i];
                        var params = {};
                        params.id = dataMiddle.id;
                        params.name = dataMiddle.name;
                        params.level = dataMiddle.level;
                        tree.push(params);
                        if (dataMiddle.hasOwnProperty("children") == true) {
                            for (var j = 0; j < dataMiddle.children.length; j++) {
                                var dataBootum = dataMiddle.children[j];
                                var params = {};
                                params.id = dataBootum.id;
                                params.name = dataBootum.name;
                                params.level = dataBootum.level;
                                params.schedule = zNodes[0].schedule;
                                tree.push(params);
                            }
                        }
                    }
                }

                for (var i = 0; i < tree.length; i++) {
                    var state = tree[i].level;
                    switch (state) {
                        case "top":
                            tree[i].level = "一级节点";
                            break;
                        case "middle":
                            tree[i].level = "二级节点";
                            break;
                        case "bottom":
                            tree[i].level = "三级节点";
                            break;
                    }
                }


                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandNode(treeNode);

                var tmpId = zTree.getSelectedNodes()[0].id;
                for (var i = 0; i < tree.length; i++) {
                    if (tmpId == tree[i].id && tree[i].level == "三级节点") {
                        var treeInfo = tree[i];
                        var params = {};
                        currTreeSelectedId = zTree.getSelectedNodes()[0].id;
                        params.id = currTreeSelectedId;
                        var data = JSON.stringify(params);
                        layer.load();
                        myHttpService.post(serviceList.getAllPlan, data).then(function successCallback(response) {
                            planList = response.data
                            $scope.planList = planList;
                            hideLoadingPage();
                        });
                        break;
                    } else if (tmpId == tree[i].id) {
                        $("#table_value  tr:not(:first)").html("");
                        var treeInfo = tree[i];
                        break;
                    }
                }

            }

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            document.getElementById("treeDemo").style.display = "";

            $scope.sendMES = function () {
                hideRMenu();
                var url = "http://localhost:8080/snapshot/dispatcherResultToMess.action?" + "id=" + idVal;
                $http({
                    'method': 'get',
                    'url': url
                })
            };


        }
    });