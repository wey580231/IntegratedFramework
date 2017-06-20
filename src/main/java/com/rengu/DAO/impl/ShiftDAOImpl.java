package com.rengu.DAO.impl;

import com.rengu.DAO.ShiftDAO;
import com.rengu.entity.RG_ShiftEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ShiftDAOImpl extends SuperDAOImpl implements ShiftDAO<RG_ShiftEntity> {
    @Override
    public List<RG_ShiftEntity> findAll() {
        Session session = SuperDAOImpl.getSession();
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
            Session session = SuperDAOImpl.getSession();
            String hql = "from RG_ShiftEntity rg_shiftEntity where rg_shiftEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                return (RG_ShiftEntity) query.list().get(0);
            } else {
                return null;
            }
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
