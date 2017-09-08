package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public interface GroupResourceDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    List<T> findAllByProviderId(String id);

    boolean deleteByProviderId(String id);
}
