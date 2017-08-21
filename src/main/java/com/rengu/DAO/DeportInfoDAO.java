package com.rengu.DAO;

/**
 * Created by XY on 2017/8/21.
 */
public interface DeportInfoDAO<T> extends SuperDAO {

    T findAllById(String id);
}
