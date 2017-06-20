package com.rengu.DAO.impl;

import com.rengu.DAO.GroupResourceDAO;
import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class GroupResourceDAOImpl extends SuperDAOImpl implements GroupResourceDAO<RG_GroupresourceEntity> {
    @Override
    public List<RG_GroupresourceEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from RG_GroupresourceEntity rg_groupresourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<RG_GroupresourceEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_GroupresourceEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_GroupresourceEntity rg_groupresourceEntity = (RG_GroupresourceEntity) query.list().get(0);
                transaction.commit();
                session.close();
                return rg_groupresourceEntity;
            } else {
                transaction.commit();
                session.close();
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_GroupresourceEntity> search(String keyWord) {
        return null;
    }
}
