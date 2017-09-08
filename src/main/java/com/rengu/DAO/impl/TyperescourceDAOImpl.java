package com.rengu.DAO.impl;

import com.rengu.DAO.TyperescourceDAO;
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
public class TyperescourceDAOImpl extends SuperDAOImpl implements TyperescourceDAO<RG_TyperescourceEntity> {
    @Override
    public List<RG_TyperescourceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_TyperescourceEntity rg_typerescourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_TyperescourceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_TyperescourceEntity rg_typerescourceEntity where rg_typerescourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_TyperescourceEntity rg_typerescourceEntity = (RG_TyperescourceEntity) query.list().get(0);
                return rg_typerescourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean delete(Object object) {
        RG_TyperescourceEntity rg_typeResourceEntity;

        if (object instanceof RG_TyperescourceEntity) {
            rg_typeResourceEntity = (RG_TyperescourceEntity) object;
            String typeResourceId = rg_typeResourceEntity.getId();
            rg_typeResourceEntity = findAllById(typeResourceId);

            //plan
            List<RG_PlanEntity> rg_PlanEntity = DAOFactory.getPlanDAOImplInstance().findAllByTypeResourceId(typeResourceId);

            //resourceTypeResource
            List<RG_ResourceTyperesourceEntity> rg_resourceTypeResourceEntity = DAOFactory.getResourceTyperesourceInstance().findAllByTypeResourceId(typeResourceId);

            if (rg_PlanEntity .size() > 0 || rg_resourceTypeResourceEntity.size() > 0) {
                //从订单异常删除订单
                if (((rg_PlanEntity .size() > 0 && DAOFactory.getPlanDAOImplInstance().deleteByTypeResourceId(typeResourceId)) ||
                        (rg_resourceTypeResourceEntity.size() > 0 && DAOFactory.getResourceTyperesourceInstance().deleteByTypeResourceId(typeResourceId)))
                        && super.delete(rg_typeResourceEntity)) {
                    return true;
                }  else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_typeResourceEntity);
            }

        } else {
            //参数错误
            return false;
        }
    }
}
