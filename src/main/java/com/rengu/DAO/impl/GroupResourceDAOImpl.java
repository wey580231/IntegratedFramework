package com.rengu.DAO.impl;

import com.rengu.DAO.GroupResourceDao;
import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class GroupResourceDAOImpl implements GroupResourceDao {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_GroupresourceEntity getEntityObject(Object object) {
        RG_GroupresourceEntity rg_groupresourceEntity = null;
        if (object instanceof RG_GroupresourceEntity) {
            return (RG_GroupresourceEntity) object;
        } else {
            return rg_groupresourceEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_GroupresourceEntity rg_groupresourceEntity = getEntityObject(object);
            if (rg_groupresourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_groupresourceEntity);
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
            RG_GroupresourceEntity rg_groupresourceEntity = getEntityObject(object);
            if (rg_groupresourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(rg_groupresourceEntity);
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
            RG_GroupresourceEntity rg_groupresourceEntity = getEntityObject(object);
            if (rg_groupresourceEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(rg_groupresourceEntity);
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
        String hql = "from RG_GroupresourceEntity rg_groupresourceEntity";
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
            RG_GroupresourceEntity rg_groupresourceEntity = getEntityObject(object);
            if (rg_groupresourceEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            this.transaction = transaction;
            String hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_groupresourceEntity.getId());
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
        String hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.name = 'han'";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
