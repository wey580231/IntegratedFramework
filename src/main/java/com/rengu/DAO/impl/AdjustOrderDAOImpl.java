package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustOrderDAO;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustOrderDAOImpl extends SuperDAOImpl implements AdjustOrderDAO<RG_AdjustOrderEntity> {
    @Override
    public List<RG_AdjustOrderEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        String hql = "from RG_AdjustOrderEntity rg_adjustOrderEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustOrderEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_AdjustOrderEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            String hql = "from RG_AdjustOrderEntity rg_adjustOrderEntity where rg_adjustOrderEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_AdjustOrderEntity rg_adjustOrderEntity = (RG_AdjustOrderEntity) query.list().get(0);
                return rg_adjustOrderEntity;
            } else {
                return null;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_AdjustOrderEntity> search(String keyWord) {
        return null;
    }
}
