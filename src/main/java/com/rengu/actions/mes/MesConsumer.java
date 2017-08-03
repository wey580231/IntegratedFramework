package com.rengu.actions.mes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rengu.entity.*;
import com.rengu.util.ApsTools;
import com.rengu.util.MessTable;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Session;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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
        ObjectMapper objectMapper = new ObjectMapper();
        while (runningFlag) {
            try {
                Message mess = messages.take();

                if (mess instanceof TextMessage) {
                    TextMessage tx = (TextMessage) mess;

                    parseMessage(tx.getText());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    //信息解析
    private void parseMessage(String message) {
        try {
            JsonNode root = Tools.jsonTreeModelParse(message);

            System.out.println("接受到消息+:" + message);

            String mesType = root.get("FC").asText();               //功能编码
            String REVICER = root.get("REVICER").asText();          //接收者
            String SENDER = root.get("SENDER").asText();            //发送者
            String UUID = root.get("UUID").asText();              //接收消息UUID，用于在回复时加入

            JsonNode dataNode = root.get("DATA");

            Session session = MySessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            //产品
            if (mesType.equals(MessTable.MES_PRODUCT)) {

                RG_ProductEntity product = null;

                product = session.get(RG_ProductEntity.class, dataNode.get("id").asText());

                if (product == null) {
                    product = new RG_ProductEntity();
                }

                product.setId(dataNode.get("id").asText());
                product.setName(dataNode.get("name").asText());
                product.setStock((short) dataNode.get("stock").asInt());
                product.setUnit(dataNode.get("unit").asText());
                product.setModel(dataNode.get("model").asText());

                session.saveOrUpdate(product);
            }
            //资源
            else if (mesType.equals(MessTable.MES_RESOURCE)) {
                RG_ResourceEntity resource = null;

                resource = session.get(RG_ResourceEntity.class, dataNode.get("id").asText());

                if (resource == null) {
                    resource = new RG_ResourceEntity();
                }

                resource.setIdR(dataNode.get("id").asText());
                resource.setName(dataNode.get("name").asText());
                resource.setMobility((short) dataNode.get("mobility").asInt());

                session.saveOrUpdate(resource);
            }
            //地点
            else if (mesType.equals(MessTable.MES_SITE)) {
                RG_SiteEntity site = null;

                site = session.get(RG_SiteEntity.class, dataNode.get("id").asText());

                if (site == null) {
                    site = new RG_SiteEntity();
                }

                site.setId(dataNode.get("id").asText());
                site.setName(dataNode.get("name").asText());
                site.setX((short) dataNode.get("x").asInt());
                site.setY((short) dataNode.get("y").asInt());
                site.setCapacity((short) dataNode.get("capacity").asInt());

                session.saveOrUpdate(site);
            }
            //地点距离
            else if (mesType.equals(MessTable.MES_SITE_DISTANCE)) {
                RG_SiteEntity site1 = (RG_SiteEntity) session.get(dataNode.get("idSite1").asText(), RG_SiteEntity.class);
                RG_SiteEntity site2 = (RG_SiteEntity) session.get(dataNode.get("idSite2").asText(), RG_SiteEntity.class);

                if (site1 != null && site2 != null) {
                    RG_DistanceEntity distance = new RG_DistanceEntity();
                    distance.setStartSite(site1);
                    distance.setEndSite(site2);
                    distance.setDistance(dataNode.get("distance").asInt());

                    session.save(distance);
                }
            }
            //监控信息
            else if (mesType.equals("")) {
                String type = dataNode.get("type").asText();

                String id = dataNode.get("id").asText();
                String name = dataNode.get("name").asText();
                String stock = dataNode.get("stock").asText();
                String totalPlace = dataNode.get("totalPlace").asText();
                String freePlace = dataNode.get("freePlace").asText();
                String state = dataNode.get("state").asText();
                String jobDesc = dataNode.get("jobDesc").asText();
                String idOrder = dataNode.get("idOrder").asText();
                String remainPower = dataNode.get("remainPower").asText();

                //立体仓库信息
                if (type.equals(MessTable.MES_DEPORT_INFO)) {
                    RG_Mes_DeportInfo deportInfo = new RG_Mes_DeportInfo();

                    deportInfo.setDeportId(id);
                    deportInfo.setDeportName(name);
                    deportInfo.setStock(Integer.parseInt(stock));
                    deportInfo.setTotalPlace(Integer.parseInt(totalPlace));
                    deportInfo.setFreePlace(Integer.parseInt(freePlace));
                    deportInfo.setReportTime(new Date());

                    session.save(deportInfo);
                }
                //仓库搬运机器人信息
                else if (type.equals(MessTable.MES_CARRY_INFO)) {
                    RG_Mes_CarryInfo carryInfo = new RG_Mes_CarryInfo();

                    carryInfo.setAgvId(id);
                    carryInfo.setState(Boolean.parseBoolean(state));
                    carryInfo.setJobDesc(jobDesc);
                    carryInfo.setIdOrder(idOrder);
                    carryInfo.setReportTime(new Date());

                    session.save(carryInfo);
                }
                //AGV信息
                else if (type.equals(MessTable.MES_AGV_INFO)) {
                    RG_Mes_AgvInfo agvInfo = new RG_Mes_AgvInfo();

                    agvInfo.setAgvId(id);
                    agvInfo.setState(Boolean.parseBoolean(state));
                    agvInfo.setGoodsDesc(jobDesc);
                    agvInfo.setIdOrder(idOrder);
                    agvInfo.setRemainPower(Float.parseFloat(remainPower));
                    agvInfo.setReportTime(new Date());

                    session.save(agvInfo);
                }
                //装配线搬运机器人信息
                else if (type.equals(MessTable.MES_ASSEMBLYCARRY_INFO)) {
                    RG_Mes_AssemblyCarryInfo carryInfo = new RG_Mes_AssemblyCarryInfo();

                    carryInfo.setCarryId(id);
                    carryInfo.setState(Boolean.parseBoolean(state));
                    carryInfo.setJobDesc(jobDesc);
                    carryInfo.setIdOrder(idOrder);
                    carryInfo.setReportTime(new Date());

                    session.save(carryInfo);
                }
                //智能装配中心信息
                else if (type.equals(MessTable.MES_ASSEMBLYCENTER_INFO)) {
                    RG_Mes_AssemblyCenterInfo centerInfo = new RG_Mes_AssemblyCenterInfo();

                    centerInfo.setCarryId(id);
                    centerInfo.setState(Boolean.parseBoolean(state));
                    centerInfo.setJobDesc(jobDesc);
                    centerInfo.setIdOrder(idOrder);
                    centerInfo.setReportTime(new Date());

                    session.save(centerInfo);
                }
            }
            //设备状态信息
            else if (mesType.equals(MessTable.MES_DEVICESTATE_INFO)) {
                RG_ResourceStateEntity stateEntity = new RG_ResourceStateEntity();
                stateEntity.setResourceName(dataNode.get("resourceName").asText());
                stateEntity.setManufacturer(dataNode.get("manufacturer").asText());
                stateEntity.setIdTask(dataNode.get("idTask").asText());
                stateEntity.setIdProcess((short) dataNode.get("idProces").asInt());
                stateEntity.setIdProduct(dataNode.get("idProduct").asText());
                stateEntity.setProductName(dataNode.get("productName").asText());
                stateEntity.setT1Task(Tools.parseDate(dataNode.get("t1PlanTask").asText()));
                stateEntity.setT2Task(Tools.parseDate(dataNode.get("t2PlanTask").asText()));
                stateEntity.setCurrTime(new Date());
                stateEntity.setT1RealTask(Tools.parseDate(dataNode.get("t1RealTask").asText()));
                stateEntity.setT2RealTask(Tools.parseDate(dataNode.get("t2RealTask").asText()));
                stateEntity.setState((short) dataNode.get("state").asInt());

                session.save(stateEntity);
            }
            //订单执行信息
            else if (mesType.equals(MessTable.MES_ORDERSTATE_INFO)) {

                String idOrder = dataNode.get("idOrder").asText();

                RG_OrderEntity orderEntity = session.get(RG_OrderEntity.class, idOrder);

                if (orderEntity != null) {
                    String completeNum = dataNode.get("completeNum").asText();

                    RG_OrderStateEntity orderState = new RG_OrderStateEntity();
                    orderState.setIdTask(dataNode.get("idTask").asText());
                    orderState.setNameTask(dataNode.get("nameTask").asText());
                    orderState.setPlanDevice(dataNode.get("planDevice").asText());
                    orderState.setPlanCount(Short.parseShort(dataNode.get("planCount").asText()));

                    orderState.setActualDispatchTime(Tools.parseDate((dataNode.get("realExcuteTime").asText())));
                    orderState.setActualFinsihTime(Tools.parseDate((dataNode.get("realFinishTime").asText())));
                    orderState.setActualDispatchDevice((dataNode.get("realDispatchDevice").asText()));
                    //TODO 计划完工量格式不对
                    orderState.setActualFinishCount((short) 1);
                    orderState.setCurrTime(new Date());
                    orderState.setFinished(Boolean.parseBoolean(dataNode.get("isCompleted").asText()));
                    orderState.setFinishPercent(Float.parseFloat(dataNode.get("planCompletionRate").asText()));

                    session.save(orderState);
                }
            }
            //工序指令信息
            else if (mesType.equals(MessTable.MES_INSTRUCT_INFO)) {

            }
            //设备调整
            else if (mesType.equals(MessTable.MES_ADJUSTDEVICE_INFO)) {

            }

            //接收MES确认消息后，发送确认消息
            MesSender.instance().sendReplyMessage(mesType, UUID);

            session.getTransaction().commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRunningFlag() {
        return runningFlag;
    }

    public void setRunningFlag(boolean runningFlag) {
        this.runningFlag = runningFlag;
    }
}
