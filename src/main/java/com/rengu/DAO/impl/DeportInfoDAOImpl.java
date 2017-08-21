package com.rengu.DAO.impl;

import com.rengu.DAO.DeportInfoDAO;
import com.rengu.DAO.ProcessDAO;
import com.rengu.entity.RG_Mes_DeportInfo;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Created by XY on 2017/8/21.
 */
public class DeportInfoDAOImpl extends SuperDAOImpl implements DeportInfoDAO<RG_Mes_DeportInfo> {

    @Override
    public RG_Mes_DeportInfo findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_Mes_DeportInfo rg_deportInfoEntity where rg_deportInfoEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_Mes_DeportInfo rg_deportInfoEntity = (RG_Mes_DeportInfo) query.list().get(0);
                return rg_deportInfoEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
