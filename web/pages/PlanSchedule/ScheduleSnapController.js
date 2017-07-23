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

    .controller('ScheduleSnapController', function ($scope, $http, myHttpService, serviceList, notification) {

        layer.load(0);

        var zNodes = [];
        var idVal;//所点击的节点id值
        var rootData = [];

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
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if (zTree.getSelectedNodes().length == 1 && zTree.getSelectedNodes()[0].level == 2) {
                layer.load();
                myHttpService.get(serviceList.view3DEmulate + "?id=" + zTree.getSelectedNodes()[0].id).then(function success(response) {
                    if (response.data.result == "ok") {
                        notification.sendNotification("confirm", "数据转换成功,3D车间启动中...");
                        setTimeout(function () {
                            window.open('www.techbrood.com', '_blank');
                            // layer.open({
                            //     type: 2,
                            //     title: '3D车间',
                            //     maxmin: true,
                            //     shadeClose: true,
                            //     shade: false,
                            //     offset: 'l',
                            //     area: ['100%', '100%'],
                            //     content: "http://localhost:8080/WebGL"
                            // });
                        }, 1500);
                    } else {
                        notification.sendNotification("alert", "数据转换失败");
                    }
                    hideLoadingPage();
                });
            } else {
                notification.sendNotification("alert", "未选择节点信息");
            }
        };

        //将选中结果下发MES
        $scope.dispatchMes = function () {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if (zTree.getSelectedNodes().length == 1 && zTree.getSelectedNodes()[0].level == 2) {

                layer.confirm('是否将结果下发MES?', {
                    btn: ['下发','取消'] //按钮
                }, function(index){
                    layer.load();

                    myHttpService.get(serviceList.dispatchMes + "?id=" + zTree.getSelectedNodes()[0].id).then(function success(response) {
                        if (response.data.result == "ok") {
                            notification.sendNotification("confirm", "下发成功");
                        } else {
                            notification.sendNotification("alert", "处理失败");
                        }
                        hideLoadingPage();
                    }, function erorr() {
                        notification.sendNotification("alert", "请求失败，稍后再试");
                        hideLoadingPage();
                    });
                    layer.close(index);
                }, function(index){
                    layer.close(index);
                    notification.sendNotification("alert", "取消下发");
                });
            }else{
                notification.sendNotification("alert", "未选择节点信息");
            }
        };

        //对快照节点排序
        function sortSnapshot(childList) {
            for (var i = 0; i < childList.length; i++) {
                for (var j = i + 1; j < childList.length; j++) {
                    if (childList[j].nodeCreateTime < childList[i].nodeCreateTime) {
                        var tmp = childList[j];
                        childList[j] = childList[i];
                        childList[i] = tmp;
                    }
                }
            }
        }

        //下拉框事件改变
        $("#select").change(function () {
            zNodes.splice(0, zNodes.length);
            var idRoot;
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
                    $scope.snapShotData = response.data;
                    var datas = response.data;

                    if (datas.hasOwnProperty("childs")) {
                        var middleNodeList = datas.childs;
                        sortSnapshot(middleNodeList);
                        for (var j = 0; j < middleNodeList.length; j++) {

                            var middleNode = middleNodeList[j];

                            if (middleNode.hasOwnProperty("childs")) {

                                sortSnapshot(middleNode.childs);

                                for (var k = 0; k < middleNode.childs.length; k++) {
                                    var bottomNode = middleNode.childs[k];
                                    //默认排程节点
                                    if (middleNode.firstNode) {
                                        if (!bottomNode.apply  && !middleNode.firstNode) {
                                            bottomNode.icon = "../../images/bom_img/interNode.png";
                                        } else if (!bottomNode.apply && bottomNode.firstNode) {
                                            bottomNode.icon = "../../images/bom_img/commonNode.png";
                                        } else if(bottomNode.apply){
                                            bottomNode.icon = "../../images/bom_img/dispatchNode.png";
                                        }
                                    }
                                    //故障节点
                                    else {
                                        if (bottomNode.apply) {
                                            bottomNode.icon = "../../images/bom_img/dispatchNode.png";
                                        } else {
                                            bottomNode.icon = "../../images/bom_img/commonNode.png";
                                        }
                                    }
                                }
                                var temps = middleNode.childs;
                                delete(middleNode.childs);
                                middleNode.children = temps;
                                middleNode = middleNode;
                            }

                            if (middleNode.errorNode) {
                                middleNode.icon = "../../images/bom_img/errorNode.png";
                            } else {
                                middleNode.icon = "../../images/bom_img/interactive.png";
                            }
                        }

                        datas.icon = "../../images/bom_img/rootNode.png";
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
                    showIcon: true,
                    showLine: false,
                    fontCss: setFontCss
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

            //点击树形控件
            function zTreeOnClick(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                if (treeNode.level == 0 || treeNode.level == 1) {
                    zTree.expandNode(treeNode);
                } else if (treeNode.level == 2) {
                    var params = {};
                    params.id = treeNode.id;
                    var data = JSON.stringify(params);
                    layer.load();
                    myHttpService.post(serviceList.getAllPlan, data).then(function successCallback(response) {
                        $scope.planList = response.data;
                        hideLoadingPage();
                    });
                }
            }   //onclick

            //动态设置字体的颜色
            function setFontCss(treeId, treeNode) {
                var fs;
                if (treeNode.apply && treeNode.level == 2) {
                    fs = {color: "white", background: "#3C8DBC", border: "none"};
                }
                return fs;
            }

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            document.getElementById("treeDemo").style.display = "";
        }

    });