package com.rengu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by hanchangming on 2017/5/11.
 */
public class MySessionFactory {

    private static SessionFactory sessionFactory = null;

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
