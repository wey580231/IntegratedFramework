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
    public List<RG_AdjustProcessEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_AdjustProcessEntity findAllById(String id) {
        try {
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
    public List<RG_AdjustProcessEntity> search(String keyWord) {
        return null;
    }
}
