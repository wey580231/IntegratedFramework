package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustOrderDAO;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public class AdjustOrderDAOImpl extends SuperDAOImpl implements AdjustOrderDAO<RG_AdjustOrderEntity> {
    @Override
    public List<RG_AdjustOrderEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_AdjustOrderEntity rg_adjustOrderEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustOrderEntity> findAllByErrorState(Integer errorState) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustOrderEntity rg_adjustOrderEntity where rg_adjustOrderEntity.state =:errorState";
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
    public RG_AdjustOrderEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
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
    public RG_AdjustOrderEntity findAllByOrderId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }

            //SQL删除
            NativeQuery query = session.createNativeQuery("select * from rg_adjustorder where idOrder = ? ", RG_AdjustOrderEntity.class);
            query.setParameter(1, id);
            List<RG_AdjustOrderEntity> list = query.list();
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;

            //HQL删除
//            String hql = "from RG_AdjustOrderEntity rg_adjustOrderEntity where rg_adjustOrderEntity.ord =:id";
//            Query query = session.createQuery(hql);
//            query.setParameter("id", id);
//            if (!query.list().isEmpty()) {
//                RG_AdjustOrderEntity rg_adjustOrderEntity = (RG_AdjustOrderEntity) query.list().get(0);
//                return rg_adjustOrderEntity;
//            } else {
//                return null;
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
