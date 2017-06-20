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
    $(document).ready(function () {
        // page is now ready, initialize the calendar...
        var todayDate = moment().startOf('day');
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
        })
    });
</script>
</body>
</html>
