/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleGuideController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleGuide', {
            templateUrl: 'jsps/PlanSchedule/ScheduleGuide.jsp',
            controller: 'ScheduleGuideController'
        })
    }])
    .controller('ScheduleGuideController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;
        var scheduleDays;
        var name;
        var rollTime;
        var obj;//上次排程的json字符串
        var curobj = [];//当前排程的json字符串
        var ordId;
        var arr;
        var array = [];
        var lastarray = [];
        /*var ordId;
         var resId;
         var resGroId;
         var siteId;
         var layId;*/

        //重新加载页面，取消选中状态
        var reload = function () {
            //取消checkbox选中状态
            $(".check").prop('checked', false);
            $("input").val('');
        };

        //各表单选择时信息显示
        $scope.showOrder = function () {
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                console.log(response);
                $scope.ord = response.data;
            });
        };

        $('#scheduleButton').click(function () {
            $("input").val('');
        });

        /*$scope.showResource = function () {
         myHttpService.get(serviceList.ListResource).then(function (response) {
         console.log(response);
         $scope.res = response.data;
         });
         };
         $scope.showGroupResource = function () {
         myHttpService.get(serviceList.ListGroupResource).then(function (response) {
         console.log(response);
         $scope.resGro = response.data;
         });
         };
         $scope.showSite = function () {
         myHttpService.get(serviceList.ListSite).then(function (response) {
         console.log(response);
         $scope.site = response.data;
         });
         };*/

        var updateSelected = function (action, id) {
            operateId = id;
            if (action == 'add' & selectedCheckArray.indexOf(id) == -1) {
                selectedCheckArray.push(id);
                console.log(id + "被选中");
            }
            if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
                selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
                console.log(id + "取消选中");
            }
        };

        //用于监控点击事件，checkbox选择了就更新
        $scope.updateSelection = function ($event, id) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');
            updateSelected(action, id);
        };
        $scope.isSelected = function (id) {
            return selectedCheckArray.indexOf(id) >= 0;
        };

        //勾选订单后，点击确定，记录所选id
        $scope.checkOrId = function () {
            ordId = operateId;
            console.log("选中的id" + ordId);
        };

        //隐藏选择订单窗口
        $scope.orderHide = function () {
            $("#chooseOrder").hide();
        };

        //隐藏已选订单窗口
        $scope.hide = function () {
            $("#color_table").hide();
        };

        //勾选资源后，记录所选id
        /*$scope.checkReId = function () {
         resId = operateId;
         }
         $scope.reHide = function () {
         $("#chooseResource").hide()
         }
         //勾选资源工组后，记录所选id
         $scope.checkGrReId = function () {
         resGroId = operateId;
         }
         $scope.grReHide = function () {
         $("#chooseGroupResource").hide()
         }
         //勾选资源工位后，记录所选id
         $scope.checkSiId = function () {
         siteId = operateId;
         }
         $scope.siteHide = function () {
         $("#chooseSite").hide()
         }*/

        //排程
        $scope.configAPS = function () {
            alert("开始");

            //未完成的记录
            //array.push(obj);
            for (var i = 0; i < curobj.length; i++) {
                array.push(curobj[i]);
            }

            console.log("两部分未完成的记录");
            console.log(array);
            console.log(array[0]);
            console.log(array.length);

            /*for (var i = 0; i < array.length; i++) {
             for (var j = 0; j < array[i].orders.length; j++) {
             if (array[i].orders[j].id == operateId) {
             arr = array[i];
             console.log("$$$$$$$$");
             console.log(arr);
             }
             }
             break;
             }*/

            for (var i = 0; i < array.length; i++) {
                if (array[i].id == operateId) {
                    arr = array[i];
                    console.log("$$$$$$$$");
                    console.log(arr);
                }
                break;
            }

            var APSConfigs = {};
            //APSconfigs.t0 = moment(arr.apsStartTime).format('YYYY-MM-DD HH:mm:ss');
            //APSconfigs.t2 = moment(arr.apsEndTime).format('YYYY-MM-DD HH:mm:ss');
            APSConfigs.t0 = "";
            APSConfigs.t2 = "";

            var orders = [];
            console.log("订单");
            var params = {};
            params.id = parseInt(arr.id);
            orders.push(params);

            var layouts = {};
            layouts.id = 2;

            console.log("资源");
            var resourceArr = [];
            var resources = {};
            resources.id = 2;
            resourceArr.push(resources);

            console.log("工组");
            var groupResourcesArr = [];
            var groupResources = {};
            groupResources.id = 2;
            groupResourcesArr.push(groupResources);

            console.log("工位");
            var sitesArr = [];
            var sites = {};
            sites.id = 2;
            sitesArr.push(sites);

            /*var layouts = {};
             layouts.id = layId;
             var resourceArr = [];
             var resources = {};
             resources.id = parseInt(resId);
             resourceArr.push(resources);
             var groupResourcesArr = [];
             var groupResources = {};
             groupResources.id = parseInt(resGroId);
             groupResourcesArr.push(groupResources);
             var sitesArr = []
             var sites = {};
             sites.id = parseInt(siteId);
             sitesArr.push(sites);*/

            var params = {};
            params.name = name;
            params.scheduleWindow = parseInt(scheduleDays);
            params.rollTime = rollTime;
            params.APSConfig = APSConfigs;
            params.layout = layouts;
            params.orders = orders;
            params.resources = resourceArr;
            params.groupResource = groupResourcesArr;
            params.site = sitesArr;
            var data = JSON.stringify(params);
            console.log(data);
            $("#schedule").hide();
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                console.log("排程返回的数据:");
                console.log(response.data);
                alert("请求成功，排程成功");
            }, function errorCallback(response) {
                alert("请求错误！");
            })
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
        };


        //日历部分
        $scope.showSchedule = function () {
            //获取上次排程信息
            myHttpService.get(serviceList.getLastScheduleInfo).then(function successCallback(response) {
                console.log("获取上次排程信息状态");
                console.log(response.status);
                console.log("获取上次排程信息");
                console.log(response.data);
                obj = response.data;

                var startCalcTime = moment(obj.startCalcTime).format("YYYY-MM-DD");
                console.log("上次排程计算开始时间" + startCalcTime);

                //当前排程时间长度（b）
                scheduleDays = $("input[name='add-scheduleDays']").val();
                name = $("input[name='add-name']").val();
                rollTime = $("input[name='add-rollTime']").val();
                console.log("当前排程时间长度" + scheduleDays);

                //上次排程时间长度（c）
                var lastScheduleDays = obj.scheduleWindow;
                console.log("上次排程时间长度" + lastScheduleDays);
                //var lastScheduleDays = 7;

                //排程开始时间
                var startTime = moment().format("YYYY-MM-DD");
                console.log("排成开始时间" + startTime);

                //距上次开始排程的日期差(c)
                var tempDays = ((new Date(startTime)).getTime() - (new Date(startCalcTime)).getTime()) / (24 * 60 * 60 * 1000);
                console.log("距上次开始排程的日期差" + ((new Date(startTime)).getTime() - (new Date(startCalcTime)).getTime()) / (24 * 60 * 60 * 1000));
                //var tempDays = 30;

                //排程结束时间
                var endTime = moment().add(scheduleDays, 'day').format("YYYY-MM-DD");
                $(document).ready(function () {
                    // page is now ready, initialize the calendar...
                    $('#calendar').fullCalendar({
                        // put your options and callbacks here
                        buttonText: {
                            today: '今天',
                            month: '月',
                            week: '周',
                            day: '天'
                        },
                        allDayText: '全天',
                        monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                        monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
                        dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                        dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                        eventSources: [
                            {
                                url: 'http://localhost:8080/FullCalendar/getAllFullCalendarEvents.action',
                                type: 'POST',
                                data: {
                                    startTime: startTime,
                                    endTime: endTime
                                },
                                error: function () {
                                    alert('there was an error while fetching events!');
                                }
                            }
                        ],
                        viewRender: function (view, element) {
                            //已执行时间窗口染色
                            for (var i = 1; i <= tempDays; i++) {
                                $("td[data-date='" + moment().add(-i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'red');
                            }
                            //时间窗口染色
                            for (var i = 0; i < lastScheduleDays - tempDays; i++) {
                                $("td[data-date='" + moment().add(i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'blue');
                            }
                            //剩余窗口染色
                            for (var i = 0; i < scheduleDays - (lastScheduleDays - tempDays); i++) {
                                $("td[data-date='" + moment().add((lastScheduleDays - tempDays) + i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'green');
                            }
                        }
                    });
                });
            }, function errorCallback(response) {
                console.log("请求失败");
            });


        };

        $scope.showLastInfo = function () {
            myHttpService.get(serviceList.getLastScheduleInfo).then(function successCallback(response) {
                console.log("获取上次排程信息状态");
                console.log(response.status);
                console.log("获取上次排程信息");
                console.log(response.data);
                obj = response.data;
                console.log(obj.orders);
                console.log(obj.orders[0].finished);
                console.log(obj.orders[1].finished);
                console.log(obj.orders.length);

                for (var i = 0; i < obj.orders.length; i++) {
                    if (obj.orders[i].finished == false) {
                        var lastinfo = {};
                        lastinfo = (obj.orders[i]);
                        console.log(lastinfo);
                        lastarray.push(lastinfo);
                    } else {
                        console.log("完成了！");
                    }
                }
                $scope.lastarray = lastarray;
                console.log(lastarray);
            }, function errorCallback(response) {
                alert("请求错误！");
            })
        };


        $scope.showCurInfo = function () {
            var cur = {};
            var startTime = moment().format("YYYY-MM-DD");
            console.log("当前开始时间" + startTime);
            cur.startTime = (new Date(startTime)).getTime();

            console.log("当前排程时间" + scheduleDays);
            var scheduleDays0 = scheduleDays;

            var endTime = moment().add(scheduleDays0, 'day').format("YYYY-MM-DD");
            console.log("当前结束时间" + endTime);
            cur.endTime = (new Date(endTime)).getTime();

            cur.isFinished = false;

            var data = JSON.stringify(cur);

            console.log("当前排程json字符串" + data);

            myHttpService.post(serviceList.CurInfo, data).then(function (response) {
                console.log("获取当前排程信息" + response.status);
                console.log(response.data);
                for (var i = 0; i < response.data.length; i++) {
                    curobj.push(response.data[i]);
                }
                $scope.curinfo = curobj;
                console.log(curobj);
            });
        };

        /*$scope.showInfo = function () {
         for (var i = 0; i < curobj.length; i++) {
         if (curobj[i].id == lastarray[0].id) {
         $scope.lastarray = lastarray;
         break;
         } else {
         $scope.lastarray = lastarray;
         $scope.curinfo = curobj;
         }
         }
         }*/

        $scope.choosedOrder = function () {
            var rows = document.getElementById("orders").rows;
            var a = document.getElementsByName("check1");
            var arrchoosed = [];

            for (var i = 0; i < a.length; i++) {
                console.log("被选中" + a[i].checked);
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log("第几行被选中：" + row);
                    var params = {};
                    params.id = rows[row].cells[1].innerHTML;
                    params.name = rows[row].cells[2].innerHTML;
                    params.origin = rows[row].cells[3].innerHTML;
                    params.priority = rows[row].cells[4].innerHTML;
                    params.t0 = rows[row].cells[5].innerHTML;
                    params.t1 = rows[row].cells[6].innerHTML;
                    params.t2 = rows[row].cells[7].innerHTML;
                    console.log(params);
                    arrchoosed.push(params);
                }
            }
            $scope.form = arrchoosed;
            console.log(arrchoosed);
        };
    });