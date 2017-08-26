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
        var name = ['剩余存储位', '已用存储位'];  //饼图数据name
        var pieData = new Array();
        var dynamicChart;  //动图
        var dynamicData = {};  //动图的数据

        var timeTicket;  //定时器


        //每个表格最大显示行数
        var maxTableLineNum = 3;
        var defualtCarryListPageNum = 0;
        var defualtAssemblyCarryListPageNum = 0;
        var defualtAssemblyCenterListPageNum = 0;

        $scope.carryListPagePrvButton = function () {
            defualtCarryListPageNum = defualtCarryListPageNum - 1;
            var carryBody = {};
            carryBody.maxResults = maxTableLineNum;
            carryBody.firstResult = defualtCarryListPageNum * carryBody.maxResults;
            myHttpService.post(serviceList.getAllCarrysByFirstResultAndMaxResults, carryBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.CarryList = responseBody.tableList;
                $('#carryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#carryTableNextButton').attr('disabled', "true");
                }
                $('#carryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#carryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        $scope.carryListPageNextButton = function () {
            defualtCarryListPageNum = defualtCarryListPageNum + 1;
            var carryBody = {};
            carryBody.maxResults = maxTableLineNum;
            carryBody.firstResult = defualtCarryListPageNum * carryBody.maxResults;
            myHttpService.post(serviceList.getAllCarrysByFirstResultAndMaxResults, carryBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.CarryList = responseBody.tableList;
                $('#carryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#carryTableNextButton').attr('disabled', "true");
                }
                $('#carryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#carryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        $scope.AssemblyCarryListPagePrvButton = function () {
            defualtAssemblyCarryListPageNum = defualtAssemblyCarryListPageNum - 1;
            var assemblyCarryInfoBody = {};
            assemblyCarryInfoBody.maxResults = maxTableLineNum;
            assemblyCarryInfoBody.firstResult = defualtAssemblyCarryListPageNum * assemblyCarryInfoBody.maxResults;
            myHttpService.post(serviceList.getAllAssemblyCarrysByFirstResultAndMaxResults, assemblyCarryInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCarryList = responseBody.tableList;
                $('#assemblyCarryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCarryTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCarryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCarryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        $scope.AssemblyCarryListPageNextButton = function () {
            defualtAssemblyCarryListPageNum = defualtAssemblyCarryListPageNum + 1;
            var assemblyCarryInfoBody = {};
            assemblyCarryInfoBody.maxResults = maxTableLineNum;
            assemblyCarryInfoBody.firstResult = defualtAssemblyCarryListPageNum * assemblyCarryInfoBody.maxResults;
            myHttpService.post(serviceList.getAllAssemblyCarrysByFirstResultAndMaxResults, assemblyCarryInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCarryList = responseBody.tableList;
                $('#assemblyCarryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCarryTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCarryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCarryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        $scope.AssemblyCenterTablePrvButton = function () {
            defualtAssemblyCenterListPageNum = defualtAssemblyCenterListPageNum - 1;
            var AssemblyCenterInfoBody = {};
            AssemblyCenterInfoBody.maxResults = maxTableLineNum;
            AssemblyCenterInfoBody.firstResult = defualtAssemblyCenterListPageNum * assemblyCarryInfoBody.maxResults;
            myHttpService.post(serviceList.getAllAssemblyCentersByFirstResultAndMaxResults, AssemblyCenterInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCenterList = responseBody.tableList;
                $('#assemblyCenterTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCenterTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCenterTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCenterTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        $scope.AssemblyCenterTableNextButton = function () {
            defualtAssemblyCenterListPageNum = defualtAssemblyCenterListPageNum + 1;
            var AssemblyCenterInfoBody = {};
            AssemblyCenterInfoBody.maxResults = maxTableLineNum;
            AssemblyCenterInfoBody.firstResult = defualtAssemblyCenterListPageNum * assemblyCarryInfoBody.maxResults;
            myHttpService.post(serviceList.getAllAssemblyCentersByFirstResultAndMaxResults, AssemblyCenterInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCenterList = responseBody.tableList;
                $('#assemblyCenterTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCenterTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCenterTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCenterTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });
        }

        layer.load(0);
        $(function () {
            //初始化下拉数据
            $(".select2").select2();

            loadRightFloatMenu();

            view();

            //loadAGVInfo();

            myHttpService.post(serviceList.AllDeportInfoList).then(function successCallback(response) {
                deportData = response.data;
                $scope.deportData = response.data;
                loadRightFloatMenu();
                hideLoadingPage();
            });

            var carryBody = {};
            carryBody.maxResults = maxTableLineNum;
            carryBody.firstResult = 0;
            myHttpService.post(serviceList.getAllCarrysByFirstResultAndMaxResults, carryBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.CarryList = responseBody.tableList;
                $('#carryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#carryTableNextButton').attr('disabled', "true");
                }
                $('#carryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#carryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });

            var assemblyCarryInfoBody = {};
            assemblyCarryInfoBody.maxResults = maxTableLineNum;
            assemblyCarryInfoBody.firstResult = 0;
            myHttpService.post(serviceList.getAllAssemblyCarrysByFirstResultAndMaxResults, assemblyCarryInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCarryList = responseBody.tableList;
                $('#assemblyCarryTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCarryTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCarryTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCarryTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
                hideLoadingPage();
            });

            var AssemblyCenterInfoBody = {};
            AssemblyCenterInfoBody.maxResults = maxTableLineNum;
            AssemblyCenterInfoBody.firstResult = 0;
            myHttpService.post(serviceList.getAllAssemblyCentersByFirstResultAndMaxResults, AssemblyCenterInfoBody).then(function successCallback(response) {
                var responseBody = response.data;
                $scope.AssemblyCenterList = responseBody.tableList;
                $('#assemblyCenterTableNextButton').removeAttr("disabled");
                if (responseBody.firstIndexNum + responseBody.maxIndexNum > responseBody.totalPageNum) {
                    $('#assemblyCenterTableNextButton').attr('disabled', "true");
                }
                $('#assemblyCenterTablePrvButton').removeAttr("disabled");
                if (responseBody.firstIndexNum - 1 <= 0) {
                    $('#assemblyCenterTablePrvButton').attr('disabled', "true");
                }
                loadRightFloatMenu();
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

                layer.load();

                myHttpService.post(serviceList.DeportInfoList, id).then(function successCallback(response) {
                    var datas = response.data;
                    pieNodes.push(datas);
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

                pieData[i] = {value: value[i], name: name[i]};
            }

            //图结构数据
            option.series[0].data = pieData;

            option.legend.data = name;

            for (i = 0; i < option.series[0].data.length; i++) {

                option.series[0].data[i].value = value[i];
                option.series[0].data[i].name = name[i];

            }

            myChart.setOption(option);
            document.getElementById("pieChart").style.display = "";
        }


        myHttpService.get(serviceList.AllAGVInfoList).then(function (response) {
            dynamicData = response.data;
            hideLoadingPage();

            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

            function activeLastPointToolip(chart) {
                var points = chart.series[0].points;
                chart.tooltip.refresh(points[points.length - 1]);
            }

            $('#dynamicChart').highcharts({
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    events: {
                        load: function () {
                            // set up the updating of the chart each second
                            var series = this.series[0],
                                chart = this;
                            setInterval(function () {
                                var x = (new Date()).getTime(), y = dynamicData[dynamicData.length - 1].remainPower;
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart)
                            }, 10 * 1000);  //隔多长时间加载一次数据
                        }
                    }
                },
                title: {
                    text: '动态模拟实时数据'
                },
                xAxis: {
                    type: 'datetime',
                    maxZoom: 48 * 60 * 1000,  //x轴两点相隔10min
                    tickPixelInterval: 100  //x轴两点之间的间距（像素）
                },
                yAxis: {
                    title: {
                        text: '电量'
                    },
                    minRange: 20,
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2);
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                series: [{
                    name: '随机数据',
                    data: (function () {
                        // generate an array of random data
                        var data = [],
                            time = (new Date()).getTime(),
                            i;
                        dynamicData.forEach(
                            function handleFunction(value) {
                                data.push({
                                    x: value.reportTime,
                                    y: value.remainPower
                                });
                            }
                        );
                        return data;
                    }())
                }]
            }, function (c) {
                activeLastPointToolip(c)
            });
        });

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