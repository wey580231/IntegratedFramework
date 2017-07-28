package com.rengu.util;

import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.entity.RG_OrderEntity;

import java.util.Date;

/**
 * Created by hanch on 2017/7/19.
 */
public class ExceptionCreator {
    public static void creatOrderException(RG_OrderEntity rg_orderEntity, String adjustOrderType) {
        RG_AdjustOrderEntity rg_adjustOrderEntityTemp = DAOFactory.getAdjustOrderDAOImplInstance().findAllByOrderId(rg_orderEntity.getId());
        if (rg_adjustOrderEntityTemp == null) {
            RG_AdjustOrderEntity rg_adjustOrderEntity = new RG_AdjustOrderEntity();
            rg_adjustOrderEntity.setId(Tools.getUUID());
            rg_adjustOrderEntity.setOrigin("自动插入");
            rg_adjustOrderEntity.setState(ErrorState.ERROR_UNSOLVED);
            rg_adjustOrderEntity.setReportTime(new Date());
            rg_adjustOrderEntity.setOrd(rg_orderEntity);
            rg_adjustOrderEntity.setAdjustOrderType(adjustOrderType);
            DAOFactory.getAdjustOrderDAOImplInstance().save(rg_adjustOrderEntity);
        } else {
            rg_adjustOrderEntityTemp.setReportTime(new Date());
            DAOFactory.getAdjustOrderDAOImplInstance().update(rg_adjustOrderEntityTemp);
        }
    }
}
