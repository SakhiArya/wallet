package com.agro.wallet.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {

    @Value("${database.driver:com.mysql.jdbc.Driver}")
    private String driverClassName;
    @Value("${database.maxPool:200}")
    private Integer maxPoolSize;
    @Value("${database.minPool:50}")
    private Integer minPoolSize;
    @Value("${database.url}")
    private String jdbcUrl;
    @Value("${database.user}")
    private String username;
    @Value("${database.password}")
    private String password;


    @Bean
    @Primary
    public HikariDataSource primaryDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMinimumIdle(minPoolSize);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        return new HikariDataSource(hikariConfig);
    }
}
