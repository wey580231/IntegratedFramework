/**
 * Created by hanchangming on 2017/5/17.
 */
var webSocket;
var username;
var webSocketUrl;
var notyf;

function getPath(){
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '': pathName.substring(0, pathName.indexOf('/'));
    return window.location.host + '/' + webName + '/';

}

function getUsername() {
    username = navigator.appName + new Date().getTime();
    var txt = getPath();
    webSocketUrl = "ws://" + txt + "notification/" + username;
}

function webSocketInit() {
    notyf = new Notyf();

    getUsername();
    if (typeof WebSocket !== 'undefined') {
        webSocket = new WebSocket(webSocketUrl);
        //连接发生错误的回调方法
        webSocket.onerror = function () {
            showNotification("通知系统出现错误。", "alert")
        };
        //连接成功建立的回调方法
        webSocket.onopen = function () {
            showNotification("通知系统开启。", "confirm");
        };

        //接收到消息的回调方法
        webSocket.onmessage = function (event) {
            var webSocketObject = JSON.parse(event.data);

            if (webSocketObject.MessageType == "notification") {
                showNotification(webSocketObject.Message, webSocketObject.NotificationType)
            } else if (webSocketObject.MessageType == "3DMessage") {
                if (Global3DInstance != null) {
                    var json = "\{'data':\{'task':'RJXZPT_01_Make','good':'KQD_TJ_01','startTime':0,'endTime':5,'site':''\}\}";
                    Global3DInstance.SendMessage("APPRoot", "RunTime", json);
                }
                console.log("Recv 3d real control info====")
            }
        };
        //连接关闭的回调方法
        webSocket.onclose = function () {
            showNotification("通知系统关闭。", "alert");
        };
        window.onbeforeunload = function () {
            webSocket.close();
        }
    } else {
        showNotification("您的浏览器不支持消息通知模块，请升级浏览器。", "alert");
    }
}

function showNotification(message, status) {

    if (status === "alert") {
        notyf.alert(message);
    }
    if (status === "confirm") {
        notyf.confirm(message);
    }
}