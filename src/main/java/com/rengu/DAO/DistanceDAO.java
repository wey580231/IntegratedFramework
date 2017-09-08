package com.rengu.DAO;

import java.util.List;

public interface DistanceDAO<T> extends SuperDAO {

    List<T> findAllBySiteId(String id);

    boolean deleteBySiteId(String id);

}
