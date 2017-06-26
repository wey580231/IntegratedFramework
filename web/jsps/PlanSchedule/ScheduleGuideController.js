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
        var lastScheduleDays0;
        var date0;
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
        }

        //各表单选择时信息显示
        $scope.showOrder = function () {
            myHttpService.get(serviceList.ListOrder).then(function (response) {
                console.log(response);
                $scope.ord = response.data;
            });
        };

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
            conlose.log(ordId);
        }

        //隐藏选择订单窗口
        $scope.orderHide = function () {
            $("#chooseOrder").hide();
        }

        //隐藏已订单窗口
        $scope.hide = function () {
            $("#color_table").hide();
        }

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

            var APSconfigs = {};
            APSconfigs.t0 = t0Val;
            APSconfigs.t2 = parseInt(t2Val);

            var orders = {};
            orders.id = operateId;

            var layouts = {};
            layouts.id = 1;

            var resourceArr = [];
            var resources = {};
            resources.id = 2;
            resourceArr.push(resources);

            var groupResourcesArr = [];
            var groupResources = {};
            groupResources.id = 3;
            groupResourcesArr.push(groupResources);

            var sitesArr = []
            var sites = {};
            sites.id = 4;
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
            params.order = orders;
            params.APSconfig = APSconfigs;
            params.resource = resourceArr;
            params.groupResource = groupResourcesArr;
            params.site = sitesArr;
            var data = JSON.stringify(params);
            console.log(data);
            $("#schedule").hide();
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                alert("请求成功，开始排程");
            }, function errorCallback(response) {
                alert("请求错误！");
            })
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
        }

        $scope.showSchedule = function () {
            //获取上次排程信息
            myHttpService.get(serviceList.getLastScheduleInfo).then(function successCallback(response) {
                console.log("获取上次排程信息" + response.status);
                console.log(response.data);
                var obj = response.data;
                var startCalcTime = new Date(obj.startCalcTime);
                var endCalcTime = new Date(obj.endCalcTime);

                //当前排程时间长度（b）
                var scheduleDays = $("input[name='add-scheduleDays']").val();
                console.log("当前排程时间长度" + scheduleDays);

                //上次排程时间长度（c）
                //var data = eval('(' + obj + ')');
                var lastScheduleDays = (endCalcTime.getTime() - startCalcTime.getTime()) / (1000 * 60 * 60 * 24);
                console.log("上次排程时间长度" + lastScheduleDays);
                //var lastScheduleDays = 7;

                //距上次开始排程的日期差(c)
                var myDate = new Date();
                var temDays = (endCalcTime.getTime() - myDate.getTime()) / (1000 * 60 * 60 * 24);
                console.log("距上次开始排程的日期差" + temDays);
                //var tempDays = 30;

                //排程开始时间
                var startTime = moment().format("YYYY-MM-DD");
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


        }

        $scope.choosedOrder = function () {
            var rows = document.getElementById("orders").rows;
            var a = document.getElementsByName("check1");
            console.log(a.length);
            var table = document.getElementById("orders");
            var arr = new Array();

            for (var i = 0; i < a.length; i++) {
                console.log(a[i].checked);
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log(row);
                    var params = {};
                    params.id = rows[row].cells[1].innerHTML;
                    params.name = rows[row].cells[2].innerHTML;
                    params.origin = rows[row].cells[3].innerHTML;
                    params.priority = rows[row].cells[4].innerHTML;
                    params.t0 = rows[row].cells[5].innerHTML;
                    params.t1 = rows[row].cells[6].innerHTML;
                    params.t2 = rows[row].cells[7].innerHTML;
                    console.log(params);
                    arr.push(params);
                }
            }
            $scope.form = arr;
        };
    })