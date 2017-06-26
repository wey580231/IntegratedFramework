package com.rengu.DAO.impl;

import com.rengu.DAO.ProcessDAO;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ProcessDAOImpl extends SuperDAOImpl implements ProcessDAO<RG_ProcessEntity> {
    @Override
    public List<RG_ProcessEntity> findAll() {
        return null;
    }

    @Override
    public List<RG_ProcessEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_ProcessEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            String hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProcessEntity rg_processEntity = (RG_ProcessEntity) query.list().get(0);
                return rg_processEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_ProcessEntity> search(String keyWord) {
        return null;
    }
}
