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

        layer.load(0);

        var zNodes = [];
        var rootData = [];

        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            var dataTrue = {"isRootNode": true};
            myHttpService.post(serviceList.isRootNode, dataTrue).then(function successCallback(response) {
                rootData = response.data;
                $scope.rootdata = response.data;
                $scope.processdata = response.data;
                loadRightFloatMenu();
                hideLoadingPage();
            });
        });


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

                myHttpService.post(serviceList.isChildNode, id).then(function successCallback(response) {
                    var datas = response.data;

                    if (datas.hasOwnProperty("childProcess")) {
                        var middleNodeList = datas.childProcess;
                        sortSnapshot(middleNodeList);
                        for (var j = 0; j < middleNodeList.length; j++) {

                            var middleNode = middleNodeList[j];

                            if (middleNode.hasOwnProperty("childProcess")) {

                                sortSnapshot(middleNode.childProcess);

                                for (var k = 0; k < middleNode.childProcess.length; k++) {
                                    var bottomNode = middleNode.childProcess[k];
                                    //默认排程节点
                                    if (middleNode.firstNode) {
                                        if (!middleNode.firstNode) {
                                            bottomNode.icon = "../../images/bom_img/interNode.png";
                                        } else if (bottomNode.firstNode) {
                                            bottomNode.icon = "../../images/bom_img/commonNode.png";
                                        }
                                    }
                                }
                                var temps = middleNode.childProcess;
                                delete(middleNode.childProcess);
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
                        var temp = datas.childProcess;
                        delete(datas.childProcess);
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

            //点击树形控件
            function zTreeOnClick(e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");

                zTree.expandNode(treeNode);

            }   //onclick

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);

            document.getElementById("treeDemo").style.display = "";
        }

    });


