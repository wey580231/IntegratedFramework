package com.rengu.DAO.impl;

import com.rengu.DAO.SiteDAO;
import com.rengu.entity.RG_SiteEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class SiteDAOImpl implements SiteDAO {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_SiteEntity getEntityObject(Object object) {
        RG_SiteEntity rg_siteEntity = null;
        if (object instanceof RG_SiteEntity) {
            return (RG_SiteEntity) object;
        } else {
            return rg_siteEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_SiteEntity rg_siteEntity = getEntityObject(object);
            if (rg_siteEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_siteEntity);
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
            RG_SiteEntity rg_siteEntity = getEntityObject(object);
            if (rg_siteEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(rg_siteEntity);
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
            RG_SiteEntity rg_siteEntity = getEntityObject(object);
            if (rg_siteEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(rg_siteEntity);
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
        String hql = "from RG_SiteEntity rg_siteEntity";
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
            RG_SiteEntity rg_siteEntity = getEntityObject(object);
            if (rg_siteEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            this.transaction = transaction;
            String hql = "from RG_SiteEntity rg_siteEntity where rg_siteEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_siteEntity.getId());
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
        String hql = "from RG_SiteEntity rg_siteEntity where rg_siteEntity.name = 'han'";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
