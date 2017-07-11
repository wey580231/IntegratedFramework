package com.rengu.actions.mes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rengu.entity.*;
import com.rengu.util.MessTable;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
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

            System.out.println("Yang:" + message);

            String mesType = root.get("type").asText();
            String mesSha1 = root.get("sha1").asText();
            String mesData = root.get("data").toString();

            if (!(Tools.getSha(mesData).equals(mesSha1))) {
                return;
            }

            JsonNode dataNode = root.get("data");

            Session session = MySessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            //产品
            if (mesType.equals(MessTable.MES_PRODUCT)) {
                RG_ProductEntity product = new RG_ProductEntity();

                product.setId(dataNode.get("id").asText());
                product.setName(dataNode.get("name").asText());
                product.setStock((short) dataNode.get("stock").asInt());
                product.setUnit(dataNode.get("unit").asText());
                product.setModel(dataNode.get("model").asText());

                session.save(product);
            }
            //资源
            else if (mesType.equals(MessTable.MES_RESOURCE)) {
                RG_ResourceEntity resource = new RG_ResourceEntity();
                resource.setIdR(dataNode.get("id").asText());
                resource.setName(dataNode.get("name").asText());
                resource.setMobility((short) dataNode.get("mobility").asInt());

                session.save(resource);
            }
            //地点
            else if (mesType.equals(MessTable.MES_SITE)) {
                RG_SiteEntity site = new RG_SiteEntity();
                site.setId(dataNode.get("id").asText());
                site.setName(dataNode.get("name").asText());
                site.setX((short) dataNode.get("x").asInt());
                site.setY((short) dataNode.get("y").asInt());
                site.setCapacity((short) dataNode.get("capacity").asInt());
                session.save(site);
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
            //工艺
            else if (mesType.equals(MessTable.MES_PROCESS)) {

            }
            //立体仓库信息
            else if (mesType.equals(MessTable.MES_DEPORT_INFO)) {
                RG_Mes_DeportInfo deportInfo = new RG_Mes_DeportInfo();
                deportInfo.setDeportId(dataNode.get("id").asText());
                deportInfo.setDeportName(dataNode.get("name").asText());
                deportInfo.setStock(dataNode.get("stock").asInt());
                deportInfo.setFreePlace(dataNode.get("freePlace").asInt());
                deportInfo.setReportTime(new Date());

                session.save(deportInfo);
            }
            //仓库搬运机器人信息
            else if (mesType.equals(MessTable.MES_CARRY_INFO)) {
                RG_Mes_CarryInfo carryInfo = new RG_Mes_CarryInfo();
                carryInfo.setAgvId(dataNode.get("id").asText());
                carryInfo.setState(dataNode.get("state").asBoolean());
                carryInfo.setJobDesc(dataNode.get("jobDesc").asText());
                carryInfo.setIdOrder(dataNode.get("idOrder").asText());
                carryInfo.setReportTime(new Date());

                session.save(carryInfo);
            }
            //AGV信息
            else if (mesType.equals(MessTable.MES_AGV_INFO)) {
                RG_Mes_AgvInfo agvInfo = new RG_Mes_AgvInfo();
                agvInfo.setAgvId(dataNode.get("id").asText());
                agvInfo.setState(dataNode.get("state").asBoolean());
                agvInfo.setGoodsDesc(dataNode.get("goodsDesc").asText());
                agvInfo.setIdOrder(dataNode.get("idOrder").asText());
                agvInfo.setRemainPower((float) dataNode.get("remainPower").asDouble());
                agvInfo.setReportTime(new Date());

                session.save(agvInfo);
            }
            //装配线搬运机器人信息
            else if (mesType.equals(MessTable.MES_ASSEMBLYCARRY_INFO)) {
                RG_Mes_AssemblyCarryInfo carryInfo = new RG_Mes_AssemblyCarryInfo();
                carryInfo.setCarryId(dataNode.get("id").asText());
                carryInfo.setState(dataNode.get("state").asBoolean());
                carryInfo.setJobDesc(dataNode.get("jodDesc").asText());
                carryInfo.setIdOrder(dataNode.get("idOrder").asText());
                carryInfo.setReportTime(new Date());

                session.save(carryInfo);
            }
            //智能装配中心信息
            else if (mesType.equals(MessTable.MES_ASSEMBLYCENTER_INFO)) {
                RG_Mes_AssemblyCenterInfo centerInfo = new RG_Mes_AssemblyCenterInfo();
                centerInfo.setCarryId(dataNode.get("id").asText());
                centerInfo.setState(dataNode.get("state").asBoolean());
                centerInfo.setJobDesc(dataNode.get("jodDesc").asText());
                centerInfo.setIdOrder(dataNode.get("idOrder").asText());
                centerInfo.setReportTime(new Date());

                session.save(centerInfo);
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

            }
            //工序指令信息
            else if (mesType.equals(MessTable.MES_INSTRUCT_INFO)) {

            }
            //设备调整
            else if (mesType.equals(MessTable.MES_ADJUSTDEVICE_INFO)) {

            }

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
