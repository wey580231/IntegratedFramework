package com.rengu.DAO;

import java.util.List;

/**
 * Created by XY on 2017/9/7.
 */
public interface ResourceTyperesourceDAO<T> {
    List<T> findAllByResourceId(String id);

    List<T> findAllByTypeResourceId(String id);
}
