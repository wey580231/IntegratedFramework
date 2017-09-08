package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/5/31.
 */
public interface ResourceDAO<T> extends SuperDAO {
    List<T> findAll();

    T findAllById(String id);

    List<T> findAllByClubId(String id);

    List<T> findAllByUserId(String id);

    boolean deleteByClubId(String id);

    boolean deleteByUserId(String id);
}
