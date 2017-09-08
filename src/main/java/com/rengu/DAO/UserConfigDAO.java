package com.rengu.DAO;

import java.util.List;

public interface UserConfigDAO<T> extends SuperDAO {

    List<T> findAllByUserId(String id);

}