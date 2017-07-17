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
    .controller('PlanScheduleController', function ($scope, $location, $http, myHttpService, serviceList, renderTableService, validate, notification) {

        layer.load(0);

        var selectedCheckArray = []; //选中的checkbox的id值集合
        var scheduleDays;
        var name;
        var rollTime;
        var array = [];//未完成部分
        var orders = [];
        var layouts = {};

        var pageCount = 0;
        var currPage = 0;
        var pageTipCount = 0;

        $(function () {
            resetContent();
            $(".MyPageTip").each(function () {
                $(this).css("width", 1 / pageTipCount * 100 + "%");
            });
            $("#tipHover").css("width", 1 / pageTipCount * 100 + "%");
            document.getElementById("nextStep").disabled = true;
            document.getElementById("startSchedule").disabled = true;

            myHttpService.get(serviceList.ListSchedule).then(function (response) {
                $scope.scheduleList = response.data;
                hideLoadingPage();
            });
        });

        //跳转至交互优化界面
        $scope.interactiveSchedule = function () {
            layer.confirm('是否优化此次排程？', {
                btn: ['确定', '取消']
            }, function () {
                layer.msg('页面跳转中...', {icon: 1});
                setTimeout(function () {
                    //TODO 待解决path不能直接跳转问题
                    $location.path('/Interactive');
                    window.location.href = $location.absUrl();
                }, 1200);
            }, function () {

            });
        };

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //新建排程
        $scope.prepareNewSchedule = function () {
            myHttpService.get(serviceList.queryApsState).then(function (response) {
                if (response.data.result == "ok") {
                    if (response.data.data.state == 0) {
                        $("#modal-add").modal({show: 'true'});
                    } else {
                        layer.msg('APS正在计算中，无法排程!', {icon: 2});
                    }
                } else {
                    layer.msg('查询APS状态失败，请重试!', {icon: 2});
                }
            });

            resetContent();
        };

        //重置页面内容信息
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

            array.splice(0, array.length);
            selectedCheckArray.splice(0, selectedCheckArray.length);
            orders.splice(0, orders.length);
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

            //布局查询
            if (currPage == 1) {
                showLayoutInfo();
            } else if (currPage == 2 && orders.length == 0) {
                showOrderInfo();
            }

            if (selectedCheckArray.length <= 0) {
                document.getElementById("nextStep").disabled = true;
                document.getElementById("startSchedule").disabled = true;
            }

            document.getElementById("startSchedule").disabled = true;
        };

        //开始排程
        $scope.submitForm = function () {
            //保存选中的布局信息
            for (var i = 0; i < selectedCheckArray.length; i++) {
                var params = {};
                params.id = selectedCheckArray[i];
                layouts = params;
            }
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
            cur.startTime = (new Date(startTime)).getTime();

            var scheduleDays0 = scheduleDays;

            var endTime = moment().add(scheduleDays0, 'day').format("YYYY-MM-DD");
            cur.endTime = (new Date(endTime)).getTime();

            cur.isFinished = false;

            var data = JSON.stringify(cur);

            myHttpService.post(serviceList.CurInfo, data).then(function (response) {
                var curorder = [];
                for (var i = 0; i < response.data.length; i++) {
                    curorder.push(response.data[i]);
                }
                $scope.ordinfo = curorder;

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

        //显示布局信息
        function showLayoutInfo() {
            myHttpService.get(serviceList.getAllLayout).then(function (response) {
                var curlayout = [];
                var layout = response.data;
                for (var i = 0; i < layout.data.length; i++) {
                    curlayout.push(layout.data[i]);
                }

                $scope.layout = curlayout;
            });
        }

        //显示已选择订单的信息
        function choosedOrder() {
            var rows = document.getElementById("orders").rows;
            var a = document.getElementsByName("check1");
            var arrchoosed = [];

            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    var row = a[i].parentElement.parentElement.rowIndex;
                    var params = {};
                    params.index = rows[row].cells[1].innerHTML;
                    params.id = rows[row].cells[2].innerHTML;
                    params.name = rows[row].cells[3].innerHTML;
                    params.origin = rows[row].cells[4].innerHTML;
                    params.priority = rows[row].cells[5].innerHTML;
                    params.t0 = rows[row].cells[6].innerHTML;
                    params.t1 = rows[row].cells[7].innerHTML;
                    params.t2 = rows[row].cells[8].innerHTML;
                    arrchoosed.push(params);
                }
            }
            $scope.form = arrchoosed;
        }

        //日历部分
        var showSchedule = function () {
            //当前排程时间长度（b）
            scheduleDays = $("input[name='add-scheduleDays']").val();

            name = $("input[name='add-name']").val();
            rollTime = $("input[name='add-rollTime']").val();

            var startTime = moment().format("YYYY-MM-DD");
            var endTime = moment().add(scheduleDays, 'day').format("YYYY-MM-DD");

            $("#calendar").show();
            $('#calendar').fullCalendar({
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
                if (selectedCheckArray.length > 0) {
                    document.getElementById("nextStep").disabled = "";
                    document.getElementById("startSchedule").disabled = "";
                }
            }

            if (action == 'remove' && selectedCheckArray.indexOf(id) != -1) {
                selectedCheckArray.splice(selectedCheckArray.indexOf(id), 1);
                if (selectedCheckArray.length == 0) {
                    document.getElementById("nextStep").disabled = true;
                    document.getElementById("startSchedule").disabled = true;
                }
            }
        };

        //用于监控点击事件，checkbox选择了就更新
        $scope.updateSelection = function ($event, id) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');

            //布局页面，只选择一个
            if (currPage == 1 && checkbox.checked && selectedCheckArray.length >= 1) {
                checkbox.checked = false;
                return;
            }

            updateSelected(action, id);
        };

        $scope.isSelected = function (id) {
            return selectedCheckArray.indexOf(id) >= 0;
        };

        //排程
        function configAPS() {
            if (selectedCheckArray.length <= 0) {
                selectedCheckArray.splice(0, selectedCheckArray.length);
                notification.sendNotification("alert", "至少选择一条订单信息!");
                return false;
            } else {
                var APSConfigs = {};
                APSConfigs.t0 = "";
                APSConfigs.t2 = "";


                var resourceArr = [];
                var resources = {};
                resources.id = 2;
                resourceArr.push(resources);

                var groupResourcesArr = [];
                var groupResources = {};
                groupResources.id = 2;
                groupResourcesArr.push(groupResources);

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
                        notification.sendNotification("confirm", "排程成功");
                    }
                }, function errorCallback() {
                    notification.sendNotification("alert", "请求失败");
                });
                document.getElementById("nextStep").disabled = true;
            }
        }

    });