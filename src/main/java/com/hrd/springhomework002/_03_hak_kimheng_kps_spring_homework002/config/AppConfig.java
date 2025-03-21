package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:postgresql://localhost:5432/hrd_center");
        source.setUsername("postgres");
        source.setPassword("1234");
        return source;
    }
}
