package com.rengu.servlet;

import com.rengu.actions.mes.MesConsumer;
import com.rengu.actions.mes.MesReceiver;
import com.rengu.util.ApsTools;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Created by wey580231 on 2017/7/3.
 */
public class StartUpServlet extends HttpServlet {

    private MesReceiver mesReceiver = null;

    private BlockingQueue<Message> messages = new ArrayBlockingQueue<Message>(Integer.valueOf(Tools.getDatabaseProperties().get("MaxCount").toString()));

    private List<MesConsumer> threadList = new ArrayList<MesConsumer>();

    private Logger logger = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String log4jLocation = config.getInitParameter("log4j-properties-location");
        String webAppPath = config.getServletContext().getRealPath("/");
        String log4jProp = webAppPath + log4jLocation;

        File props = new File(log4jProp);
        if (props.exists()) {
            System.out.println("使用: " + log4jProp + "初始化日志设置信息");
            PropertyConfigurator.configure(log4jProp);
        } else {
            System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化");
            BasicConfigurator.configure();
        }

        logger = Logger.getLogger(StartUpServlet.class.getName());

        logger.info(" debug ");

        logger.warning("yayaayayayy");

        //【1】初始化Hibernate
        MySessionFactory.getSessionFactory();

        try {
            //【2】启动activeMQ接收线程
            mesReceiver = new MesReceiver(messages);
            mesReceiver.start();

            //【3】初始化多线程解析ActiveMQ服务器消息
            for (int i = 0; i < Integer.valueOf(Tools.getDatabaseProperties().get("ConsumerCount").toString()); i++) {
                MesConsumer con = new MesConsumer(messages);
                con.setRunningFlag(true);
                threadList.add(con);
                con.start();
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        //【4】第一次排程時将job、task、plan、log、order清空
        ApsTools.instance().resetApsDatabase();

    }

    @Override
    public void destroy() {
        if (mesReceiver != null) {
            mesReceiver.setRunningFlag(false);
        }
        for (int i = 0; i < threadList.size(); i++) {
            threadList.get(i).setRunningFlag(false);
        }
    }
}
