package com.rengu.DAO.impl;

import com.rengu.DAO.DeportInfoDAO;
import com.rengu.DAO.ProcessDAO;
import com.rengu.entity.RG_Mes_AssemblyCarryInfo;
import com.rengu.entity.RG_Mes_DeportInfo;
import com.rengu.entity.RG_ProcessEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/8/21.
 */
public class DeportInfoDAOImpl extends SuperDAOImpl implements DeportInfoDAO<RG_Mes_DeportInfo> {

    @Override
    public List<RG_Mes_DeportInfo> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_DeportInfo rg_deportInfo";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_Mes_DeportInfo findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_Mes_DeportInfo rg_deportInfo where rg_deportInfo.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            System.out.println(query);
            if (!query.list().isEmpty()) {
                RG_Mes_DeportInfo rg_deportInfo = (RG_Mes_DeportInfo) query.list().get(0);
                return rg_deportInfo;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
