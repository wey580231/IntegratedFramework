/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.BOMManagementController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/BOMManagement', {
            templateUrl: 'jsps/OrderManagement/BOMManagement.jsp',
            controller: 'BOMManagementController'
        })
    }])
    .controller("BOMManagementController", function ($scope, myHttpService, serviceList) {
        var zNodes = [];
        var dataArr = [];

        var dataTrue = {"isRootNode": true};
        myHttpService.post(serviceList.isRootNode, dataTrue).then(function successCallback(response) {
            console.log("*****" + response.status);
            console.log(response.data);
            var rootdata = response.data;
            $scope.rootdata = response.data;
            $scope.process = response.data;
            showRoot(rootdata);
        });

        var showRoot = function (rootdata) {
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
                    console.log(dataArr.length);
                    console.log(dataArr);
                    if (dataArr.length > 0) {
                        showChild();
                    }
                });
            }
        };

        var showChild = function () {
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
            console.log("++++++" + dataArr);
            console.log(dataArr.length);
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
            console.log(dataArr);
            //zNodes = data;

            zNodes = dataArr;
            console.log("&&&&&&&&&" + zNodes);

            function OnRightClick(event, treeNode) {
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
                    console.log(zTree.getSelectedNodes()[0]);
                    zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
                    console.log(zTree.getSelectedNodes()[0]);
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

            var zTree, rMenu;
            //初始化BOM树
            $(document).ready(function () {
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                rMenu = $("#rMenu");
            });

        };

        $("#testBOM").change(function () {
            var val = $(this).children('option:selected').val();
            if (val.length > 0) {
                for (var i = 0; i < zNodes.length; i++) {
                    console.log(zNodes);
                    if (zNodes[i].name = val) {
                        var zTreeNodes = zNodes[i];
                    }
                    break;
                }
            }
            else {

            }
        });
        /*$scope.showTree = function () {

         $("#testBOM").children('option:selected').val();
         var name = $("#testBOM").children('option:selected').attr("name");
         console.log($("#testBOM").children('option:selected').attr("name"));
         console.log($("#testBOM").children('option:selected').attr("value"));

         console.log("选中的要现实的树");
         //var myselect = document.getElementById("testBOM");
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
         var zTreeNodes;
         for (var i = 0; i < zNodes.length; i++) {
         console.log(zNodes);
         if (zNodes[i].name = name) {
         zTreeNodes = zNodes[i];
         }
         break;
         }
         console.log(zTreeNodes);


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
         console.log(zTree.getSelectedNodes()[0]);
         zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
         console.log(zTree.getSelectedNodes()[0]);
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


         var zTree, rMenu;
         $.fn.zTree.init($("#treeDemo"), setting, zTreeNodes);
         rMenu = $("#rMenu");
         }

         //右键操作
         function OnRightClick(event,treeNode) {
         var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
         var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
         var x = event.pageX || event.clientX + scrollX;
         var y = event.pageY || event.clientY + scrollY;
         if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
         zTree.cancelSelectedNode();
         showRMenu("root", x, y);
         } else if (treeNode && !treeNode.noR) {
         zTree.selectNode(treeNode);
         showRMenu("node", x, y);
         }
         }*/


    });

