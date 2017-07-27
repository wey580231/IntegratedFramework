package com.rengu.DAO;

import java.util.List;

public interface AdjustLayoutDAO<T> {
    List<T> findAll();

    List<T> findAllByErrorState(Integer errorState);
}
