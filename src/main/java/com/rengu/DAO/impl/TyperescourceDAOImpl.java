package com.rengu.DAO.impl;

import com.rengu.DAO.TyperescourceDAO;
import com.rengu.entity.RG_TyperescourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class TyperescourceDAOImpl implements TyperescourceDAO {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_TyperescourceEntity getEntityObject(Object object) {
        RG_TyperescourceEntity rg_typerescourceEntity = null;
        if (object instanceof RG_TyperescourceEntity) {
            return (RG_TyperescourceEntity) object;
        } else {
            return rg_typerescourceEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_TyperescourceEntity rg_typerescourceEntity = getEntityObject(object);
            if (rg_typerescourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_typerescourceEntity);
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
            RG_TyperescourceEntity rg_typerescourceEntity = getEntityObject(object);
            if (rg_typerescourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(rg_typerescourceEntity);
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
            RG_TyperescourceEntity rg_typerescourceEntity = getEntityObject(object);
            if (rg_typerescourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(rg_typerescourceEntity);
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
        String hql = "from RG_TyperescourceEntity rg_typerescourceEntity";
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
            RG_TyperescourceEntity rg_typerescourceEntity = getEntityObject(object);
            if (rg_typerescourceEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_TyperescourceEntity rg_typerescourceEntity where rg_typerescourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_typerescourceEntity.getId());
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
        String hql = "from RG_TyperescourceEntity rg_typerescourceEntity where rg_typerescourceEntity.name = 'han'";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
