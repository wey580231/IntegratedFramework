package com.rengu.DAO;

import java.util.Date;
import java.util.List;

/**
 * Created by hanchangming on 2017/5/22.
 */
public interface OrdersDAO<T> extends SuperDAO {
    List<T> findAll();

    List<T> findAllByUsername(String username);

    T findAllById(String id);

    List<T> findAllByisFinishedAndDate(Date startDate, Date endDate, boolean isFinished);

    boolean deleteById(String id);
}
