package com.rengu.util;

import com.rengu.entity.RG_State3DEntity;
import org.hibernate.Session;


public class D3Tools {

    //获取下发给3d车间的快照节点
    public static String getD3SnapshotId(int id) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        RG_State3DEntity state3DEntity = session.get(RG_State3DEntity.class, id);
        if (state3DEntity != null) {
            return state3DEntity.getSnapshotId();
        }
        return null;
    }
}
