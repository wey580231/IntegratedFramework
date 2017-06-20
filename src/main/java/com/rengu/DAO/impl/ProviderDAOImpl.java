package com.rengu.DAO.impl;

import com.rengu.DAO.ProviderDAO;
import com.rengu.entity.RG_ProviderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ProviderDAOImpl extends SuperDAOImpl implements ProviderDAO<RG_ProviderEntity> {
    @Override
    public List<RG_ProviderEntity> findAll() {
        return null;
    }

    @Override
    public List<RG_ProviderEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_ProviderEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_ProviderEntity rg_providerEntity where rg_providerEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProviderEntity rg_providerEntity = (RG_ProviderEntity) query.list().get(0);
                transaction.commit();
                session.close();
                return rg_providerEntity;
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
    public List<RG_ProviderEntity> search(String keyWord) {
        return null;
    }
}
