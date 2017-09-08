package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public interface ProviderDAO<T> extends SuperDAO {
    T findAllById(String id);

    List<T> findAllByClubId(String id);

    boolean deleteByClubId(String id);
}
