package com.rengu.DAO.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.jdbc.IterateBlock;
import com.rengu.DAO.EventLogDAO;
import com.rengu.entity.RG_EventLogEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hanch on 2017/7/20.
 */
public class EventLogDAOImpl extends SuperDAOImpl implements EventLogDAO<RG_EventLogEntity> {
    @Override
    public List<RG_EventLogEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_EventLogEntity rg_eventLogEntity order by rg_eventLogEntity.creatTime desc";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    //查询最近七天的日志信息
    public String getLatestEventEventDialog() {

        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        String jsonString = "";

        ObjectMapper mapper = new ObjectMapper();                //定义转换类
        ObjectNode root = mapper.createObjectNode();             //创建根节点
        root.put("result", "ok");

        ArrayNode arrayNode = mapper.createArrayNode();

        NativeQuery query = session.createNativeQuery("select * from rg_eventlog where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(creatTime) order by creatTime desc", RG_EventLogEntity.class);
        List<RG_EventLogEntity> list = query.list();
        if (list.size() > 0) {
            Iterator<RG_EventLogEntity> iter = list.iterator();
            while (iter.hasNext()) {
                RG_EventLogEntity entity = iter.next();

                ObjectNode node = mapper.createObjectNode();
                node.put("eventType", entity.getEventType());
                node.put("logItemtype", entity.getLogItemtype());
                node.put("creatTime", Tools.formatToStandardDate(entity.getCreatTime()));
                node.put("title", entity.getTitle());
                node.put("content", entity.getContent());

                arrayNode.add(node);
            }

            root.put("data", arrayNode);
        }

        try {
            jsonString = mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();

        return jsonString;
    }
}
