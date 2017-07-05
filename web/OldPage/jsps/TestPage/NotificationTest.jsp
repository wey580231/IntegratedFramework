<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="lib/UIKit-2.27.4/css/uikit.gradient.min.css"/>
    <link rel="stylesheet"
          href="lib/UIKit-2.27.4/css/components/notify.gradient.min.css"/>
</head>
<body onload="webSocketInit()">
<button onclick="sendMessage()">发送</button>
</body>
<script src="lib/jquery/jquery-3.2.1.min.js"></script>
<script src="lib/UIKit-2.27.4/js/uikit.min.js"></script>
<script src="lib/UIKit-2.27.4/js/components/notify.min.js"></script>
<script src="jsps/TestPage/WebSocketNotification.js"></script>
</html>