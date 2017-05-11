package com.rengu.actions;

import com.opensymphony.xwork2.ModelDriven;
import com.rengu.DAO.UsersDAO;
import com.rengu.DAO.impl.UsersDAOImpl;
import com.rengu.entity.UsersEntity;
import com.rengu.util.SuperAction;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class doLoginAction extends SuperAction implements ModelDriven<UsersEntity> {

    UsersEntity usersEntity = new UsersEntity();

    @Override
    public String execute() throws Exception {
//        return super.execute();
        UsersDAO usersDAO = new UsersDAOImpl();
        if (usersDAO.userLogin(usersEntity)) {
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public UsersEntity getModel() {
        return this.usersEntity;
    }
}