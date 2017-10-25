package com.rengu.actions.mes;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.entity.*;
import com.rengu.util.MessTable;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import com.rengu.util.UserConfigTools;
import org.hibernate.Session;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 多线程解析active'MQ消息
 * Created by wey580231 on 2017/7/5.
 */
public class MesConsumer extends Thread {

    private BlockingQueue<Message> messages;

    private boolean runningFlag = false;

    public MesConsumer(BlockingQueue<Message> messages) {
        this.setName("MesConsumer");
        this.messages = messages;
    }

    public void run() {
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

//    private void parseMessage(String message, int a) {
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = Tools.jsonTreeModelParse(message);
//            String mesType = jsonNode.get("FC").asText();                //功能编码
//            String UUID = jsonNode.get("UUID").asText();                 //接收消息UUID，用于在回复时加入
//            JsonNode dataNode = jsonNode.get("DATA");
//            System.out.println("消息类型：" + mesType + "，接受到消息+:" + message);
//            Session session = MySessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
//            //产品表
//            if (mesType.equals(MessTable.MES_PRODUCT)) {
//                RG_ProductEntity rg_productEntity = session.get(RG_ProductEntity.class, dataNode.get("id").asText());
//                if (rg_productEntity == null) {
//                    rg_productEntity = new RG_ProductEntity();
//                }
//                rg_productEntity.setId(dataNode.get("id").asText());
//                rg_productEntity.setName(dataNode.get("name").asText());
//                rg_productEntity.setStock((short) dataNode.get("stock").asInt());
//                rg_productEntity.setUnit(dataNode.get("unit").asText());
//                rg_productEntity.setModel(dataNode.get("model").asText());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //信息解析
    private void parseMessage(String message) {
        try {
            JsonNode root = Tools.jsonTreeModelParse(message);
            String mesType = root.get("FC").asText();                //功能编码
            String UUID = root.get("UUID").asText();                 //接收消息UUID，用于在回复时加入
            JsonNode dataNode = root.get("DATA");
            Session session = MySessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("消息类型：" + mesType + "，接受到消息+:" + message);
            //【已调】产品
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
            //【已调】资源
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
            //【已调】地点
            else if (mesType.equals(MessTable.MES_SITE)) {

                if (dataNode.isArray()) {
                    for (int i = 0; i < dataNode.size(); i++) {
                        JsonNode subNode = dataNode.get(i);
                        RG_SiteEntity site = null;
                        site = session.get(RG_SiteEntity.class, subNode.get("id").asText());

                        if (site == null) {
                            site = new RG_SiteEntity();
                        }

                        site.setId(subNode.get("id").asText());
                        site.setName(subNode.get("name").asText());
                        site.setX((short) subNode.get("x").asInt());
                        site.setY((short) subNode.get("y").asInt());
                        site.setCapacity((short) subNode.get("capacity").asInt());

                        session.saveOrUpdate(site);
                    }
                }
            }
            //【已调】地点距离
            else if (mesType.equals(MessTable.MES_SITE_DISTANCE)) {
                if (dataNode.isArray()) {
                    for (int i = 0; i < dataNode.size(); i++) {
                        JsonNode subNode = dataNode.get(i);

                        String idSite1 = subNode.get("idSite1").asText();
                        String idSite2 = subNode.get("idSite2").asText();

                        RG_SiteEntity site1 = (RG_SiteEntity) session.get(RG_SiteEntity.class, idSite1);
                        RG_SiteEntity site2 = (RG_SiteEntity) session.get(RG_SiteEntity.class, idSite2);


                        if (site1 != null && site2 != null) {
                            RG_DistanceEntity distance = new RG_DistanceEntity();

                            RG_DistanceEntity distance2 = (RG_DistanceEntity) session.createQuery("select distance from RG_DistanceEntity distance where distance.startSite.id =:idSite1 and distance.endSite.id =:idSite2").setParameter("idSite1", idSite1).setParameter("idSite2", idSite2).uniqueResult();

                            if (distance2 != null) {
                                //String idDistance = distance2.getId();
                                int distance3 = subNode.get("distance").asInt();
                                session.createNativeQuery("update rg_distance set distance = ?").setParameter(1, distance3).executeUpdate();
                                /*session.delete(distance2);
                                distance.setId(idDistance);
                                distance.setStartSite(site1);
                                distance.setEndSite(site2);
                                distance.setDistance(subNode.get("distance").asInt());*/
                            } else {
                                distance.setId(Tools.getUUID());
                                distance.setStartSite(site1);
                                distance.setEndSite(site2);
                                distance.setDistance(subNode.get("distance").asInt());
                                session.saveOrUpdate(distance);
                            }


                            /*distance.setId(Tools.getUUID());
                            distance.setStartSite(site1);
                            distance.setEndSite(site2);
                            distance.setDistance(subNode.get("distance").asInt());*/

                            //session.saveOrUpdate(distance);
                        }
                    }
                }
            }
            //监控信息
            else if (mesType.equals(MessTable.MES_REAL_INFO)) {
                String type = dataNode.get("type").asText();
                //【已调】立体仓库信息
                //TODO 仓库信息中含有多个货物，修改保存的方式
                if (type.equals(MessTable.MES_DEPORT_INFO)) {
                    JsonNode subDataNode = dataNode.get("data");
                    if (subDataNode.isArray()) {
                        for (int i = 0; i < subDataNode.size(); i++) {
                            JsonNode realData = subDataNode.get(i);

                            RG_Mes_DeportInfo deportInfo = new RG_Mes_DeportInfo();

                            if (realData.get("name") != null && realData.get("name").isArray()) {
                                JsonNode nameNode = realData.get("name");
                                /*for (int j = 0; j < nameNode.size(); j++) {
                                }*/
                                deportInfo.setDeportName(nameNode.toString());
                            }

                            if (realData.get("productId") != null && realData.get("productId").isArray()) {
                                JsonNode product = realData.get("productId");
                                for (int j = 0; j < product.size(); j++) {

                                }
                            }

                            deportInfo.setId(Tools.getUUID());
                            deportInfo.setDeportId(realData.get("id").asText());
//                            deportInfo.setDeportName(subDataNode.get("name").asText());
                            deportInfo.setStock(Integer.parseInt(realData.get("stock").asText()));
//                            deportInfo.setTotalPlace(Integer.parseInt(subDataNode.get("totalPlace").asText()));
                            deportInfo.setFreePlace(Integer.parseInt(realData.get("freePlace").asText()));
                            deportInfo.setReportTime(new Date());
                            session.save(deportInfo);
                        }
                    }
                }
                //仓库搬运机器人信息
                else if (type.equals(MessTable.MES_CARRY_INFO)) {
                    RG_Mes_CarryInfo carryInfo = new RG_Mes_CarryInfo();
                    carryInfo.setAgvId(dataNode.get("id").asText());
                    carryInfo.setState(Boolean.parseBoolean(dataNode.get("state").asText()));
                    carryInfo.setJobDesc(dataNode.get("jobDesc").asText());
                    carryInfo.setIdOrder(dataNode.get("idOrder").asText());
                    carryInfo.setReportTime(new Date());
                    session.save(carryInfo);
                }
                //【已调】AGV信息
                else if (type.equals(MessTable.MES_AGV_INFO)) {
                    JsonNode subDataNode = dataNode.get("data");

                    RG_Mes_AgvInfo agvInfo = new RG_Mes_AgvInfo();

                    agvInfo.setAgvId(subDataNode.get("id").asText());
                    agvInfo.setState(subDataNode.get("state").asBoolean());
                    agvInfo.setGoodsDesc(subDataNode.get("jobDesc").asText());
                    agvInfo.setIdOrder(subDataNode.get("idOrder").asText());
                    agvInfo.setRemainPower(Float.parseFloat(subDataNode.get("remainPower").asText()));
                    agvInfo.setReportTime(new Date());
//                    agvInfo.setSite(subDataNode.get("site").asText());

                    session.save(agvInfo);
                }
                //【已调】装配线搬运机器人信息
                else if (type.equals(MessTable.MES_ASSEMBLYCARRY_INFO)) {
                    RG_Mes_AssemblyCarryInfo carryInfo = new RG_Mes_AssemblyCarryInfo();

                    carryInfo.setCarryId(dataNode.get("id").asText());
                    carryInfo.setState(Boolean.parseBoolean(dataNode.get("state").asText()));
                    carryInfo.setJobDesc(dataNode.get("jobDesc").asText());
                    carryInfo.setIdOrder(dataNode.get("idOrder").asText());
                    carryInfo.setReportTime(new Date());

                    session.save(carryInfo);
                }
                //【已调】智能装配中心信息
                else if (type.equals(MessTable.MES_ASSEMBLYCENTER_INFO)) {
                    RG_Mes_AssemblyCenterInfo centerInfo = new RG_Mes_AssemblyCenterInfo();

                    centerInfo.setCarryId(dataNode.get("id").asText());
                    centerInfo.setState(Boolean.parseBoolean(dataNode.get("state").asText()));
                    centerInfo.setJobDesc(dataNode.get("jobDesc").asText());
                    centerInfo.setIdOrder(dataNode.get("idOrder").asText());
                    centerInfo.setReportTime(new Date());

                    session.save(centerInfo);
                }
            }
            //【已调】设备状态信息
            else if (mesType.equals(MessTable.MES_DEVICESTATE_INFO)) {

                RG_ResourceStateEntity stateEntity = new RG_ResourceStateEntity();
                stateEntity.setId(Tools.getUUID());
//                stateEntity.setResourceName(dataNode.get("resourceName").asText());
//                stateEntity.setManufacturer(dataNode.get("manufacturer").asText());
                stateEntity.setIdTask(dataNode.get("idTask").asText());
                stateEntity.setIdProcess(Short.parseShort(dataNode.get("idProcess").asText()));
                stateEntity.setIdProduct(dataNode.get("idProduct").asText());
                stateEntity.setProductName(dataNode.get("productName").asText());
                stateEntity.setT1Task(Tools.parseStandTextDate(dataNode.get("t1PlanTask").asText()));
                stateEntity.setT2Task(Tools.parseStandTextDate(dataNode.get("t2PlanTask").asText()));
                stateEntity.setCurrTime(new Date());
                stateEntity.setT1RealTask(Tools.parseStandTextDate(dataNode.get("t1RealTask").asText()));
                stateEntity.setT2RealTask(Tools.parseStandTextDate(dataNode.get("t2RealTask").asText()));
                stateEntity.setState(Short.parseShort(dataNode.get("state").asText()));

                session.save(stateEntity);
            }
            //订单执行信息
            else if (mesType.equals(MessTable.MES_ORDERSTATE_INFO)) {

                //获取订单id
                String idOrder = dataNode.get("idOrder").asText();

                //TODO 完工数量待以后确定
                //String completeNum = dataNode.get("completeNum").asText();

                //订单状态
                RG_OrderStateEntity orderState = new RG_OrderStateEntity();

                orderState.setIdTask(dataNode.get("idTask").asText());

                //获取task表中的nameTask字段
                String idTask = dataNode.get("idTask").asText();
                RG_TaskEntity taskEntity = (RG_TaskEntity) session.get(RG_TaskEntity.class, idTask);
                orderState.setNameTask(taskEntity.getName());

                orderState.setPlanDevice(dataNode.get("planDevice").toString());

                //x
                short planCount = Short.parseShort(dataNode.get("planCount").asText());
                orderState.setPlanCount(planCount);

                String actualDispatchTime = dataNode.get("realExcuteTime").toString();
                String actualFinsihTime = dataNode.get("realFinishTime").toString();

                orderState.setActualDispatchTime(Tools.parseStandTextDate(dataNode.get("realExcuteTime").asText()));
                orderState.setActualFinsihTime(Tools.parseStandTextDate(dataNode.get("realFinishTime").asText()));
                orderState.setActualDispatchDevice(dataNode.get("realDispatchDevice").toString());


                //向工序页面插入异常
                String idSnapshot = UserConfigTools.getLatestDispatchMesId("1");
                List<RG_PlanEntity> planEntity = (List<RG_PlanEntity>) session.createQuery("select plan from RG_PlanEntity plan where plan.snapShort.id =:idSnapshot").setParameter("idSnapshot", idSnapshot).list();

                for (RG_PlanEntity plan : planEntity) {
                    String t1Task = plan.getT1Task();
                    String t2Task = plan.getT2Task();

                    if (!t1Task.equals(actualDispatchTime) || !t2Task.equals(actualFinsihTime)) {
                        //调整工序异常
                        RG_AdjustProcessEntity adjustProcess = new RG_AdjustProcessEntity();

                        adjustProcess.setId(Tools.getUUID());
                        adjustProcess.setIdOrder(idOrder);
                        adjustProcess.setIdTask(idTask);
                        adjustProcess.setState(1);

                        session.save(adjustProcess);
                    }

                }


                //TODO x已完成  计划完工量格式不对，框架需要转换((合格+不合格)/计划数)
                short unqulifiedCount = Short.parseShort(dataNode.get("unqualifiedCount").asText());
                short qualifiedCount = Short.parseShort(dataNode.get("qualifiedCount").asText());

                float actualFinishCount = 0;
                if (planCount != 0) {
                    actualFinishCount = (float) (unqulifiedCount + qualifiedCount) / planCount;
                }

                //x
                orderState.setUnqualifiedCount(unqulifiedCount);
                orderState.setQualifiedCount(qualifiedCount);
                orderState.setActualFinishCount(actualFinishCount);//实际完工量

                orderState.setCurrTime(new Date());
                orderState.setFinished(Boolean.parseBoolean(dataNode.get("isCompleted").asText()));

                //x获取订单拥有的排程数
                RG_OrderEntity orderEntity = (RG_OrderEntity) session.get(RG_OrderEntity.class, idOrder);
                String idProduct = orderEntity.getProductByIdProduct().getId();

                List<RG_ProcessEntity> processEntity = (List<RG_ProcessEntity>) session.createQuery("select process from RG_ProcessEntity process where process.productByIdProduct.id =:idProduct").setParameter("idProduct", idProduct).list();
                int count = 0;
                for (RG_ProcessEntity process : processEntity) {
                    List<RG_ProcessEntity> processEntity2 = (List<RG_ProcessEntity>) session.createQuery("select process from RG_ProcessEntity process").list();
                    for (RG_ProcessEntity process2 : processEntity2) {
                        if (process2.getIdRoot().equals(process.getId()) && !process2.isTransport()) {
                            count++;  //订单拥有的排程数

                            //向工序页面插入异常
                            /*List<RG_PlanEntity> planEntity=  (List<RG_PlanEntity>)session.createQuery("select plan from RG_PlanEntity plan where plan.processByIdProcess.id =:idProcess").setParameter("idProcess",process2.getId()).list();

                            for (RG_PlanEntity plan : planEntity) {
                                String t1Task = plan.getT1Task();
                                String t2Task = plan.getT2Task();

                                    if(!t1Task.equals(actualDispatchTime) || !t2Task.equals(actualFinsihTime)) {
                                        //调整工序异常
                                        RG_AdjustProcessEntity adjustProcess = new RG_AdjustProcessEntity();

                                        adjustProcess.setId(Tools.getUUID());
                                        adjustProcess.setIdOrder(idOrder);
                                        adjustProcess.setIdTask(idTask);
                                        adjustProcess.setState(1);

                                        session.save(adjustProcess);
                                    }

                            }*/
                        }
                    }

                }
                System.out.println("count....:" + count);
                float finishPercent = 0;
                if (count != 0) {
                    finishPercent = actualFinishCount / count;
                    orderState.setFinishPercent(finishPercent);
                }

                session.save(orderState);
//                }
            }
            //【已调】工序指令信息
            else if (mesType.equals(MessTable.MES_INSTRUCT_INFO)) {
                RG_RealDataEntity data = new RG_RealDataEntity();
//                data.setIdResource(dataNode.get("idResource").asText());
                data.setState(dataNode.get("state").asText());
                data.setGood(dataNode.get("good").asText());
                data.setStartLocation(dataNode.get("startLocation").asText());
                data.setEndLocation(dataNode.get("endLocation").asText());
                String valueType = dataNode.get("valueType").asText();
                data.setValueType(valueType);
                //TODO value待处理
                if (valueType.equals("time")) {
                    data.setValue(dataNode.get("value").asText());
                }
                if (valueType.equals("pos")) {
                    data.setValue(dataNode.get("value").asText());
                }
                //Yang 20170825新增idTask
//                data.setIdTask(dataNode.get("idTask").asText());
                session.save(data);
            }
            //【已调】设备调整
            else if (mesType.equals(MessTable.MES_ADJUSTDEVICE_INFO)) {
                String idOrder = dataNode.get("idOrder").asText();
                Date reportTime = Tools.parseStandTextDate(dataNode.get("reportTime").asText());
                String idResource = dataNode.get("idResource").asText();
                Boolean enable = dataNode.get("enable").asBoolean();

                RG_AdjustDeviceEntity data = new RG_AdjustDeviceEntity();
                data.setId(Tools.getUUID());
                data.setOrderId(idOrder);
                data.setReportTime(reportTime);
                data.setResoureId(idResource);
                data.setCancelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                session.save(data);

                //TODO 待记录并产生异常信息
                System.out.println(idOrder + "__" + reportTime + "__" + idResource + "__" + enable);
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
