/**
 * Created by wey580231 on 2017/7/8.
 */
'use strict';
angular.module("IntegratedFramework.PlanScheduleController", ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ScheduleGuide', {
            templateUrl: 'pages/PlanSchedule/PlanSchedule.html',
            controller: 'PlanScheduleController'
        })
    }])
    .controller('PlanScheduleController', function ($scope, $http, myHttpService, serviceList, renderTableService) {
        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var editData = [];//保存基础信息
        var operateId;
        var scheduleDays;
        var name;
        var rollTime;
        var lastobj = [];//上次排程的json字符串
        var curobj = [];//当前排程的json字符串
        var array = [];//两次未完成部分


        myHttpService.get(serviceList.ListSchedule).then(function (response) {
            $scope.scheduleList = response.data;
        });

        var pageCount = 0;
        var currPage = 0;
        var pageTipCount = 0;

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        $(document).ready(function () {
            resetContent();
            $(".MyPageTip").each(function () {
                $(this).css("width", 1 / pageTipCount * 100 + "%");
            });
            $("#tipHover").css("width", 1 / pageTipCount * 100 + "%");
        });

        //新建排程
        $scope.prepareNewSchedule = function () {
            resetContent();

            reset();
        }

        function resetContent() {
            $("#startSchedule").hide();
            $("#previouseStep").hide();
            $("#nextStep").show();

            pageCount = $(".MyPage").size();
            pageTipCount = $(".MyPageTip").size();

            $(".MyPage").eq(currPage).hide();
            currPage = 0;
            $(".MyPage").eq(currPage).show();

            choosePageTip();
        }

        function choosePageTip() {
            $("#tipHover").animate({"left": currPage / pageTipCount * 100 + "%"}, 250, function () {
                $("#tipHover").text($(".MyPageTip").eq(currPage).text());
            });
        }

        //上一步
        $scope.previous = function () {
            if (currPage >= 0) {
                $("#startSchedule").hide();
                $("#nextStep").show();

                $(".MyPage").eq(currPage).hide();
                currPage -= 1;
                $(".MyPage").eq(currPage).show();
            }

            if (currPage == 0) {
                $("#previouseStep").hide();
                $("#nextStep").show();
            }
            choosePageTip();
        }

        //下一步
        $scope.next = function () {
            if (currPage < pageCount - 1) {
                $("#previouseStep").show();
                $(".MyPage").eq(currPage).hide();
                currPage += 1;
                $(".MyPage").eq(currPage).show();
                $(".previouseStep").eq(currPage).show();
            }

            if (currPage == pageCount - 1) {
                $("#nextStep").hide();
                $("#startSchedule").show();
            }
            choosePageTip();

            showInfo();

            choosedOrder();

            reset();

            //getIdSelections();
        }

        //开始排程
        $scope.submitForm = function () {
            configAPS();
        }


        //基本信息检验
        var orderEditValidate = function () {
            var params = {};
            params.name = $("input[name='edit-name']").val();
            params.rollTime = $("input[name='edit-rollTime']").val();
            params.scheduleDays = $("input[name='edit-scheduleDays']").val();
            editData = params;

            if (!validate.checkLength(params.name) || !validate.checkChinese(params.name)) {
                $("#edit-name").removeClass("has-success");
                $("#edit-name").addClass("has-error");
            } else {
                $("#edit-name").removeClass("has-error");
                $("#edit-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.rollTime) || !validate.checkLength(params.rollTime)) {
                $("#edit-rollTime").removeClass("has-success");
                $("#edit-rollTime").addClass("has-error");
            } else {
                $("#edit-rollTime").removeClass("has-error");
                $("#edit-rollTime").addClass(" has-success");
            }

            if (!validate.checkNumber(params.scheduleDays) || !validate.checkLength(params.scheduleDays)) {
                $("#edit-scheduleDays").removeClass("has-success");
                $("#edit-scheduleDays").addClass("has-error");
            } else {
                $("#edit-scheduleDays").removeClass("has-error");
                $("#edit-scheduleDays").addClass(" has-success");
            }


            if (validate.checkLength(params.name) && validate.checkChinese(params.name) && validate.checkLength(params.rollTime) && validate.checkNumber(params.rollTime) &&
                validate.checkLength(params.scheduleDays) && validate.checkNumber(params.scheduleDays)) {
                return true;
            } else {
                return false;
            }
        };

        //显示订单信息
        function showInfo() {

                //开始访问当前未完成的记录
                curobj.splice(0, curobj.length);
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

                    $scope.info = curobj;
                });



        };


        //显示已选择订单的信息
        function choosedOrder() {
            var rows = document.getElementById("orders").rows;
            var a = document.getElementsByName("check1");
            var arrchoosed = [];

            for (var i = 0; i < a.length; i++) {
                console.log("被选中" + a[i].checked);
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    console.log("第几行被选中：" + row);
                    var params = {};
                    params.index = rows[row].cells[1].innerHTML;
                    params.id = rows[row].cells[2].innerHTML;
                    params.name = rows[row].cells[3].innerHTML;
                    params.origin = rows[row].cells[4].innerHTML;
                    params.priority = rows[row].cells[5].innerHTML;
                    params.t0 = rows[row].cells[6].innerHTML;
                    params.t1 = rows[row].cells[7].innerHTML;
                    params.t2 = rows[row].cells[8].innerHTML;
                    console.log(params);
                    arrchoosed.push(params);
                }
            }
            $scope.form = arrchoosed;
            console.log(arrchoosed);
        };

        // //日历排程染色
        // $(function () {
        //   /*  //当前排程时间长度（b）
        //     var scheduleDays = 30;
        //     //上次排程时间长度（c）
        //     var lastScheduleDays = 30;
        //     //距上次开始排程的日期差(c)
        //     var tempDays = 7;*/
        //     //排程开始时间
        //     var startTime = moment().format("YYYY-MM-DD");
        //     //排程结束时间
        //     var endTime = moment().add(scheduleDays, 'day').format("YYYY-MM-DD");
        //     $(document).ready(function () {
        //         // page is now ready, initialize the calendar...
        //         $('#calendar').fullCalendar({
        //             // put your options and callbacks here
        //             buttonText: {
        //                 today: '今天',
        //                 month: '月',
        //                 week: '周',
        //                 day: '天'
        //             },
        //             allDayText: '全天',
        //             monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        //             monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
        //             dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        //             dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        //             eventSources: [
        //                 {
        //                     url: 'http://localhost:8080/FullCalendar/getAllFullCalendarEvents.action',
        //                     type: 'POST',
        //                     data: {
        //                         startTime: startTime,
        //                         endTime: endTime
        //                     },
        //                     error: function () {
        //                         alert('there was an error while fetching events!');
        //                     }
        //                 }
        //             ]
        //             /* viewRender: function (view, element) {
        //                 //已执行时间窗口染色
        //                 for (var i = 1; i <= tempDays; i++) {
        //                     $("td[data-date='" + moment().add(-i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'red');
        //                 }
        //                 //时间窗口染色
        //                 for (var i = 0; i < lastScheduleDays - tempDays; i++) {
        //                     $("td[data-date='" + moment().add(i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'blue');
        //                 }
        //                 //剩余窗口染色
        //                 for (var i = 0; i < scheduleDays - (lastScheduleDays - tempDays); i++) {
        //                     $("td[data-date='" + moment().add((lastScheduleDays - tempDays) + i, "day").format('YYYY-MM-DD') + "']").css('backgroundColor', 'green');
        //                 }
        //             }*/
        //         });
        //     });
        // });



        //日历部分
        $scope.showSchedule = function () {
            //获取上次排程信息
            myHttpService.get(serviceList.getLastScheduleInfo).then(function successCallback(response) {
                console.log("获取上次排程信息状态");
                console.log(response.status);
                console.log("获取上次排程信息");
                console.log(response.data);
                var obj = response.data;

                var startCalcTime = moment(obj.startCalcTime).format("YYYY-MM-DD");
                console.log("上次排程计算开始时间" + startCalcTime);

                //当前排程时间长度（b）
                scheduleDays = $("input[name='add-scheduleDays']").val();
                console.log("当前排程时间长度" + scheduleDays);
                name = $("input[name='add-name']").val();
                rollTime = $("input[name='add-rollTime']").val();
                console.log("当前排程滚动周期" + rollTime);

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
                console.log("排成结束时间" + startTime);
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
                            // url: 'http://localhost:8080/IntegratedFramework/FullCalendar/getAllFullCalendarEvents.action',
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
                   /* viewRender: function (view, element) {
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
                    }*/

                });
                // $("#calendar").show();
            }, function errorCallback(response) {
                console.log("请求失败");
            });


        };

        //表格信息重置
        function reset() {
            $("input").val('');
        };


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


        //排程

        function configAPS() {

            // //未完成的记录
            // console.log("两部分未完成的记录");
            // console.log(array);
            // console.log(array.length);
            //
            // for (var i = 0; i < array.length; i++) {
            //     console.log(operateId);
            //     if (array[i].id == operateId) {
            //         arr = array[i];
            //         console.log("$$$$$$$$");
            //         console.log(arr);
            //     }
            // }

            var APSConfigs = {};
            APSConfigs.t0 = "";
            APSConfigs.t2 = "";

            var orders = [];
            console.log("订单");
            var params = {};
            params.id = operateId;
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

            var params = {};
            params.name = name;
            params.scheduleWindow = parseInt(scheduleDays);
            params.rollTime = parseInt(rollTime);
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
                console.log(response.status);
                curobj.splice(0, curobj.length);
                lastobj.splice(0, lastobj.length);
                array.splice(0, array.length);
                location.reload(true);
            }, function errorCallback(response) {
                alert("请求错误！");
            });
        };

    });