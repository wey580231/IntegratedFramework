package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/7/5.
 */
public interface ProductDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);
}
