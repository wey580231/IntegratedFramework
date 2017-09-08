package com.rengu.DAO;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/13.
 */
public interface ProcessDAO<T> extends SuperDAO {
    List<T> findAllByIsRootNode(boolean isRootNode);

    T findAllById(String id);

    List<T> findAllByProductId(String id);

    List<T> findAllByProcessId(String id);

    boolean deleteByProductId(String id);

    boolean deleteByProcessId(String id);
}