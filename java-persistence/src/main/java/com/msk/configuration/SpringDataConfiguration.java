package com.msk.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories("com.msk.repositories")

public class SpringDataConfiguration {

//    @Value(value = "${DATABASE_USERNAME}")
//    private String username;

//    @Value(value = "${DATABASE_PASSWORD}")
//    private String password;

    @Bean
    public DataSource dataSource() {

        //String username = System.getenv("DATABASE_USERNAME");
        //String password = System.getenv("DATABASE_PASSWORD");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/CH02?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("!optodev.2023");

        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setShowSql(true);

        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource());

        // Configure JPA properties
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");

        emf.setJpaProperties(properties);
        emf.setJpaVendorAdapter(vendorAdapter());
        emf.setPackagesToScan("com.msk");

        return emf;
    }
}
