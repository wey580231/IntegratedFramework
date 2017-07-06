package com.rengu.servlet;

import com.rengu.actions.mes.MesConsumer;
import com.rengu.actions.mes.MesReceiver;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;

import javax.jms.Message;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wey580231 on 2017/7/3.
 */
public class StartUpServlet extends HttpServlet {

    private MesReceiver mesReceiver = null;

    private BlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(Integer.valueOf(Tools.getDatabaseProperties().get("MaxCount").toString()));

    private List<MesConsumer> threadList = new ArrayList<MesConsumer>();

    public void init() throws ServletException {

        //【1】初始化Hibernate
        MySessionFactory.getSessionFactory();

//        try {
//            //【2】启动activeMQ接收线程
//            mesReceiver = new MesReceiver(messages);
//            mesReceiver.start();
//
//            //【3】初始化多线程解析ActiveMQ服务器消息
//            for (int i = 0; i < Integer.valueOf(Tools.getDatabaseProperties().get("ConsumerCount").toString()); i++) {
//                MesConsumer con = new MesConsumer(messages);
//                con.setRunningFlag(true);
//                threadList.add(con);
//                con.start();
//            }
//
//        } catch (JMSException e) {
//            e.printStackTrace();
//        } catch (ConnectException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void destroy() {
//        if (mesReceiver != null) {
//            mesReceiver.setRunningFlag(false);
//        }
//        for (int i = 0; i < threadList.size(); i++) {
//            threadList.get(i).setRunningFlag(false);
//        }
    }
}
