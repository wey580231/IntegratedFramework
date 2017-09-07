package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustDeviceDAO;
import com.rengu.DAO.ResourceTyperesourceDAO;
import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ResourceTyperesourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/9/7.
 */
public class ResourceTyperesourceDAOImpl extends SuperDAOImpl implements ResourceTyperesourceDAO<RG_ResourceTyperesourceEntity> {

    @Override
    public List<RG_ResourceTyperesourceEntity> findAllByResourceId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceTyperesourceEntity rg_resourceTypeResourceEntity where rg_resourceTypeResourceEntity.resourceByResourceId.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_ResourceTyperesourceEntity> findAllByTypeResourceId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceTyperesourceEntity rg_resourceTypeResourceEntity where rg_resourceTypeResourceEntity.typeresourceById.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public boolean deleteByResourceId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ResourceTyperesourceEntity rg_resourceTypeResourceEntity where rg_resourceTypeResourceEntity.resourceByResourceId.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByTypeResourceId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ResourceTyperesourceEntity rg_resourceTypeResourceEntity where rg_resourceTypeResourceEntity.typeresourceById.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
