package com.rengu.DAO.impl;

import com.rengu.DAO.LayoutDAO;
import com.rengu.entity.RG_LayoutEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/5.
 */
public class LayoutDAOImpl implements LayoutDAO {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    private RG_LayoutEntity getEntityObject(Object object) {
        RG_LayoutEntity rg_layoutEntity = null;
        if (object instanceof RG_LayoutEntity) {
            return (RG_LayoutEntity) object;
        } else {
            return rg_layoutEntity;
        }
    }

    @Override
    public boolean save(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public List<?> findAll() {
        return null;
    }

    @Override
    public List<?> findAllByUsername(Object object) {
        return null;
    }

    @Override
    public List<?> findAllById(Object object) {
        try {
            RG_LayoutEntity rg_layoutEntity = getEntityObject(object);
            if (rg_layoutEntity == null) {
                return null;
            }
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            this.transaction = transaction;
            String hql = "from RG_LayoutEntity rg_layoutEntity where rg_layoutEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", rg_layoutEntity.getId());
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<?> search(String keyWord) {
        return null;
    }
}
