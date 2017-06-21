<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/5/16
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/lib/FullCalendar-3.4.0/fullcalendar.min.css'/>
</head>
<body>
<div id='calendar'></div>
<script src='${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/lib/Moment/moment-with-locales.js'></script>
<script src='${pageContext.request.contextPath}/lib/FullCalendar-3.4.0/fullcalendar.min.js'></script>
<script>
    //时间窗口长度
    var countDays = 30;
    //时间窗口背景颜色
    var backgroundColor = 'blue';
    $(document).ready(function () {
        // page is now ready, initialize the calendar...
        $('#calendar').fullCalendar({
            // put your options and callbacks here
            viewRender: function (view, element) {
                for (var i = 0; i < countDays; i++) {
                    var nowDate = moment().add(i, "d").format('YYYY-MM-DD');
                    $("td[data-date='" + nowDate + "']").css('backgroundColor', backgroundColor);
                }
            },
            editable: true,
            eventLimit: true,
            navLinks: true,
            eventSources: [
                'http://localhost:8080/FullCalendar/getAllFullCalendarEvents.action'
            ]
        });
    });
</script>
</body>
</html>
