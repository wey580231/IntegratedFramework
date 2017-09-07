package com.rengu.DAO.impl;

import com.rengu.DAO.GroupResourceDAO;
import com.rengu.entity.RG_GroupresourceEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ResourceTyperesourceEntity;
import com.rengu.entity.RG_TyperescourceEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class GroupResourceDAOImpl extends SuperDAOImpl implements GroupResourceDAO<RG_GroupresourceEntity> {
    @Override
    public List<RG_GroupresourceEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_GroupresourceEntity rg_groupresourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_GroupresourceEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_GroupresourceEntity rg_groupresourceEntity where rg_groupresourceEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_GroupresourceEntity rg_groupresourceEntity = (RG_GroupresourceEntity) query.list().get(0);
                return rg_groupresourceEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Object object) {
        RG_GroupresourceEntity rg_groupResourceEntity;

        if (object instanceof RG_GroupresourceEntity) {
            rg_groupResourceEntity = (RG_GroupresourceEntity) object;
            String groupResourceId = rg_groupResourceEntity.getId();
            rg_groupResourceEntity = findAllById(groupResourceId);

            //plan
            List<RG_PlanEntity> rg_PlanEntity = DAOFactory.getPlanDAOImplInstance().findAllByGroupResourceId(groupResourceId);

            if (rg_PlanEntity .size() > 0) {
                if (DAOFactory.getPlanDAOImplInstance().deleteByGroupResourceId(groupResourceId) && super.delete(rg_groupResourceEntity)) {
                    return true;
                }  else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_groupResourceEntity);
            }

        } else {
            //参数错误
            return false;
        }
    }
}
