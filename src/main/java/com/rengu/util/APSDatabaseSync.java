package com.rengu.util;

import com.rengu.DAO.impl.*;
import com.rengu.entity.*;

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
    public static void SyncAPSTable(String[] tableNameLists, String databaseType, String databaseName) throws SQLException, ClassNotFoundException, ParseException {
        for (String tableName : tableNameLists) {
            //读取目标数据库
            String SQLString = "select * from " + tableName + "";
            List list = Tools.executeSQLForList(databaseType, databaseName, SQLString);
            if (tableName.equals(DatabaseInfo.APS_PRODUCT)) {
                SyncProductTable(list);
            }
            if (tableName.equals(DatabaseInfo.APS_ORDER)) {
                SyncOrderTable(list);
            }
            if (tableName.equals(DatabaseInfo.APS_ASSISANTPROCESS)) {
                SyncAssisantProcessTable(list);
            }
            if (tableName.equals(DatabaseInfo.APS_PROCESS)) {
                SyncAssisantProcessTable(list);
            }
            if (tableName.equals(DatabaseInfo.APS_RESOURCE)) {
                SyncAssisantProcessTable(list);
            }
        }
    }

    //同步产品表
    private static boolean SyncProductTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = (HashMap) object;
                RG_ProductEntity rg_productEntity = new RG_ProductEntity();
                rg_productEntity.setId(getStringFromHashMap(tempMap, "ID"));
                rg_productEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_productEntity.setType(getStringFromHashMap(tempMap, "TYPE"));
                rg_productEntity.setRef(getStringFromHashMap(tempMap, "REF"));
                rg_productEntity.setDepth(getStringFromHashMap(tempMap, "DEPTH"));
                rg_productEntity.setStock(getShortFromHashMap(tempMap, "STOCK"));
                ProductDAOImpl productDAO = DAOFactory.getProductDAOImplInstance();
                productDAO.save(rg_productEntity);
            } else {
                System.out.println("产品表同步失败");
                return false;
            }
        }
        System.out.println("产品表同步成功");
        return true;
    }

    //同步资源表
    private static boolean SyncResourceTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = (HashMap) object;
                RG_ResourceEntity rg_resourceEntity = new RG_ResourceEntity();
                rg_resourceEntity.setIdR(getStringFromHashMap(tempMap, "ID"));
                rg_resourceEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_resourceEntity.setIdFeatureResource(getStringFromHashMap(tempMap, "IDFEATURERESOURCE"));
                rg_resourceEntity.setCritical(getStringFromHashMap(tempMap, "CRITICAL"));
                rg_resourceEntity.setDateForbidden(getStringFromHashMap(tempMap, "DATEFORBIDDEN"));
                rg_resourceEntity.setRate(getDoubleFromHashMap(tempMap, "RATE"));
                rg_resourceEntity.setColor(getStringFromHashMap(tempMap, "COLOR"));
                rg_resourceEntity.setState(getByteFromHashMap(tempMap, "STATE"));
                rg_resourceEntity.setWeekend(getStringFromHashMap(tempMap, "WEEKEND"));
                rg_resourceEntity.setCalendar(getStringFromHashMap(tempMap, "CALENDAR"));
                rg_resourceEntity.setMobility(getShortFromHashMap(tempMap, "MOBILITY"));
                rg_resourceEntity.setNameShift(getStringFromHashMap(tempMap, "NAMESHIFT"));
                rg_resourceEntity.setSlot(getStringFromHashMap(tempMap, "SLOT"));
                rg_resourceEntity.setIdIcon(getStringFromHashMap(tempMap, "IDICON"));
                rg_resourceEntity.setTypeSite(getStringFromHashMap(tempMap, "TYPESITE"));
                rg_resourceEntity.setIdSite0(getStringFromHashMap(tempMap, "IDSITE0"));
                rg_resourceEntity.setIdSiteGroupResource(getStringFromHashMap(tempMap, "IDSITEGROUPRESOURCE"));
                rg_resourceEntity.setIdSiteSequence(getStringFromHashMap(tempMap, "IDSITESEQUENCE"));
                rg_resourceEntity.setQuantity0(getShortFromHashMap(tempMap, "QUANTITY0"));
                rg_resourceEntity.setSizeIcon(getByteFromHashMap(tempMap, "SIZEICON"));
                rg_resourceEntity.setUnit(getStringFromHashMap(tempMap, "UNIT"));
                rg_resourceEntity.setMakespan(getStringFromHashMap(tempMap, "MAKESPAN"));
                rg_resourceEntity.setSameTypeSequence(getStringFromHashMap(tempMap, "SAMETYPESEQUENCE"));
                ResourceDAOImpl resourceDAO = DAOFactory.getResourceInstance();
                resourceDAO.save(rg_resourceEntity);
            } else {
                System.out.println("产品表同步失败");
                return false;
            }
        }
        System.out.println("产品表同步成功");
        return true;
    }

    //同步工艺表
    private static boolean SyncProcessTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = (HashMap) object;
                RG_ProcessEntity rg_processEntity = new RG_ProcessEntity();
                rg_processEntity.setId(getStringFromHashMap(tempMap, "ID"));
                rg_processEntity.setName(getStringFromHashMap(tempMap, "NAME"));
                rg_processEntity.setIdPrec(getStringFromHashMap(tempMap, "IDPREC"));
                rg_processEntity.setIdSucc(getStringFromHashMap(tempMap, "IDSUCC"));
                rg_processEntity.setMinTimeSucc(getShortFromHashMap(tempMap, "MINTIMESUCC"));
                rg_processEntity.setMaxTimeSucc(getShortFromHashMap(tempMap, "MAXTIMESUCC"));
                rg_processEntity.setOrdToParent(getShortFromHashMap(tempMap, "ORDTOPPARENT"));
                rg_processEntity.setSlot1(getStringFromHashMap(tempMap, "SLOT1"));
                rg_processEntity.setSlot2(getStringFromHashMap(tempMap, "SLOT2"));
                rg_processEntity.setInitTime(getShortFromHashMap(tempMap, "INITTIME"));
                rg_processEntity.setUnitTime(getShortFromHashMap(tempMap, "UNITTIME"));
                rg_processEntity.setPostTime(getShortFromHashMap(tempMap, "POSTTIME"));
                rg_processEntity.setCheckTime(getShortFromHashMap(tempMap, "CHECKTIME"));
                rg_processEntity.setDelta(getShortFromHashMap(tempMap, "DELTA"));
                rg_processEntity.setEstimate(getShortFromHashMap(tempMap, "ESTIMATE"));
                rg_processEntity.setContinuous(getStringFromHashMap(tempMap, "CONTINUOUS"));
                rg_processEntity.setQuantity(getShortFromHashMap(tempMap, "QUANTITY"));
                rg_processEntity.setMinQtySwitch(getShortFromHashMap(tempMap, "MINQTSWITCH"));
                rg_processEntity.setMaxQtySwitch(getShortFromHashMap(tempMap, "MAXQTSWITCH"));
                rg_processEntity.setMaxResourceDivision(getShortFromHashMap(tempMap, "MAXRESOURCEDIVISION"));
                rg_processEntity.setMinTimeDivision(getShortFromHashMap(tempMap, "MINTIMEDIVISION"));
                rg_processEntity.setMinQtyDivision(getShortFromHashMap(tempMap, "MINQTYDIVISION"));
                rg_processEntity.setMinQtyBatch(getShortFromHashMap(tempMap, "MINQTYBATCH"));
                rg_processEntity.setMinTimeBatch(getShortFromHashMap(tempMap, "MINTIMEBATCH"));
                rg_processEntity.setIdCoupledGroupResource(getStringFromHashMap(tempMap, "IDCOUPLEDGROUPRESOURCE"));
                rg_processEntity.setIdCoupledTypeShift(getStringFromHashMap(tempMap, "IDCOUPLEDTYPESHIFT"));
                rg_processEntity.setOrdToRoot(getShortFromHashMap(tempMap, "ORDTOROOT"));
                rg_processEntity.setOrdToRootChild(getStringFromHashMap(tempMap, "ORDTOROOTCHILD"));
                rg_processEntity.setIdRoot(getStringFromHashMap(tempMap, "IDROOT"));
                rg_processEntity.setIdExclusive(getStringFromHashMap(tempMap, "IDEXCLUSIVE"));
                rg_processEntity.setIdCoupledResouce(getStringFromHashMap(tempMap, "IDCOUPLEDRESOURCE"));
                rg_processEntity.setTypeShift(getByteFromHashMap(tempMap, "TYPESHIFT"));
                rg_processEntity.setIdSwitch(getStringFromHashMap(tempMap, "IDSWITCH"));
                rg_processEntity.setMaxQtyDivision(getShortFromHashMap(tempMap, "MAXQTYDIVISION"));
                rg_processEntity.setMaxTimeDivision(getShortFromHashMap(tempMap, "MAXTIMEDIVISION"));
                rg_processEntity.setMaxQtyBatch(getShortFromHashMap(tempMap, "MAXQTYBATCH"));
                rg_processEntity.setMaxTimeBatch(getShortFromHashMap(tempMap, "MAXTIMEBATCH"));
                rg_processEntity.setPreemptive(getStringFromHashMap(tempMap, "PREEMPTIVE"));
                rg_processEntity.setExclusiveJob(getStringFromHashMap(tempMap, "EXCLUSIVEJOB"));
                rg_processEntity.setExclusiveOrder(getStringFromHashMap(tempMap, "EXCLUSIVEORDER"));
                rg_processEntity.setCoupledTypeOrder(getStringFromHashMap(tempMap, "COUPLEDTYPEORDER"));
                rg_processEntity.setIdCoupled(getStringFromHashMap(tempMap, "IDCOUPLED"));
                rg_processEntity.setIdCoupledShift(getStringFromHashMap(tempMap, "IDCOUPLEDSHIFT"));
                rg_processEntity.setIdCoupledTypeResource(getStringFromHashMap(tempMap, "IDCOUPLEDTYPERESOURCE"));
                rg_processEntity.setIdCoupledTypeSite(getStringFromHashMap(tempMap, "IDCOUPLEDTYPESITE"));
                rg_processEntity.setIdCoupledSite(getStringFromHashMap(tempMap, "IDCOUPLEDSITE"));
                rg_processEntity.setUnitQuantity(getShortFromHashMap(tempMap, "UNITQUANTITY"));
                rg_processEntity.setIdIcon(getStringFromHashMap(tempMap, "IDICON"));
                rg_processEntity.setModQtySwitch(getShortFromHashMap(tempMap, "MODQTYSWITCH"));
                rg_processEntity.setBatch(getStringFromHashMap(tempMap, "BATCH"));
                rg_processEntity.setModQtyBatch(getShortFromHashMap(tempMap, "MODQTYBATCH"));
                rg_processEntity.setModResourceDivision(getShortFromHashMap(tempMap, "MODRESOURCEDIVISION"));
                rg_processEntity.setModTimeBatch(getShortFromHashMap(tempMap, "MODTIMEBATCH"));
                rg_processEntity.setModQtyDivision(getShortFromHashMap(tempMap, "MODQTYDIVISION"));
                rg_processEntity.setModTimeDivision(getShortFromHashMap(tempMap, "MODTIMEDIVISION"));
                rg_processEntity.setMinResourceDivision(getShortFromHashMap(tempMap, "MINRESOURCEDIVISION"));
                rg_processEntity.setNbTask(getShortFromHashMap(tempMap, "NBTASK"));
                ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
                processDAO.save(rg_processEntity);
            } else {
                System.out.println("工艺表同步失败");
                return false;
            }
        }
        System.out.println("工艺表同步成功");
        return true;
    }

    //同步工艺辅表
    private static boolean SyncAssisantProcessTable(List list) {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = (HashMap) object;
                RG_AssisantprocessEntity rg_assisantprocessEntity = new RG_AssisantprocessEntity();
                rg_assisantprocessEntity.setId(Tools.getUUID());
                rg_assisantprocessEntity.setMinResource(getShortFromHashMap(tempMap, "MINRESOURCE"));
                rg_assisantprocessEntity.setMaxResource(getShortFromHashMap(tempMap, "MAXRESOURCE"));
                rg_assisantprocessEntity.setGrp(getShortFromHashMap(tempMap, "GRP"));
                rg_assisantprocessEntity.setWeightParallel(getShortFromHashMap(tempMap, "WEIGHTPARALLEL"));
                rg_assisantprocessEntity.setWeightSequence(getShortFromHashMap(tempMap, "WEIGHTPSEQUENCE"));
                rg_assisantprocessEntity.setTypeSite(getStringFromHashMap(tempMap, "TYPESITE"));
                rg_assisantprocessEntity.setIdSite(getStringFromHashMap(tempMap, "IDSITE"));
                rg_assisantprocessEntity.setSiteInGroupResource(getStringFromHashMap(tempMap, "SITEINGROUPERSOURCE"));
                AssisantprocessDAOImpl assisantprocessDAO = DAOFactory.getAssisantprocessDAOInstance();
                assisantprocessDAO.save(rg_assisantprocessEntity);
            } else {
                System.out.println("工艺辅表同步失败");
                return false;
            }
        }
        System.out.println("工艺辅表同步成功");
        return true;
    }

    //同步订单表
    private static boolean SyncOrderTable(List list) throws ParseException {
        for (Object object : list) {
            if (object instanceof HashMap) {
                Map tempMap = (HashMap) object;
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
            } else {
                System.out.println("订单表同步失败");
                return false;
            }
        }
        System.out.println("订单表同步成功");
        return true;
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

    private static Double getDoubleFromHashMap(Map map, String mapKay) {
        if (map.get(mapKay) != null) {
            return Double.parseDouble(map.get(mapKay).toString());
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.parse(map.get(mapKay).toString().trim());
        } else {
            return null;
        }
    }
}
