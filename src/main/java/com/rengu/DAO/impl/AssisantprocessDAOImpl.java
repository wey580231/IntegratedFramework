package com.rengu.DAO.impl;

import com.rengu.DAO.AssisantprocessDAO;
import com.rengu.entity.RG_AssisantprocessEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class AssisantprocessDAOImpl implements AssisantprocessDAO {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_AssisantprocessEntity getEntityObject(Object object) {
        RG_AssisantprocessEntity rg_assisantprocessEntity = null;
        if (object instanceof RG_AssisantprocessEntity) {
            return (RG_AssisantprocessEntity) object;
        } else {
            return rg_assisantprocessEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_AssisantprocessEntity rg_assisantprocessEntity = getEntityObject(object);
            if (rg_assisantprocessEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_assisantprocessEntity);
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
            RG_AssisantprocessEntity rg_assisantprocessEntity = getEntityObject(object);
            if (rg_assisantprocessEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(rg_assisantprocessEntity);
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
            RG_AssisantprocessEntity rg_assisantprocessEntity = getEntityObject(object);
            if (rg_assisantprocessEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(rg_assisantprocessEntity);
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
        String hql = "from RG_AssisantprocessEntity rg_assisantprocessEntity";
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
            RG_AssisantprocessEntity rg_assisantprocessEntity = getEntityObject(object);
            if (rg_assisantprocessEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_AssisantprocessEntity rg_assisantprocessEntity where rg_assisantprocessEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_assisantprocessEntity.getId());
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
        String hql = "from RG_AssisantprocessEntity rg_assisantprocessEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
