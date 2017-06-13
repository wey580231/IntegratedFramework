package com.rengu.DAO.impl;

import com.rengu.DAO.ScheduleDAO;
import com.rengu.entity.RG_ScheduleEntity;

import java.util.List;

/**
 * Created by hanchangming on 2017/6/12.
 */
public class ScheduleDAOImpl extends SuperDAOImpl implements ScheduleDAO<RG_ScheduleEntity> {
    @Override
    public List<RG_ScheduleEntity> findAll() {
        return null;
    }

    @Override
    public List<RG_ScheduleEntity> findAllByUsername(String username) {
        return null;
    }

    @Override
    public RG_ScheduleEntity findAllById(String id) {
        return null;
    }

    @Override
    public List<RG_ScheduleEntity> search(String keyWord) {
        return null;
    }
}
