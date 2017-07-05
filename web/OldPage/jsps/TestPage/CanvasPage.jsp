<%--
  Created by IntelliJ IDEA.
  User: hanchangming
  Date: 2017/6/26
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CanvasPage</title>
</head>
<body onload="draw()">
<canvas id="canvas" width="150" height="150"></canvas>
<script type="text/javascript">
    function draw() {
        var canvas = document.getElementById('canvas');
        if (canvas.getContext) {
            var context = canvas.getContext('2d');
            context.fillStyle = "rgb(200,0,0)";
            context.fillRect(10, 10, 55, 50);
            context.fillStyle = "rgba(0, 0, 200, 0.5)";
            context.fillRect(30, 30, 55, 50);
        }
    }
</script>
</body>
</html>
