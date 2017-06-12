package com.rengu.DAO.impl;

import com.rengu.DAO.ScheduleDAO;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/12.
 */
public class ScheduleDAOImpl implements ScheduleDAO {
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_ScheduleEntity getEntityObject(Object object) {
        RG_ScheduleEntity rg_scheduleEntity = null;
        if (object instanceof RG_ScheduleEntity) {
            return (RG_ScheduleEntity) object;
        } else {
            return rg_scheduleEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_ScheduleEntity rg_scheduleEntity = getEntityObject(object);
            if (rg_scheduleEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(rg_scheduleEntity);
            this.transaction = transaction;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public List<?> findAll() {
        return null;
    }

    @Override
    public List<?> findAllByUsername(Object object) {
        return null;
    }

    @Override
    public List<?> findAllById(Object object) {
        return null;
    }

    @Override
    public List<?> search(String keyWord) {
        return null;
    }
}
