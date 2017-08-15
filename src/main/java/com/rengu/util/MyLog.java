package com.rengu.util;

import com.rengu.servlet.StartUpServlet;
import org.apache.log4j.Logger;

public class MyLog {

    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger(MyLog.class.getName());
        }

        return logger;
    }
}
