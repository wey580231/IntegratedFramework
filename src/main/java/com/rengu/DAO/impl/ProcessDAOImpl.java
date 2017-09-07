package com.rengu.DAO.impl;

import com.rengu.DAO.ProcessDAO;
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
public class ProcessDAOImpl extends SuperDAOImpl implements ProcessDAO<RG_ProcessEntity> {
    @Override
    public List<RG_ProcessEntity> findAllByIsRootNode(boolean isRootNode) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.rootProcess =:isRootNode";
        Query query = session.createQuery(hql);
        query.setParameter("isRootNode", isRootNode);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_ProcessEntity> findAllByProductId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.productByIdProduct.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    @Override
    public RG_ProcessEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_ProcessEntity rg_processEntity = (RG_ProcessEntity) query.list().get(0);
                return rg_processEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_ProcessEntity> findAllByProcessId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_ProcessEntity rg_processEntity where rg_processEntity.processByIdProcess.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public boolean deleteByProductId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ProcessEntity rg_processEntity where rg_processEntity.productByIdProduct.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByProcessId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_ProcessEntity rg_processEntity where rg_processEntity.processByIdProcess.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object object) {
        RG_ProcessEntity rg_processEntity;

        if (object instanceof RG_ProcessEntity) {
            rg_processEntity = (RG_ProcessEntity) object;
            String processId = rg_processEntity.getId();
            rg_processEntity = findAllById(processId);

            //assisantprocess
            List<RG_AssisantprocessEntity> rg_assisantprocessEntity = DAOFactory.getAssisantprocessDAOInstance().findAllByProcessId(processId);

            //process
            List<RG_ProcessEntity> rg_processEntity2 = DAOFactory.getProcessDAOImplInstance().findAllByProcessId(processId);

            if(rg_assisantprocessEntity .size() > 0 || rg_processEntity2 .size() > 0){
                if (((rg_assisantprocessEntity .size() > 0 && DAOFactory.getAssisantprocessDAOInstance().deleteByProcessId(processId)) ||
                        (rg_processEntity2 .size() > 0 && DAOFactory.getProcessDAOImplInstance().deleteByProcessId(processId)))
                        && super.delete(rg_processEntity)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_processEntity);
            }


        } else {
            //参数错误
            return false;
        }
    }
}
