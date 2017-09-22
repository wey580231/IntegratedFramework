package com.rengu.DAO;

import com.rengu.entity.RG_AdjustProcessEntity;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/16.
 */
public interface AdjustProcessDAO<T> {
    List<T> findAll();

    List<T> findAllByErrorState(Integer errorState);

    T findAllById(String id);

    void configState(RG_AdjustProcessEntity rg_adjustProcessEntity, Integer errorState);
}
