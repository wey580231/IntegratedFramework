package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.SuperAction;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class OrdersAction extends SuperAction implements ModelDriven<RG_OrderEntity> {
    RG_OrderEntity rg_orderEntity = new RG_OrderEntity();

    @Override
    public RG_OrderEntity getModel() {
        return null;
    }

    public String findAllByUsername() throws Exception {
        return "success";
    }
}
