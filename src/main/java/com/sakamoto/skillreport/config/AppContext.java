package com.sakamoto.skillreport.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource.properties")
@EnableJpaRepositories(basePackages = "com.sakamoto.skillreport.repositories")
@EnableTransactionManagement
public class AppContext {

    @Bean(name = "dataSource", destroyMethod = "close")
    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig("/datasource.properties");
        return new HikariDataSource(config);
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.globally_quoted_identifiers", "true");
        properties.put("hibernate.connection.release_mode", "after_transaction");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("jakarta.persistence.sharedCache.mode", "ENABLE_SELECTIVE");

        return properties;
    }

    @Bean(name = "entityManagerFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.sakamoto.skillreport.model");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(getHibernateProperties());

        return factoryBean;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public PlatformTransactionManager getTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return txManager;
    }

    @Bean(name = "exceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean("modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}