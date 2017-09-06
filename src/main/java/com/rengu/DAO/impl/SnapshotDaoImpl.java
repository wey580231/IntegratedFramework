package com.rengu.DAO.impl;

import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_ProviderEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.DAOFactory;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanch on 2017/6/29.
 */
public class SnapshotDaoImpl extends SuperDAOImpl {
    public List<RG_SnapshotNodeEntity> findAllByLevel(String level) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_SnapshotNodeEntity entity where entity.level =:level order by entity.nodeCreateTime desc";
        Query query = session.createQuery(hql);
        query.setParameter("level", level);
        List list = query.list();
        transaction.commit();
        return list;
    }

    public RG_SnapshotNodeEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_SnapshotNodeEntity rg_snapshotNodeEntity where rg_snapshotNodeEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_SnapshotNodeEntity rg_snapshotNodeEntity = (RG_SnapshotNodeEntity) query.list().get(0);
                return rg_snapshotNodeEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<RG_SnapshotNodeEntity> findAllBySnapshotId(String rootId,String parentId) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_SnapshotNodeEntity rg_snapshotEntity where rg_snapshotEntity.rootParent.id =:rootId and rg_snapshotEntity.parent.id =:parentId";
        Query query = session.createQuery(hql);
        query.setParameter("rootId", rootId);
        query.setParameter("parentId", parentId);
        List list = query.list();
        return list;
    }

    public boolean deleteBySnapshotId(String rootId,String parentId) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_SnapshotNodeEntity snapshot where snapshot.rootParent.id =:rootId and snapshot.parent.id =:parentId").setParameter("rootId",rootId).setParameter("parentId",parentId).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Object object) {
        RG_SnapshotNodeEntity rg_snapshotEntity;

        if (object instanceof RG_SnapshotNodeEntity) {
            rg_snapshotEntity = (RG_SnapshotNodeEntity) object;
            String snapshotId = rg_snapshotEntity.getId();
            String snapshotRootId = rg_snapshotEntity.getRootParent().getId();
            String snapshotParentId = rg_snapshotEntity.getParent().getId();
            rg_snapshotEntity = findAllById(snapshotId);

            //plan
            List<RG_SnapshotNodeEntity> rg_snapshotEntity2 = DAOFactory.getSnapshotDaoImplInstance().findAllBySnapshotId(snapshotRootId,snapshotParentId);

            if(rg_snapshotEntity2 .size() > 0 ){
                //从user删除userConfig和config
                if (DAOFactory.getSnapshotDaoImplInstance().deleteBySnapshotId(snapshotRootId,snapshotParentId) && super.delete(rg_snapshotEntity)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                //直接删除
                return super.delete(rg_snapshotEntity);
            }


        } else {
            //参数错误
            return false;
        }
    }


}
