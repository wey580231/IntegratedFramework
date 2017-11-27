package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustProcessDAO;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustProcessDAOImpl extends SuperDAOImpl implements AdjustProcessDAO<RG_AdjustProcessEntity> {
    @Override
    public List<RG_AdjustProcessEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_AdjustProcessEntity rg_adjustProcessEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustProcessEntity> findAllByErrorState(Integer errorState) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustProcessEntity rg_adjustProcessEntity where rg_adjustProcessEntity.state =:errorState";
            Query query = session.createQuery(hql);
            query.setParameter("errorState", errorState);
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_AdjustProcessEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustProcessEntity rg_adjustProcessEntity  where rg_adjustProcessEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_AdjustProcessEntity rg_adjustProcessEntity = (RG_AdjustProcessEntity) query.list().get(0);
                return rg_adjustProcessEntity;
            } else {
                return null;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void configState(RG_AdjustProcessEntity rg_adjustProcessEntity, Integer errorState) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        rg_adjustProcessEntity.setState(errorState);
        session.saveOrUpdate(rg_adjustProcessEntity);

        session.getTransaction().commit();
        session.close();

        System.out.println("更新异常ID：" + rg_adjustProcessEntity.getId() + "的状态为：" + rg_adjustProcessEntity.getState());
    }
}
