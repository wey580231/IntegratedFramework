/**
 * Created by hanchangming on 2017/5/16.
 */
'use strict';
angular.module("IntegratedFramework.EquipmentMonitoringController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/EquipmentMonitoring', {
            templateUrl: 'pages/OnlineMonitoringAndManagement/EquipmentMonitoring.html',
            controller: 'EquipmentMonitoringController'
        })
    }])

    .controller('EquipmentMonitoringController', function ($scope, $http, myHttpService, serviceList, validate, notification, renderTableService) {

        var deportData = [];  //下拉框数据
        var pieNodes = [];  //饼图的数据
        var value = [234,222];  //饼图的颜色

        layer.load(0);
        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            loadRightFloatMenu();

            //loadPieChart();

            myHttpService.post(serviceList.AllDeportInfoList).then(function successCallback(response) {
                deportData = response.data;
                $scope.deportData = response.data;
                loadRightFloatMenu();
                hideLoadingPage();
            });

            myHttpService.get(serviceList.CarryInfoList).then(function (response) {
                $scope.CarryList = response.data;

                hideLoadingPage();
            });

            myHttpService.get(serviceList.AssemblyCarryInfoList).then(function (response) {
                $scope.AssemblyCarryList = response.data;

                hideLoadingPage();
            });

            myHttpService.get(serviceList.AssemblyCenterInfoList).then(function (response) {
                $scope.AssemblyCenterList = response.data;

                hideLoadingPage();
            });
        });


        //下拉框事件改变
        $("#select").change(function () {

            pieNodes.splice(0, pieNodes.length);
            var DeportId;
            var val = $(this).children('option:selected').val();


            if (val.length > 0) {
                for (var i = 0; i < deportData.length; i++) {
                    if (deportData[i].deportName == val) {
                        DeportId = deportData[i].id;
                        break;
                    }
                }
                var params = {};
                params.id = DeportId;
                var id = JSON.stringify(params);
                console.log(id);

                layer.load();

                myHttpService.post(serviceList.DeportInfoList, id).then(function successCallback(response) {
                    var datas = response.data;
                    console.log(datas);

                    pieNodes.push(datas);

                    console.log(pieNodes);

                    loadPieChart();
                    hideLoadingPage();
                });
                $scope.$apply();
            } else {
                $scope.$apply();
                //显示暂无数据
                view();
            }

        });

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };


        /*
         * 饼图
         * */
        //- PIE CHART -

        function loadPieChart() {
            var myChart = echarts.init(document.getElementById('pieChart'));

            var option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'right',
                    data: ['已用存储位', '剩余存储位']
                },

                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    position: 'center',
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            }
                        }

                    }
                ]
            };

            //图结构数据
            option.series[0].data = pieNodes;


            myChart.setOption(option);
        }

        function view() {
            document.getElementById("pieChart").style.display = "";
            var myChart = echarts.init(document.getElementById('pieChart'));
            var option = {
                series: [
                    {
                        roam: true,
                    }
                ]
            };
            myChart.setOption(option);
        }

        /*
         * 温度
         * -----------------------
         */
        // We use an inline data source in the example, usually data would
        // be fetched from a server
        /*var data = [], totalPoints = 100;

        function getRandomData() {

            if (data.length > 0)
                data = data.slice(1);

            // Do a random walk
            while (data.length < totalPoints) {

                var prev = data.length > 0 ? data[data.length - 1] : 50,
                    y = prev + Math.random() * 10 - 5;

                if (y < 0) {
                    y = 0;
                } else if (y > 100) {
                    y = 100;
                }

                data.push(y);
            }

            // Zip the generated y values with the x values
            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]]);
            }

            return res;
        }

        var interactive_plot = $.plot("#interactive", [getRandomData()], {
            grid: {
                borderColor: "#f3f3f3",
                borderWidth: 1,
                tickColor: "#f3f3f3"
            },
            series: {
                shadowSize: 0, // Drawing is faster without shadows
                color: "#3c8dbc"
            },
            lines: {
                fill: true, //Converts the line chart to area chart
                color: "#3c8dbc"
            },
            yaxis: {
                min: 0,
                max: 100,
                show: true
            },
            xaxis: {
                show: true
            }
        });

        var updateInterval = 500; //Fetch data ever x milliseconds
        var realtime = "on"; //If == to on then fetch data every x seconds. else stop fetching
        function update() {

            interactive_plot.setData([getRandomData()]);

            // Since the axes don't change, we don't need to call plot.setupGrid()
            interactive_plot.draw();
            if (realtime === "on")
                setTimeout(update, updateInterval);
        }

        //INITIALIZE REALTIME DATA FETCHING
        if (realtime === "on") {
            update();
        }
        //REALTIME TOGGLE
        $("#realtime .btn").click(function () {
            if ($(this).data("toggle") === "on") {
                realtime = "on";
            }
            else {
                realtime = "off";
            }
            update();
        });*/
        /*
         * END INTERACTIVE CHART
         */


    });