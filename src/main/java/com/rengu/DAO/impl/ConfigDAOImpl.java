package com.rengu.DAO.impl;

import com.rengu.DAO.ConfigDAO;
import com.rengu.DAO.UserConfigDAO;
import com.rengu.entity.RG_ConfigEntity;
import com.rengu.entity.RG_UserConfigEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/9/6.
 */
public class ConfigDAOImpl extends SuperDAOImpl implements ConfigDAO<RG_ConfigEntity> {

    public List<RG_ConfigEntity> findAllByUserId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ConfigEntity rg_configEntity where rg_configEntity.userByIdUser.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public boolean delete(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ConfigEntity config where config.userByIdUser.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
