package com.rengu.DAO.impl;

import com.rengu.DAO.ProviderDAO;
import com.rengu.entity.RG_ProviderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ProviderDAOImpl extends SuperDAOImpl implements ProviderDAO<RG_ProviderEntity> {
    @Override
    public RG_ProviderEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ProviderEntity rg_providerEntity where rg_providerEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProviderEntity rg_providerEntity = (RG_ProviderEntity) query.list().get(0);
                return rg_providerEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
