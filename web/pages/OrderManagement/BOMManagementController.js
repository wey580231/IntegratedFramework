/**
 * Created by zhaoqi on 2017/7/9.
 */
'use strict';
angular.module("IntegratedFramework.BOMManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/BOMManagement', {
            templateUrl: 'pages/OrderManagement/BOMManagement.html',
            controller: 'BOMManagementController'
        })
    }])
    .controller('BOMManagementController', function ($scope, $http, myHttpService, serviceList) {

        $(function () {
            //初始化下拉数据
            $(".select2").select2();
        });

        var zTreeNodes = [];
        var zNodes = [];
        var idVal;//所右击的节点id值

        var dataArr = [];
        var dataTrue = {"isRootNode": true};
        myHttpService.post(serviceList.isRootNode, dataTrue).then(function successCallback(response) {
            console.log(response.data);
            var rootdata = response.data;
            $scope.rootdata = response.data;
            $scope.processdata = response.data;

            var rootLength = rootdata.length;

            for (var i = 0; i < rootLength; i++) {
                var params = {};
                var id = rootdata[i].id;
                params.id = id;
                console.log(params);
                var dataId = JSON.stringify(params);
                console.log("根节点的id");
                console.log(dataId);
                myHttpService.post(serviceList.isChildNode, dataId).then(function successCallback(response) {
                    console.log("$$$$$$" + response.status);
                    console.log(response.data);
                    var data = response.data;
                    console.log(data);
                    dataArr.push(data);
                    console.log(dataArr);
                    if (dataArr.length > 0) {
                        for (var i = 0; i < dataArr.length; i++) {
                            console.log("第一个根节点");
                            console.log(dataArr[i]);
                            var datas = dataArr[i];
                            console.log("是否有一级");
                            console.log(datas.hasOwnProperty("childProcess"));
                            if (datas.hasOwnProperty("childProcess") == true) {
                                var childList = dataArr[i].childProcess;
                                console.log("根节点下的一级");
                                console.log(childList);
                                console.log("根节点下的一级长度");
                                console.log(childList.length);
                                for (var j = 0; j < childList.length; j++) {
                                    console.log("一级节点下是否有");
                                    console.log(childList[j].hasOwnProperty("childProcess"));
                                    var childLists = childList[j];
                                    if (childLists.hasOwnProperty("childProcess") == true) {
                                        console.log("一级节点下删除前的二级");
                                        console.log(childLists);
                                        var temps = childLists.childProcess;
                                        console.log(temps);
                                        delete(childLists.childProcess);
                                        childLists.children = temps;
                                        console.log("一级节点下删除后的二级");
                                        console.log(childLists);
                                        childList[j] = childLists;
                                    }
                                }
                                console.log(childList);
                                var temp = datas.childProcess;
                                delete(datas.childProcess);
                                datas.children = temp;
                            }
                        }
                        zNodes = dataArr;
                        console.log("&&&&&&&&&");
                        console.log(zNodes);
                    }
                })
            }
        });

        $("#select").change(function () {
            var val = $(this).children('option:selected').val();
            console.log(val);
            console.log(zNodes);
            if (val.length > 0) {
                for (var i = 0; i < zNodes.length; i++) {
                    if (zNodes[i].name == val) {
                        zTreeNodes.push(zNodes[i]);
                        break;
                    }
                }
                console.log(zTreeNodes);
                loadTree();
            } else {
                document.getElementById("treeDemo").style.display = "none";
            }
            zTreeNodes.splice(0, zTreeNodes.length);
        });

        function loadTree() {
            var zTree, rMenu;
            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon: false,
                    showLine: false
                },
                callback: {
                    onRightClick: OnRightClick,
                    onDblClick: zTreeOnClick
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
            };

            //右击
            function OnRightClick(event, treeNode) {
                idVal = "";
                idVal = zTree.getSelectedNodes()[0].id;
                var e = event || window.event;
                if (!treeNode && e.target.tagName.toLowerCase() != "button" && $(e.target).parents("a").length == 0) {
                    zTree.cancelSelectedNode();
                    showRMenu("root", e.clientX, e.clientY);
                } else if (treeNode && !treeNode.noR) {
                    zTree.selectNode(treeNode);
                    showRMenu("node", e.clientX, e.clientY);
                }
            }

            //右击菜单显示
            function showRMenu(type, x, y) {
                $("#rMenu ul").show();
                if (type == "root") {
                    $("#m_del").hide();
                } else {
                    $("#m_del").show();
                }
                rMenu.css({"top": (y - 110) + "px", "left": (x - 250) + "px", "visibility": "visible"});
                $("body").bind("mousedown", onBodyMouseDown);
            }

            //右击菜单隐藏
            function hideRMenu() {
                if (rMenu) rMenu.css({"visibility": "hidden"});
                $("body").unbind("mousedown", onBodyMouseDown);
            }


            function onBodyMouseDown(event) {
                if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
                    rMenu.css({"visibility": "hidden"});
                }
            }

            //双击显示表格信息
            function zTreeOnClick(treeNode) {
                if (treeNode.isParent) {
                    return false;
                } else {
                    var id = zTree.getSelectedNodes()[0].id;
                    var params = {};
                    params.id = id;
                    var data = JSON.stringify(params);
                    myHttpService.post(serviceList.getAllPlan, data).then(function successCallback(response) {
                        $scope.planList = response.data;
                    });
                    return true;
                }
            }

            var addCount = 1;

            //增加节点
            $scope.addTreeNode = function () {
                hideRMenu();
                var newNode = {name: "增加" + (addCount++)};
                if (zTree.getSelectedNodes()[0]) {
                    newNode.checked = zTree.getSelectedNodes()[0].checked;
                    zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
                } else {
                    zTree.addNodes(null, newNode);
                }
            };


            //删除节点
            $scope.removeTreeNode = function () {
                hideRMenu();
                var nodes = zTree.getSelectedNodes();
                if (nodes && nodes.length > 0) {
                    if (nodes[0].children && nodes[0].children.length > 0) {
                        var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
                        if (confirm(msg) == true) {
                            zTree.removeNode(nodes[0]);
                        }
                    } else {
                        zTree.removeNode(nodes[0]);
                    }
                }
            };

            $scope.checkTreeNode = function (checked) {
                var nodes = zTree.getSelectedNodes();
                if (nodes && nodes.length > 0) {
                    zTree.checkNode(nodes[0], checked, true);
                }
                hideRMenu();
            };

            $scope.resetTree = function () {
                hideRMenu();
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            };

            // $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zTreeNodes);
            zTree = $.fn.zTree.getZTreeObj("treeDemo");
            rMenu = $("#rMenu");
            document.getElementById("treeDemo").style.display = "";
            // });
        }
    });