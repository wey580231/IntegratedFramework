package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanch on 2017/7/1.
 */
public interface PlanDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    List<T> findAllBySnapshotId(String id);

    T findAllByOrderId(String id);
}
