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
public class TyperescourceDAOImpl extends SuperDAOImpl implements TyperescourceDAO<RG_TyperescourceEntity> {
    @Override
    public List<RG_TyperescourceEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        super.transaction = transaction;
        super.session = session;
        String hql = "from RG_TyperescourceEntity rg_typerescourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_TyperescourceEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_TyperescourceEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            super.transaction = transaction;
            super.session = session;
            String hql = "from RG_TyperescourceEntity rg_typerescourceEntity where rg_typerescourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return (RG_TyperescourceEntity) query.list().get(0);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_TyperescourceEntity> search(String keyWord) {
        return null;
    }
}
