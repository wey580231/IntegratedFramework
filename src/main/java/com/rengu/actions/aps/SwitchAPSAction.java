package com.rengu.actions.aps;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.impl.PlanDAOImpl;
import com.rengu.actions.SuperAction;
import com.rengu.entity.ApsProcess1Entity;
import com.rengu.entity.ApsProcess2Entity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

/**
 * Created by XY on 2017/11/7.
 */
public class SwitchAPSAction  extends SuperAction {

    public void switchAPSModel() throws Exception {
        JsonNode jsonNode = Tools.jsonTreeModelParse(Tools.getHttpRequestBody(this.httpServletRequest));
        String id = jsonNode.get("id").toString();

        //连接数据库
        //加载驱动
        /*Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC";
        String username = "root";
        String password = "root";

        Class.forName("oracle.jdbc.OracleDriver");
        String url1 = "jdbc:oracle:thin:@localhost:1521:APS";
        String username1 = "APS";
        String password1 = "APS";*/

        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        //transaction.begin();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }

        /*
        * 0.MySQL
        * 1.简化模型
        * 2.细化模型
        * */

        //得到连接对象
        /*Connection conn0 = DriverManager.getConnection(url,username,password);    //mysql
        Connection conn = DriverManager.getConnection(url1,username1,password1);  //oracle

        //每次先清空利用率表
        String sql11 = "delete from APS_PROCESS";

        //得到PreparedStatement对象
        PreparedStatement pstmt1 = conn.prepareStatement(sql11);
        pstmt1.executeUpdate();*/


        if(id.equals("1")){

            //获取MySQL中的process简化模型数据
            //String sql1 = "select * from aps_process1";

            //process1中的数据总数
            //int countProcess1 = (int)session.createQuery("select count(*) from ApsProcess1Entity").uniqueResult();
            //process1中的数据

            //清空APS_PROCESS数据库
            String[] tableNames = {DatabaseInfo.APS_PROCESS};
            Tools.executeSQLForInitTable(DatabaseInfo.ORACLE, DatabaseInfo.APS, tableNames);

            List<ApsProcess1Entity> process1EntityList = (List<ApsProcess1Entity>)session.createQuery("select APSProcess1 from ApsProcess1Entity APSProcess1").list();

            //遍历process集合
            /*for(int i=0; i<process1EntityList.size(); i++){

            }*/
            if(process1EntityList != null){
                for(ApsProcess1Entity apsProcess1 : process1EntityList){

                    //插入APS数据库
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(apsProcess1));

                }
            }



            /*PreparedStatement pstmt11 = conn.prepareStatement(sql1);

            //获取结果集
            ResultSet rs = pstmt11.executeQuery();

            //列数
            ResultSetMetaData rsmd=rs.getMetaData();
            int process1Col = rsmd.getColumnCount();*/

            /*while(rs.next()){

                //插入APS数据库
                //Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(rg_shiftEntity));

                for (int i = 1; i <= process1Col; i++) {//角标从1开始
                    rs.getObject(i);
                }

            }*/






            //插入oracle的process表中

        }else if(id.equals("2")){
            //清空APS_PROCESS数据库
            String[] tableNames = {DatabaseInfo.APS_PROCESS};
            Tools.executeSQLForInitTable(DatabaseInfo.ORACLE, DatabaseInfo.APS, tableNames);

            List<ApsProcess2Entity> process2EntityList = (List<ApsProcess2Entity>)session.createQuery("select APSProcess2 from ApsProcess2Entity APSProcess2").list();

            if(process2EntityList != null){
                for(ApsProcess2Entity apsProcess2 : process2EntityList){

                    //插入APS数据库
                    Tools.executeSQLForUpdate(DatabaseInfo.ORACLE, DatabaseInfo.APS, EntityConvertToSQL.insertSQLForAPS(apsProcess2));

                }
            }

        }




        /*String jsonString = Tools.entityConvertToJsonString(rg_planEntityList);
        Tools.jsonPrint(jsonString, this.httpServletResponse);*/
    }

}
