package com.rengu.actions;

import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AutoRollingSchedulingAction extends SuperAction {
    public void autoRollingScheduling() throws ParseException {
        //获取最后一次排程信息
        RG_ScheduleEntity rg_scheduleEntity = DAOFactory.getScheduleDAOImplInstance().findAllById(UserConfigTools.getLatestSchedule("1"));
        if (rg_scheduleEntity == null) {
            //未获取到最后一次排程信息
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("未发现最后一次排程信息", "alert"));
        } else {
            //获取到最后一次排程信息
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("发现最后一次排程信息", "confirm"));
            //todo 产生未完成订单的代码
            //1.获取上次排程中以计算但是未完成的订单
            List<RG_OrderEntity> unFinishedOrderList = DAOFactory.getOrdersDAOInstance().findAllByStateAndIsFinished(Byte.parseByte("3"), false);
            if (unFinishedOrderList.size() != 0) {
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
                        System.out.println(simpleDateFormat.format(sum));
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
                        //处理拖期异常
                        ApsTools.instance().executeCommand(ApsTools.getAdjustProcessHandlingURL(rg_adjustProcessEntity));
                        System.out.println("当前APS服务器是否在进行计算：" + ApsTools.isRunning);
                        while (ApsTools.isRunning) {
                            try {
                                System.out.println("循环中");
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            //2、
        }
    }
}