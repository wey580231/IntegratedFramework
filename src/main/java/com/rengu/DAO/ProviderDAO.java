package com.rengu.DAO;

/**
 * Created by hanchangming on 2017/6/13.
 */
public interface ProviderDAO<T> extends SuperDAO {
    T findAllById(String id);
}
