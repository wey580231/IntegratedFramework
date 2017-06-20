package com.rengu.DAO.impl;

import com.rengu.DAO.UsersDAO;
import com.rengu.entity.RG_UserEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class UsersDAOImpl extends HibernateDaoSupport implements UsersDAO {

    @Override
    public boolean userLogin(RG_UserEntity usersEntity) {
        return false;
    }

    @Override
    public boolean userSignin(RG_UserEntity userEntity) {
        return false;
    }
}
