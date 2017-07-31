package com.rengu.DAO;

import java.util.Date;
import java.util.List;

/**
 * Created by hanchangming on 2017/5/22.
 */
public interface OrdersDAO<T> extends SuperDAO {
    List<T> findAll();

    List<T> findAllByUsername(String username);

    List<T> findByState(Byte state);

    T findAllById(String id);

    List<T> findAllByisFinishedAndDate(Date startDate, Date endDate, boolean isFinished);
}
