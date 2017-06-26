package com.rengu.DAO.impl;

import com.rengu.DAO.ResourceDAO;
import com.rengu.entity.RG_ResourceEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public class ResourceDAOImpl extends SuperDAOImpl implements ResourceDAO<RG_ResourceEntity> {
    @Override
    public List<RG_ResourceEntity> findAll() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        String hql = "from RG_ResourceEntity rg_resourceEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_ResourceEntity> findAllByUsername(String username) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            String hql = "from RG_ResourceEntity rg_resourceEntity where rg_resourceEntity.clubByIdClub.name =:nameClub";
            Query query = session.createQuery(hql);
            query.setParameter("nameClub", username);
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public RG_ResourceEntity findAllById(String id) {
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
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

    @Override
    public List<RG_ResourceEntity> search(String keyWord) {
        return null;
    }
}
