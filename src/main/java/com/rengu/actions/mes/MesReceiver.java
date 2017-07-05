package com.rengu.actions.mes;

import javax.jms.*;
import javax.tools.Tool;

import com.rengu.util.Tools;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.net.ConnectException;
import java.util.concurrent.BlockingQueue;

/**
 * ActiveMQ接收端
 * Created by wey580231 on 2017/7/3.
 */
public class MesReceiver extends Thread {

    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;

    private boolean runningFlag = true;
    private BlockingQueue<Message> messages;

    public MesReceiver(BlockingQueue<Message> messages) throws JMSException, ConnectException {

        this.messages = messages;

        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                Tools.getDatabaseProperties().getProperty("BrokerURL"));

        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
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
                    messages.put(message);
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
