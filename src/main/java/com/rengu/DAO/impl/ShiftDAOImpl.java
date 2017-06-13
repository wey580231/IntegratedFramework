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
public class ShiftDAOImpl extends SuperDAOImpl implements ShiftDAO<RG_ShiftEntity> {
    @Override
    public List<RG_ShiftEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        super.transaction = transaction;
        super.session = session;
        String hql = "from RG_ShiftEntity rg_shiftEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_ShiftEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_ShiftEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            super.transaction = transaction;
            super.session = session;
            String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return (RG_ShiftEntity) query.list().get(0);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_ShiftEntity> search(String keyWord) {
        return null;
    }
}
