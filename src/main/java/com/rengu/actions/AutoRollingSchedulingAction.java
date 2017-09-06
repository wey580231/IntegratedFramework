package com.rengu.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AutoRollingSchedulingAction extends SuperAction {
    public boolean testFlag = false;

    public void autoRollingScheduling() throws ParseException, JsonProcessingException {
        testFlag = true;
        //获取最后一次排程信息
        RG_ScheduleEntity rg_scheduleEntity = DAOFactory.getScheduleDAOImplInstance().findAllById(UserConfigTools.getLatestSchedule("1"));
        if (rg_scheduleEntity == null) {
            //未获取到最后一次排程信息
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("未发现最后一次排程信息", "alert"));
            testFlag = false;
        } else {
            //获取到最后一次排程信息
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("发现最后一次排程信息", "confirm"));
            //todo 产生未完成订单的代码
            Set<RG_OrderEntity> temUunFinishedOrderList = rg_scheduleEntity.getOrders();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYYY-mm-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rg_scheduleEntity.getScheduleTime());
            calendar.add(Calendar.DAY_OF_MONTH, rg_scheduleEntity.getRollTime());
            List<RG_OrderEntity> unFinishedOrderList = new ArrayList<>();
            List<RG_OrderEntity> clearOrderlist = new ArrayList<>();
            for (RG_OrderEntity rg_OrderEntity : temUunFinishedOrderList) {
                Date orderTime = rg_OrderEntity.getT2();
                Date rollTime = calendar.getTime();
                System.out.println("产生异常订单的时间段：早于" + rollTime + "晚于" + rg_scheduleEntity.getScheduleTime());
                if (orderTime.before(rollTime) && orderTime.after(rg_scheduleEntity.getScheduleTime())) {
                    unFinishedOrderList.add(rg_OrderEntity);
                }
                if (orderTime.before(rollTime)) {
                    clearOrderlist.add(rg_OrderEntity);
                }
            }
            System.out.println("需要处理的异常数量为：" + unFinishedOrderList.size());
            if ((int) (Math.random() * 100) % 2 == 0) {
                System.out.println("真幸运不需要处理异常！！！直接删除订单");
                Tools.deleteAPSOrder(DatabaseInfo.ORACLE, DatabaseInfo.APS, unFinishedOrderList);
                unFinishedOrderList.clear();
            }
            //1.获取上次排程中以计算但是未完成的订单
            if (unFinishedOrderList.size() != 0) {
                //删除除了异常模拟之外的订单
                for (RG_OrderEntity rg_orderEntity : unFinishedOrderList) {
                    clearOrderlist.remove(rg_orderEntity);
                }
                Tools.deleteAPSOrder(DatabaseInfo.ORACLE, DatabaseInfo.APS, clearOrderlist);
                //查询到未完成订单
                //产生订单未处理异常(模拟)
                for (RG_OrderEntity rg_OrderEntity : unFinishedOrderList) {
                    //查找当前订单对应的工序信息列表
                    List<RG_PlanEntity> planEntityList = DAOFactory.getPlanDAOImplInstance().findAllByOrderId(rg_OrderEntity.getId());
                    if (planEntityList.size() != 0) {
                        //从列表中随机获取工序
                        int i = (int) (Math.random() * 50);
                        RG_PlanEntity rg_planEntity = planEntityList.get(i);
                        //根据随机的Plan信息生成工序异常
                        RG_AdjustProcessEntity rg_adjustProcessEntity = new RG_AdjustProcessEntity();
                        rg_adjustProcessEntity.setId(Tools.getUUID());
                        rg_adjustProcessEntity.setReportTime(new Date());
                        rg_adjustProcessEntity.setIdTask(rg_planEntity.getIdTask());
                        rg_adjustProcessEntity.setIdJob(rg_planEntity.getIdJob());
                        rg_adjustProcessEntity.setIdOrder(rg_planEntity.getOrderByIdOrder().getId());
                        rg_adjustProcessEntity.setOriginalResource(rg_planEntity.getResourceByIdResource().getIdR());
                        rg_adjustProcessEntity.setAppointResource(rg_planEntity.getResourceByIdResource().getIdR());
                        //生成指定拖期时间
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        // 工序时间长度
                        long length = 82740000 - (Tools.parseStandTextDate(rg_planEntity.getT2Task()).getTime() - Tools.parseStandTextDate(rg_planEntity.getT1Task()).getTime());
                        long t1 = Tools.stringConvertToDate(rg_planEntity.getT1Task()).getTime();
                        long sum = t1 + length;
                        Date dateTime = simpleDateFormat.parse(simpleDateFormat.format(sum));
//                        Date dateTime = simpleDateFormat.parse(simpleDateFormat.format(Tools.stringConvertToDate(rg_planEntity.getT1Task()).getTime() + 60 * 60 * 1000 * 4));
                        rg_adjustProcessEntity.setAppointStartTime(dateTime);
                        rg_adjustProcessEntity.setOriginalStartTime(Tools.parseStandTextDate(rg_planEntity.getT1Task()));
                        rg_adjustProcessEntity.setOrigin("自动模拟");
                        rg_adjustProcessEntity.setState(ErrorState.ERROR_UNSOLVED);
                        DAOFactory.getAdjustProcessDAOImplInstance().save(rg_adjustProcessEntity);
                    }
                }

                //对未完成的订单进行计算
                List<RG_AdjustProcessEntity> adjustProcessEntityList = DAOFactory.getAdjustProcessDAOImplInstance().findAllByErrorState(ErrorState.ERROR_UNSOLVED);
                if (adjustProcessEntityList.size() > 0) {
                    for (RG_AdjustProcessEntity rg_adjustProcessEntity : adjustProcessEntityList) {
                        System.out.println("当前APS服务器是否在进行计算：" + ApsTools.isRunning);
                        //处理拖期异常
                        ApsTools.instance().executeCommand(ApsTools.getAdjustProcessHandlingURL(rg_adjustProcessEntity));
                        while (ApsTools.isRunning) {
                            try {
                                System.out.println("APS计算中...");
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            //没有异常模拟直接删除所有已完成的订单
            Tools.deleteAPSOrder(DatabaseInfo.ORACLE, DatabaseInfo.APS, clearOrderlist);
            //【2】将订单下发给MES

            //【3】自动进行下一次排程，参见ApsTable的createPostBody方法
            new ScheduleAction().parseAndSnaphost(createPostBody(), calendar.getTime());
        }
        testFlag = false;
    }

    public String createPostBody() throws ParseException, JsonProcessingException {
        Session session = MySessionFactory.getSessionFactory().openSession();
        String latestScheduleId = UserConfigTools.getLatestSchedule("1");
        if (latestScheduleId != null && latestScheduleId.length() > 0) {
            RG_ScheduleEntity scheduleEntity = session.get(RG_ScheduleEntity.class, latestScheduleId);
            if (scheduleEntity != null) {
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode mainNode = mapper.createObjectNode();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                //Todo 待按照时间筛选出订单
                ArrayNode orderNode = mapper.createArrayNode();
                calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(scheduleEntity.getScheduleTime())));
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getRollTime());
                Date startRollingTime = calendar.getTime();     //
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getRollTime());
                Date endRollingTime = calendar.getTime();

                calendar.setTime(startRollingTime);
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getScheduleWindow());
                Date endSelectOrderTime = calendar.getTime();
                calendar.setTime(endSelectOrderTime);
                calendar.add(Calendar.DAY_OF_MONTH, - scheduleEntity.getRollTime());
                Date startSelectOrderTime = calendar.getTime();
                mainNode.put("name", "排程-" + Tools.formatToStandardDate(startRollingTime));
                mainNode.put("scheduleWindow", scheduleEntity.getScheduleWindow());
                mainNode.put("rollTime", scheduleEntity.getRollTime());
                mainNode.put("scheduleOption", scheduleEntity.getScheduleOption());

                String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.t2 between ? and ? and rg_orderEntity.state =:state";
                Query query = session.createQuery(hql);
                query.setParameter(0, startSelectOrderTime);
                query.setParameter(1, endSelectOrderTime);
                query.setParameter("state", Byte.parseByte("0"));
                List<RG_OrderEntity> orderEntityList = query.list();
                //本次滚动新增的订单
                for (RG_OrderEntity rg_OrderEntity : orderEntityList) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("id", rg_OrderEntity.getId());
                    orderNode.add(objectNode);
                }
                //添加上次排程使用的订单信息
                for (RG_OrderEntity rg_orderEntity : scheduleEntity.getOrders()) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("id", rg_orderEntity.getId());
                    orderNode.add(objectNode);
                }
                mainNode.put("orders", orderNode);

                ObjectNode apsNode = mapper.createObjectNode();
                calendar.setTime(startRollingTime);
                calendar.add(Calendar.DAY_OF_YEAR, -2);
                apsNode.put("t0", calendar.getTime().getTime());
                calendar.setTime(endSelectOrderTime);
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getScheduleOption());
                apsNode.put("t2", calendar.getTime().getTime());
                apsNode.put("modeScheduling", scheduleEntity.getApsModel());

                mainNode.put("APSConfig", apsNode);

                ObjectNode layoutNode = mapper.createObjectNode();
                layoutNode.put("id", scheduleEntity.getLayout().getId());
                mainNode.put("layout", layoutNode);

                ArrayNode resourceNode = mapper.createArrayNode();
                ObjectNode resNode = mapper.createObjectNode();
                resNode.put("id", 2);
                resourceNode.add(resNode);
                mainNode.put("resources", resourceNode);

                ArrayNode groupResourceNode = mapper.createArrayNode();
                ObjectNode groupNode = mapper.createObjectNode();
                groupNode.put("id", 2);
                groupResourceNode.add(groupNode);
                mainNode.put("groupResource", groupResourceNode);

                ArrayNode sitesNode = mapper.createArrayNode();
                ObjectNode siteNode = mapper.createObjectNode();
                siteNode.put("id", 2);
                sitesNode.add(siteNode);
                mainNode.put("site", sitesNode);

                System.out.println(mapper.writeValueAsString(mainNode));
                return mapper.writeValueAsString(mainNode);
            }
        }
        return null;
    }
}