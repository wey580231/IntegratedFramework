package com.rengu.actions.mes;

import com.rengu.util.Tools;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMQ发送端
 * Created by wey580231 on 2017/7/5.
 */
public class MesSender {

    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    private static MesSender sender = null;

    private MesSender() {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                Tools.getDatabaseProperties().getProperty("BrokerURL"));
        try {
            connection = connectionFactory.createConnection();
            connection.start();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    //发送消息
    public void sendMessage(String message) throws JMSException {
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(Tools.getDatabaseProperties().getProperty("ReceiveQueue"));
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage tx = session.createTextMessage(message);

        producer.send(tx);

        session.commit();
    }

    public static MesSender instance() {
        if (sender == null) {
            sender = new MesSender();
        }
        return sender;
    }

}
