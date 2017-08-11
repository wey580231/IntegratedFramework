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

                //显示暂无数据
                view();

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
                                            bottomNode.symbol = "../../images/bom_img/transport.png";
                                        } else if (bottomNode.transport == 1) {
                                            bottomNode.icon = "../../images/bom_img/fit.png";
                                            bottomNode.symbol = "../../images/bom_img/fit.png";
                                        } else {
                                            bottomNode.icon = "../../images/bom_img/errorNode.png";
                                            bottomNode.symbol = "../../images/bom_img/errorNode.png";
                                        }
                                    }
                                    var temps = middleNode.childProcess;
                                    delete(middleNode.childProcess);
                                    middleNode.children = temps;
                                    middleNode = middleNode;
                                    if (middleNode.children.length > 0) {
                                        if (middleNode.transport == 0) {
                                            middleNode.icon = "../../images/bom_img/interactive.png";
                                            middleNode.symbol = "../../images/bom_img/interactive.png";
                                        } else if (middleNode.transport == 1) {
                                            middleNode.icon = "../../images/bom_img/fit.png";
                                            middleNode.symbol = "../../images/bom_img/fit.png";
                                        } else {
                                            middleNode.icon = "../../images/bom_img/errorNode.png";
                                            middleNode.symbol = "../../images/bom_img/errorNode.png";
                                        }
                                    } else {
                                        if (middleNode.transport == 0) {
                                            middleNode.icon = "../../images/bom_img/transport.png";
                                            middleNode.symbol = "../../images/bom_img/transport.png";
                                        } else if (middleNode.transport == 1) {
                                            middleNode.icon = "../../images/bom_img/fit.png";
                                            middleNode.symbol = "../../images/bom_img/fit.png";
                                        } else {
                                            middleNode.icon = "../../images/bom_img/errorNode.png";
                                            middleNode.symbol = "../../images/bom_img/errorNode.png";
                                        }
                                    }

                                }
                            }
                            datas.icon = "../../images/bom_img/rootNode.png";
                            datas.symbol = "../../images/bom_img/rootNode.png";
                            var temp = datas.childProcess;
                            delete(datas.childProcess);
                            datas.children = temp;
                        }
                        zNodes.push(datas);
                        loadTree();
                        loadDAG();
                        hideLoadingPage();
                    })
                } else {
                    document.getElementById("treeDemo").style.display = "none";
                    // $("#table_value  tr:not(:first)").html("");
                    $scope.processData = "";
                    $scope.$apply();
                    //显示暂无数据
                    view();
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

            function loadDAG() {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('dag'));

                // 指定图表的配置项和数据
                var option = {
                    series: [
                        {
                            name: '树图',
                            type: 'tree',
                            orient: 'horizontal',  // vertical horizontal
                            rootLocation: {x: 100, y: 230}, // 根节点位置  {x: 100, y: 'center'}
                            nodePadding: 8,
                            layerPadding: 200,
                            roam: true,
                            symbolSize: 6,
                            legendHoverLink:true,
                            itemStyle: {
                                normal: {
                                    color: '#4883b4',
                                    label: {
                                        show: true,
                                        position: 'right',
                                        formatter: "{b}",
                                        textStyle: {
                                            color: '#000',
                                            fontSize: 5
                                        }
                                    },
                                    lineStyle: {
                                        color: '#ccc',
                                        type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                                    }
                                },
                                emphasis: {
                                    label: {
                                        show: true,
                                        textStyle: {
                                            color: '#991096',
                                        }
                                    },
                                    borderWidth: 2
                                }
                            },
                        }
                    ]
                };


                //图结构数据
                option.series[0].data = zNodes;


                //用来存储子节点（供收缩，打开节点使用）
                var clickMap = {};
                myChart.on('click', function (params) {

                    //节点点击显示具体信息
                    $scope.processData = params;
                    $scope.$apply();

                    //当前点击节点的名称
                    var _name = params.name;
                    var _option = option;
                    var len1 = _option.series[0].data.length;
                    var f = false;//是否找到对应节点

                    //循环_option中的信息
                    for (var j = 0; j < len1; j++) {
                        //根节点的折叠展开
                        if (_option.series[0].data[j].children.length >= 0) {
                            if (_option.series[0].data[j].name == _name) {
                                //判断该节点是否已关闭，若clickMap中存在当前节点名称的数据，并且不为空。则说明已关闭，要打开。
                                if (clickMap.hasOwnProperty(_name) && clickMap[_name] != null) {
                                    //将clickMap中该节点的信息重新赋值给当前节点
                                    _option.series[0].data[j].children = clickMap[_name];
                                    clickMap[_name] = null;//成功打开后，将clickMap中的数据赋null
                                    f = true;
                                    //跳出所有循环。
                                    break;
                                }
                                //执行到这里，说明未关闭
                                f = true;
                                //若所点击的节点存在子节点，则
                                if (_option.series[0].data[j].children) {
                                    //将子节点信息存入clickMap，形式【当前点击节点的name为key，子节点数据为value】
                                    clickMap[_option.series[0].data[j].name] = _option.series[0].data[j].children;
                                    //然后将_option中的当前子节点删除
                                    delete _option.series[0].data[j].children;
                                    //如果option的data结构只有根节点名称，没有children，则无法正确显示
                                    _option.series[0].data[j].children = "";
                                }
                                //跳出所有循环
                                break;
                            }

                            //二级节点折叠展开
                            var len2 = _option.series[0].data[j].children.length;
                            for (var k = 0; k < len2; k++) {
                                if (_option.series[0].data[j].children[k].name == _name) {
                                    //判断该节点是否已关闭，若clickMap中存在k为当前节点名称的数据，并且不为空。则说明已关闭，要打开。
                                    if (clickMap.hasOwnProperty(_name) && clickMap[_name] != null) {
                                        //将clickMap中的该节点的子节点信息重新赋值给当前节点
                                        _option.series[0].data[j].children[k].children = clickMap[_name];
                                        clickMap[_name] = null;//成功打开后，将clickMap中的数据赋null
                                        f = true;
                                        //跳出所有循环。
                                        break;
                                    }
                                    //执行到这里，说明未关闭
                                    f = true;
                                    //若所点击的节点存在子节点
                                    if (_option.series[0].data[j].children[k].children) {
                                        //将子节点信息存入clickMap，形式【当前点击节点的name为key，子节点数据为value】
                                        clickMap[_option.series[0].data[j].children[k].name] = _option.series[0].data[j].children[k].children;
                                        //然后将_option中的当前子节点删除。
                                        delete _option.series[0].data[j].children[k].children;
                                    }
                                    //跳出所有循环
                                    break;
                                }
                                if (f)break;
                            }
                        }
                        if (f)break;
                    }
                    myChart.clear();
                    //重新赋值，渲染图表
                    myChart.setOption(_option);
                });
                // 使用刚指定的配置项和数据显示图表
                myChart.setOption(option);
                document.getElementById("dag").style.display = "";
            }

            //显示暂无数据
            function view() {
                document.getElementById("dag").style.display = "";
                var myChart = echarts.init(document.getElementById('dag'));
                var option = {
                    series: [
                        {
                            roam: true,
                        }
                    ]
                };
                myChart.setOption(option);
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


        }
    );