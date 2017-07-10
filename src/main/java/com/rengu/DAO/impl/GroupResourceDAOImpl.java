package com.rengu.DAO.impl;

import com.rengu.DAO.GroupResourceDAO;
import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class GroupResourceDAOImpl extends SuperDAOImpl implements GroupResourceDAO<RG_GroupresourceEntity> {
    @Override
    public List<RG_GroupresourceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_GroupresourceEntity rg_groupresourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_GroupresourceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_GroupresourceEntity rg_groupresourceEntity = (RG_GroupresourceEntity) query.list().get(0);
                return rg_groupresourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
