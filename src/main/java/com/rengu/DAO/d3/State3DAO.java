package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rengu.entity.RG_LayoutDetailEntity;
import com.rengu.entity.RG_LayoutEntity;
import com.rengu.entity.RG_State3DEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.tools.Tool;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 3D车间状态设置操作
 * Created by wey580231 on 2017/6/5.
 */
public class State3DAO {

    RG_State3DEntity state3DEntity;

    //由3D车间定时查询当前的设置状态
    public String getCurrentState() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from RG_State3DEntity entity where entity.id =:id");
        query.setParameter("id", 1);
        List<RG_State3DEntity> list = query.list();

        String jsonString = "";
        if (list.size() == 1 && list.get(0) instanceof RG_State3DEntity) {
            RG_State3DEntity entity = (RG_State3DEntity) list.get(0);
            jsonString = entity.toJson();

            session.getTransaction().commit();
            session.close();
        } else {
            jsonString = Tools.resultCode("1", "Can't execute operation");
        }
        return jsonString;
    }

    //根据ID名获取对应ID的所有信息
    public String getLayoutById(String s) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String jsonString = "";

        Query query = session.createQuery("from RG_LayoutEntity layout where layout.name = :name");
        query.setParameter("name", s);
        List list = query.list();
        if (list.size() == 1) {
            if (list.get(0) instanceof RG_LayoutEntity) {
                RG_LayoutEntity layout = (RG_LayoutEntity) list.get(0);
                Set<RG_LayoutDetailEntity> details = layout.getDetails();
                Iterator<RG_LayoutDetailEntity> iter = details.iterator();
                while (iter.hasNext()) {
                    RG_LayoutDetailEntity entity = iter.next();
                }
                jsonString = layout.toJson();
            }
        }

        session.getTransaction().commit();
        session.close();

        return jsonString;
    }

    //更新布局信息
    public boolean updateLayout(String layoutName, String data) {
        ObjectMapper objectMapper = new ObjectMapper();

        RG_LayoutDetailEntity[] arr = new RG_LayoutDetailEntity[0];
        try {
            arr = objectMapper.readValue(data, RG_LayoutDetailEntity[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from RG_LayoutEntity entity where entity.name=:name");
        query.setParameter("name", layoutName);
        List<RG_LayoutEntity> layout = query.list();
        if (layout.size() == 1 && layout.get(0) instanceof RG_LayoutEntity) {
            RG_LayoutEntity entity = (RG_LayoutEntity) layout.get(0);
            Set<RG_LayoutDetailEntity> details = entity.getDetails();
            Iterator<RG_LayoutDetailEntity> iter = details.iterator();
            while (iter.hasNext()) {
                RG_LayoutDetailEntity detail = iter.next();
                for (int i = 0; i < arr.length; i++) {
                    if (detail.getId() == arr[i].getId()) {
                        detail.setPos(arr[i].getPos());
                        detail.setState(arr[i].getState());
                        detail.setItem(arr[i].getItem());
                        break;
                    }
                }
            }
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }

    //插入新布局
    public boolean insertLayout(String layoutName, String data) {
        ObjectMapper objectMapper = new ObjectMapper();

        RG_LayoutDetailEntity[] arr = new RG_LayoutDetailEntity[0];
        try {
            arr = objectMapper.readValue(data, RG_LayoutDetailEntity[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        RG_LayoutEntity layout = new RG_LayoutEntity();
        layout.setName(layoutName);

        session.save(layout);

        for (int i = 0; i < arr.length; i++) {
            arr[i].setLayout(layout);
            session.save(arr[i]);
        }

        session.getTransaction().commit();
        session.close();
        return true;
    }
}
