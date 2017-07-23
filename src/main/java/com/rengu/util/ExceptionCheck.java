package com.rengu.util;

import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;

/**
 * Created by hanch on 2017/7/19.
 */
public class ExceptionCheck {
    public static boolean orderExceptionCheck(RG_OrderEntity rg_orderEntity) {
        long t2TimeStamp = rg_orderEntity.getT2().getTime();
        RG_ScheduleEntity rg_scheduleEntity = DAOFactory.getScheduleDAOImplInstance().findAllById(UserConfigTools.getLatestSchedule("1"));
        if (rg_scheduleEntity == null) {
            return false;
        }
        long scheduleStartTime = rg_scheduleEntity.getScheduleTime().getTime();
        long scheduleEndTime = scheduleStartTime + rg_scheduleEntity.getRollTime().intValue() * 60 * 60 * 24 * 1000;
        if (t2TimeStamp >= scheduleStartTime && t2TimeStamp <= scheduleEndTime) {
            ExceptionCreator.creatOrderException(rg_orderEntity);
            WebSocketNotification.broadcast(Tools.creatNotificationMessage("产生紧急插单异常", "alert"));
            Tools.createEventLog(EventLogTools.ExceptionCreateEvent, EventLogTools.StandardTimeLineItem, "紧急插单异常", EventLogTools.createOrderEventContent(rg_orderEntity), rg_orderEntity.getId());
            return true;
        } else {
            return false;
        }
    }
}
