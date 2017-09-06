package com.rengu.DAO.impl;

import com.rengu.DAO.ProviderDAO;
import com.rengu.entity.RG_ClubEntity;
import com.rengu.entity.RG_ConfigEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ProviderEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ProviderDAOImpl extends SuperDAOImpl implements ProviderDAO<RG_ProviderEntity> {
    @Override
    public RG_ProviderEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ProviderEntity rg_providerEntity where rg_providerEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProviderEntity rg_providerEntity = (RG_ProviderEntity) query.list().get(0);
                return rg_providerEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<RG_ProviderEntity> findAllByClubId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ProviderEntity rg_providerEntity where rg_providerEntity.clubByIdClub.id =:id";
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
            session.createQuery("delete from RG_ProviderEntity provider where provider.clubByIdClub.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object object) {
        RG_ProviderEntity rg_providerEntity;

        if (object instanceof RG_ProviderEntity) {
            rg_providerEntity = (RG_ProviderEntity) object;
            String providerId = rg_providerEntity.getId();
            rg_providerEntity = findAllById(providerId);

            //plan
            List<RG_PlanEntity> rg_planEntity = DAOFactory.getPlanDAOImplInstance().findAllByProviderId(providerId);

            if(rg_planEntity .size() > 0 ){
                //从user删除userConfig和config
                if (DAOFactory.getPlanDAOImplInstance().deleteByProviderId(providerId) && super.delete(rg_providerEntity)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_providerEntity);
            }


        } else {
            //参数错误
            return false;
        }
    }
}
