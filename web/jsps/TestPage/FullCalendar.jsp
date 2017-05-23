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
<body onload="webNotificationInit()">
<div id='calendar'></div>
<script src='${pageContext.request.contextPath}/lib/jquery/jquery-3.2.1.min.js'></script>
<script src='${pageContext.request.contextPath}/lib/Moment/moment-with-locales.js'></script>
<script src='${pageContext.request.contextPath}/lib/FullCalendar-3.4.0/fullcalendar.min.js'></script>
<script>
    $(document).ready(function () {
        // page is now ready, initialize the calendar...
        var todayDate = moment().startOf('day');
        var YM = todayDate.format('YYYY-MM');
        var YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
        var TODAY = todayDate.format('YYYY-MM-DD');
        var TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');
        $('#calendar').fullCalendar({
            // put your options and callbacks here
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,listWeek'
            },
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            navLinks: true,
            events: [
                {
                    title: 'All Day Event',
                    start: YM + '-01'
                },
                {
                    title: 'Long Event',
                    start: YM + '-07',
                    end: YM + '-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: YM + '-09T16:00:00'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: YM + '-16T16:00:00'
                },
                {
                    title: 'Conference',
                    start: YESTERDAY,
                    end: TOMORROW
                },
                {
                    title: 'Meeting',
                    start: TODAY + 'T10:30:00',
                    end: TODAY + 'T12:30:00'
                },
                {
                    title: 'Lunch',
                    start: TODAY + 'T12:00:00'
                },
                {
                    title: 'Meeting',
                    start: TODAY + 'T14:30:00'
                },
                {
                    title: 'Happy Hour',
                    start: TODAY + 'T17:30:00'
                },
                {
                    title: 'Dinner',
                    start: TODAY + 'T20:00:00'
                },
                {
                    title: 'Birthday Party',
                    start: TOMORROW + 'T07:00:00'
                },
                {
                    title: 'Click for Google',
                    url: 'http://google.com/',
                    start: YM + '-28'
                }
            ]
        })
    });
</script>
</body>
</html>
