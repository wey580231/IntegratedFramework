package com.rengu.DAO.impl;

import com.rengu.DAO.ClubDAO;
import com.rengu.entity.RG_ClubEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ClubDAOImpl extends SuperDAOImpl implements ClubDAO<RG_ClubEntity> {
    @Override
    public RG_ClubEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ClubEntity rg_clubEntity where rg_clubEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ClubEntity rg_clubEntity = (RG_ClubEntity) query.list().get(0);
                return rg_clubEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
