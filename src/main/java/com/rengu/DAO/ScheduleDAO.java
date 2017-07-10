package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/12.
 */
public interface ScheduleDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);
}
