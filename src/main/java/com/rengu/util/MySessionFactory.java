package com.rengu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class MySessionFactory {

    private static SessionFactory sessionFactory = null;

    //获取集成框架数据库访问会话
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        } else {
            return sessionFactory;
        }
    }
}
