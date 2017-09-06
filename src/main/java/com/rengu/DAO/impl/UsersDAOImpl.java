package com.rengu.DAO.impl;

import com.rengu.DAO.UsersDAO;
import com.rengu.entity.*;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class UsersDAOImpl extends SuperDAOImpl implements UsersDAO<RG_UserEntity> {
    @Override
    public List<RG_UserEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_UserEntity rg_userEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_UserEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_UserEntity rg_userEntity where rg_userEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_UserEntity rg_userEntity = (RG_UserEntity) query.list().get(0);
                return rg_userEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean delete(Object object) {
        RG_UserEntity rg_userEntity;

        if (object instanceof RG_UserEntity) {
            rg_userEntity = (RG_UserEntity) object;
            String userId = rg_userEntity.getId();
            rg_userEntity = findAllById(userId);


            //userConfig
            List<RG_UserConfigEntity> rg_userConfigEntity = DAOFactory.getUserConfigDAOImplInstance().findAllByUserId(userId);

            //config
            List<RG_ConfigEntity> rg_configEntity = DAOFactory.getConfigDAOImplInstance().findAllByUserId(userId);

            if((rg_userConfigEntity .size() > 0 || rg_configEntity .size() > 0) ){
                //从user删除userConfig和config
                if (((rg_userConfigEntity .size() > 0 && DAOFactory.getUserConfigDAOImplInstance().delete(userId)) ||
                        (rg_configEntity .size() > 0 && DAOFactory.getConfigDAOImplInstance().delete(userId)))
                        && super.delete(rg_userEntity)) {
                        return true;
                } else {
                        return false;
                }

            } else {
                //直接删除
                return super.delete(rg_userEntity);
            }


        } else {
            //参数错误
            return false;
        }
    }

}
