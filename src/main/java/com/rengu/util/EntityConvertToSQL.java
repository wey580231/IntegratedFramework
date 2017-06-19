package com.rengu.util;

import com.rengu.entity.*;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * Created by hanchangming on 2017/5/27.
 */
public class EntityConvertToSQL {
    public static String insertAPSConfigSQL(String name, String value) {
        String SQLString = "UPDATE " + DatabaseInfo.APS_CONGIF + " SET value='" + value + "' WHERE name='" + name + "';";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_OrderEntity rg_orderEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_ORDER + " (id,name,idClub,priority,idProduct,quantity,t0,t1,t2,color,state) VALUES ('" + rg_orderEntity.getId()
                + "','" + rg_orderEntity.getName()
                + "','" + rg_orderEntity.getClubByIdClub().getId()
                + "'," + rg_orderEntity.getPriority()
                + ",'" + rg_orderEntity.getProductByIdProduct().getId()
                + "'," + rg_orderEntity.getQuantity()
                + ",'" + rg_orderEntity.getT0()
                + "','" + rg_orderEntity.getT1()
                + "','" + rg_orderEntity.getT2()
                + "','" + rg_orderEntity.getColor()
                + "'," + rg_orderEntity.getState() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_ResourceEntity rg_resourceEntity) throws SQLException, ClassNotFoundException {
        //拼接IdTypeResource集合
        String IdTypeResource = "{";
        Iterator<RG_TyperescourceEntity> rg_typerescourceEntityIterator = rg_resourceEntity.getTyperesourcesById().iterator();
        while (rg_typerescourceEntityIterator.hasNext()) {
            RG_TyperescourceEntity rg_typerescourceEntity = rg_typerescourceEntityIterator.next();
            if (rg_typerescourceEntityIterator.hasNext()) {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_typerescourceEntity));
                IdTypeResource = IdTypeResource + rg_typerescourceEntity.getId() + ",";
            } else {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_typerescourceEntity));
                IdTypeResource = IdTypeResource + rg_typerescourceEntity.getId();
            }
        }
        IdTypeResource = IdTypeResource + "}";

        //拼接IdSite集合
        String IdSite = "{";
        Iterator<RG_SiteEntity> rg_siteEntityIterator = rg_resourceEntity.getSitesById().iterator();
        while (rg_siteEntityIterator.hasNext()) {
            RG_SiteEntity rg_siteEntity = rg_siteEntityIterator.next();
            if (rg_siteEntityIterator.hasNext()) {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_siteEntity));
                IdSite = IdSite + rg_siteEntity.getId() + ",";
            } else {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_siteEntity));
                IdSite = IdSite + rg_siteEntity.getId();
            }
        }
        IdSite = IdSite + "}";

        //拼接IdShift集合
        String IdShift = "{";
        Iterator<RG_ShiftEntity> rg_shiftEntityIterator = rg_resourceEntity.getShiftsById().iterator();
        while (rg_shiftEntityIterator.hasNext()) {
            RG_ShiftEntity rg_shiftEntity = rg_shiftEntityIterator.next();
            if (rg_shiftEntityIterator.hasNext()) {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_shiftEntity));
                IdShift = IdShift + rg_shiftEntity.getId() + ",";
            } else {
                Tools.executeSQLForUpdate(DatabaseInfo.MySQL, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_shiftEntity));
                IdShift = IdShift + rg_shiftEntity.getId();
            }
        }
        IdShift = IdShift + "}";
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_RESOURCE + " (id,name,IdTypeResource,idGroupResource,IdSite,mobility,critical,IdShift,NameShift,DateForbidden,weekend) VALUES ('"
                + rg_resourceEntity.getId() + "','"
                + rg_resourceEntity.getName() + "','"
                + IdTypeResource + "','"
                + rg_resourceEntity.getGroupresourceByIdGroupResource().getId() + "','"
                + IdSite + "'," + rg_resourceEntity.getMobility() + ",'"
                + rg_resourceEntity.getCritical() + "','"
                + IdShift + "','"
                + rg_resourceEntity.getNameShift() + "','"
                + rg_resourceEntity.getDateForbidden() + "','"
                + rg_resourceEntity.getWeekend() + "');";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_GroupresourceEntity rg_groupresourceEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_GROUPRESOURCE +
                " (id,name,IdSite,idSite0,idProvider,idClub,color) VALUES ('" + rg_groupresourceEntity.getId()
                + "','" + rg_groupresourceEntity.getName()
                + "','" + rg_groupresourceEntity.getIdSite()
                + "','" + rg_groupresourceEntity.getIdSite0()
                + "','" + rg_groupresourceEntity.getProviderByIdProvider().getId()
                + "','" + rg_groupresourceEntity.getProviderByIdProvider().getClubByIdClub().getId()
                + "','" + rg_groupresourceEntity.getColor() + "');";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_SiteEntity rg_siteEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SITE + " (id,name,color,idIcon,sizeIcon,capacity) VALUES ('"
                + rg_siteEntity.getId() + "','"
                + rg_siteEntity.getName() + "','"
                + rg_siteEntity.getColor() + "','"
                + rg_siteEntity.getIdIcon() + "',"
                + rg_siteEntity.getSizeIcon() + ","
                + rg_siteEntity.getCapacity() + ");";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_TyperescourceEntity rg_typerescourceEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_TYPERESOURCE + " (id,name,attribute) VALUES ('" + rg_typerescourceEntity.getId() + "','" + rg_typerescourceEntity.getName() + "','" + rg_typerescourceEntity.getAttribute() + "');";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_ShiftEntity rg_shiftEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SHIFT + " (id,name,type,Slot) VALUES ('" + rg_shiftEntity.getId() + "','" + rg_shiftEntity.getName() + "','" + rg_shiftEntity.getType() + "','" + rg_shiftEntity.getSlot() + "');";
        return SQLString;
    }
}
