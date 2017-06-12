package com.rengu.util;

import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ResourceEntity;
import com.rengu.entity.RG_SiteEntity;

/**
 * Created by hanchangming on 2017/5/27.
 */
public class EntityConvertToSQL {
    public static String insertSQLForAPS(RG_OrderEntity rg_orderEntity) {
        String SQLString = "INSERT INTO " + Info.APS_ORDER + " (id,name,idClub,priority,idProduct,quantity,t0,t1,t2,color,state) VALUES (" + rg_orderEntity.getId() + "," + rg_orderEntity.getName() + "," + rg_orderEntity.getClubByIdClub().getId() + "," + rg_orderEntity.getPriority() + "," + rg_orderEntity.getProductByIdProduct().getId() + "," + rg_orderEntity.getQuantity() + "," + rg_orderEntity.getT0() + "," + rg_orderEntity.getT1() + "," + rg_orderEntity.getT2() + "," + rg_orderEntity.getColor() + "," + rg_orderEntity.getState() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_ResourceEntity rg_resourceEntity) {
        String SQLString = "";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_GroupresourceEntity rg_groupresourceEntity) {
        String SQLString = "";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_SiteEntity rg_siteEntity) {
        String SQLString = "";
        return SQLString;
    }
}
