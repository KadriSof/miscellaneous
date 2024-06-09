package com.msk.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUtil {

    private static final SessionUtil instance = new SessionUtil();
    private static final String CONFIG_NAME = "/configuration.properties";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SessionFactory factory;

    private SessionUtil() {
        initialize();
    }

    private void initialize() {
        logger.info("reloading factory");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static Session getSession() {
        return getInstance().factory.openSession();
    }

    private static SessionUtil getInstance() {
        return instance;
    }

    public static void forceReload() {
        getInstance().initialize();
    }
}

