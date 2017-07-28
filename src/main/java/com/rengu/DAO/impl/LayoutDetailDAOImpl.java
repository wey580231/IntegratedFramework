package com.rengu.DAO.impl;

import com.rengu.DAO.LayoutDetailDAO;
import com.rengu.DAO.OrdersDAO;
import com.rengu.entity.RG_LayoutDetailEntity;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by XY on 2017/7/28.
 */
public class LayoutDetailDAOImpl  extends SuperDAOImpl implements LayoutDetailDAO<RG_LayoutDetailEntity> {

    @Override
    public RG_LayoutDetailEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_LayoutDetailEntity rg_layoutDetailEntity where rg_layoutDetailEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_LayoutDetailEntity rg_layoutDetailEntity = (RG_LayoutDetailEntity) query.list().get(0);
                return rg_layoutDetailEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
