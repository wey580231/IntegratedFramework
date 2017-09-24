package com.rengu.util;

import com.rengu.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hanchangming on 2017/5/27.
 */
public class EntityConvertToSQL {
    public static String updateAPSConfigSQL(String name, String value) {
        String SQLString = "UPDATE " + DatabaseInfo.APS_CONGIF + " SET value='" + value + "' WHERE name='" + name + "'";
        return SQLString;
    }

    public static String updateSQLForAPS(RG_OrderEntity rg_orderEntity) {
        String SQLString = "update " + DatabaseInfo.APS_ORDER + " set name = '" + rg_orderEntity.getName() + "',idclub = '" + rg_orderEntity.getClubByIdClub().getId() + "',priority = '" + rg_orderEntity.getPriority() + "',IDPRODUCT = '" + rg_orderEntity.getProductByIdProduct().getId() + "',quantity = '" + rg_orderEntity.getQuantity() + "', t0 = '" + Tools.dateConvertToString(rg_orderEntity.getT0()) + "',t1 = '" + Tools.dateConvertToString(rg_orderEntity.getT1()) + "',t2 = '" + Tools.dateConvertToString(rg_orderEntity.getT2()) + "',color = '" + rg_orderEntity.getColor() + "',state = '" + "0" + "' where id = '" + rg_orderEntity.getId() + "'";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_OrderEntity rg_orderEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_ORDER + " (id,name,IDCLUB,priority,IDPRODUCT,quantity,t0,t1,t2,color,state) VALUES ('" + rg_orderEntity.getId()
                + "','" + rg_orderEntity.getName()
                + "','" + rg_orderEntity.getClubByIdClub().getId()
                + "'," + rg_orderEntity.getPriority()
                + ",'" + rg_orderEntity.getProductByIdProduct().getId()
                + "'," + rg_orderEntity.getQuantity()
                + ",'" + Tools.dateConvertToString(rg_orderEntity.getT0())
                + "','" + Tools.dateConvertToString(rg_orderEntity.getT1())
                + "','" + Tools.dateConvertToString(rg_orderEntity.getT2())
                + "','" + rg_orderEntity.getColor()
                + "'," + 0 + ")";   //rg_orderEntity.getState()
        return SQLString;
    }

    public static String insertSQLForAPS(RG_ResourceEntity rg_resourceEntity) throws SQLException, ClassNotFoundException {
        //拼接IdTypeResource集合
        StringBuffer idTypeResourceBuff = new StringBuffer();

        idTypeResourceBuff.append("{");
        Iterator<RG_TyperescourceEntity> rg_typerescourceEntityIterator = rg_resourceEntity.getTyperesourcesById().iterator();
        while (rg_typerescourceEntityIterator.hasNext()) {
            RG_TyperescourceEntity rg_typerescourceEntity = rg_typerescourceEntityIterator.next();
            if (rg_typerescourceEntityIterator.hasNext()) {
                idTypeResourceBuff.append("\"" + rg_typerescourceEntity.getId() + "\",\"");
            } else {
                idTypeResourceBuff.append("\"" + rg_typerescourceEntity.getId() + "\"");
            }
        }
        idTypeResourceBuff.append("}");
        //拼接IdSite集合

        StringBuffer IdSiteBuff = new StringBuffer();
        IdSiteBuff.append("{");
        Iterator<RG_SiteEntity> rg_siteEntityIterator = rg_resourceEntity.getSitesById().iterator();
        while (rg_siteEntityIterator.hasNext()) {
            RG_SiteEntity rg_siteEntity = rg_siteEntityIterator.next();
            if (rg_siteEntityIterator.hasNext()) {
                IdSiteBuff.append("\"" + rg_siteEntity.getId() + "\",\"");
            } else {
                IdSiteBuff.append("\"" + rg_siteEntity.getId() + "\"");
            }
        }
        IdSiteBuff.append("}");

        //拼接IdShift集合
        StringBuffer IdShiftBuff = new StringBuffer();

        IdShiftBuff.append("{");
        Iterator<RG_ShiftEntity> rg_shiftEntityIterator = rg_resourceEntity.getShiftsById().iterator();
        while (rg_shiftEntityIterator.hasNext()) {
            RG_ShiftEntity rg_shiftEntity = rg_shiftEntityIterator.next();
            if (rg_shiftEntityIterator.hasNext()) {
                IdShiftBuff.append("\"" + rg_shiftEntity.getId() + "\",\"");
            } else {
                IdShiftBuff.append("\"" + rg_shiftEntity.getId() + "\"");
            }
        }
        IdShiftBuff.append("}");
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_RESOURCE + " (id,name,IdTypeResource,idGroupResource,IdSite,mobility,critical,IdShift,NameShift,RATE,COLOR,STATE,CALENDAR,SLOT,IDICON,IDSITE0,QUANTITY0,SIZEICON,IDCLUB,WEEKEND,MAKESPAN) VALUES ('"
                + rg_resourceEntity.getIdR() + "','"
                + rg_resourceEntity.getName() + "','"
                + idTypeResourceBuff.toString() + "','"
                + rg_resourceEntity.getGroupresourceByIdGroupResource().getId() + "','"
                + IdSiteBuff.toString() + "'," + rg_resourceEntity.getMobility() + ",'"
                + rg_resourceEntity.getCritical() + "','"
                + IdShiftBuff.toString() + "','"
                + rg_resourceEntity.getNameShift() + "',"
                + rg_resourceEntity.getRate() + ",'"
                + rg_resourceEntity.getColor() + "',"
                + rg_resourceEntity.getState() + ",'"
                + rg_resourceEntity.getCalendar() + "','"
                + rg_resourceEntity.getSlot() + "','"
                + rg_resourceEntity.getIdIcon() + "','"
                + rg_resourceEntity.getIdSite0() + "',"
                + rg_resourceEntity.getQuantity0() + ",'"
                + rg_resourceEntity.getSizeIcon() + "','"
                + rg_resourceEntity.getClubByIdClub().getId() + "','"
                + rg_resourceEntity.getWeekend() + "','"
                + rg_resourceEntity.getMakespan() + "')";
        return SQLString;
    }

    public static String insertSQLForAPS_APS_RESOURCE_TYPERESOURCE(String resourceId, String typeResourceId) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_RESOURCE_TYPERESOURCE +
                " (idResource,idTypeResource,maxCapacityParallel,capacitySequence,delaySequence) VALUES ('" + resourceId
                + "','" + typeResourceId
                + "'," + 1
                + "," + 1
                + "," + 0 + ")";
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
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SITE + " (id,name,type,x,y,color,idIcon,sizeIcon,capacity) VALUES ('"
                + rg_siteEntity.getId() + "','"
                + rg_siteEntity.getName() + "','"
                + rg_siteEntity.getType() + "',"
                + rg_siteEntity.getX() + ","
                + rg_siteEntity.getY() + ",'"
                + rg_siteEntity.getColor() + "','"
                + rg_siteEntity.getIdIcon() + "',"
                + rg_siteEntity.getSizeIcon() + ","
                + rg_siteEntity.getCapacity() + ")";
        return SQLString;
    }

    public static String insertSQLForAPS(RG_TyperescourceEntity rg_typerescourceEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_TYPERESOURCE + " (id,name,attribute) VALUES ('" + rg_typerescourceEntity.getId() + "','" + rg_typerescourceEntity.getName() + "','" + rg_typerescourceEntity.getAttribute() + "');";
        return SQLString;
    }

    /*public static String insertSQLForAPS(RG_ShiftEntity rg_shiftEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SHIFT + " (id,name,type,Slot) VALUES ('" + rg_shiftEntity.getId() + "','" + rg_shiftEntity.getName() + "','" + rg_shiftEntity.getType() + "','" + rg_shiftEntity.getSlot() + "');";
        return SQLString;
    }*/

    public static String insertSQLForAPS(RG_ShiftEntity rg_shiftEntity) {
        String SQLString = "INSERT INTO " + DatabaseInfo.APS_SHIFT + " (id,name,Slot) VALUES ('" + rg_shiftEntity.getId() + "','" + rg_shiftEntity.getName() + "','" + rg_shiftEntity.getSlot() + "');";
        return SQLString;
    }

    private static List getListFromHashMap(String stringList) {
        List list = new ArrayList();
        stringList = stringList.replace("{", "");
        stringList = stringList.replace("}", "");
        String[] stringLists = stringList.split(",");
        for (String s : stringLists) {
            s = s.replaceAll("\"", "");
            list.add(s);
        }
        return list;
    }
}
