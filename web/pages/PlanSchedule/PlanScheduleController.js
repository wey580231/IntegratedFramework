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
    .controller('PlanScheduleController', function ($scope, $http, myHttpService, serviceList, renderTableService, validate, notification) {

        layer.load(0);

        var selectedCheckArray = [];    //选中的checkbox的id值集合
        var scheduleDays;
        var name;
        var rollTime;
        //var curobj = [];//当前排程的json字符串
        var array = [];//两次未完成部分
        var orders = [];
        var layouts = {};

        $(function () {
            myHttpService.get(serviceList.ListSchedule).then(function (response) {
                $scope.scheduleList = response.data;

                hideLoadingPage();
            });
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
            document.getElementById("nextStep").disabled = true;
            document.getElementById("startSchedule").disabled = true;
        });

        //新建排程
        $scope.prepareNewSchedule = function () {
            resetContent();
        };

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
        };

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

            if (orders.length == 0) {
                showOrderInfo();
            }

            showLayoutInfo();
            document.getElementById("startSchedule").disabled = true;

            //choosedOrder();
            //getIdSelections();
        };

        //开始排程
        $scope.submitForm = function () {

            //保存选中的布局信息
            for (var i = 0; i < selectedCheckArray.length; i++) {
                var params = {};
                params.id = selectedCheckArray[i];
                layouts = params;

            }
            selectedCheckArray.splice(0, selectedCheckArray.length);
            console.log("所选择的布局信息");
            console.log(layouts);

            configAPS();
        };


        //基本信息检验
        $scope.infoValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.rollTime = $("input[name='add-rollTime']").val();
            params.scheduleDays = $("input[name='add-scheduleDays']").val();


            if (!validate.checkLength(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }

            if (!validate.checkNumber(params.rollTime) || !validate.checkLength(params.rollTime)) {
                $("#add-rollTime").removeClass("has-success");
                $("#add-rollTime").addClass("has-error");
            } else {
                $("#add-rollTime").removeClass("has-error");
                $("#add-rollTime").addClass(" has-success");
            }

            if (!validate.checkNumber(params.scheduleDays) || !validate.checkLength(params.scheduleDays)) {
                $("#add-scheduleDays").removeClass("has-success");
                $("#add-scheduleDays").addClass("has-error");
            } else {
                $("#add-scheduleDays").removeClass("has-error");
                $("#add-scheduleDays").addClass(" has-success");
            }

            if (validate.checkLength(params.rollTime) && validate.checkNumber(params.rollTime) &&
                validate.checkLength(params.scheduleDays) && validate.checkNumber(params.scheduleDays) && validate.checkLength(params.name)) {
                document.getElementById("nextStep").disabled = "";
                showSchedule();

                return true;

            } else {
                document.getElementById("nextStep").disabled = true;
                return false;
            }
        };


        //显示订单信息
        function showOrderInfo() {
            //开始访问当前未完成的记录
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

            console.log("当前json字符串" + data);

            myHttpService.post(serviceList.CurInfo, data).then(function (response) {
                var curorder = [];
                for (var i = 0; i < response.data.length; i++) {
                    curorder.push(response.data[i]);
                }
                $scope.ordinfo = curorder;
                //curorder.splice(0, curorder.length);

                if (selectedCheckArray.length == 0) {
                    document.getElementById("nextStep").disabled = true;
                }

                for (var i = 0; i < selectedCheckArray.length; i++) {
                    var params = {};
                    params.id = selectedCheckArray[i];
                    orders.push(params);
                }
                selectedCheckArray.splice(0, selectedCheckArray.length);

            });
        }


        /*$("#check1").click(function () {
            if ($(this).attr("checked") == true) {
                //当前为选中状态
                document.getElementById("nextStep").disabled = "";
            } else {
                //当前为不选中状态
                document.getElementById("nextStep").disabled = true;
            }
        })*/



        function showLayoutInfo() {
            myHttpService.get(serviceList.getAllLayout).then(function (response) {
                var curlayout = [];
                var layout = response.data;
                for (var i = 0; i < layout.data.length; i++) {
                    /* var temp = layout.data[i].layoutId;
                     delete(layout.data[i].layoutId);
                     layout.data[i].id = temp;*/
                    curlayout.push(layout.data[i]);
                }

                $scope.layout = curlayout;
                //curlayout.splice(0, curlayout.length);
            });

        }

        /*
         function getChoosedOrder() {
         for (var i = 0; i < selectedCheckArray.length; i++) {
         var params = {};
         params.id = selectedCheckArray[i];
         orders.push(params);
         }
         console.log("所选择的订单信息");
         console.log(orders);
         selectedCheckArray.splice(0, selectedCheckArray.length);
         }
         */

        /*function getChoosedLayout() {
         for (var i = 0; i < selectedCheckArray.length; i++) {
         var params = {};
         params.id = selectedCheckArray[i];
         layouts.push(params);
         }
         console.log("所选择的布局信息");
         console.log(layouts);
         selectedCheckArray.splice(0, selectedCheckArray.length);
         }
         */
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
        }

        //日历部分
        var showSchedule = function () {

            //当前排程时间长度（b）
            scheduleDays = $("input[name='add-scheduleDays']").val();
            console.log("当前排程时间长度" + scheduleDays);

            name = $("input[name='add-name']").val();
            rollTime = $("input[name='add-rollTime']").val();
            console.log("当前排程滚动周期" + rollTime);

            //排程开始时间
            var startTime = moment().format("YYYY-MM-DD");
            console.log("排成开始时间" + startTime);

            //排程结束时间
            var endTime = moment().add(scheduleDays, 'day').format("YYYY-MM-DD");
            console.log("排成结束时间" + startTime);

            $("#calendar").show();
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
                /*viewRender: function (view, element) {
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


        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };

        $scope.hideCalendar = function () {
            $("#calendar").hide();
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };

        $scope.showCalendar = function () {
            $("#calendar").show();
        };

        var updateSelected = function (action, id) {
            //operateId = id;
            if (action == 'add' & selectedCheckArray.indexOf(id) == -1) {
                selectedCheckArray.push(id);
                document.getElementById("nextStep").disabled = "";
                document.getElementById("startSchedule").disabled = "";
                console.log(id + "被选中");
            }
            if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
                selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
                document.getElementById("nextStep").disabled = true;
                document.getElementById("startSchedule").disabled = true;
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

            var APSConfigs = {};
            APSConfigs.t0 = "";
            APSConfigs.t2 = "";

            /*      for (var i = 0; i < selectedCheckArray.length; i++) {
             var params = {};
             params.id = selectedCheckArray[i];
             orders.push(params);
             }*/

            /*   var layouts = {};
             layouts.id = 2;*/

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
            $("#modal-add").hide();
            $(".modal-backdrop").remove();
            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                //清空所用的数组和变量
                array.splice(0, array.length);
                selectedCheckArray.splice(0, selectedCheckArray.length);
                orders.splice(0, orders.length);
                delete layouts.id;
                name = "";
                scheduleDays = "";
                rollTime = "";
                var data = response.data;
                if (data.result == "error") {
                    notification.sendNotification("alert", "排程失败");
                } else {
                    //location.reload(true);
                    //刷新表格
                    myHttpService.get(serviceList.ListSchedule).then(function (response) {
                        $scope.scheduleList = response.data;
                    });
                    notification.sendNotification("alert", "排程成功");
                }
            }, function errorCallback() {
                notification.sendNotification("alert", "请求失败");
            });
            document.getElementById("nextStep").disabled = true;
        }
    });