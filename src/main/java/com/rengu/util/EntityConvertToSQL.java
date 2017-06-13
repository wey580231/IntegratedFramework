package com.rengu.util;

import com.rengu.entity.*;

/**
 * Created by hanchangming on 2017/5/27.
 */
public class EntityConvertToSQL {
    public static String insertAPSConfigSQL(String name, String value) {
        String SQLString = "UPDATE " + DatabaseInfo.APS_CONGIF + " SET value=" + value + " WHERE name=" + name + ";";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_OrderEntity rg_orderEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_ORDER + " (id,name,idClub,priority,idProduct,quantity,t0,t1,t2,color,state) VALUES (" + rg_orderEntity.getId() + "," + rg_orderEntity.getName() + "," + rg_orderEntity.getClubByIdClub().getId() + "," + rg_orderEntity.getPriority() + "," + rg_orderEntity.getProductByIdProduct().getId() + "," + rg_orderEntity.getQuantity() + "," + rg_orderEntity.getT0() + "," + rg_orderEntity.getT1() + "," + rg_orderEntity.getT2() + "," + rg_orderEntity.getColor() + "," + rg_orderEntity.getState() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_ResourceEntity rg_resourceEntity) {
        //拼接IdTypeResource集合
        String IdTypeResource = "{";
        for (RG_TyperescourceEntity rg_typerescourceEntity : rg_resourceEntity.getTyperesourcesById()) {
            if (rg_resourceEntity.getTyperesourcesById().iterator().hasNext()) {
                IdTypeResource = IdTypeResource + rg_typerescourceEntity.getId() + ",";
            } else {
                IdTypeResource = IdTypeResource + rg_typerescourceEntity.getId();
            }
        }
        IdTypeResource = IdTypeResource + "}";

        //拼接IdSite集合
        String IdSite = "{";
        for (RG_SiteEntity rg_siteEntity : rg_resourceEntity.getSitesById()) {
            if (rg_resourceEntity.getSitesById().iterator().hasNext()) {
                IdSite = IdSite + rg_siteEntity.getId() + ",";
            } else {
                IdSite = IdSite + rg_siteEntity.getId();
            }
        }
        IdSite = IdSite + "}";

        //拼接IdShift集合
        String IdShift = "{";
        for (RG_ShiftEntity rg_shiftEntity : rg_resourceEntity.getShiftsById()) {
            if (rg_resourceEntity.getShiftsById().iterator().hasNext()) {
                IdShift = IdShift + rg_shiftEntity.getId() + ",";
            } else {
                IdShift = IdShift + rg_shiftEntity.getId();
            }
        }
        IdShift = IdShift + "}";
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_RESOURCE + " (id,name,IdTypeResource,idGroupResource,IdSite,mobility,critical,IdShift,NameShift,DateForbidden,weekend) VALUES (" + rg_resourceEntity.getId() + "," + rg_resourceEntity.getName() + "," + IdTypeResource + "," + rg_resourceEntity.getGroupresourceByIdGroupResource().getId() + "," + IdSite + "," + rg_resourceEntity.getMobility() + "," + rg_resourceEntity.getCritical() + "," + IdShift + "," + rg_resourceEntity.getNameShift() + "," + rg_resourceEntity.getDateForbidden() + "," + rg_resourceEntity.getWeekend() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_GroupresourceEntity rg_groupresourceEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_GROUPRESOURCE + " (id,name,IdSite,idSite0,idProvider,idClub,color) VALUES (" + rg_groupresourceEntity.getId() + "," + rg_groupresourceEntity.getName() + "," + rg_groupresourceEntity.getIdSite() + "," + rg_groupresourceEntity.getIdSite0() + "," + rg_groupresourceEntity.getProviderByIdProvider().getId() + "," + rg_groupresourceEntity.getProviderByIdProvider().getClubByIdClub().getId() + "," + rg_groupresourceEntity.getColor() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_SiteEntity rg_siteEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SITE + " (id,name,color,idIcon,sizeIcon,capacity) VALUES (" + rg_siteEntity.getId() + "," + rg_siteEntity.getName() + "," + rg_siteEntity.getColor() + "," + rg_siteEntity.getIdIcon() + "," + rg_siteEntity.getSizeIcon() + "," + rg_siteEntity.getCapacity() + ");";
        return SQLString;
    }
}
