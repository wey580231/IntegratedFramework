package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustDeviceDAO;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustDeviceDAOImpl extends SuperDAOImpl implements AdjustDeviceDAO<RG_AdjustDeviceEntity> {
    @Override
    public List<RG_AdjustDeviceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_AdjustDeviceEntity rg_adjustDeviceEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustDeviceEntity> findAllByErrorState(Integer errorState) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustDeviceEntity rg_adjustDeviceEntity where rg_adjustDeviceEntity.state =:errorState";
            Query query = session.createQuery(hql);
            query.setParameter("errorState", errorState);
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_AdjustDeviceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustDeviceEntity entity where entity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_AdjustDeviceEntity rg_adjustDeviceEntity = (RG_AdjustDeviceEntity) query.list().get(0);
                return rg_adjustDeviceEntity;
            } else {
                return null;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_AdjustDeviceEntity findAllByOrderId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }

            //SQL删除
            NativeQuery query = session.createNativeQuery("select * from rg_adjustdevice where order_id = ? ", RG_AdjustDeviceEntity.class);
            query.setParameter(1, id);
            List<RG_AdjustDeviceEntity> list = query.list();
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
