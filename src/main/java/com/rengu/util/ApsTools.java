package com.rengu.util;

import com.rengu.DAO.impl.*;
import com.rengu.entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过SOCKET访问aps pomserver，获取APS的状态信息
 * Created by wey580231 on 2017/6/13.
 */
public class ApsTools {

    public static final int UNKNOWN = -1;
    public static final int IDLE = 0;
    public static final int BUSY_EXECUTE = 0;        //请求执行项目
    public static final int BUSY = 1;                //用于返回客户端查询服务器的状态
    public static final int STOPPED = 1;
    public static final int STARTED = 1;
    public static final int ALREADY = 2;
    public static final int FINISHED = 3;

    //aps部署的地址和端口号
    private static ApsTools apsTool = null;
    private final String replyApsAction = "/aps/updateProgress";
    private String apsHost;
    private int apsPort;

    //集成框架部署的地址和端口号
    private String localAddress;
    private String localPort;
    private Socket socket;
    private BufferedReader breader = null;
    private OutputStream os = null;

    private ApsTools() {
        try {
            apsHost = Tools.getDatabaseProperties().getProperty("APSHost");
            apsPort = Integer.parseInt(Tools.getDatabaseProperties().getProperty("APSPort"));

            localAddress = Tools.getDatabaseProperties().getProperty("LocalAddress");
            localPort = Tools.getDatabaseProperties().getProperty("LocalPort");

            socket = new Socket(apsHost, apsPort);

            os = socket.getOutputStream();
            breader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApsTools instance() {
        if (apsTool == null) {
            synchronized (ApsTools.class) {
                if (apsTool == null) {
                    apsTool = new ApsTools();
                }
            }
        }
        return apsTool;
    }

    //TODO 待将连接修改成APS提供的访问连接
    //获取紧急插单
//    public static String getAdjustOrderHandlingURL(RG_AdjustOrderEntity entity) {
//
//        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Order/AcceptOrder.n" +
//                "&" +
//                "BUFFER=1\\n2\\n"+entity.getId()+"\\n001\\n2000-01-01\\t06:00\\n120\\n"+entity.getId()+"\\n"+entity.getName()
//                +"\\n"+entity.getQuantity()+"\\nKqd\\nG01\\n1\\n2014-05-23\\t08:50\\n2014-05-23\\t08:50\\n\n" +
//                "2014-05-25\\t11:24\n";
//
//
//        String result1 = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Order/AcceptOrder.n" +
//                "&" +
//                "BUFFER=1\\n2\\n" + entity.getId() + "\\n001\\n2000-01-01\\t06:00\\n120\\n" + entity.getUnavailableStartTime()
//                + "\\n" + entity.getUnavailableEndTime() + "\\n1\\n2\\n" + convertSpaceWithTab(entity.getUnavailableStartDate()) + "\\n" + convertSpaceWithTab(entity.getUnavailableEndDate()) +
//                "&" +
//                "REPLY=" + ApsTools.instance().getReplyAddress() +
//                "&" +
//                "ID=001" +
//                "&" +
//                "DELAY=1000\n";
//        return result;
//    }

    //获取工序调整
    public static String getAdjustProcessHandlingURL(RG_AdjustProcessEntity entity) {

        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Task/MoveTask.n" +
                "&" +
                "BUFFER=1\\n2\\n" + entity.getIdTask() + "\\n001\\n2000-01-01\\t06:00\\n120\\n1\\n6\\n" +
                entity.getIdJob() + "\\n" + entity.getIdOrder() + "\\n" + entity.getOriginalResource() + "\\n" + ApsTools.instance().convertSpaceWithTab(Tools.formatDate(entity.getOriginalStartTime()))
                + "\\n" + entity.getAppointResource() + "\\n" + ApsTools.instance().convertSpaceWithTab(Tools.formatDate(entity.getAppointStartTime())) +
                "&" +
                "REPLY=" + ApsTools.instance().getReplyAddress() +
                "&" +
                "ID=001" +
                "&" +
                "DELAY=1000";

        return result;
    }

    //设备资源取消
    public String getCancelDeviceURL(RG_AdjustDeviceEntity entity) {
        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Resource/RejectResource.n" +
                "&" +
                "BUFFER=1\\n2\\n" + entity.getResoureId() + "\\n001\\n2000-01-01\\t06:00\\n120\\n"
                + convertSpaceWithTab(entity.getCancelTime()) + "\\n" + convertSpaceWithTab(entity.getLatestCancelTime()) +
                "&" +
                "REPLY=" + ApsTools.instance().getReplyAddress() +
                "&" +
                "ID=001" +
                "&" +
                "DELAY=1000";
        return result;
    }

    //设备资源不可用aps请求连接
    public String getUnavailableDeviceURL(RG_AdjustDeviceEntity entity) {
        String result = "/NCL:RUN?Program=./Model/Interaction/Rescheduling/Resource/ModifyResourceTimeGantt.n" +
                "&" +
                "BUFFER=1\\n2\\n" + entity.getResoureId() + "\\n001\\n2000-01-01\\t06:00\\n120\\n" + entity.getUnavailableStartTime()
                + "\\n" + entity.getUnavailableEndTime() + "\\n1\\n2\\n" + convertSpaceWithTab(entity.getUnavailableStartDate()) + "\\n" + convertSpaceWithTab(entity.getUnavailableEndDate()) +
                "&" +
                "REPLY=" + ApsTools.instance().getReplyAddress() +
                "&" +
                "ID=001" +
                "&" +
                "DELAY=1000\n";
        return result;
    }

    //将字符转中包含的\s替换成\t
    private String convertSpaceWithTab(String source) {
        source = source.trim();

        Pattern pattern = Pattern.compile("(\\s)+|\t|\r|\n");
        Matcher match = pattern.matcher(source);

        return match.replaceAll("\\\\t");
    }

    //获取排程结果
    public void getScheduleResult(RG_SnapshotNodeEntity bottomSnapshot) throws SQLException, ClassNotFoundException {
        String SQLString = "select * from aps_plan";
        List<?> list = Tools.executeSQLForResultSet(DatabaseInfo.MySQL, DatabaseInfo.APS, SQLString);

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

//        for (Object object : list) {
        for (int i = 0; i < 5; i++) {
//            if (object instanceof HashMap) {
            if (true) {
                RG_PlanEntity rg_planEntity = new RG_PlanEntity();

                rg_planEntity.setId(Tools.getUUID());

//                Map tempMap = (HashMap) object;
//                rg_planEntity.setIdTask(tempMap.get("idTask").toString());
//                rg_planEntity.setIdJob(tempMap.get("idJob").toString());
//                rg_planEntity.setNameTask(tempMap.get("nameTask").toString());
//                rg_planEntity.setNameOrder(tempMap.get("nameOrder").toString());
//                rg_planEntity.setNameJob(tempMap.get("nameJob").toString());
//                rg_planEntity.setNameResource(tempMap.get("nameResource").toString());
//                rg_planEntity.setNameGroupResource(tempMap.get("nameGroupResource").toString());
//                rg_planEntity.setNameTypeResource(tempMap.get("nameTypeResource").toString());
//                rg_planEntity.setNameSite(tempMap.get("nameSite").toString());
//                rg_planEntity.setNameProvider(tempMap.get("nameProvider").toString());
//                rg_planEntity.setOrdToParentTask(Short.parseShort(tempMap.get("ordToParentTask").toString()));
//                rg_planEntity.setIdTaskResourceSucc(tempMap.get("idTaskResourceSucc").toString());
//                rg_planEntity.setPreemptiveTask(tempMap.get("preemptiveTask").toString());
//                rg_planEntity.setDivisibleTask(tempMap.get("divisibleTask").toString());
//                rg_planEntity.setContinuousTask(tempMap.get("continuousTask").toString());
//                rg_planEntity.setQuantityTask(Short.parseShort(tempMap.get("quantityTask").toString()));
//                rg_planEntity.setQuantityResourceTask(Short.parseShort(tempMap.get("quantityResourceTask").toString()));
//                rg_planEntity.setQuantityBatchTask(Short.parseShort(tempMap.get("quantityBatchTask").toString()));
//                rg_planEntity.setQtySequence(Short.parseShort(tempMap.get("qtySequence").toString()));
//                rg_planEntity.setT1Task(tempMap.get("t1Task").toString());
//                rg_planEntity.setT2Task(tempMap.get("t2Task").toString());
//                rg_planEntity.setT2ExtendedTask(tempMap.get("t2ExtendedTask").toString());
//                rg_planEntity.setAdvice(tempMap.get("advice").toString());
//                rg_planEntity.setEstimateTask(tempMap.get("estimateTask").toString());
//                rg_planEntity.setTimeTask(tempMap.get("timeTask").toString());
//                rg_planEntity.setInitTimeTask(tempMap.get("initTimeTask").toString());
//                rg_planEntity.setUnitTimeTask(tempMap.get("unitTimeTask").toString());
//                rg_planEntity.setPostTimeTask(tempMap.get("postTimeTask").toString());
//                rg_planEntity.setCheckTimeTask(tempMap.get("checkTimeTask").toString());
//                rg_planEntity.setIdGroupResource0Task(tempMap.get("idGroupResource0Task").toString());
//                rg_planEntity.setIdResource0Task(tempMap.get("idResource0Task").toString());
//                rg_planEntity.setIdSite0Task(tempMap.get("idSite0Task").toString());
//                rg_planEntity.setQuantity0Task(Short.parseShort(tempMap.get("quantity0Task").toString()));
//                rg_planEntity.setT10Task(tempMap.get("t10Task").toString());
//                rg_planEntity.setT20Task(tempMap.get("t20Task").toString());
//                rg_planEntity.setT20ExtendedTask(tempMap.get("t20ExtendedTask").toString());
//                rg_planEntity.setT1Job(tempMap.get("t1Job").toString());
//                rg_planEntity.setT2Job(tempMap.get("t2Job").toString());
//                rg_planEntity.setQuantityJob(Short.parseShort(tempMap.get("quantityJob").toString()));
//                rg_planEntity.setNbTaskJob(Short.parseShort(tempMap.get("nbTaskJob").toString()));
//                rg_planEntity.setRefProductJob(tempMap.get("refProductJob").toString());
//                rg_planEntity.setOrdToRootJob(Short.parseShort(tempMap.get("ordToRootJob").toString()));
//                rg_planEntity.setOrdToRootChildJob(tempMap.get("OrdToRootChildJob").toString());
//                rg_planEntity.setT1Order(tempMap.get("t1Order").toString());
//                rg_planEntity.setT2Order(tempMap.get("t2Order").toString());
//                rg_planEntity.setQuantityOrder(Short.parseShort(tempMap.get("quantityOrder").toString()));
//                rg_planEntity.setPriorityOrder(Short.parseShort(tempMap.get("priorityOrder").toString()));
//                rg_planEntity.setColorOrder(tempMap.get("colorOrder").toString());
//                rg_planEntity.setState(Byte.parseByte(tempMap.get("state").toString()));
//
//                //获取Club实体
//                String hql = "from RG_ClubEntity rg_clubEntity where rg_clubEntity.id =:id";
//                Query query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idClub").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setClubByIdClub((RG_ClubEntity) query.list().get(0));
//                }
//
//                //获取Process实体
//                hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idProcess").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setProcessByIdProcess((RG_ProcessEntity) query.list().get(0));
//                }
//
//                //获取Order实体
//                hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idOrder").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setOrderByIdOrder((RG_OrderEntity) query.list().get(0));
//                }
//
//                //获取Resource实体
//                hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idResource").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setResourceByIdResource((RG_ResourceEntity) query.list().get(0));
//                }
//
//                //获取Site实体
//                hql = "from RG_SiteEntity rg_siteEntity where rg_siteEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idSite").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setSiteByIdSite((RG_SiteEntity) query.list().get(0));
//                }
//
//                //获取GroupResource实体
//                hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idGroupResource").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setGroupresourceByIdGroupResource((RG_GroupresourceEntity) query.list().get(0));
//                }
//
//                //获取TypeResource实体
//                hql = "from RG_TyperescourceEntity rg_typerescourceEntity where rg_typerescourceEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idTypeResource").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setTyperescourceByIdTypeResource((RG_TyperescourceEntity) query.list().get(0));
//                }
//
//                //获取TypeResource实体
//                hql = "from RG_ProviderEntity rg_providerEntity where rg_providerEntity.id =:id";
//                query = session.createQuery(hql);
//                query.setParameter("id", tempMap.get("idProvider").toString());
//                if (!query.list().isEmpty()) {
//                    rg_planEntity.setProviderByIdProvider((RG_ProviderEntity) query.list().get(0));
//                }

                //Snapshot
                if (bottomSnapshot != null) {
                    bottomSnapshot.getPlans().add(rg_planEntity);

                    rg_planEntity.setSnapShort(bottomSnapshot);
                }

                session.save(rg_planEntity);
            }
        }

    }

    //拼接请求命令
    private String combineCommand(String requestURL) {
        StringBuffer requestHeader = new StringBuffer();
        requestHeader
                .append("GET " + requestURL + " HTTP/1.1\n")
                .append("HOST: " + apsHost + ":" + apsPort + " " + "\n")
                .append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n")
                .append("Accept-Encoding:gzip, deflate, sdch\n")
                .append("Accept-Language:zh-CN,zh;q=0.8\n")
                .append("Cache-Control:no-cache\n")
                .append("User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0\n")
                .append("Encoding:UTF-8\n")
                .append("Connection:keep-alive" + "\n").append("\n");

        return requestHeader.toString();
    }

    //执行请求
    private String execute(String command) {
        StringBuffer result = new StringBuffer();
        try {
            os.write(combineCommand(command).getBytes());
            os.flush();
            String oneline = null;

            while ((oneline = breader.readLine()) != null) {
                result.append(oneline).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString().toLowerCase();
    }

    //请求执行项目
    public int executeCommand(String command) {
        String result = execute(command);
        if (result.contains("finished")) {
            return ApsTools.FINISHED;
        } else if (result.contains("already")) {
            return ApsTools.ALREADY;
        } else if (result.contains("started")) {
            return ApsTools.STARTED;
        } else if (result.contains("busy")) {
            return ApsTools.BUSY_EXECUTE;
        }

        return ApsTools.UNKNOWN;
    }

    //客户端查询PoemServer当前运行状态
    public int queryExecuteState() {
        String result = execute("/NCL:STATE");
        if (result.contains("busy")) {
            return ApsTools.BUSY;
        } else if (result.contains("idle")) {
            return ApsTools.IDLE;
        }
        return ApsTools.UNKNOWN;
    }

    //客户端请求终止PoemServer运行
    public int requestStopExecute() {
        String result = execute("/NCL:STOP");
        if (result.contains("stopped")) {
            return ApsTools.STOPPED;
        } else if (result.contains("idle")) {
            return ApsTools.IDLE;
        }
        return ApsTools.UNKNOWN;
    }

    public int startAPSSchedule(String middleId) {
        String cmd = "/NCL:RUN?Program=./Model/Script/ScriptAutoScheduling.n" +
                "&" +
                "REPLY=" + getReplyAddress() +
                "&" +
                "ID=" + middleId + "" +
                "&" +
                "DELAY=1000000" +
                "&" +
                "buffer=001";

        return executeCommand(cmd);
    }

    //获取aps计算完后返回结果地址
    public String getReplyAddress() {
        return localAddress + ":" + localPort + replyApsAction;
    }
}
