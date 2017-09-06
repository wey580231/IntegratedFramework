package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/9/6.
 */
public interface ConfigDAO<T> extends SuperDAO {

    List<T> findAllByUserId(String id);

}
