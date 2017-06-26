package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustDeviceDAO;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustDeviceDAOImpl extends SuperDAOImpl implements AdjustDeviceDAO<RG_AdjustDeviceEntity> {
    @Override
    public List<RG_AdjustDeviceEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_AdjustDeviceEntity entity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustDeviceEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_AdjustDeviceEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustDeviceEntity entity where entity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_AdjustDeviceEntity rg_adjustDeviceEntity = (RG_AdjustDeviceEntity) query.list().get(0);
                return rg_adjustDeviceEntity;
            } else {
                return null;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_AdjustDeviceEntity> search(String keyWord) {
        return null;
    }
}
