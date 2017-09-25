package com.rengu.DAO.impl;

import com.rengu.DAO.ResourceDAO;
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
public class ResourceDAOImpl extends SuperDAOImpl implements ResourceDAO<RG_ResourceEntity> {
    @Override
    public List<RG_ResourceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceEntity rg_resourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_ResourceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ResourceEntity rg_resourceEntity = (RG_ResourceEntity) query.list().get(0);
                return rg_resourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_ResourceEntity findAllById(Session session, String id) {
        try {
            String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ResourceEntity rg_resourceEntity = (RG_ResourceEntity) query.list().get(0);
                return rg_resourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean update(Session session, RG_ResourceEntity rg_resourceEntity) {
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }



            session.merge(rg_resourceEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        /*Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            transaction = session.beginTransaction();
        }

        Set<RG_ShiftEntity> rg_shiftEntitySet = rg_resourceEntity.getShiftsById();
        if (rg_shiftEntitySet != null) {
            for (RG_ShiftEntity rg_shiftEntity : rg_shiftEntitySet) {
                rg_shiftEntity.getResources().remove(rg_resourceEntity);
                session.update(rg_resourceEntity);
            }

            transaction.commit();
            return true;
        } else {
            return false;
        }*/

    }

    public boolean save(Session session,Object object) {
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.saveOrUpdate(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
