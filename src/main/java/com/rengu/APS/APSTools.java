package com.rengu.APS;

import com.rengu.util.Info;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by hanchangming on 2017/6/1.
 */
public class APSTools {
    public static String insertAPSConfigSQL(String name, String value) {
        String SQLString = "UPDATE " + Info.APS_CONGIF + " SET value=" + value + " WHERE name=" + name + ";";
        return SQLString;
    }

    public static boolean isScheduled() {
        String url = "http://192.168.0.128:89/NCL:STATE?ID=001&PROGRAM=./Model/Script/ScriptAutoScheduling.n&MESSAGE={Message}";
        int port = 89;
        try {
            Socket socket = new Socket(url, port);
            System.out.print(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}