package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/8/21.
 */
public interface DeportInfoDAO<T> extends SuperDAO {

    List<T> findAll();

    T findAllById(String id);
}
