package com.rengu.DAO;

import com.rengu.entity.UserEntity;

/**
 * Created by hanchangming on 2017/5/11.
 */
public interface UsersDAO {
    boolean userLogin(UserEntity usersEntity);

    boolean userSignin(UserEntity userEntity);
}
