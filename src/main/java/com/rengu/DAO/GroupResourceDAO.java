package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public interface GroupResourceDAO<T> extends SuperDAO {
    List<T> findAll();

    List<T> findAllByUsername(T t);

    T findAllById(T t);

    List<T> search(String keyWord);
}
