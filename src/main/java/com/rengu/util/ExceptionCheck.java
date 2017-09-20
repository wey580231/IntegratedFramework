package com.rengu.util;

import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;

import java.util.Date;
import java.util.Set;

/**
 * Created by hanch on 2017/7/19.
 */
public class ExceptionCheck {
    //检查是否产生订单相关异常
    public static boolean orderExceptionCheck(RG_OrderEntity rg_orderEntity) {
        RG_ScheduleEntity rg_scheduleEntity = DAOFactory.getScheduleDAOImplInstance().findAllById(UserConfigTools.getLatestSchedule("1"));
        if (rg_scheduleEntity == null) {
            return false;
        }
        Set<RG_SnapshotNodeEntity> rg_snapshotNodeEntitySet = rg_scheduleEntity.getSnapshot().getChilds();
        boolean isSendToMES = false;
        for (RG_SnapshotNodeEntity rg_snapshotNodeEntity : rg_snapshotNodeEntitySet) {
            if (rg_snapshotNodeEntity.getApply() == true) {
                isSendToMES = true;
            }
        }
        //计算排程结束时间
        Date scheduleEndDate = Tools.dateCalculator(rg_scheduleEntity.getScheduleTime(), rg_scheduleEntity.getScheduleWindow());
        boolean isBeforeEndDate = rg_orderEntity.getT2().before(scheduleEndDate);
        boolean isAfterStartDate = rg_orderEntity.getT2().after(rg_scheduleEntity.getScheduleTime());
        if (isAfterStartDate && isBeforeEndDate) {
            if (isSendToMES) {
                //紧急插单
                ExceptionCreator.creatOrderException(rg_orderEntity, "紧急插单分析");
                Tools.createEventLog(EventLogTools.ExceptionCreateEvent, EventLogTools.StandardTimeLineItem, "紧急插单异常", EventLogTools.createOrderEventContent(rg_orderEntity), rg_orderEntity.getId());
                WebSocketNotification.broadcast(Tools.creatNotificationMessage("产生紧急插单异常", "alert"));
            } else {
                //交期承诺分析
                ExceptionCreator.creatOrderException(rg_orderEntity, "交期承诺分析");
                Tools.createEventLog(EventLogTools.ExceptionCreateEvent, EventLogTools.StandardTimeLineItem, "交期承诺分析", EventLogTools.createOrderEventContent(rg_orderEntity), rg_orderEntity.getId());
                WebSocketNotification.broadcast(Tools.creatNotificationMessage("产生紧急插单异常", "alert"));
            }
            return true;
        } else {
            return false;
        }
    }
}
