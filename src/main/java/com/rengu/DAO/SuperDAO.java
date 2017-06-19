package com.rengu.DAO;

/**
 * Created by hanchangming on 2017/5/22.
 */
public interface SuperDAO {

    boolean save(Object object);

    boolean delete(Object object);

    boolean update(Object object);
}
