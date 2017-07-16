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
    .controller('ScheduleSnapController', function ($scope, $http, myHttpService, serviceList) {

        layer.load(0);

        var zNodes = [];
        var idVal;//所点击的节点id值
        var rootData = [];
        var planList = [];


        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            loadRightFloatMenu();

            hideLoadingPage();
        });

        //3D车间查看转换结果
        $scope.viewIn3DFactory = function () {
            layer.load();

            hideLoadingPage();
        };

        //将选中结果下发MES
        $scope.dispatchMes = function () {
            layer.confirm('将结果下发MES？', {
                btn: ['下发', '取消'] //按钮
            }, function () {
                layer.load();
                setTimeout(function () {
                    layer.msg('已下发', {icon: 1});
                    hideLoadingPage();
                }, 2000);
            }, function () {
                layer.msg('取消下发', {icon: 2});
            });
        };

        var dataTrue = {"level": "top"};
        myHttpService.post(serviceList.isRootLevel, dataTrue).then(function successCallback(response) {
            rootData = response.data;
            $scope.dataArr = response.data;

        });

        $("#select").change(function () {
            zNodes.splice(0, zNodes.length);
            planList.splice(0, planList.length);
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
                console.log(id);
                myHttpService.post(serviceList.getTree, id).then(function successCallback(response) {

                    var datas = response.data;

                    console.log(datas);
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


                    var dataTrea = "节点：" + datas.name + "，类型：一级节点，" + "" +
                        "子节点分" + count + "级";
                    document.getElementById("textarea").value = dataTrea;

                    console.log(datas);
                    console.log("根节点");
                    console.log("是否有一级");
                    console.log(datas.hasOwnProperty("childs"));
                    if (datas.hasOwnProperty("childs") == true) {
                        var childList = datas.childs;
                        console.log("根节点下的一级");
                        console.log(childList);
                        console.log("根节点下的一级长度");
                        console.log(childList.length);
                        for (var j = 0; j < childList.length; j++) {
                            console.log("一级节点下是否有");
                            console.log(childList[j].hasOwnProperty("childs"));
                            var childLists = childList[j];
                            if (childLists.hasOwnProperty("childs") == true) {
                                console.log("一级节点下删除前的二级");
                                console.log(childLists);
                                var temps = childLists.childs;
                                delete(childLists.childs);
                                childLists.children = temps;
                                console.log("一级节点下删除后的二级");
                                console.log(childLists);
                                childList[j] = childLists;
                            }
                        }
                        console.log(childList);
                        var temp = datas.childs;
                        delete(datas.childs);
                        datas.children = temp;
                    }
                    zNodes.push(datas);
                    console.log("&&&&&&&&&");
                    console.log(zNodes);
                    loadTree();
                })
            } else {
                document.getElementById("textarea").value = "";
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
                    onClick: zTreeOnClick,
                    beforeExpand: beforeExpand,
                    onExpand: onExpand
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
                console.log(zNodes);
                params.id = zNodes[0].id;
                params.name = zNodes[0].name;
                params.level = zNodes[0].level;
                tree.push(params);
                console.log(tree);

                if (zNodes[0].hasOwnProperty("children") == true) {
                    for (var i = 0; i < zNodes[0].children.length; i++) {
                        var dataMiddle = zNodes[0].children[i];
                        var params = {};
                        params.id = dataMiddle.id;
                        params.name = dataMiddle.name;
                        params.level = dataMiddle.level;
                        tree.push(params);
                        console.log(tree);
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
                        console.log(tree);
                    }
                }


                for (var i = 0; i < tree.length; i++) {
                    var state = tree[i].level;
                    console.log(state);
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
                zTree.expandNode(treeNode, null, null, null, true);


                var dataTrea;
                document.getElementById("textarea").value = "";
                var id = zTree.getSelectedNodes()[0].id;
                console.log(id);
                for (var i = 0; i < tree.length; i++) {
                    if (id == tree[i].id && tree[i].level == "三级节点") {
                        var treeInfo = tree[i];
                        var params = {};
                        params.id = id;
                        var data = JSON.stringify(params);
                        dataTrea = "节点：" + treeInfo.name + "，类型：" + treeInfo.level + "\n" + "排程开始时间：" + moment(treeInfo.schedule.startCalcTime).format("YYYY-MM-DD")
                            + "，排程结束时间：" + moment(treeInfo.schedule.endCalcTime).format("YYYY-MM-DD") + "，排程周期长度：" + treeInfo.schedule.scheduleWindow + "\n" +"APS计算开始时间"
                            + moment(treeInfo.schedule.apsStartTime).format("YYYY-MM-DD") + "，APS计算结束时间" + moment(treeInfo.schedule.apsEndTime).format("YYYY-MM-DD");
                        document.getElementById("textarea").value = dataTrea;
                        myHttpService.post(serviceList.getAllPlan, data).then(function successCallback(response) {
                            planList = response.data
                            $scope.planList = planList;
                        });
                        break;
                    } else if (id == tree[i].id) {
                        $("#table_value  tr:not(:first)").html("");
                        var treeInfo = tree[i];
                        dataTrea = "节点：" + treeInfo.name + "，类型：" + treeInfo.level;
                        document.getElementById("textarea").value = dataTrea;
                        break;
                    }
                }
            }


            var curExpandNode = null;

            function beforeExpand(treeId, treeNode) {
                var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
                var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
                    if (treeNode !== treeNodeP.children[i]) {
                        zTree.expandNode(treeNodeP.children[i], false);
                    }
                }
                while (pNode) {
                    if (pNode === treeNode) {
                        break;
                    }
                    pNode = pNode.getParentNode();
                }
                if (!pNode) {
                    singlePath(treeNode);
                }

            }

            function singlePath(newNode) {
                if (newNode === curExpandNode) return;

                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    rootNodes, tmpRoot, tmpTId, i, j, n;

                if (!curExpandNode) {
                    tmpRoot = newNode;
                    while (tmpRoot) {
                        tmpTId = tmpRoot.tId;
                        tmpRoot = tmpRoot.getParentNode();
                    }
                    rootNodes = zTree.getNodes();
                    for (i = 0, j = rootNodes.length; i < j; i++) {
                        n = rootNodes[i];
                        if (n.tId != tmpTId) {
                            zTree.expandNode(n, false);
                        }
                    }
                } else if (curExpandNode && curExpandNode.open) {
                    if (newNode.parentTId === curExpandNode.parentTId) {
                        zTree.expandNode(curExpandNode, false);
                    } else {
                        var newParents = [];
                        while (newNode) {
                            newNode = newNode.getParentNode();
                            if (newNode === curExpandNode) {
                                newParents = null;
                                break;
                            } else if (newNode) {
                                newParents.push(newNode);
                            }
                        }
                        if (newParents != null) {
                            var oldNode = curExpandNode;
                            var oldParents = [];
                            while (oldNode) {
                                oldNode = oldNode.getParentNode();
                                if (oldNode) {
                                    oldParents.push(oldNode);
                                }
                            }
                            if (newParents.length > 0) {
                                zTree.expandNode(oldParents[Math.abs(oldParents.length - newParents.length) - 1], false);
                            } else {
                                zTree.expandNode(oldParents[oldParents.length - 1], false);
                            }
                        }
                    }
                }
                curExpandNode = newNode;
            }

            function onExpand(event, treeId, treeNode) {
                curExpandNode = treeNode;
            }


            $scope.show3D = function () {
                var url = "http://localhost:8080/snapshot/view3DEmulate.action?" + "id=" + idVal;
                $http({
                    'method': 'get',
                    'url': url
                })
            };

            $scope.sendMES = function () {
                hideRMenu();
                var url = "http://localhost:8080/snapshot/dispatcherResultToMess.action?" + "id=" + idVal;
                $http({
                    'method': 'get',
                    'url': url
                })
            };

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            document.getElementById("treeDemo").style.display = "";

        }

    });

