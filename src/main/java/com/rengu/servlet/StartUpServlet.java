package com.rengu.servlet;

import com.rengu.actions.mes.MesReceiver;
import com.rengu.util.MySessionFactory;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.net.ConnectException;

/**
 * Created by wey580231 on 2017/7/3.
 */
public class StartUpServlet extends HttpServlet {

    private MesReceiver mesReceiver = null;

    //TODO  默认启动
    public void init() throws ServletException {
        //【1】初始化Hibernate
        MySessionFactory.getSessionFactory();

        //【2】启动activeMQ接收线程
        try {
            mesReceiver = new MesReceiver();
            mesReceiver.start();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        //【3】初始化多线程解析ActiveMQ服务器消息

    }

    @Override
    public void destroy() {
        if (mesReceiver != null) {
            mesReceiver.setRunningFlag(false);
        }
    }
}
