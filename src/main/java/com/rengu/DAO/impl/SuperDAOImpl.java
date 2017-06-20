package com.rengu.DAO.impl;

import com.rengu.DAO.SuperDAO;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class SuperDAOImpl implements SuperDAO {
    protected static Session session = MySessionFactory.getSessionFactory().openSession();
    protected static Transaction transaction = getSession().beginTransaction();

    public static Session getSession() {
        //初始化Session
        if (!session.isOpen()) {
            session.close();
            session = MySessionFactory.getSessionFactory().openSession();
            System.out.println("以初始化session");
            return session;
        } else {
            return session;
        }
    }

    public static Transaction getTransaction() {
        //初始化Transaction
        if (!transaction.isActive()) {
            transaction = getSession().beginTransaction();
            System.out.println("以初始化transaction");
            return transaction;
        } else {
            return transaction;
        }
    }

    @Override
    public boolean save(Object object) {
        try {
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
            session.update(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
