package com.rengu.DAO.impl;

import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.OrderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/22.
 */
public class OrdersDAOImpl implements OrdersDAO {

    private OrderEntity getOrderEntity(Object object) {
        OrderEntity orderEntity = null;
        if (object instanceof OrderEntity) {
            return (OrderEntity) object;
        } else {
            return orderEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
            OrderEntity orderEntity = getOrderEntity(object);
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(orderEntity);
            transaction.commit();
            session.close();
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
    public boolean findAll() {
        return false;
    }

    @Override
    public boolean findAllByUsername(Object object) {
        try {
            OrderEntity orderEntity = getOrderEntity(object);
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from OrderEntity orderEntity where orderEntity.idClub =:idClub";
            Query query = session.createQuery(hql);
//            query.setParameter("idClub", orderEntity.getIdClub());
            List list = query.list();
            transaction.commit();
            if (list.size() <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
