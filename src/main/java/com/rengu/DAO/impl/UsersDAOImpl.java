package com.rengu.DAO.impl;

import com.rengu.DAO.UsersDAO;
import com.rengu.entity.RG_UserEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class UsersDAOImpl extends HibernateDaoSupport implements UsersDAO {

    @Override
    public boolean userLogin(RG_UserEntity usersEntity) {
        Transaction transaction = null;
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            String hql = "from RG_UserEntity userEntity where userEntity.name=:username and userEntity.password=:password";
            Query query = session.createQuery(hql);
            query.setParameter("username", usersEntity.getName());
            query.setParameter("password", usersEntity.getPassword());
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

    @Override
    public boolean userSignin(RG_UserEntity userEntity) {
        Transaction transaction = null;
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (transaction != null) {
                transaction = null;
            }
        }
    }
}
