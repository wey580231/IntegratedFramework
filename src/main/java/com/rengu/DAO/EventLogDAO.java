package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanch on 2017/7/20.
 */
public interface EventLogDAO<T> extends SuperDAO {
    List<T> findAll();
}
