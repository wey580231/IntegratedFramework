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
public class SiteDAOImpl extends SuperDAOImpl implements SiteDAO<RG_SiteEntity> {
    @Override
    public List<RG_SiteEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        super.transaction = transaction;
        super.session = session;
        String hql = "from RG_SiteEntity rg_siteEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_SiteEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_SiteEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            super.transaction = transaction;
            super.session = session;
            String hql = "from RG_SiteEntity rg_siteEntity where rg_siteEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                return (RG_SiteEntity) query.list().get(0);
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_SiteEntity> search(String keyWord) {
        return null;
    }
}
