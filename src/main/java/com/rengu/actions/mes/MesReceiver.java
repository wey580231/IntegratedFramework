package com.rengu.actions.mes;

import javax.jms.*;
import javax.tools.Tool;

import com.rengu.util.Tools;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.net.ConnectException;

/**
 * Created by wey580231 on 2017/7/3.
 */
public class MesReceiver extends Thread {

    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
    private Connection connection = null;
    // Session： 一个发送或接收消息的线程
    private Session session;
    // Destination ：消息的目的地;消息发送给谁.
    private Destination destination;
    // 消费者，消息接收者
    private MessageConsumer consumer;

    private boolean runningFlag = true;

    public MesReceiver() throws JMSException, ConnectException {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                Tools.getDatabaseProperties().getProperty("BrokerURL"));

        // 构造从工厂得到连接对象
        connection = connectionFactory.createConnection();
        // 启动
        connection.start();
        // 获取操作连接
        session = connection.createSession(Boolean.FALSE,
                Session.AUTO_ACKNOWLEDGE);
        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
        destination = session.createQueue(Tools.getDatabaseProperties().getProperty("ReceiveQueue"));
        consumer = session.createConsumer(destination);
    }

    @Override
    public void run() {
        try {
            Tools.showLog();
            while (runningFlag) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public void setRunningFlag(boolean runningFlag) {
        this.runningFlag = runningFlag;
    }
}
