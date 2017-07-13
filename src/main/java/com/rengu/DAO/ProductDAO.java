package com.rengu.DAO;

/**
 * Created by hanchangming on 2017/7/5.
 */
public interface ProductDAO<T> extends SuperDAO {
    T findAllById(String id);
}
