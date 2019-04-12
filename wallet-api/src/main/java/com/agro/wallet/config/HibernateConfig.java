package com.agro.wallet.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL5Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.agro.wallet","com.agro"})
@EnableJpaAuditing
public class HibernateConfig {

    private static final Logger logger = LoggerFactory
        .getLogger(HibernateConfig.class);

    @Autowired
    private HikariDataSource hikariDataSource;

    public String[] getPackagesToScan() {
        return new String[]{"com.agro.wallet"};
    }

    @Bean(destroyMethod = "")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new
            LocalContainerEntityManagerFactoryBean();
        em.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        em.setDataSource(hikariDataSource);
        em.setJpaDialect(new HibernateJpaDialect());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPackagesToScan(getPackagesToScan());
        em.setJpaProperties(hibernateProperties());
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new
            JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        jpaTransactionManager.setGlobalRollbackOnParticipationFailure(false);
        return jpaTransactionManager;
    }

    @Bean
    /*
      To catch Platform specific exceptions and throw them as Spring's unified
      unchecked exceptions. DAOs need to be marked as @Repository for this
      to take
      effect.
    */
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, MySQL5Dialect.class.getName());
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
            SpringSessionContext.class.getName());
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);
        properties.put(Environment.USE_QUERY_CACHE, false);
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, false);
        //properties.put(Environment.HBM2DDL_AUTO, "validate");
        //properties.put(Environment.HBM2DDL_AUTO, "create");
        properties.put(Environment.DIALECT,
            "org.hibernate.dialect.MySQL5InnoDBDialect");
        return properties;
    }
}
