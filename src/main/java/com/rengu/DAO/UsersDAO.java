package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/11.
 */
public interface UsersDAO<T> {
    List<T> findAll();

    T findAllById(String id);
}
