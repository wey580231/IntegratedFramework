package com.rengu.DAO.impl;

import com.rengu.DAO.SuperDAO;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class SuperDAOImpl implements SuperDAO {
    protected static Session session;
    protected static Transaction transaction;

    public static Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public boolean save(Object object) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            this.transaction = transaction;
            this.session = session;
            session.save(object);
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
            this.transaction = transaction;
            this.session = session;
            session.delete(object);
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
            this.transaction = transaction;
            this.session = session;
            session.update(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
