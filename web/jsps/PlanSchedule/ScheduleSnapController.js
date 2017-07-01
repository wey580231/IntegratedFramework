/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleSnapController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleSnap', {
            templateUrl: 'jsps/PlanSchedule/ScheduleSnap.jsp',
            controller: 'ScheduleSnapController'
        })
    }])
    .controller('ScheduleSnapController', function ($scope, $http, myHttpService, serviceList) {


        var dataTrue = {"level": "top"};
        myHttpService.post(serviceList.isRootLevel, dataTrue).then(function successCallback(response) {
            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon: false,
                    showLine: false
                },
                check: {
                    enable: false
                },
                callback: {
                    onRightClick: OnRightClick
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
            var zNodes = [];


            console.log("*****" + response.status);
            console.log(response.data);
            var dataArr = response.data;

            for (var i = 0; i < dataArr.length; i++) {
                console.log("根节点");
                console.log(dataArr[i]);
                var datas = dataArr[i];
                console.log("是否有一级");
                console.log(datas.hasOwnProperty("childs"));
                if (datas.hasOwnProperty("childs") == true) {
                    var childList = dataArr[i].childs;
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
                            console.log(temps);
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
            }
            console.log(dataArr);
            //zNodes = data;
            zNodes = dataArr;
            console.log("&&&&&&&&&" + zNodes);

            //右键操作
            /*function OnRightClick(event, treeId, treeNode) {
             if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
             zTree.cancelSelectedNode();
             showRMenu("root", event.clientX, event.clientY);
             } else if (treeNode && !treeNode.noR) {
             zTree.selectNode(treeNode);
             showRMenu("node", event.clientX, event.clientY);
             }
             }*/

            function OnRightClick(event, treeId, treeNode) {
                var x = event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
                var y = event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
                if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
                    zTree.cancelSelectedNode();
                    showRMenu("root", x, y);
                } else if (treeNode && !treeNode.noR) {
                    zTree.selectNode(treeNode);
                    showRMenu("node", x, y);
                }
            }

            //显示右键操作
            function showRMenu(type, x, y) {
                $("#rMenu").show();
                rMenu.css({"top": (y - 70) + "px", "left": (x - 220) + "px", "visibility": "visible"});
                $("#container").bind("mousedown", onBodyMouseDown);
            }

            //隐藏右键操作
            function hideRMenu() {
                if (rMenu) rMenu.css({"visibility": "hidden"});
                $("#container").unbind("mousedown", onBodyMouseDown);
            }

            //鼠标按下操作
            function onBodyMouseDown(event) {
                if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
                    rMenu.css({"visibility": "hidden"});
                }
            }

            var addCount = 1;
            //增加节点
            $scope.addTreeNode = function () {
                hideRMenu();
                var newNode = {name: "增加" + (addCount++)};
                if (zTree.getSelectedNodes()[0]) {
                    console.log("@@@@@@");
                    console.log(zTree.getSelectedNodes());
                    console.log(zTree.getSelectedNodes()[0]);
                    zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
                } else {
                    zTree.addNodes(null, newNode);
                }
                getChildNodes();
            };

            //删除节点
            $scope.removeTreeNode = function () {
                hideRMenu();
                var nodes = zTree.getSelectedNodes();
                if (nodes && nodes.length > 0) {
                    if (nodes[0].children && nodes[0].children.length > 0) {
                        var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n确定删除？";
                        if (confirm(msg) == true) {
                            zTree.removeNode(nodes[0]);
                        }
                    } else {
                        zTree.removeNode(nodes[0]);
                    }
                }
            };

            //重命名节点
            $scope.renameTreeNode = function () {
                hideRMenu();
                var nodes = zTree.getSelectedNodes();
                zTree.editName(nodes[0]);
                var newName = nodes[0].name;
                if (newName.length == 0) {
                    alert("节点不能为空！");
                    return false;
                }
            };

            var getChildNodes = function () {
                var childNodes = zTree.transformToArray(zTree.getSelectedNodes()[0]);
                console.log(childNodes);
                var nodes = [];
                for (i = 0; i < childNodes.length; i++) {
                    nodes[i] = childNodes[i].id;
                }
                console.log(nodes);

                return nodes.join(",");
            };

            var zTree, rMenu;
            //初始化BOM树
            $(document).ready(function () {
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                rMenu = $("#rMenu");
            });
        });

        /*var zNodes = [
         {
         id: 1, name: "右键菜单 1", open: true,
         children: [
         {
         id: 11, name: "节点 1-1",
         children: [
         {id: 21, name: "节点 1-11"},
         {id: 22, name: "节点 1-12"},
         {id: 23, name: "节点 1-13"},
         {id: 24, name: "节点 1-14"}
         ]
         },
         {id: 12, name: "节点 1-2"}
         ]
         },
         {
         id: 2, name: "右键操作 2", open: true,
         children: [
         {id: 21, name: "节点 2-1"},
         {id: 22, name: "节点 2-2"},
         {id: 23, name: "节点 2-3"},
         {id: 24, name: "节点 2-4"}
         ]
         }
         ];*/


    });