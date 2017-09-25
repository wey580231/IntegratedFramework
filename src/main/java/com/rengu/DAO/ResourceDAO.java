package com.rengu.DAO;

import com.rengu.entity.RG_ResourceEntity;
import com.rengu.entity.RG_ShiftEntity;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public interface ResourceDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    RG_ResourceEntity findAllById(Session session, String id);
}
