package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanch on 2017/7/1.
 */
public interface PlanDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    List<T> findAllBySnapshotId(String id);

    List<T> findAllByOrderId(String id);

    List<T> findAllByProviderId(String id);

    List<T> findAllByClubId(String id);

    List<T> findAllByResourceId(String id);

    boolean deleteByClubId(String id);

    boolean deleteByProviderId(String id);

    boolean deleteByResourceId(String id);

}
