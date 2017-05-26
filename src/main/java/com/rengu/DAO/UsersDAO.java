package com.rengu.DAO;

import com.rengu.entity.RG_UserEntity;

/**
 * Created by hanchangming on 2017/5/11.
 */
public interface UsersDAO {
    boolean userLogin(RG_UserEntity usersEntity);

    boolean userSignin(RG_UserEntity userEntity);
}
