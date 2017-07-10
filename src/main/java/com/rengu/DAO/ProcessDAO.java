package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public interface ProcessDAO<T> extends SuperDAO {
    List<T> findAllByIsRootNode(boolean isRootNode);

    T findAllById(String id);
}