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
        var apsStart;
        var apsEnd;
        var scheduleOption;
        var modeSchedule;
        var array = [];//未完成部分

        var idVal;
        var id_params = {}; //保存选中的记录的id信息

        var orders = [];
        var layouts = {};

        var PageInfo = {};
        PageInfo.selectedIndex = new Array();     //每个页面中保存选择的索引
        PageInfo.data = new Array();              //每个页面对应的数据信息，只加载一次

        var pageCount = 0;
        var currPage = 0;
        var pageTipCount = 0;


        $(function () {
            pageCount = $(".MyPage").size();
            for (var i = 0; i < pageCount; i++) {
                PageInfo.selectedIndex[i] = new Array();
                PageInfo.data[i] = new Array();
            }

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
            var msg = "是否优化此次排程？";
            if (confirm(msg) == true) {
                notification.sendNotification("confirm", "页面跳转中...");
                //layer.msg('页面跳转中...', {icon: 1});
                setTimeout(function () {
                    //TODO 待解决path不能直接跳转问题
                    $location.path('/Interactive');
                    window.location.href = $location.absUrl();
                }, 1200);
            } else {

            }
        };

        //渲染checkBox样式
        $scope.renderTable = function ($last) {
            renderTableService.renderTable($last);
        };

        //隐藏日历控件信息
        function hideCalendar() {
            $("#calendar").hide();
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
        };

        //新建排程
        $scope.prepareNewSchedule = function () {
            myHttpService.get(serviceList.queryApsState).then(function (response) {
                if (response.data.result == "ok") {
                    if (response.data.data.state == 0) {
                        $('#modal-add').modal({backdrop: 'static', keyboard: false});
                        $("#modal-add").show();
                        document.getElementById("start").value = getCurDate();
                        hideCalendar();
                    } else {
                        notification.sendNotification("alert", "查询APS状态失败");

                        // layer.msg('APS正在计算中，无法排程!', {icon: 2});
                    }
                } else {
                    notification.sendNotification("alert", "查询APS状态失败，请重试!");
                    // layer.msg('查询APS状态失败，请重试!', {icon: 2});
                }
            }, function (response) {
            });
            resetContent();
        };
        //当前时间显示
        function getCurDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
            return currentdate;
        }


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
            orders.splice(0, orders.length);

            document.getElementById("start").value = getCurDate();
            //清空页面信息
            for (var i = 0; i < pageCount; i++) {
                PageInfo.selectedIndex[i].splice(0, PageInfo.selectedIndex[i].length);
                PageInfo.data[i].splice(0, PageInfo.data[i].length);
            }
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

            //布局查询
            if (currPage == 1) {
                if (PageInfo.selectedIndex[currPage].length <= 0) {
                    document.getElementById("nextStep").disabled = true;
                } else {
                    document.getElementById("nextStep").disabled = false;
                }
            }
            //订单查询
            else if (currPage == 2) {
                if (PageInfo.selectedIndex[currPage].length <= 0) {
                    document.getElementById("startSchedule").disabled = true;
                } else {
                    document.getElementById("startSchedule").disabled = false;
                }
            }
            //第一页
            else {
                document.getElementById("nextStep").disabled = false;
            }
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
                if (PageInfo.data[currPage].length == 0) {
                    showLayoutInfo();
                }
                if (PageInfo.selectedIndex[currPage].length <= 0) {
                    document.getElementById("nextStep").disabled = true;
                } else {
                    document.getElementById("nextStep").disabled = false;
                }
            }
            //订单查询
            else if (currPage == 2) {
                if (PageInfo.data[currPage].length == 0) {
                    showOrderInfo();
                }
                if (PageInfo.selectedIndex[currPage].length <= 0) {
                    document.getElementById("startSchedule").disabled = true;
                } else {
                    document.getElementById("startSchedule").disabled = false;
                }
            }
        };

        //查询布局信息
        function showLayoutInfo() {
            myHttpService.get(serviceList.getAllLayout).then(function (response) {
                var layout = response.data;
                for (var i = 0; i < layout.data.length; i++) {
                    PageInfo.data[currPage].push(layout.data[i]);
                }
                $scope.layout = PageInfo.data[currPage];
            });
        }

        //查询订单信息
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
                for (var i = 0; i < response.data.length; i++) {
                    PageInfo.data[currPage].push(response.data[i]);
                }
                $scope.ordinfo = PageInfo.data[currPage];

            });
        }

        //开始排程
        $scope.submitForm = function () {

            for (var i = 0; i < pageCount; i++) {
                if (i == 1) {
                    layouts.id = PageInfo.selectedIndex[i][0];
                }
                else if (i == 2) {
                    for (var j = 0; j < PageInfo.selectedIndex[i].length; j++) {
                        var params = {};
                        params.id = PageInfo.selectedIndex[i][j];
                        orders.push(params);
                    }
                }
            }
            configAPS();
        };

        //基本信息检验
        $scope.infoValidate = function () {
            var params = {};
            params.name = $("input[name='add-name']").val();
            params.rollTime = $("input[name='add-rollTime']").val();
            params.scheduleDays = $("input[name='add-scheduleDays']").val();
            params.scheduleOption = $("input[name='scheduleOption']").val();
            params.modeSchedule = $("#selectAdd option:selected").val();
            /*   var apsStart = $("input[name='add-start']").val();
             var apsEnd = $("input[name='add-end']").val();
             params.apsStart = (new Date($("input[name='add-start']").val())).getTime();
             params.apsEnd = (new Date($("input[name='add-end']").val())).getTime();*/

            if (!validate.checkLength(params.name)) {
                $("#add-name").removeClass("has-success");
                $("#add-name").addClass("has-error");
            } else {
                $("#add-name").removeClass("has-error");
                $("#add-name").addClass(" has-success");
            }
            if (!validate.checkLength(params.scheduleOption)) {
                $("#scheduleOption").removeClass("has-success");
                $("#scheduleOption").addClass("has-error");
            } else {
                $("#scheduleOption").removeClass("has-error");
                $("#scheduleOption").addClass(" has-success");
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

            /* if (!validate.checkLength(apsStart)) {
             $("#add-start").removeClass("has-success");
             $("#add-start").addClass("has-error");
             } else {
             $("#add-start").removeClass("has-error");
             $("#add-start").addClass(" has-success");
             }
             if (!validate.checkLength(apsEnd)) {
             $("#add-end").removeClass("has-success");
             $("#add-end").addClass("has-error");

             } else {
             $("#add-end").removeClass("has-error");
             $("#add-end").addClass(" has-success");
             }*/
            if (!validate.checkLength(params.modeSchedule)) {
                $("#add-schedule").removeClass("has-success");
                $("#add-schedule").addClass("has-error");
            } else {
                $("#add-schedule").removeClass("has-error");
                $("#add-schedule").addClass(" has-success");
            }

            if (validate.checkLength(params.rollTime) && validate.checkNumber(params.rollTime) && validate.checkLength(params.scheduleOption) &&
                validate.checkLength(params.scheduleDays) && validate.checkNumber(params.scheduleDays) && validate.checkLength(params.name)
                /* && validate.checkLength(params.apsStart) && validate.checkLength(params.apsEnd)*/ && validate.checkLength(params.modeSchedule)) {
                document.getElementById("nextStep").disabled = "";
                showSchedule();
                return true;
            } else {
                document.getElementById("nextStep").disabled = true;
                return false;
            }
        };


        //日历部分
        var showSchedule = function () {

            //当前排程时间长度（b）
            scheduleDays = $("input[name='add-scheduleDays']").val();

            name = $("input[name='add-name']").val();
            rollTime = $("input[name='add-rollTime']").val();

            apsStart = (new Date($("input[name='add-start']").val())).getTime();

            /*apsEnd = (new Date($("input[name='add-end']").val())).getTime();*/

            modeSchedule = $("#selectAdd option:selected").val();

            scheduleOption = parseInt($("input[name='scheduleOption']").val());
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

            setEndTime();
        };

        //表格信息重置
        $scope.reset = function () {
            $("input").val('');
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");

        };

        $scope.showCalendar = function () {
            $("#calendar").show();
        };

        //用于监控点击事件，checkbox选择了就更新
        $scope.updateSelection = function ($event, id) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');

            //布局页面，只选择一个
            if (currPage == 1 && checkbox.checked && PageInfo.selectedIndex[currPage].length >= 1) {
                checkbox.checked = false;
                return;
            }

            updateSelected(action, id);
        };

        //添加或取消勾选时更新对应页面的列表
        var updateSelected = function (action, id) {

            if (action == 'add' & PageInfo.selectedIndex[currPage].indexOf(id) == -1) {
                PageInfo.selectedIndex[currPage].push(id);
                if (PageInfo.selectedIndex[currPage].length > 0) {
                    document.getElementById("nextStep").disabled = "";
                    document.getElementById("startSchedule").disabled = "";
                }
            }

            if (action == 'remove' && PageInfo.selectedIndex[currPage].indexOf(id) != -1) {
                PageInfo.selectedIndex[currPage].splice(PageInfo.selectedIndex[currPage].indexOf(id), 1);
                if (PageInfo.selectedIndex[currPage].length == 0) {
                    document.getElementById("nextStep").disabled = true;
                    document.getElementById("startSchedule").disabled = true;
                }
            }
        };

        $scope.isSelected = function (id) {
            return selectedCheckArray.indexOf(id) >= 0;
        };

        //开始排程
        $scope.submitForm = function () {

            layer.load(0);

            for (var i = 0; i < pageCount; i++) {
                if (i == 1) {
                    layouts.id = PageInfo.selectedIndex[i][0];
                }
                else if (i == 2) {
                    for (var j = 0; j < PageInfo.selectedIndex[i].length; j++) {
                        var params = {};
                        params.id = PageInfo.selectedIndex[i][j];
                        orders.push(params);
                    }
                }
            }
            configAPS();
        };


        //比较日期，时间大小
        function compareTime(startDate, endDate) {
            if (startDate.length > 0 && endDate.length > 0) {
                var startDateTemp = startDate.split(" ");
                var endDateTemp = endDate.split(" ");

                var arrStartDate = startDateTemp[0].split("-");
                var arrEndDate = endDateTemp[0].split("-");

                var arrStartTime = startDateTemp[1].split(":");
                var arrEndTime = endDateTemp[1].split(":");
                var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1], arrStartTime[2]);
                var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1], arrEndTime[2]);

                if (allStartDate.getTime() >= allEndDate.getTime()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }


        //时间戳转时间
        function formatDateTime(time) {
            var date = new Date(time);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        };

        //计算apsEnd结束时间
        function setEndTime() {
            var cur = {};
            var apsEnds;
            var startTime = moment().format("YYYY-MM-DD");
            cur.startTime = (new Date(startTime)).getTime();

            var scheduleDays0 = scheduleDays;

            var endTime = moment().add(scheduleDays0, 'day').format("YYYY-MM-DD");
            cur.endTime = (new Date(endTime)).getTime();

            cur.isFinished = false;

            var data = JSON.stringify(cur);

            myHttpService.post(serviceList.CurInfo, data).then(function (response) {
                var info = response.data;
                var max = formatDateTime(info[0].t2)//订单中t2最晚
                for (var i = 1; i < info.length; i++) {
                    if (compareTime(max, formatDateTime(info[i].t2))) {
                        max = formatDateTime(info[i].t2);
                    }
                }

                apsEnds = new Date(max);
                var seperator1 = "-";
                var seperator2 = ":";
                var month = apsEnds.getMonth() + 1;
                var strDate=new Date(apsEnds.setDate(apsEnds.getDate() + scheduleOption)).getDate();
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                apsEnds = apsEnds.getFullYear() + seperator1 + month + seperator1 + strDate
                    + " " + apsEnds.getHours() + seperator2 + apsEnds.getMinutes()
                    + seperator2 + apsEnds.getSeconds();
                document.getElementById("end").value = apsEnds;
                //优化结束时间
                apsEnd = (new Date(apsEnds)).getTime();
            });
        }

        //排程
        function configAPS() {
            var APSConfigs = {};
            APSConfigs.t0 = apsStart;
            APSConfigs.t2 = apsEnd;
            APSConfigs.modeScheduling = modeSchedule;

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
            console.log(params);
            var data = JSON.stringify(params);
            $("#modal-add").hide();
            $(".modal-backdrop").remove();

            myHttpService.post(serviceList.beginSchedule, data).then(function successCallback(response) {
                var data = response.data;
                //清空所用的数组和变量
                selectedCheckArray.splice(0, selectedCheckArray.length);
                delete layouts.id;
                name = "";
                scheduleDays = "";
                rollTime = "";
                apsStart = "";
                apsEnd = "";
                if (data.result == "error") {
                    notification.sendNotification("alert", "排程失败");
                } else {
                    //刷新表格
                    myHttpService.get(serviceList.ListSchedule).then(function (response) {
                        $scope.scheduleList = response.data;
                    });
                    notification.sendNotification("confirm", "排程成功");
                }
                hideLoadingPage();
            }, function errorCallback() {
                notification.sendNotification("alert", "请求失败");
                hideLoadingPage();
            });
            document.getElementById("nextStep").disabled = true;
        }

        //获取信息
        var getInfo = function () {
            $("div").removeClass("has-error");
            $("div").removeClass("has-success");
            if (hasCheckRows()) {
                var a = document.getElementsByName("check");
                var row = 1;
                for (var i = 0; i < a.length; i++) {
                    if (a[i].checked) {
                        idVal = $("#table_value").find("tr").eq(row).find("td").eq(1).html();
                        id_params.id = idVal;
                    }
                    row++;
                }
                return true;
            } else {
                return false;
            }
        };

        //删除
        $scope.deleteSchedule = function () {
            if (getInfo()) {
                layer.confirm('是否删除选中的排程信息?', {
                    btn: ['删除', '取消'] //按钮
                }, function (index) {
                    layer.load();

                    var idInfo = JSON.stringify(id_params);
                    myHttpService.delete(serviceList.DeleteSchedule, idInfo).then(function successCallback() {
                        myHttpService.get(serviceList.ListSchedule).then(function (response) {
                            $scope.scheduleList = response.data;
                            notification.sendNotification("confirm", "删除成功");
                        });
                    }, function errorCallback() {
                        notification.sendNotification("alert", "请求失败");
                    });
                    layer.close(index);
                }, function (index) {
                    layer.close(index);
                    notification.sendNotification("alert", "取消删除");
                });
            } else {
                notification.sendNotification("alert", "请选择一条排程记录!");
            }
        };

        //显示当前布局的放大图
        $scope.showDetail = function (event) {
            var e = event || window.event;
            var target = e.target || e.srcElement;
            var content = "<img src='" + target.src + "' style='width:100%;height:100%;'>";
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: ['830px', '730px'],
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: content
            });
        }
    });