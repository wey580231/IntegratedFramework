package com.rengu.DAO;

import com.rengu.entity.RG_OrderEntity;
import org.hibernate.Session;

import java.text.ParseException;
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

    List<T> findAllByStateAndIsFinished(Byte state, boolean isFinished) throws ParseException;

    void configOrderState(RG_OrderEntity rg_orderEntity, Session session, Byte orderState);

    void configOrderState(RG_OrderEntity rg_orderEntity, Byte orderState);
}
