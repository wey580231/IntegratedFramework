package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public interface AdjustOrderDAO<T> {
    List<T> findAll();

    List<T> findAllByErrorState(Integer errorState);

    T findAllById(String id);

    T findAllByOrderId(String id);

    List<T> findAllByAdjustOrderType(String adjustOrderType);
}
