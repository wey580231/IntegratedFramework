package com.rengu.actions;

import com.rengu.util.Tools;
import com.rengu.util.WebSocketNotification;

/**
 * Created by hanch on 2017/7/20.
 */
public class TestFunc extends SuperAction {
    public void webSocketNotificationTest() throws Exception {
        WebSocketNotification.broadcast(Tools.creatNotificationMessage("hi", "confirm"));
    }
}
