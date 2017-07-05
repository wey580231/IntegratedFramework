package com.rengu.util;

import com.rengu.DAO.impl.ProductDAOImpl;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ProductEntity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanch on 2017/7/4.
 */
public class APSDatabaseSync {
    public static boolean SyncAPSTable(String tableName) throws SQLException, ClassNotFoundException {
        String SQLString = "select * from " + tableName + "";
        List list = Tools.executeSQLForList(DatabaseInfo.ORACLE, DatabaseInfo.APS, SQLString);
        if (tableName.equals(DatabaseInfo.APS_PRODUCT)) {
            return SyncProductTable(list);
        }
        if (tableName.equals(DatabaseInfo.APS_ORDER)) {
            return SyncOrderTable(list);
        }
        return false;
    }

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

    private static boolean SyncOrderTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = new HashMap();
                RG_OrderEntity rg_orderEntity = new RG_OrderEntity();
                rg_orderEntity.setId(getStringFromHashMap(tempMap, "ID"));
                rg_orderEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_orderEntity.setType(getStringFromHashMap(tempMap, "TYPE"));
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
}
