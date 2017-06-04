package com.rengu.APS;

import com.rengu.util.Info;

/**
 * Created by hanchangming on 2017/6/1.
 */
public class APSTools {
    public static String insertAPSConfigSQL(String name, String value) {
        String SQLString = "UPDATE " + Info.APS_CONGIF + " SET value=" + value + " WHERE name=" + name + ";";
        return SQLString;
    }
}