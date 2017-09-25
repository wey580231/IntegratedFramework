package com.rengu.DAO.impl;

import com.rengu.DAO.ShiftDAO;
import com.rengu.entity.RG_ResourceEntity;
import com.rengu.entity.RG_ShiftEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ShiftDAOImpl extends SuperDAOImpl implements ShiftDAO<RG_ShiftEntity> {
    @Override
    public List<RG_ShiftEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ShiftEntity rg_shiftEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_ShiftEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ShiftEntity rg_shiftEntity = (RG_ShiftEntity) query.list().get(0);
                return rg_shiftEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_ShiftEntity findAllById(Session session, String id) {
        try {
            String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ShiftEntity rg_shiftEntity = (RG_ShiftEntity) query.list().get(0);
                return rg_shiftEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean delete(Session session, RG_ShiftEntity rg_shiftEntity) {
        Set<RG_ResourceEntity> rg_resourceEntities = rg_shiftEntity.getResources();
        if (rg_resourceEntities != null) {
            for (RG_ResourceEntity rg_resourceEntity : rg_resourceEntities) {
                rg_resourceEntity.setNameShift(null);
                rg_resourceEntity.getShiftsById().remove(rg_shiftEntity);
                session.saveOrUpdate(rg_resourceEntity);
            }
            session.delete(rg_shiftEntity);
            return true;
        } else {
            return false;
        }
    }
}
