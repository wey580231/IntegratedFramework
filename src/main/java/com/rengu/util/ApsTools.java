package com.rengu.util;

import com.rengu.DAO.impl.*;
import com.rengu.entity.RG_PlanEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String apsHost;
    private int apsPort;

    //集成框架部署的地址和端口号
    private String localAddress;
    private String localPort;

    private final String replyApsAction = "/aps/updateProgress";

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

    //获取排程结果
    public void getScheduleResult() throws SQLException, ClassNotFoundException {
        String SQLString = "select * from aps_plan";
        List<?> list = Tools.executeSQLForResultSet(DatabaseInfo.MySQL, DatabaseInfo.APS, SQLString);
        for (Object object : list) {
            if (object instanceof HashMap) {
                RG_PlanEntity rg_planEntity = new RG_PlanEntity();
                Map tempMap = (HashMap) object;
                rg_planEntity.setIdTask(tempMap.get("idTask").toString());
                rg_planEntity.setIdJob(tempMap.get("idJob").toString());
                rg_planEntity.setNameTask(tempMap.get("nameTask").toString());
                rg_planEntity.setNameOrder(tempMap.get("nameOrder").toString());
                rg_planEntity.setNameJob(tempMap.get("nameJob").toString());
                rg_planEntity.setNameResource(tempMap.get("nameResource").toString());
                rg_planEntity.setNameGroupResource(tempMap.get("nameGroupResource").toString());
                rg_planEntity.setNameTypeResource(tempMap.get("nameTypeResource").toString());
                rg_planEntity.setNameSite(tempMap.get("nameSite").toString());
                rg_planEntity.setNameProvider(tempMap.get("nameProvider").toString());
                rg_planEntity.setOrdToParentTask(Short.parseShort(tempMap.get("ordToParentTask").toString()));
                rg_planEntity.setIdTaskResourceSucc(tempMap.get("idTaskResourceSucc").toString());
                rg_planEntity.setPreemptiveTask(tempMap.get("preemptiveTask").toString());
                rg_planEntity.setDivisibleTask(tempMap.get("divisibleTask").toString());
                rg_planEntity.setContinuousTask(tempMap.get("continuousTask").toString());
                rg_planEntity.setQuantityTask(Short.parseShort(tempMap.get("quantityTask").toString()));
                rg_planEntity.setQuantityResourceTask(Short.parseShort(tempMap.get("quantityResourceTask").toString()));
                rg_planEntity.setQuantityBatchTask(Short.parseShort(tempMap.get("quantityBatchTask").toString()));
                rg_planEntity.setQtySequence(Short.parseShort(tempMap.get("qtySequence").toString()));
                rg_planEntity.setT1Task(tempMap.get("t1Task").toString());
                rg_planEntity.setT2Task(tempMap.get("t2Task").toString());
                rg_planEntity.setT2ExtendedTask(tempMap.get("t2ExtendedTask").toString());
                rg_planEntity.setAdvice(tempMap.get("advice").toString());
                rg_planEntity.setEstimateTask(tempMap.get("estimateTask").toString());
                rg_planEntity.setTimeTask(tempMap.get("timeTask").toString());
                rg_planEntity.setInitTimeTask(tempMap.get("initTimeTask").toString());
                rg_planEntity.setUnitTimeTask(tempMap.get("unitTimeTask").toString());
                rg_planEntity.setPostTimeTask(tempMap.get("postTimeTask").toString());
                rg_planEntity.setCheckTimeTask(tempMap.get("checkTimeTask").toString());
                rg_planEntity.setIdGroupResource0Task(tempMap.get("idGroupResource0Task").toString());
                rg_planEntity.setIdResource0Task(tempMap.get("idResource0Task").toString());
                rg_planEntity.setIdSite0Task(tempMap.get("idSite0Task").toString());
                rg_planEntity.setQuantity0Task(Short.parseShort(tempMap.get("quantity0Task").toString()));
                rg_planEntity.setT10Task(tempMap.get("t10Task").toString());
                rg_planEntity.setT20Task(tempMap.get("t20Task").toString());
                rg_planEntity.setT20ExtendedTask(tempMap.get("t20ExtendedTask").toString());
                rg_planEntity.setT1Job(tempMap.get("t1Job").toString());
                rg_planEntity.setT2Job(tempMap.get("t2Job").toString());
                rg_planEntity.setQuantityJob(Short.parseShort(tempMap.get("quantityJob").toString()));
                rg_planEntity.setNbTaskJob(Short.parseShort(tempMap.get("nbTaskJob").toString()));
                rg_planEntity.setRefProductJob(tempMap.get("refProductJob").toString());
                rg_planEntity.setOrdToRootJob(Short.parseShort(tempMap.get("ordToRootJob").toString()));
                rg_planEntity.setOrdToRootChildJob(tempMap.get("OrdToRootChildJob").toString());
                rg_planEntity.setT1Order(tempMap.get("t1Order").toString());
                rg_planEntity.setT2Order(tempMap.get("t2Order").toString());
                rg_planEntity.setQuantityOrder(Short.parseShort(tempMap.get("quantityOrder").toString()));
                rg_planEntity.setPriorityOrder(Short.parseShort(tempMap.get("priorityOrder").toString()));
                rg_planEntity.setColorOrder(tempMap.get("colorOrder").toString());
                rg_planEntity.setState(Byte.parseByte(tempMap.get("state").toString()));

                //获取Club实体
                ClubDAOImpl clubDAO = DAOFactory.getClubDAOImplInstance();
                rg_planEntity.setClubByIdClub(clubDAO.findAllById(tempMap.get("idClub").toString()));
                clubDAO.getTransaction().commit();

                //获取Process实体
                ProcessDAOImpl processDAO = DAOFactory.getProcessDAOImplInstance();
                rg_planEntity.setProcessByIdProcess(processDAO.findAllById(tempMap.get("idProcess").toString()));
                processDAO.getTransaction().commit();

                //获取Order实体
                OrdersDAOImpl ordersDAO = DAOFactory.getOrdersDAOInstance();
                rg_planEntity.setOrderByIdOrder(ordersDAO.findAllById(tempMap.get("idOrder").toString()));
                ordersDAO.getTransaction().commit();

                //获取Resource实体
                ResourceDAOImpl resourceDAO = DAOFactory.getResourceInstance();
                rg_planEntity.setResourceByIdResource(resourceDAO.findAllById(tempMap.get("idResource").toString()));
                resourceDAO.getTransaction().commit();

                //获取Site实体
                SiteDAOImpl siteDAO = DAOFactory.getSiteInstance();
                rg_planEntity.setSiteByIdSite(siteDAO.findAllById(tempMap.get("idSite").toString()));
                siteDAO.getTransaction().commit();

                //获取GroupResource实体
                GroupResourceDAOImpl groupResourceDAO = DAOFactory.getGroupResourceInstance();
                rg_planEntity.setGroupresourceByIdGroupResource(groupResourceDAO.findAllById(tempMap.get("idGroupResource").toString()));
                groupResourceDAO.getTransaction().commit();

                //获取TypeResource实体
                TyperescourceDAOImpl typerescourceDAO = DAOFactory.getTyperescourceInstance();
                rg_planEntity.setGroupresourceByIdGroupResource(groupResourceDAO.findAllById(tempMap.get("idTypeResource").toString()));
                typerescourceDAO.getTransaction().commit();

                //获取Provider实体
                ProviderDAOImpl providerDAO = DAOFactory.getProviderDAOImplInstance();
                rg_planEntity.setProviderByIdProvider(providerDAO.findAllById(tempMap.get("idProvider").toString()));
                providerDAO.getTransaction().commit();

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

    public int startAPSSchedule() {
        String cmd = "/NCL:RUN?Program=./Model/Script/ScriptAutoScheduling.n" +
                "&" +
                "REPLY=" + getReplyAddress() +
                "&" +
                "ID=" + GlobalVariable.MiddleSnapshotId + "" +
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
