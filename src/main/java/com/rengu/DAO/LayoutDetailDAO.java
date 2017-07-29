package com.rengu.DAO;

/**
 * Created by XY on 2017/7/28.
 */
public interface LayoutDetailDAO<T> extends SuperDAO {
    T findAllById(String id);

}
