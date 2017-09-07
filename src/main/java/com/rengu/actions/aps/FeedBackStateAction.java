package com.rengu.actions.aps;

import com.opensymphony.xwork2.ActionContext;
import com.rengu.DAO.aps.ApsDao;
import com.rengu.actions.SuperAction;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.entity.RG_UserConfigEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.*;

/**
 * APS回调框架更新计算状态
 * Created by wey580231 on 2017/6/13.
 */
public class FeedBackStateAction extends SuperAction {

    private static final String APS_RESULT_SUCCESS = "1";         //APS计算成功结果标识

    private ApsDao apsDao = new ApsDao();

    //Yang 20170901 删除订单回调接口
    public void deleteOrder() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到删除订单回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {
                BackupThread queryThrad = new BackupThread(BackupThread.Query_Order_State);
                Thread thread = new Thread(queryThrad);
                thread.start();
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS结果格式不符合要求，无法解析!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //接收aps应急优化回调接口
    public void interactiveAps() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到应急优化回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {

                Session session = MySessionFactory.getSessionFactory().openSession();
                session.beginTransaction();

                RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
                String bottomId = userconfig.getBottomSnapshotId();
                if (bottomId != null) {
                    RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, bottomId);

                    if (bottomSnapshot != null) {
                        if (state[0].equals(APS_RESULT_SUCCESS)) {
                            bottomSnapshot.setApsInteractive(true);
                            //Yang 当APS交互优化后，如果需要在交互优化，则在优化成功后，自动返回结果
                            switchResult("1");
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS应急滚动优化成功!", "confirm"));
                        } else {
                            bottomSnapshot.setApsInteractive(false);
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS应急滚动优化失败!", "alert"));
                        }
                    }
                    session.update(bottomSnapshot);
                }

                session.getTransaction().commit();
                session.close();
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS结果格式不符合要求，无法解析!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //接收备份快照的结果
    public void backupSnapshot() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到备份快照回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {
                Session session = MySessionFactory.getSessionFactory().getCurrentSession();

                if (!session.getTransaction().isActive()) {
                    session.beginTransaction();
                }

                RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
                String bottomId = userconfig.getBottomSnapshotId();
                if (bottomId != null) {
                    RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, bottomId);

                    if (bottomSnapshot != null) {
                        if (state[0].equals(APS_RESULT_SUCCESS)) {
                            bottomSnapshot.setApsBackupSnaoshot(true);
                            UserConfigTools.updateApsSnapshotId("1", bottomSnapshot.getId());
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS快照备份成功!", "confirm"));
                        } else {
                            bottomSnapshot.setApsBackupSnaoshot(false);
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS快照备份失败!", "alert"));
                        }
                        session.update(bottomSnapshot);
                    }
                }
                session.getTransaction().commit();
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算出错!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //APS回调恢复快照
    public void recoverSnapshot() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到恢复快照回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {

                Session session = MySessionFactory.getSessionFactory().getCurrentSession();
                if (!session.getTransaction().isActive()) {
                    session.beginTransaction();
                }

                RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
                String bottomSnapshotId = userconfig.getApsCurrSnapshotId();
                if (bottomSnapshotId != null) {
                    RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, bottomSnapshotId);

                    if (bottomSnapshot != null) {
                        if (state[0].equals(APS_RESULT_SUCCESS)) {
                            bottomSnapshot.setApsRecoverSnapshot(true);

                            BackupThread queryThrad = new BackupThread(BackupThread.Reset_Activex);
                            Thread thread = new Thread(queryThrad);
                            thread.start();

                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS恢复快照成功!", "confirm"));
                        } else {
                            bottomSnapshot.setApsRecoverSnapshot(false);
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS恢复快照失败!", "alert"));
                        }
                        session.update(bottomSnapshot);
                    }
                }
                session.getTransaction().commit();
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算出错!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //接收恢复交互控件结果
    public void recoverInterX() {
        MyLog.getLogger().info("接受aps恢复交互控件结果===========");
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }


    //下发订单
    public void dispatchOrder() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到订单下发回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {

                Session session = MySessionFactory.getSessionFactory().openSession();
                session.beginTransaction();

                RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
                String dispatchMesId = userconfig.getDispatchMesSnapshotId();
                if (dispatchMesId != null) {
                    RG_SnapshotNodeEntity bottomSnapShot = session.get(RG_SnapshotNodeEntity.class, dispatchMesId);
                    RG_SnapshotNodeEntity middleSnapShot = null;
                    if (bottomSnapShot != null) {
                        middleSnapShot = bottomSnapShot.getParent();
                    }

                    if (bottomSnapShot != null && middleSnapShot != null) {
                        if (state[0].equals(APS_RESULT_SUCCESS)) {
                            bottomSnapShot.setApsDispatchOrder(true);
                            middleSnapShot.setApsDispatchOrder(true);
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS恢复订单成功!", "confirm"));
                        } else {
                            bottomSnapShot.setApsDispatchOrder(false);
                            middleSnapShot.setApsDispatchOrder(false);
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS恢复订单失败!", "alert"));
                        }
                        session.update(middleSnapShot);
                        session.update(bottomSnapShot);
                    }
                }
                session.getTransaction().commit();
                session.close();
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算出错!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //【1】接收APS运行状态;【2】同步APS结果;【3】创建APS快照
    public void recvApsResult() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameterMap = context.getParameters();

        boolean result = false;

        if (parameterMap.size() == 3) {
            String[] id = (String[]) parameterMap.get("id");
            String[] state = (String[]) parameterMap.get("STATE");
            String[] message = (String[]) parameterMap.get("MESSAGE");

            MyLog.getLogger().info("=============收到计算结果回复消息啦============");

            if (id.length > 0 && state.length > 0 && message.length > 0) {
                switchResult(state[0]);
            }
        } else {
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算出错!", "alert"));
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }

    //处理aps转换结果
    private void switchResult(String replyState) {
        RG_UserConfigEntity userconfig = UserConfigTools.getUserConfig("1");
        if (userconfig != null && userconfig.getRootSnapshotId().length() > 0) {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            Query query = session.createQuery("from RG_SnapshotNodeEntity entity where entity.id=:id");
            query.setParameter("id", userconfig.getRootSnapshotId());
            List list = query.list();
            if (list.size() > 0 && list.get(0) instanceof RG_SnapshotNodeEntity) {
                RG_SnapshotNodeEntity rootSnapshot = (RG_SnapshotNodeEntity) list.get(0);

                //创建快照节点
                query = session.createQuery("from RG_SnapshotNodeEntity entity where entity.id=:id");
                query.setParameter("id", userconfig.getMiddleSnapshotId());
                list = query.list();
                RG_SnapshotNodeEntity middleSnapshot = null;
                if (list.size() > 0) {
                    middleSnapshot = (RG_SnapshotNodeEntity) list.get(0);
                }

                //TODO 查询schedule时会级联查询出其对应的set集合
                RG_ScheduleEntity schedule = rootSnapshot.getSchedule();
                schedule.setEndCalcTime(new Date());

                String nodeName = "";

                int apsReplyCount = userconfig.getApsReplyCount().intValue();

                //不是应急排程
                if (!userconfig.isErrorSchedule()) {
                    if (replyState.equals(APS_RESULT_SUCCESS)) {
                        if (apsReplyCount == 0) {
                            schedule.setState(RG_ScheduleEntity.APS_SUCCESS);
                            nodeName = "基础计算结果";
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算完成!", "confirm"));
                            setOrdersState("3", schedule);
                            Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS计算完成!", schedule.getId());
                        } else {
                            schedule.setState(RG_ScheduleEntity.APS_ADJUST);
                            if (middleSnapshot != null) {
                                nodeName = "优化调整" + middleSnapshot.getChilds().size();
                            }
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS优化计算完成!", "confirm"));
                            setOrdersState("3", schedule);
                            Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS优化计算完成!", schedule.getId());
                        }
                    }
                    //计算失败
                    else if (!(replyState.equals(APS_RESULT_SUCCESS))) {
                        schedule.setState(RG_ScheduleEntity.APS_FAIL);
                        WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算失败!", "alert"));
                        setOrdersState("0", schedule);
                        Tools.createEventLog(session, EventLogTools.ScheduleFailedEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS计算失败!", schedule.getId());
                    }
                }
                //故障应急排程
                else {
                    if (replyState.equals(APS_RESULT_SUCCESS)) {
                        if (apsReplyCount == 0) {
                            schedule.setState(RG_ScheduleEntity.ERROR_SUCCESS);
                            nodeName = "基础计算结果";
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS应急计算完成!", "confirm"));
                            setOrdersState("3", schedule);
                            setErrorState(userconfig.getErrorType(), userconfig.getErrorId(), ErrorState.ERROR_APS_FINISH);
                            Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS应急计算完成!", schedule.getId());

                        } else {
                            schedule.setState(RG_ScheduleEntity.ERROR_ADJUST);
                            if (middleSnapshot != null) {
                                nodeName = "应急优化调整" + middleSnapshot.getChilds().size();
                            }
                            WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS应急优化完成!", "confirm"));
                            setOrdersState("3", schedule);
                            setErrorState(userconfig.getErrorType(), userconfig.getErrorId(), ErrorState.ERROR_ADJUSTED);
                            Tools.createEventLog(session, EventLogTools.ScheduleCreateEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS应急优化完成!", schedule.getId());
                        }
                    }
                    //计算失败
                    else if (!(replyState.equals(APS_RESULT_SUCCESS))) {
                        schedule.setState(RG_ScheduleEntity.ERROR_FAIL);
                        WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS应急处理失败!", "alert"));
                        setOrdersState("0", schedule);
                        setErrorState(userconfig.getErrorType(), userconfig.getErrorId(), ErrorState.ERROR_ERROR);
                        Tools.createEventLog(session, EventLogTools.ScheduleFailedEvent, EventLogTools.SimpleTimeLineItem, schedule.getName() + "-" + nodeName, ":APS应急处理失败!", schedule.getId());
                    }
                }

                UserConfigTools.updateApsReplyCount("1", apsReplyCount + 1);

                RG_SnapshotNodeEntity bottomSnapshot = null;

                if (replyState.equals(APS_RESULT_SUCCESS) && middleSnapshot != null) {
                    bottomSnapshot = new RG_SnapshotNodeEntity();
                    bottomSnapshot.setId(Tools.getUUID());
                    bottomSnapshot.setName(nodeName);
                    bottomSnapshot.setLevel(SnapshotLevel.BOTTOM);
                    bottomSnapshot.setNodeCreateTime(new Date());
                    bottomSnapshot.setApply(false);
                    bottomSnapshot.setApsBackupSnaoshot(false);
                    bottomSnapshot.setApsDispatchOrder(false);
                    bottomSnapshot.setApsRecoverSnapshot(false);
                    if (userconfig.isErrorSchedule()) {
                        bottomSnapshot.setErrorNode(true);
                    } else {
                        bottomSnapshot.setErrorNode(false);
                    }

                    if (apsReplyCount == 0) {
                        bottomSnapshot.setFirstNode(true);
                    } else {
                        bottomSnapshot.setFirstNode(false);
                    }

                    bottomSnapshot.setParent(middleSnapshot);
                    bottomSnapshot.setRootParent(rootSnapshot);

                    UserConfigTools.updateBottomSnapshotId("1", bottomSnapshot.getId());
                    UserConfigTools.updateApsSnapshotId("1", bottomSnapshot.getId());

                    middleSnapshot.getChilds().add(bottomSnapshot);

                    session.save(middleSnapshot);
                }

                //APS_PLAN-->RG_PlanEntity共用一个session,确保在一个实务之中
                if (replyState.equals(APS_RESULT_SUCCESS)) {
                    try {
                        ApsTools.instance().getScheduleResult(bottomSnapshot);

                        session.getTransaction().commit();

                        //在非应急排程下，接收到aps的接口信息后，通知APS创建快照
                        if (replyState.equals(APS_RESULT_SUCCESS) && !userconfig.isErrorSchedule()) {
                            int tmpState = ApsTools.instance().queryExecuteState();
                            BackupThread queryThrad = new BackupThread(BackupThread.Recover_Snapshot, bottomSnapshot.getId());
                            Thread thread = new Thread(queryThrad);
                            thread.start();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        session.getTransaction().rollback();
                        WebSocketNotification.broadcast(Tools.creatNotificationMessage("APS计算结果转换出错!", "alert"));
                        setOrdersState("0", schedule);
                    }
                } else {
                    session.update(schedule);
                    session.getTransaction().commit();
                }
            } else {
                WebSocketNotification.broadcast(Tools.creatNotificationMessage("无法获取快照节点!", "alert"));
                session.getTransaction().commit();
            }
        }

        if (ApsTools.isRunning) {
            BackupThread queryThrad = new BackupThread(BackupThread.Query_Resuming_State);
            Thread thread = new Thread(queryThrad);
            thread.start();
        }
    }

    //查询APS状态,在对APS数据库操作之前，都要先执行状态查询操作
    public void queryApsState() {
        StringBuilder result = new StringBuilder();

        if (apsDao.queryCurrState(result)) {
            Tools.jsonPrint(result.toString(), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);

        }
    }

    //获取最新排程的基础信息
    public void queryScheduleInfo() {
        StringBuilder result = new StringBuilder();
        if (apsDao.getScheduleInfo(result)) {
            Tools.jsonPrint(result.toString(), this.httpServletResponse);
        } else {
            Tools.jsonPrint(Tools.resultCode("error", "Can't execute operation"), this.httpServletResponse);
        }
    }

    //设置订单状态
    private void setOrdersState(String state, RG_ScheduleEntity rg_scheduleEntity) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Set<RG_OrderEntity> rg_orderEntitySet = rg_scheduleEntity.getOrders();
        if (rg_orderEntitySet.size() > 0) {
            for (RG_OrderEntity orderEntity : rg_orderEntitySet) {
                if (orderEntity.getState() != Byte.parseByte("2")) {
                    orderEntity.setState(Byte.parseByte(state));
                    session.save(orderEntity);
                }
            }
        }
    }

    //设置对应故障的状态
    private int setErrorState(String errorType, String errorId, Integer state) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        NativeQuery tquery = session.createNativeQuery("update " + errorType + " set state = ? where id = ?");
        tquery.setParameter(1, state);
        tquery.setParameter(2, errorId);
        return tquery.executeUpdate();
    }

    //模拟aps应急优化结果
    public void emulateApsInterResult() {
        MyLog.getLogger().info("=======APS 交互結果转换中======");
        //【1】查询APS的定单表是否含有state=0的订单，如果有，则先调用应急交互优化接口，重新计算
        List list = new ArrayList();
        try {
            list = Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, "select * from APS_ORDER where STATE = 0 ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MyLog.getLogger().info("===查询的订数量为:" + list.size() + "=====");

        //【2】若不包含，则进行结果转换
        if (list.size() == 0) {
            switchResult("1");
        } else {
            BackupThread queryThrad = new BackupThread(BackupThread.Query_Order_State);
            Thread thread = new Thread(queryThrad);
            thread.start();
        }
        Tools.jsonPrint(Tools.apsCode("ok", "1", "recive execute operation"), this.httpServletResponse);
    }
}
