package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/8/17.
 */
public interface CarryDAO<T> extends SuperDAO  {
    List<T> findAll();
}
