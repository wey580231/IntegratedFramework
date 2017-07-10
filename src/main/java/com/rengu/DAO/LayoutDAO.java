package com.rengu.DAO;

/**
 * Created by hanchangming on 2017/6/5.
 */
public interface LayoutDAO<T> extends SuperDAO {
    T findAllById(String id);
}
