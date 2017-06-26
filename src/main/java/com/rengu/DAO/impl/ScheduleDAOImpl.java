package com.rengu.DAO.impl;

import com.rengu.DAO.ScheduleDAO;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/12.
 */
public class ScheduleDAOImpl extends SuperDAOImpl implements ScheduleDAO<RG_ScheduleEntity> {
    @Override
    public List<RG_ScheduleEntity> findAll() {
        return null;
    }

    @Override
    public List<RG_ScheduleEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_ScheduleEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ScheduleEntity rg_scheduleEntity where rg_scheduleEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ScheduleEntity rg_scheduleEntity = (RG_ScheduleEntity) query.list().get(0);
                return rg_scheduleEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_ScheduleEntity> search(String keyWord) {
        return null;
    }
}
