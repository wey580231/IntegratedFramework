package com.rengu.DAO.impl;

import com.rengu.DAO.ProductDAO;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.entity.RG_ProductEntity;
import com.rengu.entity.RG_SiteEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanchangming on 2017/7/5.
 */
public class ProductDAOImpl extends SuperDAOImpl implements ProductDAO<RG_ProductEntity> {

    @Override
    public List<RG_ProductEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ProductEntity rg_productEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_ProductEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ProductEntity rg_productEntity where rg_productEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProductEntity rg_productEntity = (RG_ProductEntity) query.list().get(0);
                return rg_productEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public boolean delete(Object object) {
        RG_ProductEntity rg_productEntity;

        if (object instanceof RG_ProductEntity) {
            rg_productEntity = (RG_ProductEntity) object;
            String productId = rg_productEntity.getId();
            rg_productEntity = findAllById(productId);

            //plan
            List<RG_PlanEntity> rg_PlanEntity = DAOFactory.getPlanDAOImplInstance().findAllByProductId(productId);

            //process
            List<RG_ProcessEntity> rg_processEntity = DAOFactory.getProcessDAOImplInstance().findAllByProductId(productId);

            if (rg_PlanEntity .size() > 0 || rg_processEntity .size() > 0) {
                if (((rg_PlanEntity .size() > 0 && DAOFactory.getPlanDAOImplInstance().deleteByProductId(productId)) ||
                        (rg_processEntity .size() > 0 && DAOFactory.getProcessDAOImplInstance().deleteByProductId(productId)))
                        && super.delete(rg_productEntity)) {
                    return true;
                }  else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_productEntity);
            }

        } else {
            //参数错误
            return false;
        }
    }
}
