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
    .controller('BOMManagementController', function ($scope, $http, myHttpService, serviceList, notification) {

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
                                    if (bottomNode.transport == 0) {
                                        bottomNode.icon = "../../images/bom_img/transport.png";
                                    } else if (bottomNode.transport == 1) {
                                        bottomNode.icon = "../../images/bom_img/fit.png";
                                    } else {
                                        bottomNode.icon = "../../images/bom_img/errorNode.png";
                                    }
                                }
                                var temps = middleNode.childProcess;
                                delete(middleNode.childProcess);
                                middleNode.children = temps;
                                middleNode = middleNode;
                                if (middleNode.children.length > 0) {
                                    if (middleNode.transport == 0) {
                                        middleNode.icon = "../../images/bom_img/interactive.png";
                                    } else if (middleNode.transport == 1) {
                                        middleNode.icon = "../../images/bom_img/fit.png";
                                    } else {
                                        middleNode.icon = "../../images/bom_img/errorNode.png";
                                    }
                                } else {
                                    if (middleNode.transport == 0) {
                                        middleNode.icon = "../../images/bom_img/transport.png";
                                    } else if (middleNode.transport == 1) {
                                        middleNode.icon = "../../images/bom_img/fit.png";
                                    } else {
                                        middleNode.icon = "../../images/bom_img/errorNode.png";
                                    }
                                }

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

        //更新BOM
        $scope.updateBOM = function () {
            layer.confirm('是否更新当前BOM信息?', {
                btn: ['确定', '取消'] //按钮
            }, function (index) {
                layer.close(index);
                notification.sendNotification("confirm", "已更新");
            }, function (index) {
                layer.close(index);
                notification.sendNotification("alert", "已取消更新");
            });
        };

        //属性设置
        $scope.propertySetting = function () {

        };

        //辅助工艺
        $scope.assisantProcess = function () {

        };

        /*        //DAG图
         var workers = {
         "identifier": {
         "consumers": 2,
         "count": 20
         },
         "lost-and-found": {
         "consumers": 1, //节点内容
         "count": 1,
         "inputQueue": "identifier", //上层节点
         "inputThroughput": 50
         },
         "monitor": {
         "consumers": 1,
         "count": 0,
         "inputQueue": "identifier",
         "inputThroughput": 50
         },
         "meta-enricher": {
         "consumers": 4,
         "count": 9900,
         "inputQueue": "identifier",
         "inputThroughput": 50
         },
         "geo-enricher": {
         "consumers": 2,
         "count": 1,
         "inputQueue": "meta-enricher",
         "inputThroughput": 50
         },
         "elasticsearch-writer": {
         "consumers": 0,
         "count": 9900,
         "inputQueue": "geo-enricher",
         "inputThroughput": 50
         }
         };
         // Set up zoom support
         var svg = d3.select("svg"),
         inner = svg.select("g"),
         zoom = d3.behavior.zoom().on("zoom", function () {
         inner.attr("transform", "translate(" + d3.event.translate + ")" +
         "scale(" + d3.event.scale + ")");
         });
         svg.call(zoom);
         var render = new dagreD3.render();
         // Left-to-right layout
         var g = new dagreD3.graphlib.Graph();
         g.setGraph({
         nodesep: 170,
         ranksep: 150,
         rankdir: "LR",
         marginx: 20,
         marginy: 20
         });
         function draw(isUpdate) {
         for (var id in workers) {
         var worker = workers[id];
         var className = worker.consumers ? "running" : "stopped";
         if (worker.count > 10000) {
         className += " warn";
         }
         var html = "<div>";
         html += "<span class=status></span>";
         html += "<span class=consumers>" + worker.consumers + "</span>";
         html += "<span class=name>" + id + "</span>";
         html += "<span class=queue><span class=counter>" + worker.count + "</span></span>";
         html += "</div>";
         g.setNode(id, {
         labelType: "html",
         label: html,
         rx: 5,
         ry: 5,
         padding: 0,
         class: className
         });
         if (worker.inputQueue) {
         g.setEdge(worker.inputQueue, id, {
         label: worker.inputThroughput + "/s",
         width: 40
         });
         }
         }
         inner.call(render, g);
         // Zoom and scale to fit
         var zoomScale = zoom.scale();
         var graphWidth = g.graph().width + 80;
         var graphHeight = g.graph().height + 140;
         var width = parseInt(svg.style("width").replace(/px/, ""));
         var height = parseInt(svg.style("height").replace(/px/, ""));
         zoomScale = Math.min(width / graphWidth, height / graphHeight);
         var translate = [(width / 2) - ((graphWidth * zoomScale) / 2), (height / 2) - ((graphHeight * zoomScale) / 2)];
         zoom.translate(translate);
         zoom.scale(zoomScale);
         zoom.event(isUpdate ? svg.transition().duration(500) : d3.select("svg"));
         }
         // Do some mock queue status updates
         setInterval(function () {
         var stoppedWorker1Count = workers["elasticsearch-writer"].count;
         var stoppedWorker2Count = workers["meta-enricher"].count;
         for (var id in workers) {
         workers[id].count = Math.ceil(Math.random() * 3);
         if (workers[id].inputThroughput) workers[id].inputThroughput = Math.ceil(Math.random() * 250);
         }
         workers["elasticsearch-writer"].count = stoppedWorker1Count + Math.ceil(Math.random() * 100);
         workers["meta-enricher"].count = stoppedWorker2Count + Math.ceil(Math.random() * 100);
         draw(true);
         }, 1000);
         // Do a mock change of worker configuration
         setInterval(function () {
         workers["elasticsearch-monitor"] = {
         "consumers": 0,
         "count": 0,
         "inputQueue": "elasticsearch-writer",
         "inputThroughput": 50
         }
         }, 5000);
         draw();*/


    });