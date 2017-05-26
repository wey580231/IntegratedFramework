package com.rengu.DAO.impl;

import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/22.
 */
public class OrdersDAOImpl implements OrdersDAO {

    private RG_OrderEntity getOrderEntity(Object object) {
        RG_OrderEntity rg_orderEntity = null;
        if (object instanceof RG_OrderEntity) {
            return (RG_OrderEntity) object;
        } else {
            return rg_orderEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            RG_OrderEntity rg_orderEntity = getOrderEntity(object);
            if (rg_orderEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            session.save(rg_orderEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        try {
            RG_OrderEntity rg_orderEntity = getOrderEntity(object);
            if (rg_orderEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            session.delete(rg_orderEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        try {
            RG_OrderEntity rg_orderEntity = getOrderEntity(object);
            if (rg_orderEntity == null) {
                return false;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            session.update(rg_orderEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<?> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from RG_OrderEntity rg_orderEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<?> findAllByUsername(Object object) {
        try {
            RG_OrderEntity rg_orderEntity = getOrderEntity(object);
            if (rg_orderEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.clubByIdClub.name =:nameClub";
            Query query = session.createQuery(hql);
            query.setParameter("nameClub", rg_orderEntity.getClubByIdClub().getName());
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<?> search(String keyWord) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.name = 'han'";
        Query query = session.createQuery(hql);
        List list = query.list();
        transaction.commit();
        return list;
    }
}
