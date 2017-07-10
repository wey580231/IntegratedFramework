package com.rengu.DAO.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by hanchangming on 2017/5/22.
 */
public class OrdersDAOImpl extends SuperDAOImpl implements OrdersDAO<RG_OrderEntity> {
    @Override
    public List<RG_OrderEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_OrderEntity rg_orderEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_OrderEntity> findAllByUsername(String username) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.clubByIdClub.name =:nameClub";
            Query query = session.createQuery(hql);
            query.setParameter("nameClub", username);
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_OrderEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_OrderEntity rg_orderEntity = (RG_OrderEntity) query.list().get(0);
                return rg_orderEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_OrderEntity> findAllByisFinishedAndDate(Date startDate, Date endDate, boolean isFinished) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.t0 between ? and ? and rg_orderEntity.finished =:isFinisfed";
        Query query = session.createQuery(hql);
        query.setParameter(0, startDate);
        query.setParameter(1, endDate);
        query.setParameter("isFinisfed", isFinished);
        List list = query.list();
        return list;
    }

    @Override
    //支持同时删除多个订单
    public boolean deleteById(String jsonString) {
        JsonNode rootNode = null;
        try {
            rootNode = Tools.jsonTreeModelParse(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        for (JsonNode node : rootNode.get("id")) {
            String orderId = node.get("id").toString();

            RG_OrderEntity entity = session.get(RG_OrderEntity.class, orderId);
            if (entity != null) {
                Set<RG_ScheduleEntity> schedules = entity.getSchedules();

                //删除schedule
                Iterator<RG_ScheduleEntity> iters = schedules.iterator();
                while (iters.hasNext()) {
                    RG_ScheduleEntity schedule = iters.next();
                    schedule.getOrders().remove(entity);
                }

                //删除emulateData
                NativeQuery query = session.createNativeQuery(" delete from rg_emulatedata where idOrder= ? ");
                query.setParameter(1, orderId);
                query.executeUpdate();

                //删除紧急插单
                query = session.createNativeQuery("delete from rg_adjustorder where idOrder = ?");
                query.setParameter(1, orderId);
                query.executeUpdate();

                //将plan表中order字段置为null
                query = session.createNativeQuery("update rg_plan set idOrder = null where idOrder = ?");
                query.setParameter(1, "1");
                query.executeUpdate();

                session.delete(entity);
            }
        }
        session.getTransaction().commit();
        return false;
    }
}
