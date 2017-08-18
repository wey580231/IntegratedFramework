package com.rengu.DAO.impl;

import com.rengu.DAO.AssemblyCenterInfoDAO;
import com.rengu.entity.RG_Mes_AssemblyCenterInfo;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public class AssemblyCenterInfoDAOImpl extends SuperDAOImpl implements AssemblyCenterInfoDAO<RG_Mes_AssemblyCenterInfo> {
    @Override
    public List<RG_Mes_AssemblyCenterInfo> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_AssemblyCenterInfo rg_assemblyCenterInfo";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
