package com.rengu.DAO.impl;

import com.rengu.DAO.SuperDAO;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class SuperDAOImpl implements SuperDAO {
    @Override
    public boolean save(Object object) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
