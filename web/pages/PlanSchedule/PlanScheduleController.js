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
        var editData = [];//保存基础信息
        var operateId;
        var scheduleDays;
        var name;
        var rollTime;
        var lastobj = [];//上次排程的json字符串
        var curobj = [];//当前排程的json字符串
        var ordId;
        var arr;//需要排程的那条记录
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

            //getIdSelections();
        }

        $scope.submitForm = function () {

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
            var obj = [];
            obj.splice(0, obj.length);
            myHttpService.get(serviceList.getLastScheduleInfo).then(function successCallback(response) {
                console.log("获取上次排程信息状态");
                console.log(response.status);
                console.log("获取上次排程信息");
                console.log(response.data);
                obj = response.data;

                for (var i = 0; i < obj.orders.length; i++) {
                    if (obj.orders[i].finished == false) {
                        var lastinfo = {};
                        lastinfo = (obj.orders[i]);
                        lastobj.push(lastinfo);
                    } else {
                        console.log("完成了！");
                    }
                }

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

                    //剔除相同的记录
                    //把上一次记录push到array
                    for (var i = 0; i < lastobj.length; i++) {
                        array.push(lastobj[i]);
                        console.log("循环一次后");
                        for (var j = 0; j < curobj.length; j++) {
                            array.push(curobj[j]);
                        }
                    }

                    console.log("删除前的数组");
                    var result = [];
                    for (var i = 0; i < array.length; i++) {
                        /*if(array[i].id==array[i+1].id){
                         array.splice(i, 1);
                         console.log(array);
                         }*/
                        var flag = true;
                        for (var j = i; j < array.length - 1; j++) {
                            if (array[i].id == array[j + 1].id) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            result.push(array[i])
                        }
                    }
                    console.log("删除后的数组");
                    array = result;
                    console.log(array);

                    $scope.info = array;
                });

            }, function errorCallback(response) {
                alert("请求错误！");

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

        $(function () {
            //当前排程时间长度（b）
            var scheduleDays = 30;
            //上次排程时间长度（c）
            var lastScheduleDays = 30;
            //距上次开始排程的日期差(c)
            var tempDays = 7;
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
        });
    });