package com.rengu.DAO.impl;

import com.rengu.DAO.ResourceDAO;
import com.rengu.entity.*;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ResourceDAOImpl extends SuperDAOImpl implements ResourceDAO<RG_ResourceEntity> {
    @Override
    public List<RG_ResourceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceEntity rg_resourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_ResourceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ResourceEntity rg_resourceEntity = (RG_ResourceEntity) query.list().get(0);
                return rg_resourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<RG_ResourceEntity> findAllByClubId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.clubByIdClub.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public List<RG_ResourceEntity> findAllByUserId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.userByIdUser.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public boolean deleteByClubId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            //String hql = "delete ";
            session.createQuery("delete from RG_ResourceEntity resource where resource.clubByIdClub.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByUserId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ResourceEntity resource where resource.userByIdUser.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object object) {
        RG_ResourceEntity rg_resourceEntity;

        if (object instanceof RG_ResourceEntity) {
            rg_resourceEntity = (RG_ResourceEntity) object;
            String resourceId = rg_resourceEntity.getIdR();
            rg_resourceEntity = findAllById(resourceId);

            //订单设备
            RG_AdjustDeviceEntity rg_adjustDeviceEntity = DAOFactory.getAdjustDeviceDAOImplInstance().findAllByResourceId(resourceId);

            //plan
            List<RG_PlanEntity> rg_PlanEntity = DAOFactory.getPlanDAOImplInstance().findAllByResourceId(resourceId);

            //resourceTypeResource
            List<RG_ResourceTyperesourceEntity> rg_resourceTypeResourceEntity = DAOFactory.getResourceTyperesourceInstance().findAllByResourceId(resourceId);

            if (rg_PlanEntity .size() > 0 || rg_adjustDeviceEntity != null || rg_resourceTypeResourceEntity.size() > 0) {
                //从订单异常删除订单
                if (((rg_PlanEntity .size() > 0 && DAOFactory.getPlanDAOImplInstance().deleteByResourceId(resourceId)) ||
                        (rg_adjustDeviceEntity != null && DAOFactory.getAdjustDeviceDAOImplInstance().delete(rg_adjustDeviceEntity))||
                        (rg_resourceTypeResourceEntity.size() > 0 && DAOFactory.getResourceTyperesourceInstance().deleteByResourceId(resourceId)))
                                && super.delete(rg_resourceEntity)) {
                    return true;
                }  else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_resourceEntity);
            }

        } else {
            //参数错误
            return false;
        }
    }
}
