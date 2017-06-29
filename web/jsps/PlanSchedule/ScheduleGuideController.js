/**
 * Created by zhaoqi on 2017/5/18.
 */
'use strict';
angular.module("IntegratedFramework.ScheduleGuideController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleGuide', {
            templateUrl: '/jsps/PlanSchedule/ScheduleGuide.jsp',
            controller: 'ScheduleGuideController'
        })
    }])
    .controller('ScheduleGuideController', function ($scope, $http, myHttpService, serviceList) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var operateId;
        var scheduleDays;
        var obj;//上次排程的json字符串
        var curobj;//当前排程的json字符串
        var ordId;
        var arr;
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

        //隐藏已订单窗口
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
            var nameVal = $("input[name='add-name']").val();
            var scheduleVal = $("input[name='add-schedule']").val();
            var rollTimeVal = $("input[name='add-rollTime']").val();
            var scheduleDaysVal = $("input[name='add-scheduleDays']").val();
            var t0Val = $("input[name='add-t0']").val();
            var t2Val = $("input[name='add-t2']").val();

            //未完成的记录
            var array = [];
            array.push(obj);
            array.push(curobj);

            console.log("两部分未完成的信息" + array);

            for (var i = 0; i < array.length; i++) {
                if (array[i].id == operateId) {
                    console.log("被选中的记录" + array[i]);
                    arr = array[i];
                    console.log("被选中的记录arr" + arr);
                }
            }

            var APSconfigs = {};
            APSconfigs.t0 = t0Val;
            APSconfigs.t2 = parseInt(t2Val);

            var orders = {};
            orders.id = operateId;

            var layouts = {};
            layouts.id = arr.layout.id;

            var resourceArr = [];
            var resources = {};
            resources.id = arr.resource.id;
            resourceArr.push(resources);

            var groupResourcesArr = [];
            var groupResources = {};
            //groupResources.id = arr.orders[0].idGroupResource;
            groupResources.id = arr.groupResource.id;
            groupResourcesArr.push(groupResources);

            var sitesArr = [];
            var sites = {};
            sites.id = arr.site.id;
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
            params.name = nameVal;
            params.scheduleWindow = parseInt(scheduleVal);
            params.rollTime = parseInt(rollTimeVal);
            params.layout = layouts;
            params.orders = orders;
            params.APSconfig = APSconfigs;
            params.resource = resourceArr;
            params.groupResource = groupResourcesArr;
            params.site = sitesArr;
            var data = JSON.stringify(params);
            console.log(data);
            $("#schedule").hide();
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                console.log("排程返回的数据:" + response.data);
                alert("请求成功，开始排程");
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
                console.log("获取上次排程信息状态" + response.status);
                console.log("获取上次排程信息" + response.data);
                obj = response.data;

                var startCalcTime = moment(obj.startCalcTime).format("YYYY-MM-DD");
                console.log("排程时间" + (new Date(startCalcTime)).getTime());

                //当前排程时间长度（b）
                scheduleDays = $("input[name='add-scheduleDays']").val();
                console.log("当前排程时间长度" + scheduleDays);

                //上次排程时间长度（c）
                var lastScheduleDays = obj.scheduleWindow;
                console.log("上次排程时间长度" + lastScheduleDays);
                //var lastScheduleDays = 7;

                //排程开始时间
                var startTime = moment().format("YYYY-MM-DD");
                console.log("当前时间" + startTime);

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
            myHttpService.get(serviceList.getLastScheduleInfo).then(function (response) {
                console.log("获取上次排程信息状态" + response.status);
                console.log("获取上次排程信息" + response.data);
                var obj = response.data;
                console.log(obj.orders);
                console.log(obj.orders[0].finished);
                var lastarray = [];
                for (var i = 0; i < obj.orders.length; i++) {
                    if (obj.orders[i].finished == false) {
                        var lastinfo = {};
                        lastinfo = (obj.orders[i]);
                        console.log(lastinfo);
                    } else {
                        console.log("都完成了！");
                    }
                    lastarray.push(lastinfo);
                }
                console.log(lastarray);
                $scope.lastarray = lastarray;
            });
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
            myHttpService.post(serviceList.LastInfo, data).then(function (response) {
                console.log("获取当前未完成信息" + response.status);
                console.log(response.data);
                curobj = response.data;
                $scope.curinfo = response.data;
            });
        };

        $scope.choosedOrder = function () {
            var rows = document.getElementById("orders").rows;
            var a = document.getElementsByName("check1");
            var arrchoosed = [];

            for (var i = 0; i < a.length; i++) {
                console.log("被选中" + a[i].checked);
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log("第几行" + row);
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
            $scope.form = arr;
        };
    });