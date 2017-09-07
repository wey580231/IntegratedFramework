package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public interface AssisantprocessDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    List<T> findAllByProcessId(String id);

    boolean deleteByProcessId(String id);
}
