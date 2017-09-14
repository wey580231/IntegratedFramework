package com.rengu.simulate;

import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.Tools;

import java.util.Date;
import java.util.List;

public class AdjustProcessException {

    public static List<RG_OrderEntity> getAdjustProcessExceptionList(RG_ScheduleEntity rg_scheduleEntity) {
        //计算上次排程的开始和结束时间。
        Date lastScheduleStartTime = rg_scheduleEntity.getScheduleTime();
        Date lastScheduleEndTime = Tools.dateCalculator(lastScheduleStartTime, rg_scheduleEntity.getRollTime());
        return null;
    }
}
