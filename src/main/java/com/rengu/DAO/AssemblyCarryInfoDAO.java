package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/8/18.
 */
public interface AssemblyCarryInfoDAO<T> extends SuperDAO  {
    List<T> findAll();
}
