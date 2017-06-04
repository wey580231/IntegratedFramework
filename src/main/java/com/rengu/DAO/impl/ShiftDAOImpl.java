package com.rengu.DAO.impl;

import com.rengu.DAO.ShiftDAO;
import com.rengu.entity.RG_ShiftEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ShiftDAOImpl implements ShiftDAO {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_ShiftEntity getEntityObject(Object object) {
        RG_ShiftEntity rg_shiftEntity = null;
        if (object instanceof RG_ShiftEntity) {
            return (RG_ShiftEntity) object;
        } else {
            return rg_shiftEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_ShiftEntity rg_shiftEntity = getEntityObject(object);
            if (rg_shiftEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_shiftEntity);
            this.transaction = transaction;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        try {
            RG_ShiftEntity rg_shiftEntity = getEntityObject(object);
            if (rg_shiftEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(rg_shiftEntity);
            this.transaction = transaction;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        try {
            RG_ShiftEntity rg_shiftEntity = getEntityObject(object);
            if (rg_shiftEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(rg_shiftEntity);
            this.transaction = transaction;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<?> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from RG_ShiftEntity rg_shiftEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<?> findAllByUsername(Object object) {
        return findAll();
    }

    @Override
    public List<?> findAllById(Object object) {
        try {
            RG_ShiftEntity rg_shiftEntity = getEntityObject(object);
            if (rg_shiftEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_shiftEntity.getId());
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<?> search(String keyWord) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.name = 'han'";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
