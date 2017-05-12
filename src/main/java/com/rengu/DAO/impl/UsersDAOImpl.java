package com.rengu.DAO.impl;

import com.rengu.DAO.UsersDAO;
import com.rengu.entity.UsersEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class UsersDAOImpl implements UsersDAO {

    public boolean userLogin(UsersEntity usersEntity) {
        Transaction transaction = null;
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "from UsersEntity";
            Query query = session.createQuery(hql);
//            query.setParameter(0, usersEntity.getUsername());
//            query.setParameter(1, usersEntity.getPassword());
//            query.setParameter(2, usersEntity.getId());
            List list = query.list();
            transaction.commit();
            if (list.size() <= 0) {
                return false;
            }
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            if (transaction != null) {
                transaction = null;
            }
        }
    }
}
