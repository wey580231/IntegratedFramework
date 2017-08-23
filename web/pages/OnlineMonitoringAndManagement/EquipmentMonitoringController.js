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
        var option;    //饼图的元素
        var myChart;   //饼图
        var freePlace;  //剩余存储位
        var totalPlace;  //总存储位
        var value = new Array();  //饼图的数据
        var name = ['剩余存储位','已用存储位'];  //饼图数据name
        var pieData = new Array();
        var dynamicChart;  //动图

        var timeTicket;  //定时器

        layer.load(0);
        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            loadRightFloatMenu();

            view();

            loadAGVInfo();

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
            myChart = echarts.init(document.getElementById('pieChart'));

            option = {
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'right'

                },

                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['65%', '85%'],
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

            value[0] = pieNodes[0].freePlace;
            value[1] = pieNodes[0].totalPlace - pieNodes[0].freePlace;

            for (var i = 0; i < 2; i++) {

                pieData[i] = {value: value[i],name:name[i]};
            }

            //图结构数据
            option.series[0].data = pieData;

            option.legend.data = name;

            console.log(name);

            console.log(option.series[0].data.length);
            for (i = 0; i < option.series[0].data.length; i++) {

                option.series[0].data[i].value = value[i];
                option.series[0].data[i].name = name[i];

            }

            myChart.setOption(option);
            document.getElementById("pieChart").style.display = "";
        }


        /*
        * 动图(AGVInfo)
        * */
        function loadAGVInfo(){
            //基于准备好的dom，初始化echarts图表
            dynamicChart = echarts.init(document.getElementById('dynamicChart'));

            option = {
                title : {
                    text: '动态数据'
                },
                tooltip : {
                    trigger: 'axis'   //鼠标悬浮的时候出现数据
                },
                legend: {
                    data:['电量']     //最上面的标签
                },

                dataZoom : {       //下面的手动调整时间（此处隐藏掉）
                    show : false,
                    start : 0,
                    end : 100
                },
                xAxis : [     //x轴
                    {
                        type : 'category',
                        boundaryGap : true,
                        data : (function (){
                            var now = new Date();
                            var res = [];
                            var len = 10;  //x轴长度为10
                            while (len--) {
                                res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                                now = new Date(now - 4000);   //横坐标隔多少秒，x轴加载以4秒为单位
                            }
                            return res;
                        })()
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        name : '电量'
                    }
                ],
                series : [

                    {
                        name:'电量',
                        type:'line',
                        data:(function (){         //从后台获取电量放这儿
                            var res = [];
                            var len = 10;
                            while (len--) {
                                res.push((Math.random()*10 + 5).toFixed(1) - 0);
                            }
                            return res;
                        })()
                    }
                ]
            };

            dynamicChart.setOption(option);

            var lastData = 100;
            var axisData;
            clearInterval(timeTicket);
            timeTicket = setInterval(function (){
                axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

                // 动态数据接口 addData
                dynamicChart.addData([

                    [
                        0,        // 系列索引
                        lastData, // 新增数据
                        false,    // 新增数据是否从队列头部插入
                        false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                        axisData  // 坐标轴标签
                    ]
                ]);
            }, 2100);


            document.getElementById("dynamicChart").style.display = "";
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