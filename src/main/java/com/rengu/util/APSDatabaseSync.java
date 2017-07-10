package com.rengu.util;

import com.rengu.DAO.impl.OrdersDAOImpl;
import com.rengu.DAO.impl.ProductDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ProductEntity;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanch on 2017/7/4.
 */
public class APSDatabaseSync {
    public static boolean SyncAPSTable(String tableName) throws SQLException, ClassNotFoundException, ParseException {
        String SQLString = "select * from " + tableName + "";
        List list = Tools.executeSQLForList(DatabaseInfo.MySQL, DatabaseInfo.APS, SQLString);
        if (tableName.equals(DatabaseInfo.APS_PRODUCT)) {
            return SyncProductTable(list);
        }
        if (tableName.equals(DatabaseInfo.APS_ORDER)) {
            return SyncOrderTable(list);
        }
        return false;
    }

    //同步产品表
    private static boolean SyncProductTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = new HashMap();
                RG_ProductEntity rg_productEntity = new RG_ProductEntity();
                rg_productEntity.setId(getStringFromHashMap(tempMap, "ID"));
                rg_productEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_productEntity.setType(getStringFromHashMap(tempMap, "TYPE"));
                rg_productEntity.setRef(getStringFromHashMap(tempMap, "REF"));
                rg_productEntity.setDepth(getStringFromHashMap(tempMap, "DEPTH"));
                rg_productEntity.setStock(getShortFromHashMap(tempMap, "STOCK"));
                ProductDAOImpl productDAO = DAOFactory.getProductDAOImplInstance();
                productDAO.save(rg_productEntity);
                return true;
            }
        }
        return false;
    }

    //同步订单表
    private static boolean SyncOrderTable(List list) throws ParseException {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = new HashMap();
                RG_OrderEntity rg_orderEntity = new RG_OrderEntity();
                rg_orderEntity.setId(getStringFromHashMap(tempMap, "ID"));
                rg_orderEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_orderEntity.setType(getStringFromHashMap(tempMap, "TYPE"));
                rg_orderEntity.setQuantity(getShortFromHashMap(tempMap, "QUANTITY"));
                rg_orderEntity.setPriority(getShortFromHashMap(tempMap, "PRIORITY"));
                rg_orderEntity.setT0(getDateFromHashMap(tempMap, "T0"));
                rg_orderEntity.setT1(getDateFromHashMap(tempMap, "T1"));
                rg_orderEntity.setT2(getDateFromHashMap(tempMap, "T2"));
                rg_orderEntity.setEstimate(getShortFromHashMap(tempMap, "ESTIMATE"));
                rg_orderEntity.setColor(getStringFromHashMap(tempMap, "COLOR"));
                rg_orderEntity.setState(getByteFromHashMap(tempMap, "STATE"));
                rg_orderEntity.setT1Plan(getDateFromHashMap(tempMap, "T1PLAN"));
                rg_orderEntity.setT2Plan(getDateFromHashMap(tempMap, "T2PLAN"));
                rg_orderEntity.setSelected(getByteFromHashMap(tempMap, "SELECTED"));
                rg_orderEntity.setT1Interaction(getStringFromHashMap(tempMap, "T1INTERACTION"));
                rg_orderEntity.setT2Interaction(getStringFromHashMap(tempMap, "T2INTERACTION"));
                rg_orderEntity.setDelay(getShortFromHashMap(tempMap, "DELAY"));
                rg_orderEntity.setOrd(getShortFromHashMap(tempMap, "ORD"));
                rg_orderEntity.setIdPree(getStringFromHashMap(tempMap, "IDPREC"));
                rg_orderEntity.setIdSucc(getStringFromHashMap(tempMap, "IDSUCC"));
                rg_orderEntity.setIdExclusive(getStringFromHashMap(tempMap, "IDEXCLUSIVE"));
                rg_orderEntity.setNbTask(getShortFromHashMap(tempMap, "NBTASK"));
                OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
                ordersDAO.save(rg_orderEntity);
                return true;
            }
        }
        return false;
    }

    private static String getStringFromHashMap(Map map, String mapKay) {
        if (map.get(mapKay) != null) {
            return map.get(mapKay).toString();
        } else {
            return null;
        }
    }

    private static Short getShortFromHashMap(Map map, String mapKay) {
        if (map.get(mapKay) != null) {
            return Short.parseShort(map.get(mapKay).toString());
        } else {
            return null;
        }
    }

    private static Byte getByteFromHashMap(Map map, String mapKay) {
        if (map.get(mapKay) != null) {
            return Byte.parseByte(map.get(mapKay).toString());
        } else {
            return null;
        }
    }

    private static Date getDateFromHashMap(Map map, String mapKay) throws ParseException {
        if (map.get(mapKay) != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            return simpleDateFormat.parse(map.get(mapKay).toString());
        } else {
            return null;
        }
    }
}
