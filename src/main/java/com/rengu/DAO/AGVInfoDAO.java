package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/8/23.
 */
public interface AGVInfoDAO<T> extends SuperDAO  {
    List<T> findAll();
}
