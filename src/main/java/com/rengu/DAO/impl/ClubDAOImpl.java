package com.rengu.DAO.impl;

import com.rengu.DAO.ClubDAO;
import com.rengu.entity.*;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public class ClubDAOImpl extends SuperDAOImpl implements ClubDAO<RG_ClubEntity> {
    @Override
    public RG_ClubEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ClubEntity rg_clubEntity where rg_clubEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ClubEntity rg_clubEntity = (RG_ClubEntity) query.list().get(0);
                return rg_clubEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean delete(Object object) {
        RG_ClubEntity rg_clubEntity;

        if (object instanceof RG_ClubEntity) {
            rg_clubEntity = (RG_ClubEntity) object;
            String clubId = rg_clubEntity.getId();
            rg_clubEntity = findAllById(clubId);


            //config
            List<RG_ConfigEntity> rg_configEntity = DAOFactory.getConfigDAOImplInstance().findAllByClubId(clubId);

            //provider
            List<RG_ProviderEntity> rg_providerEntity = DAOFactory.getProviderDAOImplInstance().findAllByClubId(clubId);

            //plan
            List<RG_PlanEntity> rg_planEntity = DAOFactory.getPlanDAOImplInstance().findAllByClubId(clubId);

            //resource
            List<RG_ResourceEntity> rg_resourceEntity = DAOFactory.getResourceInstance().findAllByClubId(clubId);

            if(rg_providerEntity.size() > 0 || rg_configEntity .size() > 0 || rg_planEntity .size() > 0 || rg_resourceEntity .size() > 0 ){
                if (((rg_providerEntity .size() > 0 && DAOFactory.getProviderDAOImplInstance().deleteByClubId(clubId)) ||
                        (rg_configEntity .size() > 0 && DAOFactory.getConfigDAOImplInstance().deleteByClubId(clubId)) ||
                        (rg_planEntity .size() > 0 && DAOFactory.getPlanDAOImplInstance().deleteByClubId(clubId)) ||
                        (rg_resourceEntity .size() > 0 && DAOFactory.getResourceInstance().deleteByClubId(clubId)))
                        && super.delete(rg_clubEntity)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_clubEntity);
            }


        } else {
            //参数错误
            return false;
        }
    }

}
