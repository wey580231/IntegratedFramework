package com.rengu.actions.mes;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.concurrent.BlockingQueue;

/**
 * 多线程解析active'MQ消息
 * Created by wey580231 on 2017/7/5.
 */
public class MesConsumer extends Thread {

    private BlockingQueue<Message> messages;

    private boolean runningFlag = false;

    public MesConsumer(BlockingQueue<Message> messages) {
        this.messages = messages;
    }

    public void run() {
        while (runningFlag) {
            try {
                Message mess = messages.take();

                if (mess instanceof TextMessage) {
                    TextMessage tx = (TextMessage) mess;
                    System.out.println(tx.getText());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunningFlag() {
        return runningFlag;
    }

    public void setRunningFlag(boolean runningFlag) {
        this.runningFlag = runningFlag;
    }
}
